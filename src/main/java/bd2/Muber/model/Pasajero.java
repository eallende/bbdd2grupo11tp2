package bd2.Muber.model;

import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Usuario {
	
	private List<Viaje> viajesRealizadosPasajero;
	private double creditoDisponible;
	
	public Pasajero(){
		viajesRealizadosPasajero = new ArrayList<Viaje>();
	}

	public List<Viaje> getViajesRealizadosPasajero() {
		return viajesRealizadosPasajero;
	}

	public void setViajesRealizadosPasajero(List<Viaje> viajesRealizados) {
		this.viajesRealizadosPasajero = viajesRealizados;
	}

	public double getCreditoDisponible() {
		return creditoDisponible;
	}

	public void setCreditoDisponible(double creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}
	
	
	
}
