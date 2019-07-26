package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.ForecastControlador;
import com.wollcorp.dto.DataForecastDTO;

/**
 * Clase destinada al servicio Forecast
 * @author danticona
 * @version 1.0
 */
@Path("/forecast")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ForecastService {
	
	ForecastControlador forecastControlador = new ForecastControlador();
		
	/**
	 * Procedimiento para el envío de Naves y servicios para el reporte Forecast
	 * @param token
	 * @return Naves y Servicios
	 */
	@GET
	public Response getDataForecast(@HeaderParam("token") String token) {
		
		DataForecastDTO dataForecastDTO = forecastControlador.getDatosForecast(token);
		
		if(dataForecastDTO != null) {
			
			return Response.status(Response.Status.OK).entity(dataForecastDTO).build();
			
		} else {
			
			return Response.status(Response.Status.BAD_REQUEST).entity("Token no valido").build();
			
		}
		
		
		
	}
	
}
