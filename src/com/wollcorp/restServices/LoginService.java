package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Usuario;
import com.wollcorp.controladores.LoginControlador;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	LoginControlador loginControlador = new LoginControlador();
	
	@POST
	public Response servicioLogin(Login login) {
		
		if(loginControlador.validarLogin(login)) {
			
			if(loginControlador.obtenerUsuario(login)) {//Encuentra 1 usuario
				
				return Response.status(Response.Status.OK).entity((Usuario)Globales.variablesGlobales.get("usuario")).build();
				
			} else {
				
				return Response.status(Response.Status.BAD_REQUEST).entity((Log)Globales.variablesGlobales.get("log")).build();
				
			}
			
		} else {
			
			return Response.status(Response.Status.UNAUTHORIZED).entity((Log)Globales.variablesGlobales.get("log")).build();
			
		}
	}

}
