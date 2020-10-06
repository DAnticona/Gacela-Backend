package com.wollcorp.dto;

public class ConexionDto {

	private String servidor;
	private String dataBase;
	private String token;
	private String noUsua;

	public String getServidor() {
		return this.servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getDataBase() {
		return this.dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNoUsua() {
		return this.noUsua;
	}

	public void setNoUsua(String noUsua) {
		this.noUsua = noUsua;
	}

}
