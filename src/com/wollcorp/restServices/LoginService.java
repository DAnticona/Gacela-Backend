package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.wollcorp.controladores.LoginControlador;
import com.wollcorp.dto.UsuarioDTO;
import com.wollcorp.globales.Log;

/**
 * Clase destinada al Login del sistema
 * @author danticona
 * @version 1.0
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	LoginControlador loginControlador = new LoginControlador();
	
	@POST
	public Response servicioLogin(@HeaderParam("authorization") String auth) {
		
		UsuarioDTO usuarioDTO = null;
		
		usuarioDTO = loginControlador.validarLogin(auth);
		
		if(usuarioDTO != null) {
			
			return Response.status(Response.Status.OK)
					.header("Token", loginControlador.getToken())
					.entity(usuarioDTO).build();
			
		} else if(Log.codigo == 18456 && Log.estado == "S0001"){
			
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(Log.mensaje).build();
			
		} else {
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(Log.mensaje).build();
			
		}
		 
	}

}
