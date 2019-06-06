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
import com.wollcorp.globales.Globales;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	LoginDaoImpl loginDao = null;
	UsuarioDaoImpl usuarioDao = null;
	Usuario usuarioConectado = null;
	
	Date fechaSistema = new Date();
	
	@POST
	public Response servicioLogin(Login login) {
		
		loginDao = new LoginDaoImpl();
		
		if(loginDao.login(login)) {
			
			System.out.println(fechaSistema + " INFO - " + loginDao.getInfo());
			
			//Registra el conector en las variables Globales del Sistema
			Globales.variablesGlobales.add(loginDao.getConector());
			
			//Devuelve datos del usuario
			usuarioDao = new UsuarioDaoImpl();
			
			usuarioConectado = usuarioDao.obtenerUsuario(login.getUsuario());
			
			return Response.status(Response.Status.OK).entity(usuarioConectado).build();
			
		} else {
			
			System.err.println(fechaSistema + " ERROR - " + loginDao.getError());
			
			return Response.status(Response.Status.UNAUTHORIZED).entity(login).build();
			
		}
		
		
	}

}
