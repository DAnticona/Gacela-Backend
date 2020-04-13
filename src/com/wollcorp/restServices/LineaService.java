package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.LineaControlador;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.LineaRes;

@Path("/linea")
public class LineaService {
	
	LineaControlador lineaControlador = new LineaControlador();
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLineas(@HeaderParam("token") String token) {
		
		LineaRes lineaRes = new LineaRes();
		
		try {
			
			if (token != null) {

				lineaRes = lineaControlador.obtieneLineas(token);

				if (lineaRes.getLineas() != null) {

					return Response.status(Response.Status.OK).entity(lineaRes).build();

				} else {
					
					lineaRes.setError(new ErrorRes());
					lineaRes.getError().setMensaje("Error Interno al obtener las naves");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(lineaRes).build();

				}

			} else {

				lineaRes.setError(new ErrorRes());
				lineaRes.getError().setMensaje("Requerimiento inválido al obtener las naves");

				return Response.status(Response.Status.BAD_REQUEST).entity(lineaRes).build();

			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			lineaRes.setError(new ErrorRes());
			lineaRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(lineaRes).build();
			
		}
		

		
	}

}
