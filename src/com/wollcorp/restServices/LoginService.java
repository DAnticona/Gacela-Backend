package com.wollcorp.restServices;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.LoginDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.globales.Log;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	LoginDaoImpl loginDao = null;
	
	UsuarioDaoImpl usuarioDao = null;
	Usuario usuarioConectado = null;
	
	Date fechaSistema = new Date();
	
	Log log = new Log();
	
	String mensaje = null;
	
	@POST
	public Response servicioLogin(Login login) {
		
		loginDao = new LoginDaoImpl();
		
		mensaje = "INTENTO DE LOGIN - USUARIO: " + login.getUsuario();
		log.registraInfo(fechaSistema, mensaje);
		
		if(loginDao.login(login)) {
			
			//Devuelve datos del usuario
			usuarioDao = new UsuarioDaoImpl();
			
			mensaje = "CONSULTA DATOS DEL USUARIO: " + login.getUsuario();
			log.registraInfo(fechaSistema, mensaje);
			
			usuarioConectado = usuarioDao.obtenerUsuario(login.getUsuario());
			
			if(usuarioConectado != null) {
				
				return Response.status(Response.Status.OK).entity(usuarioConectado).build();
				
			} else {
				
				return Response.status(Response.Status.BAD_REQUEST).entity("USUARIO NO REGISTRADO EN BD...").build();
				
			}
			
			
			
		} else {
			
			return Response.status(Response.Status.UNAUTHORIZED).entity(loginDao.getConector().getErrorCode()).build();
			
		}
	}

}
