package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.TipoDocumentoControlador;
import com.wollcorp.controladores.UsuariosControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.TipoDocumentoRes;
import com.wollcorp.restServices.responses.UsuarioRes;

@Path("/tidoc")
public class TiposDocumentoRest {

	TipoDocumentoControlador tipoDocumentoControlador = new TipoDocumentoControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@HeaderParam("token") String token) {

		TipoDocumentoRes tipoDocumentoRes = new TipoDocumentoRes();

		if (token != null) {

			tipoDocumentoRes = tipoDocumentoControlador.getTipoDocumento(token);

			if (tipoDocumentoRes.getTipoDocumento() != null) {

				// System.out.println("Mes: ");
				// System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());

				return Response.status(Response.Status.OK).entity(tipoDocumentoRes).build();

			} else {

				tipoDocumentoRes.setError(new ErrorRes());
				tipoDocumentoRes.getError().setMensaje("Error Interno al obtener los Tipos de documentos");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(tipoDocumentoRes).build();

			}

		} else {

			tipoDocumentoRes.setError(new ErrorRes());
			tipoDocumentoRes.getError().setMensaje("Requerimiento inválido al obtener el usuario");

			return Response.status(Response.Status.BAD_REQUEST).entity(tipoDocumentoRes).build();

		}

	}

}
