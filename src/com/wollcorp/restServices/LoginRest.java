package com.wollcorp.restServices;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.LoginControlador;
import com.wollcorp.dto.ConexionDto;
import com.wollcorp.dto.ErrorDto;

/**
 * Servicio REST para el login del sistema, envía los datos al controlador
 * LoginControlador.java.
 * 
 * @author David Anticona - danticona@wollcorp.com
 * <ul>
 * 	<li><b>Created:</b> 17/10/2019</li>
 * 	<li><b>Modified:</b> 27/04/2020</li>
 * </ul>
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginRest {

	LoginControlador loginControlador = new LoginControlador();

	/**
	 * Método GET del servicio REST para el login
	 * @param auth Es una cadena encriptada que contiene el usuario y el password
	 * @return un objeto Response con el objeto de conexion o el objeto error.
	 */
	@GET
	public Response servicioLogin(@HeaderParam("authorization") String auth) {

		ConexionDto conexionDto;
		ErrorDto errorDto;

		try {
			if (auth != null) {

				conexionDto = loginControlador.validarLogin(auth);
				return Response
						.status(Response.Status.OK)
						.entity(conexionDto)
						.build();

			} else {
				return Response
						.status(Response.Status.BAD_REQUEST)
						.build();
			}
		} catch (SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(errorDto)
					.build();

		} catch (ClassNotFoundException | IOException e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(errorDto)
					.build();

		}
	}

}
