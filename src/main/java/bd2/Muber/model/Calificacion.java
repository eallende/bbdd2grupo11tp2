package bd2.Muber.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Calificacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Long idCalificacion;
	@Expose
	private String comentario;
	@Expose
	private int puntaje;
	@Expose
	private Pasajero pasajero;
	@Expose
	private Viaje viaje;
	
	public Calificacion(){
		
	}
	
	public Long getIdCalificacion() {
		return idCalificacion;
	}
	public void setIdCalificacion(Long idCalificacion) {
		this.idCalificacion = idCalificacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero calificacionPasajero) {
		this.pasajero = calificacionPasajero;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	
	
}
