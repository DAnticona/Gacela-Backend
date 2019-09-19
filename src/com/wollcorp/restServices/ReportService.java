package com.wollcorp.restServices;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.wollcorp.controladores.conventersExcel.ForecastExcel;

@Path("/report")
public class ReportService {
	
    @GET
    @Path("/{file}")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFile(@HeaderParam("token") String token, @PathParam("file") String fileName) {
    	
    	String TXT_FILE = ForecastExcel.filePath + fileName;
    	
    	System.out.println("Creando: " + TXT_FILE);
 
        File file = new File(TXT_FILE);
 
        ResponseBuilder response = Response.ok((Object) file);
        // response.header("Content-Disposition", "attachment; filename=\"FORECAST1908210050.xlsx\"");
        response.header("Content-Disposition", "attachment;");
        return response.build();
 
    }
    
    
    @DELETE
    @Path("/{file}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFile(@HeaderParam("token") String token, @PathParam("file") String fileName) {
    	
    	   	
    	String TXT_FILE = ForecastExcel.filePath + fileName;
    	
    	System.out.println("Borrando: " + TXT_FILE);
 
        File file = new File(TXT_FILE);
        
        if(file.delete()) {
        	
        	return Response.status(Response.Status.OK).build();
        	
        } else {
        	
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        	
        }
 
    }

}
