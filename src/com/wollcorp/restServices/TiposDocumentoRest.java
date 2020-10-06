package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.TipoDocumentoControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.TipoDocumentoDto;

@Path("/tidoc")
public class TiposDocumentoRest {

	TipoDocumentoControlador tipoDocumentoControlador = new TipoDocumentoControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@HeaderParam("token") String token) {

		ErrorDto errorDto;
		try {

		if (token != null) {

			TipoDocumentoDto tipoDocumentoDto = tipoDocumentoControlador.listarTiposDocumento(token);

			if (tipoDocumentoDto.getTiposDocumento() != null) {

				return Response.status(Response.Status.OK).entity(tipoDocumentoDto).build();

			} else {
				throw new Exception("Error interno");
			}
		} else {
			throw new Exception("Token inválido");
		}
		} catch(SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			
		} catch(Exception e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			
		}

	}

}
