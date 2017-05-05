package bd2.Muber.dao;

import java.io.Serializable;

import bd2.Muber.exception.DAOException;

public interface GenericDAO<T> {
	public T save(T entity) throws DAOException;
	public T update(T entity) throws DAOException;
	public T get(Serializable id) throws DAOException;
}
