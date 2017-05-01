package muber.test.integration;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import bd2.Muber.dao.CalificacionDAO;
import bd2.Muber.dao.ConductorDAO;
import bd2.Muber.dao.DAOFactory;
import bd2.Muber.dao.MuberDAO;
import bd2.Muber.dao.PasajeroDAO;
import bd2.Muber.dao.UsuarioDAO;
import bd2.Muber.dao.ViajeDAO;
import bd2.Muber.exception.DAOException;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Muber;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Usuario;
import bd2.Muber.model.Viaje;
import bd2.Muber.util.EstadoEnum;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MuberIntegrationTest {
	
	final static Logger log = Logger.getLogger(MuberIntegrationTest.class);
	static SessionFactory factory;
	private static Muber muber;

	public static void main(String[] args) {
		ConfigureLogger();
		CrearMuber();
		AllTestsCases();
	}

	private static void ConfigureLogger() {
		org.apache.log4j.BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
		Logger.getRootLogger().setLevel(Level.INFO);
	}
	
	private static void CrearMuber() {
		
		//Crear objeto Muber
		log.info("-----------Inicio de operación: crearMuber()---------");
		muber = new Muber();
		MuberDAO muberDAO = DAOFactory.getMuberDAO();
		try {
			muber = muberDAO.save(muber);
			log.info("----------Se creo el objeto Muber con id: " + muber.getIdMuber() + "------------");
			log.info("-----------Fin de operación: crearMuber()---------");
		} catch (DAOException e) {
			log.error("*******Error al crear el objeto Muber*********");
			e.toString();
		}
		
	}	

	private static void AllTestsCases() {
		
		
		//Crear conductor
		Conductor conductor = createConductorTest();
		
		//Crear Viaje
		Viaje viaje = createViajeTest();

		//Crear pasajeros
		Pasajero pasajeroGerman = createPasajeroTest("Germán6", 1500);
		Pasajero pasajeroAlicia = createPasajeroTest("Alicia6", 1500);
//		Pasajero pasajeroMargarita = createPasajeroTest("Margarita5", 1500);
		
		//Agregar pasajeros al viaje
		viaje.getPasajerosViaje().add(pasajeroGerman);
		viaje.getPasajerosViaje().add(pasajeroAlicia);
//		viaje.getPasajerosViaje().add(pasajeroMargarita);
		
		//Agregar conductor al viaje
		viaje.setConductorViaje(conductor);
		
		//Actualizar viaje
		updateViaje(viaje);
		
		//Agregar conductor a muber
		muber.getConductores().add((Conductor) conductor);
		
		//Agregar pasajeros a muber
		muber.getPasajeros().add(pasajeroGerman);
		muber.getPasajeros().add(pasajeroAlicia);
//		muber.getPasajeros().add(pasajeroMargarita);
		
		//Agregar viaje a muber
		muber.getViajes().add(viaje);
		
		//Actualizar muber
		updateMuber();
		
		//Crear calificaciones
		Calificacion calificacionGerman = createCalificacionTest(pasajeroGerman, 5, "Muy bueno el viaje", viaje);
		Calificacion calificacionAlicia = createCalificacionTest(pasajeroAlicia, 3, "Viaje regular", viaje);
//		Calificacion calificacionMargarita = createCalificacionTest(pasajeroMargarita, 4, "Viaje bueno", viaje);
		
		//Agregar calificaciones al conductor
		conductor.getCalificacionesConductor().add(calificacionGerman);
		conductor.getCalificacionesConductor().add(calificacionAlicia);
//		conductor.getCalificacionesConductor().add(calificacionMargarita);
		
		//Agregar viaje al conductor
		conductor.getViajesRealizadosConductor().add(viaje);
		
		//Actualizar conductor
		updateConductor(conductor);
		
		//Descontar crédito a pasajeros.
		finalizarViajeTest(viaje);
		
		getInformacionConductor(conductor);

	}
	
	/**
	 * Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje
	 *	promedio y fecha de licencia)
	 * @param conductor
	 */
	@SuppressWarnings("unused")
	private static void getInformacionConductor(Conductor conductor) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Conductor result = null;
		try {
			result = (Conductor)(usuarioDAO.getUsuario(conductor.getIdUsuario()));
			if(result != null){
				log.info("-----------Nombre usuario conductor: " +result.getNombreUsuario()+ "---------");
				log.info("-----------Fecha vencimiento de licencia: "+result.getFechaVencimientoLicencia()+ "---------");
				List<Viaje> viajesRealizados = result.getViajesRealizadosConductor();
				for (Viaje viaje : viajesRealizados){
					log.info("-----------Viajes Realizados: ---------");
					log.info("-----------Origen"+ viaje.getOrigen()+"---------");
					log.info("-----------Destino"+ viaje.getDestino()+"---------");
					log.info("-----------Costo Total"+ viaje.getCostoTotal()+"---------");
				}
				List<Calificacion> calificaciones= result.getCalificacionesConductor();
				int total = 0;
				for (Calificacion calificacion : calificaciones){
					total=+calificacion.getPuntaje();
				}
				log.info("-----------Calificación promedio"+ total / calificaciones.size()+"---------");
				
			}
			else
				log.info("-----------No existe el conductor con id: " +conductor.getIdUsuario()+ "---------");
		} catch (DAOException e) {
			log.error("********Ocurrió un error al intentar resuperar el conductor********");
			e.toString();
		}
		
	}

	private static Conductor createConductorTest() {
		
		log.info("-----------Inicio de operación: crearConductorTest---------");
		Conductor conductor = new Conductor();
		conductor.setNombreUsuario("Roberto6");
		conductor.setPassword("RobertoConductor");
		conductor.setFechaVencimientoLicencia(new Date());
		conductor.setFechaIngresoMuber(new Date());
		Usuario saveConductor = null;
		ConductorDAO conductorDAO = DAOFactory.getConductorDAO();
		try {
			saveConductor = conductorDAO.save(conductor);
			log.info("-----------Se creo el conductor id: " +saveConductor.getIdUsuario()+ "---------");
			log.info("-----------Fin de operación: crearConductorTest---------");
		} catch (DAOException e) {
			log.error("********Ocurrió un error al intentar guardar el conductor********");
			e.toString();
		}
		return (Conductor) saveConductor;
	}
	
	private static Viaje createViajeTest(){
		
		log.info("-----------Inicio de operación: createViajeTest---------");
		Viaje viaje = new Viaje();
		viaje.setOrigen("La Plata");
		viaje.setDestino("Tres Arroyos");
		viaje.setCantidadMaximaPasajeros(4);
		viaje.setCostoTotal(900);
		viaje.setEstado(EstadoEnum.ABIERTO.toString());
		Viaje saveViaje = null;
		ViajeDAO viajeDAO = DAOFactory.getViajeDAO();
		try {
			saveViaje = viajeDAO.save(viaje);
			log.info("-----------Se creo el viaje id: " +saveViaje.getIdViaje()+ "---------");
			log.info("-----------Fin de operación: createViajeTest---------");
		} catch (DAOException e) {
			log.error("********Ocurrió un error al intentar guardar el viaje********");
			e.toString();
		}
		return saveViaje;
		
	}
	
	private static Pasajero createPasajeroTest(String nombre, double credito) {
		
		log.info("-----------Inicio de operación: createPasajeroTest---------");
		Pasajero pasajero = new Pasajero();
		pasajero.setNombreUsuario(nombre);
		pasajero.getCreditoDisponible();
		PasajeroDAO pasajeroDAO = DAOFactory.getPasajeroDAO();
		Pasajero savePasajero = null;
		try{
			savePasajero = pasajeroDAO.save(pasajero);
			log.info("-----------Se creo el pasajero id: " +savePasajero.getIdUsuario()+ "---------");
			log.info("-----------Fin de operación: createPasajeroTest---------");
		}catch (DAOException e){
			log.error("********Ocurrió un error al intentar guardar el pasajero: "+nombre+"********");
			e.toString();
		}
		return savePasajero;
	}
	
	public static Calificacion createCalificacionTest(Pasajero pasajero, int puntaje, String comentario, Viaje viaje){
		
		log.info("-----------Inicio de operación: createCalificacionTest---------");
		Calificacion calificacion = new Calificacion();
		calificacion.setPasajero(pasajero);
		calificacion.setPuntaje(puntaje);
		calificacion.setComentario(comentario);
		calificacion.setViaje(viaje);
		CalificacionDAO calificacionDAO = DAOFactory.getCalificacionDAO();
		Calificacion saveCalificacion = null;
		try{
			saveCalificacion = calificacionDAO.save(calificacion);
			log.info("-----------Se creo la calificación id: " +saveCalificacion.getIdCalificacion()+ "---------");
			log.info("-----------Fin de operación: createCalificacionTest---------");
		}catch(DAOException e){
			log.error("********Ocurrió un error al intentar guardar la calificación********");
			e.toString();
		}
		return saveCalificacion;
	}
	
	private static void finalizarViajeTest(Viaje viaje){
		
		log.info("-----------Inicio de operación: finalizarViajeTest---------");
		List<Pasajero> pasajeros = viaje.getPasajerosViaje();
		double montoViaje = viaje.getCostoTotal() / viaje.getPasajerosViaje().size();
		for(Pasajero pasajero : pasajeros){
			pasajero.setCreditoDisponible(pasajero.getCreditoDisponible() - montoViaje);
			//Actualizar pasajero
			updatePasajero(pasajero);
		}
		
		viaje.setEstado(EstadoEnum.FINALIZADO.toString());
		//Actualizar viaje
		updateViaje(viaje);
	}
	
	private static void updateViaje(Viaje viaje){
		
		log.info("-----------Inicio de operación: updateViaje---------");
		ViajeDAO viajeDAO = DAOFactory.getViajeDAO();
		try{
			viajeDAO.update(viaje);
			log.info("-----------Se actualizó el viaje id: " +viaje.getIdViaje()+ "---------");
			log.info("-----------Fin de operación: updateViaje---------");
		}catch(DAOException e){
			log.error("********Ocurrió un error al intentar actualizar el viaje: "+viaje.getIdViaje()+"********");
			e.toString();
		}
	}
	
	private static void updateMuber(){
		
		log.info("-----------Inicio de operación: updateMuber---------");
		MuberDAO muberDAO = DAOFactory.getMuberDAO();
		try{
			muberDAO.update(muber);
			log.info("-----------Se actualizó muber id: " +muber.getIdMuber()+ "---------");
			log.info("-----------Fin de operación: updateMuber---------");
		}catch(DAOException e){
			log.error("********Ocurrió un error al intentar actualizar muber: "+muber.getIdMuber()+"********");
			e.toString();
		}
	}
	
	private static void updateConductor(Conductor conductor){
		
		log.info("-----------Inicio de operación: updateConductor---------");
		ConductorDAO conductorDAO = DAOFactory.getConductorDAO();
		try{
			conductorDAO.update(conductor);
			log.info("-----------Se actualizó el conductor id: " +conductor.getIdUsuario()+ "---------");
			log.info("-----------Fin de operación: updateConductor---------");
		}catch(DAOException e){
			log.error("********Ocurrió un error al intentar actualizar el conductor: "+conductor.getIdUsuario()+"********");
			e.toString();
		}
	}
	
	private static void updatePasajero(Pasajero pasajero){
		
		log.info("-----------Inicio de operación: updatePasajero---------");
		PasajeroDAO pasajeroDAO = DAOFactory.getPasajeroDAO();
		try{
			pasajeroDAO.update(pasajero);
			log.info("-----------Se actualizó el pasajero id: " +pasajero.getIdUsuario()+ "---------");
			log.info("-----------Fin de operación: updatePasajero---------");
		}catch(DAOException e){
			log.error("********Ocurrió un error al intentar actualizar el pasajero: "+pasajero.getIdUsuario()+"********");
			e.toString();
		}
	}
}
