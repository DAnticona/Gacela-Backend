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

import com.wollcorp.controladores.FileMTC1R999Controlador;
import com.wollcorp.dto.FileCabMTC1R999DTO;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.FileMTC1R999Res;

@Path("/mtc1r999")
public class FileMTC1R999Service {

	FileMTC1R999Controlador fileMTC1R999Controlador = new FileMTC1R999Controlador();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registraProyeccionFile(@HeaderParam("token") String token, FileCabMTC1R999DTO fileCab) {

		FileMTC1R999Res fileRes = new FileMTC1R999Res();

		try {

			if (token != null) {

				fileRes = fileMTC1R999Controlador.guardarFile(token, fileCab);

				if (fileRes.getFileCab() != null) {

					return Response.status(Response.Status.OK).entity(fileRes).build();

				} else {

					fileRes.setError(new ErrorRes());
					fileRes.getError().setMensaje("Error Interno al registrar el file de proyeccion");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

				}

			} else {

				fileRes.setError(new ErrorRes());
				fileRes.getError()
						.setMensaje("mal requerimiento ó token inválido al registrar la proyeccion de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(fileRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			fileRes.setError(new ErrorRes());
			fileRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

		}

	}

	@GET
	@Path("/files")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFiles(@HeaderParam("token") String token) {

		FileMTC1R999Res fileRes = new FileMTC1R999Res();

		try {

			if (token != null) {

				fileRes = fileMTC1R999Controlador.listarFilesCab(token);

				if (fileRes.getFilesCab() != null) {

					return Response.status(Response.Status.OK).entity(fileRes).build();

				} else {

					fileRes.setError(new ErrorRes());
					fileRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

				}

			} else {

				fileRes.setError(new ErrorRes());
				fileRes.getError()
						.setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(fileRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			fileRes.setError(new ErrorRes());
			fileRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

		}

	}

	@GET
	@Path("/file")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFile(@HeaderParam("token") String token, @QueryParam("codigo") String coFile) {

		// System.out.println("coFile");

		FileMTC1R999Res fileRes = new FileMTC1R999Res();
		
		try {
			
			if (token != null) {

				fileRes = fileMTC1R999Controlador.getFile(token, coFile);

				if (fileRes.getFileCab() != null) {

					return Response.status(Response.Status.OK).entity(fileRes).build();

				} else {

					fileRes.setError(new ErrorRes());
					fileRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

				}

			} else {

				fileRes.setError(new ErrorRes());
				fileRes.getError().setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(fileRes).build();

			}
			
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			fileRes.setError(new ErrorRes());
			fileRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

		}

	}
	
	
	
	
	
	
	@GET
	@Path("/file-activo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFileActivo(@HeaderParam("token") String token) {

		FileMTC1R999Res fileRes = new FileMTC1R999Res();
		
		try {
			
			if (token != null) {

				fileRes = fileMTC1R999Controlador.getFileActivo(token);

				if (fileRes.getFileCab() != null) {

					return Response.status(Response.Status.OK).entity(fileRes).build();

				} else {

					fileRes.setError(new ErrorRes());
					fileRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

				}

			} else {

				fileRes.setError(new ErrorRes());
				fileRes.getError().setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(fileRes).build();

			}
			
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			fileRes.setError(new ErrorRes());
			fileRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(fileRes).build();

		}

	}

}
