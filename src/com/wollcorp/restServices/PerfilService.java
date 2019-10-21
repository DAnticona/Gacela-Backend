package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.PerfilControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.PerfilRes;

@Path("/perfil")
public class PerfilService {
	
	PerfilControlador perfilControlador = new PerfilControlador();
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuario")
	public Response getPerfilxUsuario(@HeaderParam("token") String token, @QueryParam("user") String noUsua) {

		PerfilRes perfilRes = new PerfilRes();

		if (token != null && noUsua != null) {

			perfilRes = perfilControlador.getPerfilxUsuario(token, noUsua);

			if (perfilRes.getPerfil() != null) {

				// System.out.println("Mes: ");
				// System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());

				return Response.status(Response.Status.OK).entity(perfilRes).build();

			} else {
				
				perfilRes.setError(new ErrorRes());
				perfilRes.getError().setMensaje("Error Interno al obtener el perfil del usuario");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(perfilRes).build();

			}

		} else {

			perfilRes.setError(new ErrorRes());
			perfilRes.getError().setMensaje("Requerimiento inválido al obtener el perfil del usuario");

			return Response.status(Response.Status.BAD_REQUEST).entity(perfilRes).build();

		}

	}

}
