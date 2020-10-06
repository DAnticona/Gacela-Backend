package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.controladores.ForecastControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.ForecastDto;

/**
 * Clase destinada al servicio Forecast
 * 
 * @author danticona
 * @version 1.0
 */
@Path("/forecast")
public class ForecastRest {

	ForecastControlador forecastControlador = new ForecastControlador();
	String fileName = null;

	/**
	 * Procedimiento para el envío de Naves y servicios para el reporte Forecast
	 * 
	 * @param token
	 * @return Naves y Servicios
	 */
//	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getDataForecast(@HeaderParam("token") String token) {
//
//		// System.out.println("Hola Forecast");
//
//		try {
//
//			DataForecastDTO dataForecastDTO = forecastControlador.getDatosForecast(token);
//
//			if (dataForecastDTO != null) {
//
//				return Response.status(Response.Status.OK).entity(dataForecastDTO).build();
//
//			} else {
//
//				return Response.status(Response.Status.BAD_REQUEST).entity("Token no valido").build();
//
//			}
//
//		} catch (SQLException e) {
//
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.codigo = e.getErrorCode();
//			Log.estado = e.getSQLState();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//		} catch (Exception e) {
//
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//		}
//
//	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response generarReporteForecast(@HeaderParam("token") String token, ForecastCab forecastCab) {
		
		ErrorDto errorDto = null;

		try {
			// RespuestaDTO res = new RespuestaDTO();
			ForecastDto forecastDto = new ForecastDto();

			fileName = forecastControlador.generarReporteForecast(token, forecastCab);

//			if (Log.estado.equals("OK")) {
//
//				forecastRes.setFileName(fileName);
//				return Response.status(Response.Status.OK).entity(forecastRes).build();
//
//			} else {
//
//				forecastRes.setError(new ErrorDto());
//
//				forecastRes.getError().setEstado(Log.estado);
//				forecastRes.getError().setMensaje(Log.mensaje);
//
//				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(forecastRes).build();
//			}
			
			if (token != null) {

				forecastDto.setFileName(fileName);
				return Response.status(Response.Status.OK).entity(forecastDto).build();

			} else {
				
				throw new Exception("Token inválido");
			}
		} catch (SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		} catch (Exception e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		}

	}

}
