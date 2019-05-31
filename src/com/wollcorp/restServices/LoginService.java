package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	@POST
	public Response validaLogin(@Context HttpHeaders httpHeaders, 
								@FormParam( "username" ) String username,
								@FormParam( "password" ) String password) {
		
		
		
		return null;
	}

}
