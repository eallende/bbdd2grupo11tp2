package bd2.Muber.model;

public class Calificacion {

	private Long idCalificacion;
	private String comentario;
	private int puntaje;
	private Pasajero pasajero;
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
