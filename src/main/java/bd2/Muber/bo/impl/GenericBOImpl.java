package bd2.Muber.bo.impl;

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
	
	

}
