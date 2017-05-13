package bd2.Muber.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Conductor extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
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
	
	public Double promedioCalificacion(){
		
		List<Calificacion> calificaciones= this.getCalificacionesConductor();
		double total = 0;
		for (Calificacion calificacion : calificaciones){
			total=+calificacion.getPuntaje();
		}
		
		return  (total / calificaciones.size());
	}

	public boolean tieneViajesAbiertos() {
		for(Viaje viaje : this.getViajesRealizadosConductor()){
			if(viaje.isAbierto())
				return true;
		}
		return false;
	}

	
	public static Comparator<Conductor> COMPARADO_POR_PROMEDIO = new Comparator<Conductor>() {
        public int compare(Conductor c1, Conductor c2) {
            return c2.promedioCalificacion().compareTo(c1.promedioCalificacion());
        }
    };
}
