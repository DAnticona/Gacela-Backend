package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.MenuControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.MenuRes;

@Path("/menu")
public class MenuRest {

	MenuControlador menuControlador = new MenuControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/perfil")
	public Response getPerfilxUsuario(@HeaderParam("token") String token, @QueryParam("perfil") String coPerf) {

		MenuRes menuRes = new MenuRes();

		if (token != null && coPerf != null) {

			menuRes = menuControlador.getMenuXPerfil(token, coPerf);

			if (menuRes.getMenus() != null) {

				System.out.println(coPerf);
				// System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());

				return Response.status(Response.Status.OK).entity(menuRes).build();

			} else {

				menuRes.setError(new ErrorRes());
				menuRes.getError().setMensaje("Error Interno al obtener los menu");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(menuRes).build();

			}

		} else {

			menuRes.setError(new ErrorRes());
			menuRes.getError().setMensaje("Requerimiento inválido al obtener los menu");

			return Response.status(Response.Status.BAD_REQUEST).entity(menuRes).build();

		}

	}

}
