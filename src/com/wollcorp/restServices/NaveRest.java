package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.Nave;
import com.wollcorp.controladores.NaveControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.NaveDto;

@Path("/nave")
public class NaveRest {
	
	NaveControlador naveControlador = new NaveControlador();
	
//	@GET
//	@Path("/temp")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getNavesActivas(@HeaderParam("token") String token) {
//		
//		System.out.println("SERVICIO DE NAVE");
//		
//		NaveDto naveRes = new NaveDto();
//		
//		try {
//			
//			if (token != null) {
//
//				naveRes = naveControlador.getNavesActivas(token);
//
//				if (naveRes.getNavesTemp() != null) {
//
//					return Response.status(Response.Status.OK).entity(naveRes).build();
//
//				} else {
//					
//					naveRes.setError(new ErrorDto());
//					naveRes.getError().setMensaje("Error Interno al obtener las naves");
//
//					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
//
//				}
//
//			} else {
//
//				naveRes.setError(new ErrorDto());
//				naveRes.getError().setMensaje("Requerimiento inválido al obtener las naves");
//
//				return Response.status(Response.Status.BAD_REQUEST).entity(naveRes).build();
//
//			}
//			
//		} catch(SQLException e) {
//			
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.codigo = e.getErrorCode();
//			Log.estado = e.getSQLState();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//			naveRes.setError(new ErrorDto());
//			naveRes.getError().setMensaje(e.toString());
//
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
//			
//		} catch(Exception e) {
//			
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//			naveRes.setError(new ErrorDto());
//			naveRes.getError().setMensaje(e.toString());
//
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
//			
//		}
//		
//
//		
//	}
	
	
	
	
	
	@GET
	@Path("/listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNaves(@HeaderParam("token") String token) {
		
		ErrorDto errorDto;
		
		try {
			
			if (token != null) {
				
				NaveDto naveDto = new NaveDto();

				naveDto = naveControlador.obtenerNaves(token);

				return Response.status(Response.Status.OK).entity(naveDto).build();

			} else {

				throw new Exception("Token inválido");

			}
			
		} catch(SQLException e) {

			errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		} catch(Exception e) {

			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}
		
	}
	
	
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraNave(@HeaderParam("token") String token, Nave nave) {
		System.out.println(nave);
		ErrorDto errorDto;
		
		try {
			
			if (token != null) {
				
				NaveDto naveDto = new NaveDto();

				naveDto = naveControlador.registrarNave(token, nave);
				
				return Response.status(Response.Status.OK).entity(naveDto).build();

			} else {

				throw new Exception("Token inválido");

			}
			
		} catch(SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		} catch(Exception e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}
		
	}
	
}
