package bd2.Muber.dao.imlp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dao.GenericDAO;
import bd2.Muber.exception.DAOException;
import bd2.Muber.hibernate.util.HibernateUtil;

public class GenericDAOImpl<T> implements GenericDAO<T> {
	
	protected Class<T> persistentClass;

	public GenericDAOImpl(Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}

	public T save(T entity) throws DAOException {
		Session session = null;
		 try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         Transaction tx = session.beginTransaction();
	         session.save(entity);
	         tx.commit();
	      }
	      catch (HibernateException e) {
	         throw new DAOException(e.toString());
	      }
	      finally {
	         if (session != null) {
	            try {
	               session.close();
	            }
	            catch (HibernateException e) {
	            	e.getMessage();
	            }
	         }
	      }

	      return entity;
	   }
	
	
	public T update(T entity) throws DAOException {
		Session session = null;
		 try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         Transaction tx = session.beginTransaction();
	         session.update(entity);
	         tx.commit();
	      }
	      catch (HibernateException e) {
	         throw new DAOException(e.toString());
	      }
	      finally {
	         if (session != null) {
	            try {
	               session.close();
	            }
	            catch (HibernateException e) {
	            }
	         }
	      }

	      return entity;
	   }
	
	@Override
	public T get(Serializable id) throws DAOException {
		Session session = null;
		T entity;
		 try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         entity = (T) session.get(this.getPersistentClass(), id);
	      }
	      catch (HibernateException e) {
	         throw new DAOException(e.toString());
	      }
	      finally {
	         if (session != null) {
	            try {
	               session.close();
	            }
	            catch (HibernateException e) {
	            }
	         }
	      }

	      return entity;
	}
	
	@Override
	public List<T> getAll() throws DAOException {
		Session session = null;
		 try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         
	         org.hibernate.Query query = session.createQuery("from " + this.getPersistentClass().getName());
	 		 return (List<T>)query.list();
	      }
	      catch (HibernateException e) {
	         throw new DAOException(e.toString());
	      }
	      finally {
	         if (session != null) {
	            try {
	               session.close();
	            }
	            catch (HibernateException e) {
	            }
	         }
	      }

	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}


	
	
}


