package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.UsuariosControlador;
import com.wollcorp.dto.usuarioDTO.UsuarioDTO;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.UsuarioRes;

@Path("/usuario")
public class UsuarioService {

	UsuariosControlador usuarioControlador = new UsuariosControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@HeaderParam("token") String token, @QueryParam("user") String noUsua) {

		UsuarioRes usuarioRes = new UsuarioRes();

		if (token != null && noUsua != null) {

			usuarioRes = usuarioControlador.getUsuario(token, noUsua);

			if (usuarioRes.getError() == null) {

				// System.out.println("Mes: ");
				// System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());

				return Response.status(Response.Status.OK).entity(usuarioRes).build();

			} else {
				
				usuarioRes.setError(new ErrorRes());
				usuarioRes.getError().setMensaje("Error Interno al obtener el usuario");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(usuarioRes).build();

			}

		} else {

			usuarioRes.setError(new ErrorRes());
			usuarioRes.getError().setMensaje("Requerimiento inválido al obtener el usuario");

			return Response.status(Response.Status.BAD_REQUEST).entity(usuarioRes).build();

		}

	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putUsuario(@HeaderParam("token") String token, UsuarioDTO usuario) {
		
		// 
		
		System.out.println("Token: ");

		// UsuarioRes usuarioRes = new UsuarioRes();

		//if (token != null && usuario != null) {
			
		/*
		 * System.out.println(usuario.getFeNaci());
		 * System.out.println(usuario.getEmail());
		 * System.out.println(usuario.getNoPers());
		 */
			
			
			return Response.status(Response.Status.OK).build();

			// usuarioRes = usuarioControlador.getUsuario(token, usuario);

			/*
			 * if (usuarioRes.getError() == null) {
			 * 
			 * // System.out.println("Mes: "); //
			 * System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());
			 * 
			 * return Response.status(Response.Status.OK).entity(usuarioRes).build();
			 * 
			 * } else {
			 * 
			 * usuarioRes.setError(new ErrorRes());
			 * usuarioRes.getError().setMensaje("Error Interno al obtener el usuario");
			 * 
			 * return
			 * Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(usuarioRes).
			 * build();
			 * 
			 * }
			 */

		/*
		 * } else {
		 * 
		 * usuarioRes.setError(new ErrorRes()); usuarioRes.getError().
		 * setMensaje("Requerimiento inválido al obtener el usuario");
		 * 
		 * return
		 * Response.status(Response.Status.BAD_REQUEST).entity(usuarioRes).build();
		 * 
		 * }
		 */

	}

}
