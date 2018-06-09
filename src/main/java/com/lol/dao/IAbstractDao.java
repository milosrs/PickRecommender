package com.lol.dao;

import java.util.List;

public interface IAbstractDao<Entity, Key> {

	List<Entity> getAll() throws DaoException;
	
	Entity get(Key id) throws DaoException;
	
	Entity insert(Entity entity) throws DaoException;
	
	Entity update(Entity entity) throws DaoException;
	
	List<Entity> getAllIn(List<Key> keys) throws DaoException;
	
	void delete(Key entity) throws DaoException;
	
	void initialize(Object proxy) throws DaoException;
}
