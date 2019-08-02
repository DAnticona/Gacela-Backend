package com.wollcorp.restServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.wollcorp.controladores.ForecastControlador;
import com.wollcorp.dto.DataForecastDTO;

/**
 * Clase destinada al servicio Forecast
 * @author danticona
 * @version 1.0
 */
@Path("/forecast")
@Produces(MediaType.APPLICATION_JSON)
public class ForecastService {
	
	ForecastControlador forecastControlador = new ForecastControlador();
		
	/**
	 * Procedimiento para el envío de Naves y servicios para el reporte Forecast
	 * @param token
	 * @return Naves y Servicios
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getDataForecast(@HeaderParam("token") String token) {
		
		DataForecastDTO dataForecastDTO = forecastControlador.getDatosForecast(token);
		
		if(dataForecastDTO != null) {
			
			return Response.status(Response.Status.OK).entity(dataForecastDTO).build();
			
		} else {
			
			return Response.status(Response.Status.BAD_REQUEST).entity("Token no valido").build();
			
		}
		
		
		
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFileForecast(@HeaderParam("token") String token,
			File file) {
		
		System.out.println(file.toString());

	    return Response.status(200).entity("Recibido").build();
		
	}
	
}
