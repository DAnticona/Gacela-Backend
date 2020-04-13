package com.wollcorp.restServices;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.RpoPlanControlador;
import com.wollcorp.dto.RpoPlanDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.RpoPlanRes;

@Path("/rpoplan")
public class RpoPlanService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obtieneRpoPlan(@HeaderParam("token") String token) {
		
		RpoPlanControlador rpoPlanControlador = new RpoPlanControlador();
		
		RpoPlanRes rpoPlanRes = new RpoPlanRes();
		
		try {

			if (token != null) {

				rpoPlanRes = rpoPlanControlador.obtieneRpoPlan(token);

				if (rpoPlanRes.getPlanes() != null) {

					return Response.status(Response.Status.OK).entity(rpoPlanRes).build();

				} else {

					rpoPlanRes.setError(new ErrorRes());
					rpoPlanRes.getError().setMensaje("Error Interno al obtener el plan RPO");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rpoPlanRes).build();

				}

			} else {

				rpoPlanRes.setError(new ErrorRes());
				rpoPlanRes.getError()
						.setMensaje("mal requerimiento ó token inválido al obtener el plan RPO");

				return Response.status(Response.Status.BAD_REQUEST).entity(rpoPlanRes).build();

			}
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			rpoPlanRes.setError(new ErrorRes());
			rpoPlanRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rpoPlanRes).build();

		}
		
	}
	
	
	
	@POST
	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registraRpoPlan(@HeaderParam("token") String token, List<RpoPlanDTO> planes) {
		
		RpoPlanControlador rpoPlanControlador = new RpoPlanControlador();
		
		RpoPlanRes rpoPlanRes = new RpoPlanRes();
		
		try {

			if (token != null) {

				rpoPlanRes = rpoPlanControlador.registraRpoPlan(token, planes);

				if (rpoPlanRes.getError() == null) {

					return Response.status(Response.Status.OK).build();

				} else {

					rpoPlanRes.setError(new ErrorRes());
					rpoPlanRes.getError().setMensaje("Error Interno al registrar la proyeccion");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rpoPlanRes).build();

				}

			} else {

				rpoPlanRes.setError(new ErrorRes());
				rpoPlanRes.getError()
						.setMensaje("mal requerimiento ó token inválido al registrar la proyeccion de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(rpoPlanRes).build();

			}
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			rpoPlanRes.setError(new ErrorRes());
			rpoPlanRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rpoPlanRes).build();

		}
		
	}
	

}
