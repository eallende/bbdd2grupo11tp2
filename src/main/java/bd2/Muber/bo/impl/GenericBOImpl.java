package bd2.Muber.bo.impl;

import java.util.List;

import bd2.Muber.bo.GenericBO;
import bd2.Muber.dao.GenericDAO;
import bd2.Muber.exception.DAOException;

public class GenericBOImpl<T> implements GenericBO<T>{
	
	private GenericDAO<T> dao;

	public GenericBOImpl() {

	}

	@Override
	public T get(Long id) {
		T entity = null;
		try {
			entity = dao.get(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	public GenericDAO<T> getDao() {
		return dao;
	}

	public void setDao(GenericDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public T save(T entity) {
		try {
			return dao.save(entity);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T update(T entity) {
		try {
			return dao.update(entity);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		try {
			return dao.getAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
