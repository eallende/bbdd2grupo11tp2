package bd2.Muber.dao.imlp;

import bd2.Muber.dao.PasajeroDAO;
import bd2.Muber.model.Pasajero;

public class PasajeroDAOImpl extends GenericDAOImpl<Pasajero> implements PasajeroDAO {

	public PasajeroDAOImpl() {
		super(Pasajero.class);
	}

}
