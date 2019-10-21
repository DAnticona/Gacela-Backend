package com.wollcorp.controladores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Conexion;
import com.wollcorp.beans.Menu;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.beans.Usuario;
import com.wollcorp.conectores.Conector;
import com.wollcorp.dao.ConexionDaoImpl;
import com.wollcorp.dao.MenuDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.dto.ConexionDTO;
import com.wollcorp.dto.MenuDTO;
import com.wollcorp.dto.PerfilDTO;
import com.wollcorp.dto.SubMenuDTO;
import com.wollcorp.dto.UsuarioDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Login;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.LoginRes;

/**
 * Esta clase es el controlador del servicio Login (LoginService.java)
 * @author danticona
 * @version 1.0
 */
public class LoginControlador {
	
	private String noUsua;
	private String paUsua;
	private String token;
	private Conexion conexion;
	// private Connection conector;
	
	
	/**
	 * Constructor para la clase LoginControlador
	 */
	public LoginControlador() {
		
	}
	
	
	
	
	/**
	 * Procedimiento para validar el login enviado al servicio LoginService, es el punto inicial de toda la validación
	 * @param login
	 */
	public LoginRes validarLogin(String authorization) {
		
		// DECLARACION DE LOS BEANS
		Usuario usuario;
		List<Menu> menus = new ArrayList<Menu>();
		
		// DECLARACION DE LOS DTO
		LoginRes loginRes = new LoginRes();
		UsuarioDTO usuarioDTO = null;
		
		PerfilDTO perfilDTO = null;
		ConexionDTO conexionDTO = null;
		
		this.noUsua = Login.decode(authorization)[0];
		this.paUsua = Login.decode(authorization)[1];
		
		Log.mensaje = "VALIDANDO NUEVO LOGIN " + noUsua + "...";
		Log.registraInfo();
		
		// this.conector = conectarBD(noUsua, paUsua);
		this.conexion = conectarBD(noUsua, paUsua);
		this.token = generarToken(noUsua);
		
		if(this.conexion != null) {
			
			if(this.token != null) {
				
				Token.tokens.add(token);
				Conector.conectores.put(token, conexion.getConector());
				
				/*
				 * Log.mensaje = "CONSULTANDO USUARIO EN BD " + noUsua + "...";
				 * Log.registraInfo();

				
				 * usuario = obtenerUsuario(noUsua, token);
				 * 
				 * if (usuario != null) {
				 * 
				 * 
				 * Log.mensaje = "ACTUALIZANDO FECHA DE ULTIMA SESION DEL USUARIO : " + noUsua;
				 * Log.registraInfo();
				 * 
				 * usuario.setFeUltSes(LocalDateTime.now());
				 * 
				 * 
				 * Log.mensaje = "OBTENIENDO MENUS DEL PERFIL : " + noUsua; Log.registraInfo();
				 * 
				 * menus = obtenerListaMenu(usuario.getPerfil().getCoPerf(), token);
				 * 
				 * // System.out.println("Tamaño Menus: " + menus.size());
				 * 
				 * for(int i = 0; i < menus.size(); i++) {
				 * 
				 * // System.out.println("Menus: " + menus.get(i).getAlMenu());
				 * 
				 * List<SubMenu> sm = obtenerSubMenusXPerfil(menus.get(i).getCoMenu(),
				 * usuario.getPerfil().getCoPerf(), token);
				 * 
				 * // System.out.println("Menus: " + menus.get(i).getAlMenu());
				 * 
				 * menus.get(i).setSubMenus(sm);
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * usuarioDTO = generaUsuarioDTO(usuario);
				 * 
				 * perfilDTO = generaPerfilDTO(usuario.getPerfil());
				 * 
				 * List<MenuDTO> menusDTO = generaMenus(menus);
				 * 
				 * perfilDTO.setMenus(menusDTO); usuarioDTO.setPerfil(perfilDTO);
				 */
					
					conexionDTO = new ConexionDTO();
					
					conexionDTO.setServidor(this.conexion.getServidor());
					conexionDTO.setDataBase(this.conexion.getDataBase());
					conexionDTO.setToken(this.token);
					conexionDTO.setNoUsua(this.noUsua);
					
					
					loginRes.setConexion(conexionDTO);
					loginRes.setConexion(conexionDTO);
//					loginRes.setUsuario(usuarioDTO);
				
			} else {
				
				loginRes.setError(new ErrorRes());
				loginRes.getError().setMensaje("Error al generar el token");
				
			}

			
				

		} else {
				
				loginRes.setError(new ErrorRes());
				loginRes.getError().setMensaje("Usuario o Contraseña Incorrecta");
				
		}
		
		/*
		} 
	
		 * else {
		 * 
		 * loginRes.setError(new ErrorRes());
		 * loginRes.getError().setMensaje("Usuario o Contraseña Incorrecta");
		 * 
		 * }
		 */
		
		return loginRes;
		
	}
	
	
	
