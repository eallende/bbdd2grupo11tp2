package bd2.web;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import bd2.Muber.bo.GenericBO;
import bd2.Muber.bo.impl.GenericBOImpl;
import bd2.Muber.dao.DAOFactory;
import bd2.Muber.dao.GenericDAO;
import bd2.Muber.model.Muber;
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
		if(muber != null)			
			return JsonUtil.generateJson("OK", muber.getPasajeros());
		else
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");		
	}
	
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		
		GenericBO<Muber> bo = getGenericBO(DAOFactory.getMuberDAO());		
		Muber muber = (Muber) bo.get(1L);
		if(muber != null)			
			return JsonUtil.generateJson("OK", muber.getConductores());
		else
			return JsonUtil.generateJson("OK", "No se encontró el objeto muber");
	}
}
