package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.LoginDaoImpl;
import com.wollcorp.dao.PerfilDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class LoginControlador {
	
	
	
	public LoginControlador() {
		
		Globales.variablesGlobales.put("log", new Log());
		
	}
	
	
	
	public boolean estaConectado(Login login) {
		
		LoginDaoImpl loginDao = new LoginDaoImpl();
		
		((Log)Globales.variablesGlobales.get("log")).setMensaje("INTENTO DE LOGIN - USUARIO: " + login.getNoUsua());
		((Log)Globales.variablesGlobales.get("log")).registraInfo();
		
		if(loginDao.isConnected(login)) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("LOGIN VALIDO - USUARIO: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			//REGISTRA EL CONECTOR EN LAS VARIABLES GLOBALES DEL SISTEMA
			Globales.variablesGlobales.put("conector", loginDao.getConector());
			
			return true;
					
		} else {
			
			return false;
			
		}
	}
	
	
	
	
	public Usuario obtenerUsuarioConectado(Login login) {
		
		//DEVUELVE DATOS DEL USUARIO
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		
		((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTANDO DATOS DEL USUARIO: " + login.getNoUsua());
		((Log)Globales.variablesGlobales.get("log")).registraInfo();
		
		Usuario usuario = usuarioDao.obtenerUsuario(login.getNoUsua());
		
		if(usuario != null) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			Globales.variablesGlobales.put("usuarioConectado", usuario);
			
			return usuario;
			
		} else {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("NINGUN USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).setException(null);
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-100);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
			return null;
			
		}
		
	}
	
	
	
	
	
	public List<Perfil> obtenerPerfilesXUsuario(Usuario usuario) {
		
		List<Perfil> perfiles = null;
		
		PerfilDaoImpl perfilDao = new PerfilDaoImpl();
		
		((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTANDO PERFILES DEL USUARIO: " + usuario.getCoUsua());
		((Log)Globales.variablesGlobales.get("log")).registraInfo();
		
		perfiles = perfilDao.obtenerPerfilesXUsuario(usuario.getCoUsua());
		
		if(perfiles.size() == 0) {
			
			//NO ES UN ERROR, FALTA ASIGNARLE PERMISOS AL USUARIO.
			((Log)Globales.variablesGlobales.get("log")).setMensaje("EL USUARIO NO TIENE NINGUN PERFIL ASIGNADO: " + usuario.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			perfiles = null;
			
		} else {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("PERFILES DEL USUARIO ENCONTRADOS: " + usuario.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
		}
		
		Globales.variablesGlobales.put("perfiles", perfiles);
		
		return perfiles;
		
	}
	
	
	
	
	
	public List<SubMenu> obtenerMenusXUsuario(){
		
		List<SubMenu> subMenues = new ArrayList<SubMenu>();
		
		//subMenuDao = 
		
		return null;
	}	
	
}
