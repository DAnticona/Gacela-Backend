package com.wollcorp.dto;

public class ConexionDTO {
	
	private String servidor;
	private String dataBase;
	private String token;
	private String noUsua; 
	
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	public String getToken() {
		return token;
	}
	public String getNoUsua() {
		return noUsua;
	}
	public void setNoUsua(String noUsua) {
		this.noUsua = noUsua;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
