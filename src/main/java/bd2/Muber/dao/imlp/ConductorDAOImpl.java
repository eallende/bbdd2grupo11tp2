package bd2.Muber.dao.imlp;

import bd2.Muber.dao.ConductorDAO;
import bd2.Muber.exception.DAOException;
import bd2.Muber.model.Conductor;

public class ConductorDAOImpl extends GenericDAOImpl<Conductor> implements ConductorDAO {

	public ConductorDAOImpl() {
		super(Conductor.class);
	}

}
