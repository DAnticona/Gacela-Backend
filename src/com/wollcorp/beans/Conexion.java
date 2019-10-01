package com.wollcorp.beans;

import java.sql.Connection;

public class Conexion {
	
	private String servidor;
	private String dataBase;
	private Connection conector; 
	
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
	public Connection getConector() {
		return conector;
	}
	public void setConector(Connection conector) {
		this.conector = conector;
	}

}
