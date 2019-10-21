package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.LoginControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.LoginRes;

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
		
		LoginRes datosLogin = null;
		
		if (auth != null) {
			
			datosLogin = loginControlador.validarLogin(auth);
			
			if(datosLogin.getConexion() != null) {
				
				return Response.status(Response.Status.OK)
						.entity(datosLogin).build();
				
			} else if(datosLogin.getError() != null) {
				
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity(datosLogin).build();
				
			} else {
				
				datosLogin = new LoginRes();
				datosLogin.setError(new ErrorRes());
				datosLogin.getError().setMensaje("Error interno");
				
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.header("Access-Control-Allow-Origin", "*")
						.entity(datosLogin).build();
				
			}
			
		}
		
		datosLogin = new LoginRes();
		datosLogin.setError(new ErrorRes());
		datosLogin.getError().setMensaje("No se llegaron a  enviar todos los datos requeridos");
		
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(datosLogin).build();
		
		 
	}

}
