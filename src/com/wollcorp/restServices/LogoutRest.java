package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.LogoutControlador;
import com.wollcorp.dto.ErrorDto;

@Path("/logout")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogoutRest {

	LogoutControlador logoutControlador = new LogoutControlador();

	@GET
	public Response servicioLogout(@HeaderParam("token") String token, @QueryParam("usuario") String noUsua) {

		ErrorDto errorDto;

		try {

			logoutControlador.cerrarConexion(noUsua, token);
			return Response.status(Response.Status.OK).build();

		} catch (SQLException e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(errorDto)
					.build();

		} catch (Exception e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(errorDto)
					.build();

		}

	}

}
