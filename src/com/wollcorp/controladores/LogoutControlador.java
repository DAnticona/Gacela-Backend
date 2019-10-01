package com.wollcorp.controladores;

import com.wollcorp.dao.ConexionDaoImpl;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Token;

public class LogoutControlador {

	public boolean desconectar(String noUsua, String token) {
		
		if(Token.tokenValido(token)) {
			Log.mensaje = "DESCONECTANDO LA SESIÓN PARA EL USUARIO: " + noUsua;
			Log.registraInfo();
			
			return desconectarBD(noUsua, token);
			
		} else {
			
			return false;
			
		}
		
	}
	
	private boolean desconectarBD(String noUsua, String token) {
		
		return (new ConexionDaoImpl()).desconectarBD(noUsua, token);
			
	}
	
}
