package com.wollcorp.restServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.ServicioControlador;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.ServicioRes;

@Path("/servicio")
public class ServicioService {
	
	ServicioControlador servicioControlador = new ServicioControlador();
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServicio(@HeaderParam("token") String token) {
		
		
		ServicioRes servicioRes = new ServicioRes();
		
		if (token != null) {

			servicioRes = servicioControlador.listaServicios(token);

			if (servicioRes.getServicios() != null) {

				return Response.status(Response.Status.OK).entity(servicioRes).build();

			} else {
				
				servicioRes.setError(new ErrorRes());
				servicioRes.getError().setMensaje("Error Interno al obtener los servicios");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(servicioRes).build();

			}

		} else {

			servicioRes.setError(new ErrorRes());
			servicioRes.getError().setMensaje("Requerimiento inválido al obtener los servicios");

			return Response.status(Response.Status.BAD_REQUEST).entity(servicioRes).build();

		}
		
	}

}
