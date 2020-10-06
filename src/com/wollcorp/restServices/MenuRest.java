package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.MenuControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.MenuDto;

@Path("/menu")
public class MenuRest {

	MenuControlador menuControlador = new MenuControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerfilxUsuario(@HeaderParam("token") String token, @QueryParam("perfil") String coPerf) {

		ErrorDto errorDto;
		
		try {

		if (token != null && coPerf != null) {

			MenuDto menuDto = menuControlador.obtenerMenusXPerfil(token, coPerf);

			if (menuDto.getMenus() != null) {
				return Response.status(Response.Status.OK).entity(menuDto).build();
			} else {
				throw new Exception("Error interno");
			}

		} else {
			throw new Exception("Token o parámetros inválidos");
		}
		} catch(SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		} catch(Exception e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}

	}

}
