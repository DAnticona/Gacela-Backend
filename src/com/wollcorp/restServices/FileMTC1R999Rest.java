package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.FileCabMTC1R999;
import com.wollcorp.controladores.FileMTC1R999Controlador;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.FileMTC1R999Dto;

@Path("/mtc1r999")
public class FileMTC1R999Rest {

	FileMTC1R999Controlador fileMTC1R999Controlador = new FileMTC1R999Controlador();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarFile(@HeaderParam("token") String token, FileCabMTC1R999 fileCab) {

		try {

			if (token != null) {

				FileMTC1R999Dto fileDto = fileMTC1R999Controlador.registrarFile(token, fileCab);

				return Response.status(Response.Status.OK).entity(fileDto).build();

			} else {

				throw new Exception("Token inválido");

			}

		} catch (SQLException e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		} catch (Exception e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		}

	}

	@GET
	@Path("/files")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFiles(@HeaderParam("token") String token) {

		try {

			if (token != null) {

				FileMTC1R999Dto fileDto = fileMTC1R999Controlador.listarFilesCab(token);

				return Response.status(Response.Status.OK).entity(fileDto).build();

			} else {

				throw new Exception("Token inválido");

			}

		} catch (SQLException e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		} catch (Exception e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		}

	}

	@GET
	@Path("/file")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFile(@HeaderParam("token") String token, @QueryParam("codigo") String coFile) {

		try {

			if (token != null) {

				FileMTC1R999Dto fileDto = fileMTC1R999Controlador.getFile(token, coFile);

				return Response.status(Response.Status.OK).entity(fileDto).build();

			} else {

				throw new Exception("Token inválido");

			}

		} catch (SQLException e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		} catch (Exception e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		}

	}

	@GET
	@Path("/file-activo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFileActivo(@HeaderParam("token") String token) {

		try {

			if (token != null) {

				FileMTC1R999Dto fileDto = fileMTC1R999Controlador.getFileActivo(token);

				return Response.status(Response.Status.OK).entity(fileDto).build();

			} else {

				throw new Exception("Token inválido");

			}

		} catch (SQLException e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setEstado(e.getSQLState());
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		} catch (Exception e) {

			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensaje(e.getMessage());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDto).build();

		}

	}

}
