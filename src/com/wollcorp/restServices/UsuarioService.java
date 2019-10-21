package com.wollcorp.restServices;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.UsuarioControlador;
import com.wollcorp.dto.usuarioDTO.UsuarioDTO;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.UsuarioRes;

@Path("/usuario")
public class UsuarioService {

	UsuarioControlador usuarioControlador = new UsuarioControlador();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@HeaderParam("token") String token, @QueryParam("user") String noUsua) {

		UsuarioRes usuarioRes = new UsuarioRes();

		if (token != null && noUsua != null) {

			usuarioRes = usuarioControlador.getUsuario(token, noUsua);

			if (usuarioRes.getError() == null) {

				// System.out.println("Mes: ");
				// System.out.println(usuarioDTO.getUsuario().getFeNaci().getMonthValue());

				return Response.status(Response.Status.OK).entity(usuarioRes).build();

			} else {

				usuarioRes.setError(new ErrorRes());
				usuarioRes.getError().setMensaje("Error Interno al obtener el usuario");

				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(usuarioRes).build();

			}

		} else {

			usuarioRes.setError(new ErrorRes());
			usuarioRes.getError().setMensaje("Requerimiento inválido al obtener el usuario");

			return Response.status(Response.Status.BAD_REQUEST).entity(usuarioRes).build();

		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putUsuario(@HeaderParam("token") String token, UsuarioDTO usuario) {

		UsuarioRes usuarioRes = new UsuarioRes();

		if (token != null && usuario != null) {

			usuarioControlador.updateUsuario(token, usuario);

			return Response.status(Response.Status.OK)
					// .entity(usuarioRes)
					.build();

		} else {

			usuarioRes.setError(new ErrorRes());
			usuarioRes.getError().setMensaje("Requerimiento inválido al obtener el usuario");

			return Response.status(Response.Status.BAD_REQUEST).entity(usuarioRes).build();

		}

	}
	
	
	
	@PUT
	@Path("/imagen")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response putImagen(@HeaderParam("token") String token, @QueryParam("user") String noUsua, File file, @Context HttpServletRequest httpServletReq) {
		
		System.out.println(httpServletReq.getContextPath());
		System.out.println(httpServletReq.getScheme());
		System.out.println(httpServletReq.getServerName());
		System.out.println(httpServletReq.getServerPort());
		
		System.out.println(httpServletReq.getRequestURL());

		UsuarioRes usuarioRes = new UsuarioRes();
		//
		
		  if (token != null && file != null) {
		  
			  usuarioControlador.updateImagen(token, noUsua, file);
		  
			  return Response.status(Response.Status.OK) // .entity(usuarioRes)
					  .build();
		  
		  } else {
		  
			  usuarioRes.setError(new ErrorRes()); usuarioRes.getError().
			  setMensaje("Requerimiento inválido al obtener el usuario");
		  
			  return Response.status(Response.Status.BAD_REQUEST)
					  .entity(usuarioRes)
					  .build();
		  
		  }
		 

	}

}
