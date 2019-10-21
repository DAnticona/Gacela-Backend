package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.PasswordControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.PasswordRes;

@Path("/password")
public class PasswordService {
	
	PasswordControlador passwordControlador = new PasswordControlador();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarPassword(@HeaderParam("token") String token,
									@HeaderParam("authorization") String auth,
									@HeaderParam("newPassword") String newPas) {
		
		PasswordRes passwordRes = null;
		
		if(token != null && auth != null && newPas != null) {
			
			passwordRes = passwordControlador.cambiaPassword(token, auth, newPas);
			
			if(passwordRes != null) {
				
				if(passwordRes.getError() == null) {
					
					return Response.status(Response.Status.OK).build();
					
				} else {
					
					return Response.status(Response.Status.UNAUTHORIZED).entity(passwordRes).build();
					
				}
				
				
			} else {
				
				passwordRes = new PasswordRes();
				passwordRes.setError(new ErrorRes());
				passwordRes.getError().setMensaje("Error interno");
				
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(passwordRes).build();
				
			}
			
			
			
		} else {
			
			passwordRes = new PasswordRes();
			passwordRes.setError(new ErrorRes());
			passwordRes.getError().setMensaje("No se llegaron a enviar todos los datos requeridos");
			
			return Response.status(Response.Status.BAD_REQUEST).entity(passwordRes).build();
			
		}
		
		
		
	}

}
