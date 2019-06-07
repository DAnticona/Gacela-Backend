package com.wollcorp.beans;

public class Usuario {
	
	private String codigo;
	private String idUser;
	private String nombre;
	private String coPers;
	
	public Usuario() {
		
	}
	
	public Usuario (String codigo, String idUser, String nombre, String coPers) {
		this.codigo = codigo;
		this.idUser = idUser;
		this.nombre = nombre;
		this.coPers = coPers;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCoPers() {
		return coPers;
	}

	public void setCoPers(String coPers) {
		this.coPers = coPers;
	}
	
	

}
