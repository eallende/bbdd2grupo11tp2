package bd2.Muber.model;

import java.util.Date;

public class Usuario {
	
	private Long idUsuario;
	private String nombreUsuario;
	private String password;
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
