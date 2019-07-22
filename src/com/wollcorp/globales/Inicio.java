package com.wollcorp.globales;

/**
 * 
 * @author danticona
 * @version 1
 *
 */
public class Inicio {
	
	private Log log;
	
	/**
	 * 
	 */
	public Inicio() {
		
		iniciaParametros();
		cargaParametros();
		
	}
	
	/**
	 * 
	 */
	public void iniciaParametros() {
		
		log = new Log();
		
	}
	
	public void cargaParametros() {
		
		Globales.variablesGlobales.put("log", log);
		
	}
	
}
