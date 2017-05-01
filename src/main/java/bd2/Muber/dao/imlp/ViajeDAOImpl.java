package bd2.Muber.dao.imlp;

import bd2.Muber.dao.ViajeDAO;
import bd2.Muber.model.Viaje;

public class ViajeDAOImpl extends GenericDAOImpl<Viaje> implements ViajeDAO {

	public ViajeDAOImpl() {
		super(Viaje.class);
	}
}
