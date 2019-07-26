package com.wollcorp.controladores;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Menu;
import com.wollcorp.beans.Usuario;
import com.wollcorp.conectores.Conector;
import com.wollcorp.dao.LoginDaoImpl;
import com.wollcorp.dao.MenuDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.dto.UsuarioDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Login;
import com.wollcorp.globales.Token;

/**
 * Esta clase es el controlador del servicio Login (LoginService.java)
 * @author danticona
 * @version 1.0
 */
public class LoginControlador {
	
	private String noUsua;
	private String paUsua;
	private Usuario usuario;
	private String token;
	private Connection conector;
	private List<Menu> menus = new ArrayList<Menu>();
	
	/**
	 * Constructor para la clase LoginControlador
	 */
	public LoginControlador() {
		
	}
	
	
	
	
	/**
	 * Procedimiento para validar el login enviado al servicio LoginService, es el punto inicial de toda la validación
	 * @param login
	 */
	public UsuarioDTO validarLogin(String authorization) {
		
		UsuarioDTO usuarioDTO = null;
		
		this.noUsua = Login.decode(authorization)[0];
		this.paUsua = Login.decode(authorization)[1];
		
		Log.mensaje = "VALIDANDO NUEVO LOGIN " + noUsua + "...";
		Log.registraInfo();
		
		this.conector = conectarBD(noUsua, paUsua);
		this.token = generarToken(noUsua);
		
		if(conector != null && token != null) {
			
			Log.mensaje = "CONECTADO A LA BD - USUARIO: " + noUsua;
			Log.registraInfo();

			Token.tokens.add(token);
			Conector.conectores.put(token, conector);

			Log.mensaje = "CONSULTANDO USUARIO EN BD " + noUsua + "...";
			Log.registraInfo();

			this.usuario = obtenerUsuario(noUsua, token);

			if (usuario != null) {

				Log.mensaje = "DATOS DE USUARIO ENCONTRADOS: " + noUsua;
				Log.registraInfo();
				
				//Globales.usuarios.put(token, usuario);

				
				Log.mensaje = "ACTUALIZANDO FECHA DE ULTIMA SESION DEL USUARIO : " + noUsua;
				Log.registraInfo();

				usuario.setFeUltSes(LocalDateTime.now());

				
				Log.mensaje = "OBTENIENDO MENUS DEL PERFIL : " + noUsua;
				Log.registraInfo();

				this.menus = obtenerListaMenu(usuario.getPerfil().getCoPerf(), token);

				Log.mensaje = "PREPARANDO DATOS DE ENVIO : " + noUsua;
				Log.registraInfo();

				usuarioDTO = new UsuarioDTO();

				usuarioDTO.setNombre(usuario.getNoPers() + " " + usuario.getApPate());
				usuarioDTO.setSexo(usuario.getSexo());
				usuarioDTO.setUsuario(usuario.getNoUsua());
				usuarioDTO.setPerfil(usuario.getPerfil().getNoPerf());
				usuarioDTO.setMenus(menus);

			}
			
		}
		
		return usuarioDTO;
		
	}
	
	
	public String getToken() {
		return this.token;
	}
	
	
	
	/**
	 * Envía la orden para conectarse a la BD, se comunica con el DAO y devuelve el objeto conexión en caso de error, devuelve null
	 * @param coUsua
	 * @param paUsua
	 * @return objeto conexión, null si no se pudo conectar
	 */
	private Connection conectarBD(String usuario, String password) {
		
		return (new LoginDaoImpl()).conectarBD(usuario, password);
			
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
	
}
