package com.wollcorp.restServices;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.CalendarioControlador;
import com.wollcorp.dto.CalendarioRes;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.globales.Log;

@Path("/calendario")
public class CalendarioService {
	
	CalendarioControlador calendarioControlador = new CalendarioControlador();
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCalendario(@HeaderParam("token") String token, @QueryParam("fechaIni") String fechaIniStr, @QueryParam("fechaFin") String fechaFinStr) {
		
		// System.out.println(fechaIniStr);
		// System.out.println(fechaFinStr);
		
		// Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		
		CalendarioRes calendarioRes = new CalendarioRes();
		
		try {
			
			Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniStr);
			Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinStr);
		
			if (token != null) {
	
				calendarioRes = calendarioControlador.getCalendario(token, fechaIni, fechaFin);
	
				if (calendarioRes.getCalendario() != null) {
	
					return Response.status(Response.Status.OK).entity(calendarioRes).build();
	
				} else {
					
					calendarioRes.setError(new ErrorDto());
					calendarioRes.getError().setMensaje("Error Interno al obtener el perfil del usuario");
	
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(calendarioRes).build();
	
				}
	
			} else {
	
				calendarioRes.setError(new ErrorDto());
				calendarioRes.getError().setMensaje("Requerimiento inválido al obtener el perfil del usuario");
	
				return Response.status(Response.Status.BAD_REQUEST).entity(calendarioRes).build();
	
			}
		
		} catch (SQLException e) {
	
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
	
			calendarioRes.setError(new ErrorDto());
			calendarioRes.getError().setMensaje(e.toString());
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(calendarioRes).build();
	
		} catch (ParseException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
			e.printStackTrace();
			
			calendarioRes.setError(new ErrorDto());
			calendarioRes.getError().setMensaje(e.toString());
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(calendarioRes).build();
		} catch(Exception e) {
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
			e.printStackTrace();
			
			calendarioRes.setError(new ErrorDto());
			calendarioRes.getError().setMensaje(e.toString());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(calendarioRes).build();
		}
		
	}

}
