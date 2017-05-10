package bd2.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import bd2.Muber.bo.GenericBO;
import bd2.Muber.bo.impl.GenericBOImpl;
import bd2.Muber.dao.DAOFactory;
import bd2.Muber.dao.GenericDAO;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Muber;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
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

//	protected Session getSession() {
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//		return session;
//	}

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
	
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		
		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
		Muber muber = (Muber) bo.get(1L);
		if(muber == null || "".equals(muber))			
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
		
		if(muber.getConductores() != null && !muber.getConductores().isEmpty()){
			
			//enviando la lista de muber no anda
//			return JsonUtil.generateJson("OK", muber.getConductores());
			
			List<Conductor> conductores = new ArrayList<Conductor>();
			for (Conductor conductor : muber.getConductores()) {
				conductores.add(conductor);
			}
			return JsonUtil.generateJson("OK", conductores);
		}
		else
			return JsonUtil.generateJson("OK", "No hay conductores registrados");
	}
	
	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajeAbiertos() {
		
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
	
	@RequestMapping(value = "/conductores/detalle", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String informacionConductor(Long id) {
		
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
}
