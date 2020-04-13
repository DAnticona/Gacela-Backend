package com.wollcorp.restServices;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.controladores.FileMTC1R999Controlador;
import com.wollcorp.dto.ProyeccionFileCabDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.ProyeccionFileRes;

@Path("/proyeccionequipos")
public class ProyeccionEquipoService {
	
//	FileMTC1R999Controlador proyeccionEquipoControlador = new FileMTC1R999Controlador();
//	
//    @POST
//    @Path("/registrarfile")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response registraProyeccionFile(@HeaderParam("token") String token, ProyeccionFileCabDTO proyeccionFileCab) {
//    	
//    	// System.out.println(proyeccionVentaCab);
//    	
//    	ProyeccionFileRes proyeccionFileRes = new ProyeccionFileRes();
//    	
//    	try {
//    		
//    		
//        	
//        	if(token != null) {
//        		
//        		proyeccionFileRes = proyeccionEquipoControlador.guardarFileSol(token, proyeccionFileCab);
//    			
//    			if(proyeccionFileRes.getProyeccion() != null) {
//    				
//    				return Response.status(Response.Status.OK).entity(proyeccionFileRes).build();
//    				
//    			} else {
//    				
//    				proyeccionFileRes.setError(new ErrorRes());
//    				proyeccionFileRes.getError().setMensaje("Error Interno al registrar el file de proyeccion");
//    				
//    				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionFileRes).build();
//    				
//    			}
//    			
//    		} else {
//    			
//    			proyeccionFileRes.setError(new ErrorRes());
//    			proyeccionFileRes.getError().setMensaje("mal requerimiento ó token inválido al registrar la proyeccion de ventas");
//
//    			return Response.status(Response.Status.BAD_REQUEST).entity(proyeccionFileRes).build();
//    			
//    		}
//
//    		
//
//    		} catch (SQLException e) {
//        	
//    			Log.mensaje = e.getMessage();
//        		Log.exception = e.toString();
//        		Log.codigo = e.getErrorCode();
//        		Log.estado = e.getSQLState();
//        		Log.nombreClase = this.getClass().getName();
//        		Log.registraError();
//        		
//        		proyeccionFileRes.setError(new ErrorRes());
//				proyeccionFileRes.getError().setMensaje(e.toString());
//        		
//        		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(proyeccionFileRes).build();
//        		
//    		}
//        	
//    }
	

}
