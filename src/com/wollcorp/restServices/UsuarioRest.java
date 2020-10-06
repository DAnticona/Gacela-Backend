package com.wollcorp.restServices;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.Usuario;
import com.wollcorp.controladores.UsuarioControlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.UsuarioDto;

@Path("/usuario")
public class UsuarioRest {

	UsuarioControlador usuarioControlador = new UsuarioControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerUsuarioPorUsername(
			@HeaderParam("token") String token,
			@QueryParam("user") String noUsua) {

		UsuarioDto usuarioDto;
		ErrorDto errorDto;
		try {

			if (token != null && noUsua != null) {

				Usuario usuario = new Usuario(noUsua);
				
				usuarioDto = usuarioControlador.obtenerUsuarioPorUsername(token, usuario);

				if (usuarioDto != null) {

					return Response.status(Response.Status.OK).entity(usuarioDto).build();

				} else {
					throw new Exception("Error interno comuníquese con el administrador del sistema");
				}

			} else {
				throw new Exception("token o username inválido");
			}
		} catch(SQLException e) {
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		} catch (Exception e) {
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarUsuario(@HeaderParam("token") String token, Usuario usuario) {

		ErrorDto errorDto;
		try {
			if (token != null && usuario != null) {
				usuarioControlador.actualizarUsuario(token, usuario);

				return Response.status(Response.Status.OK).build();

			} else {

				throw new Exception("Token o usuario inválido");

			}
		} catch (SQLException e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			errorDto.setEstado(e.getSQLState());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		} catch (Exception e) {
			
			errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}

	}

	@PUT
	@Path("/imagen")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response putImagen(
			@HeaderParam("token") String token,
			@QueryParam("user") String noUsua, File file,
			@Context HttpServletRequest httpServletReq) {

		try {
			
			if (token != null && file != null) {

				Usuario usuario = new Usuario(noUsua);
				usuarioControlador.updateImagen(token, usuario, file);

				return Response.status(Response.Status.OK).build();

			} else {
				throw new Exception("Token o archivo inválido");

			}
		} catch (Exception e) {
			
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();
			
		}

	}

}
