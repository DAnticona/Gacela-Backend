package com.wollcorp.globales;

import java.util.Date;

public class Log {
	
	private String mensaje;
	private int codigoError;
	private String estadoError;
	private String nombreClase;

	public Log() {
		
	}
	
	public void registraError() {
		
		System.err.println(new Date() + " - ERROR: " + mensaje + " - CLASE: " + nombreClase + " - CODIGO: " + codigoError + " - ESTADO: " + estadoError);
		
	}
	
	public void registraInfo() {
		
		System.out.println(new Date() + " - INFO: " + mensaje);
		
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	public String getEstadoError() {
		return estadoError;
	}

	public void setEstadoError(String estadoError) {
		this.estadoError = estadoError;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	
}
