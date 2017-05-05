package bd2.Muber.model;

import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Usuario {
	
	private List<Viaje> viajesRealizadosPasajero;
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
	
	
}
