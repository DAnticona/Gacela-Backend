package com.wollcorp.globales;

public class Inicio {
	
	private Log log;
	
	public Inicio() {
		iniciaParametros();
		cargaParametros();
	}
	
	public void iniciaParametros() {
		
		log = new Log();
		
	}
	
	public void cargaParametros() {
		
		Globales.variablesGlobales.put("log", log);
		
	}
	
}
