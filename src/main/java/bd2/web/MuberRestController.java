package bd2.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sun.org.glassfish.gmbal.ParameterNames;

import bd2.Muber.bo.GenericBO;
import bd2.Muber.bo.impl.GenericBOImpl;
import bd2.Muber.dao.DAOFactory;
import bd2.Muber.dao.GenericDAO;
import bd2.Muber.dao.PasajeroDAO;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Credito;
import bd2.Muber.model.Muber;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Usuario;
import bd2.Muber.model.Viaje;
import bd2.Muber.util.EstadoEnum;
import bd2.Muber.util.JsonUtil;

@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {
	
	protected GenericBO getGenericBO(GenericDAO dao){
		GenericBOImpl genericBO = new GenericBOImpl();
		genericBO.setDao(dao);
		return genericBO;
	}
	
	/**
	 * Lista todos los pasajeros registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/pasajeros
	 * @return Json
	 */

	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		
		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
		Muber muber = (Muber) bo.get(1L);
		
		if(muber == null || "".equals(muber))			
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
		
		if(muber.getPasajeros() != null && !muber.getPasajeros().isEmpty()){
			return JsonUtil.generateJson("OK", muber.getPasajeros());
		}
		else
			return JsonUtil.generateJson("OK", "No hay pasajeros registrados");		
	}
	
	/**
	 * Lista todos los conductores registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores
	 * @return Json
	 */
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		
//		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
//		Muber muber = (Muber) bo.get(1L);
//		if(muber == null || "".equals(muber))			
//			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
//		
//		if(muber.getConductores() != null && !muber.getConductores().isEmpty()){
//			return JsonUtil.generateJson("OK", muber.getConductores());
//		}
//		else
//			return JsonUtil.generateJson("OK", "No hay conductores registrados");
		
		GenericBO<Conductor> bo = getGenericBO(DAOFactory.getConductorDAO());
		List<Conductor> conductores = bo.getAll();
		if(conductores != null && !conductores.isEmpty()){
			return JsonUtil.generateJson("OK", conductores);
		}
		else
			return JsonUtil.generateJson("OK", "No hay conductores registrados");
	}
	
	/**
	 * Lista todos los viajes abiertos en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/viajes/abiertos
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		
		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
		Muber muber = (Muber) bo.get(1L);
		if(muber == null || "".equals(muber))			
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
		
		if(muber.getViajes() != null && !muber.getViajes().isEmpty()){
			List<Viaje> viajesAbiertos = new ArrayList<Viaje>();
			for (Viaje viaje : muber.getViajes()) {
				if(viaje.isAbierto())
					viajesAbiertos.add(viaje);
			}
			if(!viajesAbiertos.isEmpty())
				return JsonUtil.generateJson("OK", viajesAbiertos);
			else
				return JsonUtil.generateJson("OK", "No hay viajes abiertos");
		}
		else
			return JsonUtil.generateJson("OK", "No hay viajes registrados");
	}
	
	
	/**
	 * Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	 *  curl http://localhost:8080/MuberRESTful/rest/services/conductores/detalle?id={id}
	 * @param id
	 * @return Json
	 */
	@RequestMapping(value = "/conductores/detalle", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String informacionConductor(@PathParam(value = "id") Long id) {
		
		GenericBO<Conductor> bo = getGenericBO(DAOFactory.getConductorDAO());		
		Conductor conductor = bo.get(id);
		if(conductor == null || "".equals(conductor))			
			return JsonUtil.generateJson("OK", "No se encontró el conductor");
		else{
			List<Object> dataList = new LinkedList<>();
			dataList.add(conductor);
			if(!conductor.getViajesRealizadosConductor().isEmpty()){
				for(Viaje viaje : conductor.getViajesRealizadosConductor()){
					dataList.add(viaje);
				}
			}
			else
				dataList.add("El conductor no tiene viajes realizados");
			
			dataList.add("Puntaje Promedio: " + conductor.promedioCalificacion());
			
			return JsonUtil.generateJson("OK", dataList);
		}

	}
	
	/**
	 * Crea un viaje
	 * curl -d "origen={origen}&destino={destino}&conductorId={conductorId}&costoTotal={costo}&cantidadPasajeros={cantidad}" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo
	 * @param origen
	 * @param destino
	 * @param conductorId
	 * @param costoTotal
	 * @param cantidadPasajeros
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String crearViaje(String origen, String destino, Long conductorId, double costoTotal, int cantidadPasajeros) {
		
		GenericBO<Conductor> conductorBO = getGenericBO(DAOFactory.getConductorDAO());	
		GenericBO<Viaje> viajeBO = getGenericBO(DAOFactory.getViajeDAO());		

		Conductor conductor = conductorBO.get(conductorId);
		if(conductor == null || "".equals(conductor))			
			return JsonUtil.generateJson("OK", "No se encontró el conductor");
		else{
			Viaje viaje = new Viaje();
			viaje.setCantidadMaximaPasajeros(cantidadPasajeros);
			viaje.setOrigen(origen);
			viaje.setDestino(destino);
			viaje.setCostoTotal(costoTotal);
			viaje.setEstado(EstadoEnum.ABIERTO.toString());
			viaje.setConductorViaje(conductor);
			Viaje nuevoViaje = viajeBO.save(viaje);
			if(nuevoViaje != null)
				return JsonUtil.generateJson("OK", "Se creo el viaje con éxito número: " + nuevoViaje.getIdViaje());
			else
				return JsonUtil.generateJson("Error", "Ocurrió un error al crear el viaje");
		}

	}
	/**
	 * Agrega un pasajero a un viaje ya creado
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero/1/4
	 * @param viajeId
	 * @param pasajeroId
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/agregarPasajero/{viajeId}/{pasajeroId}", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@PathVariable(value = "viajeId")Long viajeId, @PathVariable(value = "pasajeroId") Long pasajeroId) {
		
		GenericBO<Pasajero> pasajeroBO = getGenericBO(DAOFactory.getPasajeroDAO());	
		GenericBO<Viaje> viajeBO = getGenericBO(DAOFactory.getViajeDAO());		

		Pasajero pasajero = pasajeroBO.get(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
			return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			Viaje viaje = viajeBO.get(viajeId);
			if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
			else{
				viaje.agregarPasajero(pasajero);
				if (viajeBO.update(viaje) != null) 
					return JsonUtil.generateJson("OK", "Se agregó el pasajero al viaje con éxito");
				else
					return JsonUtil.generateJson("Error", "Ocurrió un error al agregar el pasajero al viaje");
			}
			
		}

	}
	
	/**
	 * Crea una calificación de un pasajero para un viaje en particular
	 * curl -d "viajeId={viaje}&pasajeroId={pasajero}&puntaje={puntaje}&comentario={comentario}" http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
	 * @param viajeId
	 * @param pasajeroId
	 * @param puntaje
	 * @param comentario
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/calificar", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String calificarViaje(Long viajeId, Long pasajeroId, int puntaje, String comentario) {
		
		GenericBO<Pasajero> pasajeroBO = getGenericBO(DAOFactory.getPasajeroDAO());	
		GenericBO<Viaje> viajeBO = getGenericBO(DAOFactory.getViajeDAO());		
		GenericBO<Calificacion> calificacionBO = getGenericBO(DAOFactory.getCalificacionDAO());

		Pasajero pasajero = pasajeroBO.get(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
			return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			Viaje viaje = viajeBO.get(viajeId);
			Calificacion calificacion;
			if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
			else{
				calificacion = new Calificacion();
				calificacion.setPasajero(pasajero);
				calificacion.setViaje(viaje);
				calificacion.setPuntaje(puntaje);
				calificacion.setComentario(comentario);
				calificacion = calificacionBO.save(calificacion);
				if(calificacion != null)
					return JsonUtil.generateJson("OK", "Se creo la calificación con éxito");
				else
					return JsonUtil.generateJson("Error", "Ocurrió un error al crear la calificación");
			}
		}
	}
	
	/**
	 * Finaliza un viaje, si está abierto.
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar?viajeId={idViaje}
	 * @param viajeId
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/text")
	public String finalizarViaje(Long viajeId) {
		
		GenericBO<Viaje> viajeBO = getGenericBO(DAOFactory.getViajeDAO());		

		Viaje viaje = viajeBO.get(viajeId);
		if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
		else{
			viaje.finalizarViaje();
			if(viajeBO.update(viaje) != null)
				return JsonUtil.generateJson("OK", "El viaje fue finalizado con éxito");
			else
				return JsonUtil.generateJson("Error", "No se pudo finalizar un viaje viaje");
			}
			
		}
	
	/**
	 * Cargar crédito a un pasajero en particular
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito/3/4000
	 * @param pasajeroId
	 * @param monto
	 * @return Json
	 */

	@RequestMapping(value = "/pasajeros/cargarCredito/{pasajeroId}/{monto}",	
			method = RequestMethod.PUT, 
			produces = "application/json")
	public String cargarCredito(@PathVariable("pasajeroId") Long pasajeroId,@PathVariable("monto") String monto) {
		GenericBO<Pasajero> pasajeroBO = getGenericBO(DAOFactory.getPasajeroDAO());		

		Pasajero pasajero =pasajeroBO.get(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
				return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			double credito = Double.valueOf(monto);
			pasajero.agregarCredito(credito);
			pasajeroBO.save(pasajero);
			return JsonUtil.generateJson("OK", "Se agregó crédito al pasajero");
			
		}
	}
	
	
	/**
	 * Lista los 10 conductores mejor calificados que no tengan viajes abiertos registrados
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores/top10
	 * @return Json
	 */
	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		
		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
		Muber muber = (Muber) bo.get(1L);
		if(muber == null || "".equals(muber))			
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
		
		if(muber.getConductores() != null && !muber.getConductores().isEmpty()){
		
			List<Conductor> conductores = new ArrayList<Conductor>();
			for (Conductor conductor : muber.getConductores()) {
				if(!conductor.tieneViajesAbiertos()){
					conductores.add(conductor);
				}
			}

			Collections.sort(conductores, Conductor.COMPARADO_POR_PROMEDIO);
			
			ArrayList<String> datosConductores = new ArrayList<String>();
			if(conductores.size() > 10)
				conductores = conductores.subList(conductores.size() - 10, conductores.size());
			
			for(Conductor conductor : conductores){
				datosConductores.add(conductor.toString());
			}
			
			return JsonUtil.generateJson("OK", datosConductores);

		}
		return JsonUtil.generateJson("OK", "No hay conductores registrados");
	}
	
	/**
	 * Crea un pasajero
	 * curl -d "nombre=Hugo&credito=2300" http://localhost:8080/MuberRESTful/rest/services/pasajeros/nuevo
	 * @param nombre
	 * @param credito
	 * @return Json
	 */
	@RequestMapping(value = "/pasajeros/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String crearPasajero(String nombre, double credito) {
			
		GenericBO<Pasajero> pasajeroBO = getGenericBO(DAOFactory.getPasajeroDAO());	
	
		Pasajero pasajero = new Pasajero();
		pasajero.setNombreUsuario(nombre);
		pasajero.setCreditoDisponible(credito);;
		Pasajero nuevoPasajero = null;
		nuevoPasajero = pasajeroBO.save(pasajero);
		if(nuevoPasajero != null)
			return JsonUtil.generateJson("OK", "Se creo el pasajero con éxito número: " + pasajero.getIdUsuario());
		else
			return JsonUtil.generateJson("Error", "Ocurrió un error al crear el pasajero");
	}
	
}
