package bd2.Muber.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long idUsuario;
	@Expose
	private String nombreUsuario;
	private String password;
	@Expose
	private Date fechaIngresoMuber;
	
	public Usuario(){
		
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaIngresoMuber() {
		return fechaIngresoMuber;
	}
	public void setFechaIngresoMuber(Date fechaIngresoMuber) {
		this.fechaIngresoMuber = fechaIngresoMuber;
	}
	
	

}
