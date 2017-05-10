package bd2.Muber.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Muber implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Long idMuber;
	@Expose
	private List<Conductor> conductores;
	@Expose
	private List<Pasajero> pasajeros;
	@Expose
	private List<Viaje> viajes;
	
	public Muber(){
		conductores = new ArrayList<Conductor>();
		pasajeros = new ArrayList<Pasajero>();
		viajes = new ArrayList<Viaje>();
	}

	
	public Long getIdMuber() {
		return idMuber;
	}

	public void setIdMuber(Long idMuber) {
		this.idMuber = idMuber;
	}



	public List<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}

	public List<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	
	public void registrarPasajero(Pasajero pasajero){
		
		this.getPasajeros().add(pasajero);
	}
	
	public void registrarConductor(Conductor conductor){
		
		this.getConductores().add(conductor);
	}
	
	public void registrarViaje(Viaje viaje){
		
		this.getViajes().add(viaje);
	}
}
