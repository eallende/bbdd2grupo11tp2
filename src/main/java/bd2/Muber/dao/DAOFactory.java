package bd2.Muber.dao;

import bd2.Muber.dao.imlp.CalificacionDAOImpl;
import bd2.Muber.dao.imlp.ConductorDAOImpl;
import bd2.Muber.dao.imlp.MuberDAOImpl;
import bd2.Muber.dao.imlp.PasajeroDAOImpl;
import bd2.Muber.dao.imlp.UsuarioDAOImpl;
import bd2.Muber.dao.imlp.ViajeDAOImpl;

public class DAOFactory {
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl(); 
	}
	
	public static MuberDAO getMuberDAO() {
		return new MuberDAOImpl(); 
	}

	public static ViajeDAO getViajeDAO() {
		return new ViajeDAOImpl();
	}
	
	public static PasajeroDAO getPasajeroDAO(){
		return new PasajeroDAOImpl();
	}
	
	public static ConductorDAO getConductorDAO(){
		return new ConductorDAOImpl();
	}

	public static CalificacionDAO getCalificacionDAO() {
		return new CalificacionDAOImpl();
	}
}
