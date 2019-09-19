package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.Respuesta;
//import com.wollcorp.beans.forecast.Linea;
import com.wollcorp.controladores.ForecastControlador;
import com.wollcorp.dto.DataForecastDTO;
import com.wollcorp.globales.Log;

/**
 * Clase destinada al servicio Forecast
 * @author danticona
 * @version 1.0
 */
@Path("/forecast")
public class ForecastService {
	
	ForecastControlador forecastControlador = new ForecastControlador();
	String fileName = null;
		
	/**
	 * Procedimiento para el envío de Naves y servicios para el reporte Forecast
	 * @param token
	 * @return Naves y Servicios
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataForecast(@HeaderParam("token") String token) {
		
		// System.out.println("Hola Forecast");
		
		DataForecastDTO dataForecastDTO = forecastControlador.getDatosForecast(token);
		
		if(dataForecastDTO != null) {
			
			return Response.status(Response.Status.OK).entity(dataForecastDTO).build();
			
		} else {
			
			return Response.status(Response.Status.BAD_REQUEST).entity("Token no valido").build();
			
		}
		
		
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postFileForecast(@HeaderParam("token") String token, ForecastCab forecastCab) {
		
		Respuesta res = new Respuesta();
		
		fileName = forecastControlador.procesaDataFile(token, forecastCab);
		
		if (Log.estado.equals("OK")) {
			
			res.setMensaje(fileName);
			return Response.status(Response.Status.OK).entity(res).build();
			
		} else {
			
			res.setEstado(Log.estado);
			res.setMensaje(Log.mensaje);
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
		
	}
	
}
