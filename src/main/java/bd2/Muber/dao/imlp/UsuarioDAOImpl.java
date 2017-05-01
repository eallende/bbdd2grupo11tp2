package bd2.Muber.dao.imlp;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dao.UsuarioDAO;
import bd2.Muber.exception.DAOException;
import bd2.Muber.hibernate.util.HibernateUtil;
import bd2.Muber.model.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {
	
	public UsuarioDAOImpl(){
		super(Usuario.class);
	}

	public Usuario getUsuario(Long id) throws DAOException {
		Session session = null;
		Usuario result = null;
		 try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         Transaction tx = session.beginTransaction();
	         String hql = "FROM Usuario WHERE id_usuario = :id";
	         Query query = session.createQuery(hql);
	         query.setParameter("id", id);
	         result = (Usuario)(query.list().get(0));
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
		 return result;
	}
	
	

	
	
}
