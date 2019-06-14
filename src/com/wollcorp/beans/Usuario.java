package com.wollcorp.beans;

public class Usuario extends Persona{
	
	private String coUser;
	private String idUser;
	
	public Usuario() {
		super();
	}
	
	public Usuario (String coUser, String idUser) {
		this.coUser = coUser;
		this.idUser = idUser;
	}
	
	
	public String getCoUser() {
		return coUser;
	}
	public void setCoUser(String coUser) {
		this.coUser = coUser;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
}
