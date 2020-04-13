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

import com.wollcorp.controladores.ProyeccionControlador;
import com.wollcorp.dto.ProyeccionEquipoExcelDTO;
import com.wollcorp.dto.ProyeccionVentaCabDTO;
import com.wollcorp.dto.RatioDevolucionDTOTEMP;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.ProyeccionRes;

@Path("/proyecciones")
public class ProyeccionService {

	ProyeccionControlador proyeccionControlador = new ProyeccionControlador();

	@GET
	@Path("/ventas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProyeccionesVenta(@HeaderParam("token") String token) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		// System.out.println("Proyecciones");

		try {

			if (token != null) {

				proyeccionRes = proyeccionControlador.listarProyeccionesVenta(token);

				if (proyeccionRes.getListaProyeccionesVenta() != null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError()
						.setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();
		}

	}

	@GET
	@Path("/ventas/detalle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProyeccionVenta(@HeaderParam("token") String token, @QueryParam("codigo") String coProyVenta) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		// System.out.println(coProyVenta);

		try {

			if (token != null) {

				proyeccionRes = proyeccionControlador.getProyeccionVenta(token, coProyVenta);

				if (proyeccionRes.getProyeccionVenta() != null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al obtener la proyeccion");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError()
						.setMensaje("mal requerimiento ó token inválido al obtener la proyeccion de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();
		}

	}

	@POST
	@Path("/ventas/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registraProyeccionVenta(@HeaderParam("token") String token, ProyeccionVentaCabDTO proyeccionVentaCab) {
		
//		for(int i = 0; i < proyeccionVentaCab.getDetalles().size(); i++) {
//			System.out.println(proyeccionVentaCab.getDetalles().get(i).getEta());
//		}

		// System.out.println(proyeccionVentaCab);

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		try {

			if (token != null) {

				proyeccionRes = proyeccionControlador.registraProyeccionVenta(token, proyeccionVentaCab);

				if (proyeccionRes.getProyeccionVenta() != null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al registrar la proyeccion");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError()
						.setMensaje("mal requerimiento ó token inválido al registrar la proyeccion de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}
		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

		}

	}


	@GET
	@Path("/ventas/generar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaResumen(@HeaderParam("token") String token, @QueryParam("codigo") String coFile) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		// System.out.println("código:" + coFile);

		try {

			if (token != null) {

				proyeccionRes = proyeccionControlador.generaResumenProyeccion(token, coFile);

				if (proyeccionRes.getProyeccionGenerada() != null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al obtener la proyeccion er");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError().setMensaje("Token inválido");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

		}

	}
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/equipo/ratio/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraRatio(@HeaderParam("token") String token, RatioDevolucionDTOTEMP ratio) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();
		
		try {
			
			if (token != null) {
				
				proyeccionRes = proyeccionControlador.registraRatioDevolucion(token, ratio);

				if (proyeccionRes.getError() == null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al obtener las proyecciones");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError().setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}


		
		} catch (SQLException e) {
	
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
	
			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();
	
		}

	}
	
	@GET
	@Path("/equipo/ratio/obtener")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRatio(@HeaderParam("token") String token, @QueryParam("codigo") String coFile) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		try {

			if (token != null) {

				proyeccionRes = proyeccionControlador.obtieneRatioDevolucion(token);

				if (proyeccionRes.getRatio() != null) {

					return Response.status(Response.Status.OK).entity(proyeccionRes).build();

				} else {

					proyeccionRes.setError(new ErrorRes());
					proyeccionRes.getError().setMensaje("Error Interno al obtener la proyeccion er");

					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

				}

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError().setMensaje("Token inválido");

				return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

			}

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError().setMensaje(e.toString());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

		}

	}

	
	
	
	
	
	
	
	

	@POST
	@Path("/equipo/excel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response generaExcel(@HeaderParam("token") String token, ProyeccionEquipoExcelDTO proyeccion) {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		if (token != null) {

			proyeccionRes.setExcelName(proyeccionControlador.generaExcel(token, proyeccion.getProyEquipo(), proyeccion.getProyVenta()));

			if (proyeccionRes.getExcelName() != null) {

				return Response.status(Response.Status.OK).entity(proyeccionRes).build();

			} else {

				proyeccionRes.setError(new ErrorRes());
				proyeccionRes.getError().setMensaje("Error Interno al obtener las proyecciones");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionRes).build();

			}

		} else {

			proyeccionRes.setError(new ErrorRes());
			proyeccionRes.getError()
					.setMensaje("mal requerimiento ó token inválido al obtener las proyecciones de ventas");

			return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionRes).build();

		}

	}

}
