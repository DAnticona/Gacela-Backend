package com.wollcorp.controladores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.LoginDaoImpl;
import com.wollcorp.dao.SubMenuDaoImpl;
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
		List<SubMenu> subMenus = new ArrayList<SubMenu>();
		
		if(usuario != null) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			usuario.setFeUltSes(LocalDateTime.now());
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("ACTUALIZANDO FECHA DE ULTIMA SESION DEL USUARIO: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			usuarioDao.actualizarUsuario(usuario);
			
			Globales.variablesGlobales.put("usuarioConectado", usuario);
			
			if(usuario.getPerfil() != null) {
				
				((Log)Globales.variablesGlobales.get("log")).setMensaje("PERFIL DEL USUARIO: " + login.getNoUsua());
				((Log)Globales.variablesGlobales.get("log")).registraInfo();
				
				((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTANDO MENUS DEL USUARIO: " + usuario.getNoUsua());
				((Log)Globales.variablesGlobales.get("log")).registraInfo();
				
				subMenus = obtenerMenusXPerfil(usuario.getPerfil());
				
				usuario.setSubMenus(subMenus);
				
				
			} else {
				
				((Log)Globales.variablesGlobales.get("log")).setMensaje("EL USUARIO " + login.getNoUsua() + " NO TIENE PERFIL ASIGNADO");
				((Log)Globales.variablesGlobales.get("log")).registraInfo();
				
			}
			

			
		} else {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("NINGUN USUARIO ENCONTRADO EN BASE DE DATOS: " + login.getNoUsua());
			((Log)Globales.variablesGlobales.get("log")).setException(null);
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-100);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
			usuario = null;
			
		}
		
		return usuario;
		
	}
	
	
	
	
	
	public List<SubMenu> obtenerMenusXPerfil(Perfil perfil){
		
		List<SubMenu> subMenus = new ArrayList<SubMenu>();
		
		SubMenuDaoImpl subMenuDao = new SubMenuDaoImpl();
		
		subMenus = subMenuDao.obtenerSubMenusXPerfil(perfil.getCoPerf());
		
		if(subMenus.size() > 0) { //ENCONTRO CIERTOS MENUS ASOCIADOS AL PERFIL > 0
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("MENUS ENCONTRADOS ASOCIADOS AL PERFIL DEL USUARIO: " + perfil.getNoPerf());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
		} else { //NO ENCONTRO NINGUN MENU NI SUBMENU ASOCIADO AL PERFIL DEL USUARIO
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("NO SE ENCONTRARON MENUS ASOCIADOS AL PERFIL DEL USUARIO: " + perfil.getNoPerf());
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			subMenus = null;
			
		}
		
		return subMenus;
	}	
	
}
