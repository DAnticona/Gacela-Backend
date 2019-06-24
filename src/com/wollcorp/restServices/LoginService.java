package com.wollcorp.restServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wollcorp.beans.Login;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.beans.Usuario;
import com.wollcorp.controladores.LoginControlador;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
	
	LoginControlador loginControlador = new LoginControlador();
	
	@POST
	public Response servicioLogin(Login login) {
		
		List<Object> responseList = new ArrayList<Object>();
		Usuario usuarioConectado = null;
		Perfil perfilUsuario = null;
		List<SubMenu> subMenus = new ArrayList<SubMenu>();
		
		if(loginControlador.estaConectado(login)) {
			
			//OBTIENE EL USUARIO REGISTRADO EN LA BD - SOLO DEBE EXISTIR 1
			usuarioConectado = loginControlador.obtenerUsuarioConectado(login);
			
			if(usuarioConectado != null) {//ENCUENTRA 1 USUARIO
				
				//ENVIA LA RESPUESTA
				return Response.status(Response.Status.OK)
						//.header("Access-Control-Allow-Origin", "*")
					    //.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					    //.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
						//.entity((Perfil)Globales.variablesGlobales.get("perfil")).build();
						.entity(usuarioConectado).build();
				
				
			} else {
				
				return Response.status(Response.Status.BAD_REQUEST)
						//.header("Access-Control-Allow-Origin", "*")
					    //.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					    //.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
						.entity((Log)Globales.variablesGlobales.get("log")).build();
				
			}
			
		} else {
			
			return Response.status(Response.Status.UNAUTHORIZED)
					//.header("Access-Control-Allow-Origin", "*")
				    //.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				    //.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
					.entity((Log)Globales.variablesGlobales.get("log")).build();
			
		}
	}

}
