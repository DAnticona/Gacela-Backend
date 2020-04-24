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

import com.wollcorp.TEMP.NaveDTO;
import com.wollcorp.controladores.NaveControlador;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.NaveRes;

@Path("/nave")
public class NaveService {
	
	NaveControlador naveControlador = new NaveControlador();
	
	@GET
	@Path("/temp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNavesActivas(@HeaderParam("token") String token) {
		
		System.out.println("SERVICIO DE NAVE");
		
		NaveRes naveRes = new NaveRes();
		
		try {
			
			if (token != null) {

				naveRes = naveControlador.getNavesActivas(token);

				if (naveRes.getNavesTemp() != null) {

					return Response.status(Response.Status.OK).entity(naveRes).build();

				} else {
					
					naveRes.setError(new ErrorRes());
					naveRes.getError().setMensaje("Error Interno al obtener las naves");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();

				}

			} else {

				naveRes.setError(new ErrorRes());
				naveRes.getError().setMensaje("Requerimiento inválido al obtener las naves");

				return Response.status(Response.Status.BAD_REQUEST).entity(naveRes).build();

			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			naveRes.setError(new ErrorRes());
			naveRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
			
		}
		

		
	}
	
	
	
	
	
	@GET
	@Path("/listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNaves(@HeaderParam("token") String token) {
		
		NaveRes naveRes = new NaveRes();
		
		try {
			
			if (token != null) {

				naveRes = naveControlador.getNaves(token);

				if (naveRes.getNaves() != null) {

					return Response.status(Response.Status.OK).entity(naveRes).build();

				} else {
					
					naveRes.setError(new ErrorRes());
					naveRes.getError().setMensaje("Error Interno al obtener las naves");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();

				}

			} else {

				naveRes.setError(new ErrorRes());
				naveRes.getError().setMensaje("TOKEN INVÁLIDO");

				return Response.status(Response.Status.BAD_REQUEST).entity(naveRes).build();

			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			naveRes.setError(new ErrorRes());
			naveRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
			
		}
		
	}
	
	
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraNave(@HeaderParam("token") String token, NaveDTO nave) {
		
		NaveRes naveRes = new NaveRes();
		
		try {
			
			if (token != null) {

				naveRes = naveControlador.registraNave(token, nave);

				if (naveRes.getNave().getCoNave() != null) {

					return Response.status(Response.Status.OK).entity(naveRes).build();

				} else {
					
					naveRes.setError(new ErrorRes());
					naveRes.getError().setMensaje("Error Interno al obtener las naves");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();

				}

			} else {

				naveRes.setError(new ErrorRes());
				naveRes.getError().setMensaje("TOKEN INVÁLIDO");

				return Response.status(Response.Status.BAD_REQUEST).entity(naveRes).build();

			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			naveRes.setError(new ErrorRes());
			naveRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(naveRes).build();
			
		}
		
	}
	
}
