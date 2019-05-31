package com.wollcorp.beans;

public class Usuario {
	
	private String codigo;
	private String idUser;
	private String nombre;
	
	public Usuario() {
		
	}
	
	public Usuario (String codigo, String idUser, String nombre) {
		this.codigo = codigo;
		this.idUser = idUser;
		this.nombre = nombre;
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
	
	

}
