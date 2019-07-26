package com.wollcorp.globales;

import java.util.Date;

public class Log {
	
	public static String mensaje;
	public static int codigo;
	public static String estado;
	public static String nombreClase;
	public static String exception;

	public Log() {
		
	}
	
	public static void registraError() {
		
		System.err.println(new Date() + " - ERROR: " + mensaje + " - EXCEPTION - " + exception +
				"\n- CODIGO: " + codigo + " - ESTADO: " + estado + " - CLASE: " + nombreClase);
		
	}
	
	public static void registraInfo() {
		
		codigo = 0;
		estado = "OK";
		
		System.out.println(new Date() + " - INFO: " + mensaje + " - CODIGO: " + codigo + " - ESTADO: " + estado);
		
	}
	
}
