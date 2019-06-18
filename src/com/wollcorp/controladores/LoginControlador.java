package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.LoginDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class LoginControlador {
	
	Date fechaSistema = new Date();
	Log log = new Log();
	String mensaje;

	public LoginControlador() {
		
	}
	
	public boolean validarLogin(Login login) {
		
		LoginDaoImpl loginDao = new LoginDaoImpl();
		
		log.setMensaje("INTENTO DE LOGIN - USUARIO: " + login.getUsuario());
		log.setCodigoError(0);
		log.setEstadoError(null);
		log.setNombreClase(null);
		log.registraInfo();
		
		if(loginDao.login(login)) {
			
			log.setMensaje("LOGIN VALIDO - USUARIO: " + login.getUsuario());
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			//Registra el conector en las variables Globales del Sistema
			Globales.variablesGlobales.add(loginDao.getConector());
			return true;
					
		} else {
			
			log.setMensaje("NO PUDO ACCEDER A LA BASE DE DATOS - USUARIO: " + login.getUsuario());
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			return false;
			
		}
	}
	
	public int obtenerUsuario(Login login) {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		//Devuelve datos del usuario
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		
		log.setMensaje("CONSULTANDO DATOS DEL USUARIO: " + login.getUsuario());
		log.setCodigoError(0);
		log.setEstadoError(null);
		log.setNombreClase(null);
		log.registraInfo();
		
		usuarios = usuarioDao.obtenerUsuarios(login.getUsuario());
		
		switch (usuarios.size()) {
		case 0:
			
			log.setMensaje("NINGUN USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getUsuario());
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			return 0;
			
		case 1:
			
			log.setMensaje("USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getUsuario());
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			Globales.variablesGlobales.add(usuarios.get(0));
			
			return 1;
			
		default:
			
			log.setMensaje("MAS DE UN USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getUsuario());
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			return -1;
		}
		
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
}
