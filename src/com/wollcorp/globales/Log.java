package com.wollcorp.globales;

import java.util.Date;

public class Log {
	
	private String mensaje;
	private int codigo;
	private String estado;
	private String nombreClase;
	private String exception;

	public Log() {
		
	}
	
	public void registraError() {
		
		System.err.println(new Date() + " - ERROR: " + mensaje + " - EXCEPTION - " + exception +
				"\n- CODIGO: " + codigo + " - ESTADO: " + estado + " - CLASE: " + nombreClase);
		
	}
	
	public void registraInfo() {
		
		codigo = 0;
		estado = "OK";
		
		System.out.println(new Date() + " - INFO: " + mensaje + " - CODIGO: " + codigo + " - ESTADO: " + estado);
		
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
}
