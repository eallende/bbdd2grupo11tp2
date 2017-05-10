package bd2.Muber.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Pasajero extends Usuario implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Viaje> viajesRealizadosPasajero;
	@Expose
	private Double creditoDisponible;
	
	public Pasajero(){
		viajesRealizadosPasajero = new ArrayList<Viaje>();
	}

	public List<Viaje> getViajesRealizadosPasajero() {
		return viajesRealizadosPasajero;
	}

	public void setViajesRealizadosPasajero(List<Viaje> viajesRealizados) {
		this.viajesRealizadosPasajero = viajesRealizados;
	}

	public Double getCreditoDisponible() {
		if(creditoDisponible != null)
			return creditoDisponible;
		else
			return 0.0;
	}

	public void setCreditoDisponible(Double creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();				
        sb.append(" Nombre de Usuario: " + this.getNombreUsuario() +  " ;");
	    sb.append(" Crédito disponible: " + this.getCreditoDisponible() + " /");

	    return sb.toString();
		
	}

	public void descontarCredito(double montoViaje) {
		this.setCreditoDisponible(this.getCreditoDisponible() - montoViaje);
	}
	
	public void agregarCredito(double credito) {
		this.setCreditoDisponible(this.getCreditoDisponible() + credito);
	}
	
	
}
