package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.ServicioControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.ServicioDto;

@Path("/servicio")
public class ServicioRest {

	ServicioControlador servicioControlador = new ServicioControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServicio(@HeaderParam("token") String token) {

		ErrorDto errorDto;

		try {

			if (token != null) {

				ServicioDto servicioDto = servicioControlador.listaServicios(token);

				if (servicioDto.getServicios() != null) {

					return Response.status(Response.Status.OK).entity(servicioDto).build();

				} else {
					throw new Exception("Error interno");
				}

			} else {
				throw new Exception("Token inválido");
			}
		} catch (SQLException e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

		} catch (Exception e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

		}

	}

}
