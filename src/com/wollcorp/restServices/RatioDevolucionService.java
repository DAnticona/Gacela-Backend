/**
 * 
 */
package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.TEMP.RatioDevolucionDTO;
import com.wollcorp.controladores.RatioDevolucionControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.RatioDevolucionRes;
import com.wollcorp.globales.Log;

/**
 * @author David Anticona
 * @version 1.0
 *
 */
@Path("/ratio-devolucion")
public class RatioDevolucionService {
	
	RatioDevolucionControlador ratioDevolucionControlador = new RatioDevolucionControlador();
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraRatio(@HeaderParam("token") String token, RatioDevolucionDTO ratio) {

		RatioDevolucionRes ratioDevolucionRes = new RatioDevolucionRes();
		
		try {
			
			if (token != null) {
				
				ratioDevolucionRes = ratioDevolucionControlador.registraRatioDevolucion(token, ratio);

				if (ratioDevolucionRes.getError() == null) {

					return Response.status(Response.Status.OK).entity(ratioDevolucionRes).build();

				} else {

					ratioDevolucionRes.setError(new ErrorDto());
					ratioDevolucionRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();

				}

			} else {

				ratioDevolucionRes.setError(new ErrorDto());
				ratioDevolucionRes.getError().setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(ratioDevolucionRes).build();

			}


		
		} catch (SQLException e) {
	
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
	
			ratioDevolucionRes.setError(new ErrorDto());
			ratioDevolucionRes.getError().setMensaje(e.toString());
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();
	
		} catch (Exception e) {
	
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
	
			ratioDevolucionRes.setError(new ErrorDto());
			ratioDevolucionRes.getError().setMensaje(e.toString());
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();
	
		}

	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRatio(@HeaderParam("token") String token, @QueryParam("codigo") String coFile) {

		RatioDevolucionRes ratioDevolucionRes = new RatioDevolucionRes();

		try {

			if (token != null) {

				ratioDevolucionRes = ratioDevolucionControlador.obtieneRatioDevolucion(token);

				if (ratioDevolucionRes.getRatio() != null) {

					return Response.status(Response.Status.OK).entity(ratioDevolucionRes).build();

				} else {

					ratioDevolucionRes.setError(new ErrorDto());
					ratioDevolucionRes.getError().setMensaje("Error Interno al obtener la proyeccion er");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();

				}

			} else {

				ratioDevolucionRes.setError(new ErrorDto());
				ratioDevolucionRes.getError().setMensaje("Token inválido");

				return Response.status(Response.Status.BAD_REQUEST).entity(ratioDevolucionRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			ratioDevolucionRes.setError(new ErrorDto());
			ratioDevolucionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();

		} catch (Exception e) {
	
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
	
			ratioDevolucionRes.setError(new ErrorDto());
			ratioDevolucionRes.getError().setMensaje(e.toString());
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ratioDevolucionRes).build();
	
		}

	}

}
