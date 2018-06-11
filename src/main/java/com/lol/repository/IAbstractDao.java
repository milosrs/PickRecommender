package com.lol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbstractDao<Entity, Key> extends JpaRepository<Entity, Key> {

	List<Entity> getAll() throws DaoException;
	
	Entity get(Key id) throws DaoException;
	
	Entity insert(Entity entity) throws DaoException;
	
	Entity update(Entity entity) throws DaoException;
	
	List<Entity> getAllIn(List<Key> keys) throws DaoException;
	
	void initialize(Object proxy) throws DaoException;
}
