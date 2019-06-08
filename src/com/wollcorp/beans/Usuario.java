package com.wollcorp.beans;

public class Usuario extends Persona{
	
	private String coUser;
	private String idUser;
	private String noUser;
	
	public Usuario() {
		super();
	}
	
	public Usuario (String coUser, String idUser, String noUser) {
		this.coUser = coUser;
		this.idUser = idUser;
		this.noUser = noUser;
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
	public String getNoUser() {
		return noUser;
	}
	
	public void setNoUser(String noUser) {
		this.noUser = noUser;
	}
}
