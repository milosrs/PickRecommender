package com.lol.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class AbstractDao<Entity, Key> implements IAbstractDao<Entity, Key> {

	protected SessionFactory sf;
	protected Class<Entity> entityClass;
	protected Class<? extends Serializable> keyClass;
	protected CriteriaBuilder builder;
	protected CriteriaQuery<Entity> criteria;
	protected Root<Entity> rootObject;
	protected final Log logger = LogFactory.getLog(getClass());
	protected String rootObjID;
	
	public AbstractDao(SessionFactory sf) {
		super();
		this.sf = sf;
		resolveGenericType();
	}

	private void resolveGenericType() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		entityClass = (Class<Entity>)pt.getActualTypeArguments()[0];
		keyClass = (Class<? extends Serializable>) pt.getActualTypeArguments()[1];
	}
	
	@Override
	public List<Entity> getAll() throws DaoException {
		try {
			initializeParameters();
			criteria.select(rootObject);

			return sf.getCurrentSession().createQuery(criteria).getResultList();
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public Entity get(Key id) throws DaoException {
		try{ 
			initializeParameters();
			criteria.select(rootObject).where(builder.equal(rootObject.get(rootObjID), id));
			
			return sf.getCurrentSession().createQuery(criteria).getSingleResult();
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public List<Entity> getAllIn(List<Key> keys) throws DaoException {
		try {
			initializeParameters();
			Expression<Entity> exp = rootObject.get(rootObjID);
			Predicate predicate = exp.in(keys);
			criteria.where(predicate);
		
			return sf.getCurrentSession().createQuery(criteria).getResultList();
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public Entity insert(Entity entity) throws DaoException {
		try {
			return saveOrUpdate(entity);
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public Entity update(Entity entity) throws DaoException {
		try {
			return saveOrUpdate(entity);
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public void delete(Key entity) throws DaoException {
		try {
			initializeParameters();
			Entity e = get(entity);
			
			sf.getCurrentSession().remove(e);
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	@Override
	public void initialize(Object proxy) throws DaoException {
		try {
			Hibernate.initialize(proxy);
		} catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	private Entity saveOrUpdate(Entity entity) {
		try {
			sf.getCurrentSession().saveOrUpdate(entity);
			return entity;
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	private void initializeParameters() {
		builder = sf.getCurrentSession().getCriteriaBuilder();
		criteria = builder.createQuery(entityClass);
		rootObject = criteria.from(entityClass);
	}
	
	public String getClassName() {
		return entityClass.getSimpleName().toUpperCase();
	}

	protected SessionFactory getSessionFactory() {
		return sf;
	}

	public String getRootObjID() {
		return rootObjID;
	}

	public void setRootObjID(String rootObjID) {
		this.rootObjID = rootObjID;
	}
}
