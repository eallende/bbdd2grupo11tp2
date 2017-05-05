package bd2.Muber.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conductor extends Usuario {

	private Date fechaVencimientoLicencia;
	private List<Viaje> viajesRealizadosConductor;
	private List<Calificacion> calificacionesConductor;
	
	public Conductor(){
		
		viajesRealizadosConductor = new ArrayList<Viaje>();
		calificacionesConductor = new ArrayList<Calificacion>();
	}
	
	public Date getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}
	public void setFechaVencimientoLicencia(Date fechaVencimientoLicencia) {
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}
	public List<Viaje> getViajesRealizadosConductor() {
		return viajesRealizadosConductor;
	}
	public void setViajesRealizadosConductor(List<Viaje> viajesRealizados) {
		this.viajesRealizadosConductor = viajesRealizados;
	}
	public List<Calificacion> getCalificacionesConductor() {
		return calificacionesConductor;
	}
	public void setCalificacionesConductor(List<Calificacion> calificacionesConductor) {
		this.calificacionesConductor = calificacionesConductor;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();				
        sb.append(" Nombre de Usuario: " + this.getNombreUsuario() +  " ;");
	    sb.append(" Vencimiento de Licencia: " + this.fechaVencimientoLicencia + " /");

	    return sb.toString();
		
	}

	public void agregarCalificacion(Calificacion calificacion) {
		
		this.getCalificacionesConductor().add(calificacion);
		
	}

	public void registrarViajeRealizado(Viaje viaje) {
		
		this.getViajesRealizadosConductor().add(viaje);
	}
	
	
}
