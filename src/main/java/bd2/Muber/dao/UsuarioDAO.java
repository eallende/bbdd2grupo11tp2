package bd2.Muber.dao;

import bd2.Muber.exception.DAOException;
import bd2.Muber.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario getUsuario(Long id) throws DAOException;

}
