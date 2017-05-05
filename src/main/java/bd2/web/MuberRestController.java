package bd2.web;

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
		
		List<String> dataList = new LinkedList<>();
		if(muber.getPasajeros() != null && !muber.getPasajeros().isEmpty()){
			for (Pasajero pasajero : muber.getPasajeros()) {
			   dataList.add(pasajero.toString());
			}
			return JsonUtil.generateJson("OK", dataList);
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
		
		List<String> dataList = new LinkedList<>();
		if(muber.getConductores() != null && !muber.getConductores().isEmpty()){
			for (Conductor conductor : muber.getConductores()) {
			   dataList.add(conductor.toString());
			}
			return JsonUtil.generateJson("OK", dataList);
		}
		else
			return JsonUtil.generateJson("OK", "No hay conductores registrados");
	}
}
