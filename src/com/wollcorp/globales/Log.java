package com.wollcorp.globales;

import java.util.Date;

public class Log {

	public Log() {
		
	}
	
	public void registraError(Date fechaSistema, String error, String clase) {
		
		System.err.println(fechaSistema + " - ERROR: " + error + " - CLASE: " + clase);
		
	}
	
	public void registraInfo(Date fechaSistema, String info) {
		
		System.out.println(fechaSistema + " - INFO: " + info);
		
	}
	
}
