package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.LogoutControlador;
import com.wollcorp.globales.Log;

@Path("/logout")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogoutService {
	
	LogoutControlador logoutControlador = new LogoutControlador();
	
	@GET
	public Response servicioLogout(@HeaderParam("token") String token, @QueryParam("usuario") String noUsua) {
		
		boolean cerrado = logoutControlador.desconectar(noUsua, token);
		
		if(cerrado) {
			
			return Response.status(Response.Status.OK).entity(Log.class).build();
		
		} else {
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Log.class).build();
		
		}
		
		
	}

}
