package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.PasswordControlador;
import com.wollcorp.dto.ErrorDto;

@Path("/password")
public class PasswordRest {

	PasswordControlador passwordControlador = new PasswordControlador();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarPassword(@HeaderParam("token") String token, @HeaderParam("authorization") String auth,
			@HeaderParam("newPassword") String newPas) {

		ErrorDto errorDto;
		try {
			if (token != null && auth != null && newPas != null) {

				passwordControlador.cambiaPassword(token, auth, newPas);

				return Response.status(Response.Status.OK).build();

			} else {
				throw new Exception("token o datos inválidos");
			}

		} catch (SQLException e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
		} catch (Exception e) {
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
		}

	}

}