	/**
	 * Envía la orden para conectarse a la BD, se comunica con el DAO y devuelve el objeto conexión.
	 * En caso de error, devuelve null
	 * @param coUsua
	 * @param paUsua
	 * @return objeto conexión, null si no se pudo conectar
	 */
	private Conexion conectarBD(String usuario, String password) {
		
		return (new ConexionDaoImpl()).conectarBD(usuario, password);
			
	}
	
	
	
	
	/**
	 * Envía la orden para generar el token para el usuario.
	 * @param coUsua
	 * @return El token generado con el código de usuario, null si no pudo generar el token
	 */
	private String generarToken(String usuario) {
		
		return (new Token()).generarToken(usuario);
		
	}
	
	
	
	
	/**
	 * Envía la orden para obtener el usuario, se conecta con el DAO
	 * @param noUsua
	 * @return objeto usuario, null si no pudo traer el usuario
	 */
	private Usuario obtenerUsuario(String usuario, String token) {
		
		return (new UsuarioDaoImpl()).obtenerUsuario(usuario, token);
		
	}
	
	
	/**
	 * Envía la orden para listar los menus por perfil del usuario, se comunica con el DAO
	 * @param perfil
	 * @return lista de Menus
	 */
	private List<Menu> obtenerListaMenu(String perfil, String token){
		
		return (new MenuDaoImpl()).obtenerMenusXPerfil(perfil, token);
		
	}
	
	private List<SubMenu> obtenerSubMenusXPerfil(String coMenu, String coPerf, String token) {
		
		return (new MenuDaoImpl()).obtenerSubMenusXPerfil(coMenu, coPerf, token);
		
	}
	
	
	
	private UsuarioDTO generaUsuarioDTO(Usuario usuario) {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setCoPers(usuario.getCoPers());
		usuarioDTO.setTiDocu(usuario.getTiDocu());
		usuarioDTO.setNuDocu(usuario.getNuDocu());
		usuarioDTO.setNoPers(usuario.getNoPers());
		usuarioDTO.setApPate(usuario.getApPate());
		usuarioDTO.setApMate(usuario.getApMate());
		usuarioDTO.setFeNaci(usuario.getFeNaci());
		usuarioDTO.setSexo(usuario.getSexo());
		usuarioDTO.setNoUsua(usuario.getNoUsua());
		
		
		return usuarioDTO;
		
	}
	
	
	private PerfilDTO generaPerfilDTO(Perfil perfil) {
		
		
		PerfilDTO perfilDTO = new PerfilDTO();
		
		perfilDTO.setCoPerf(perfil.getCoPerf());
		perfilDTO.setNoPerf(perfil.getNoPerf());
		
		
		return perfilDTO;
		
	}
	
	
	
	private List<MenuDTO> generaMenus(List<Menu> menus) {
		
		List<MenuDTO> menusDTO = new ArrayList<MenuDTO>();
		
		for(Menu m : menus) {
			
			MenuDTO menuDTO = new MenuDTO();
			
			menuDTO.setCoMenu(m.getCoMenu());
			menuDTO.setNoMenu(m.getNoMenu());
			menuDTO.setAlMenu(m.getAlMenu());
			menuDTO.setRuta(m.getRuta());
			
			
			List<SubMenuDTO> subMenusDTO = new ArrayList<SubMenuDTO>();
			
			for (SubMenu sm: m.getSubMenus()) {
				
				SubMenuDTO subMenuDTO = new SubMenuDTO();
				
				subMenuDTO.setCoSubMenu(sm.getCoMenu());
				subMenuDTO.setNoSubMenu(sm.getNoMenu());
				subMenuDTO.setAlSubMenu(sm.getAlMenu());
				subMenuDTO.setRuta(sm.getRuta());
				subMenuDTO.setCoMenuPadre(m.getCoMenu());
				subMenuDTO.setIcono(sm.getIcono());
				
				subMenusDTO.add(subMenuDTO);
				
			}
			
			menuDTO.setSubMenus(subMenusDTO);					
			
			menusDTO.add(menuDTO);
			
		}
		
		return menusDTO;
		
	}
	
}
