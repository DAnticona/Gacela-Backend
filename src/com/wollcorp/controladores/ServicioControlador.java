package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.Servicio;
import com.wollcorp.dao.ServicioDao;
import com.wollcorp.dto.ServicioDto;
import com.wollcorp.globales.Token;

public class ServicioControlador {
	
	public ServicioDto listaServicios(String token) throws Exception {
		
		ServicioDto servicioDto = null;
		
		List<Servicio> servicios;
		
		if(Token.tokenValido(token)) {
			
			servicios = (new ServicioDao()).listar(token);
			servicioDto = new ServicioDto();
			servicioDto.setServicios(servicios);
			
		}
		
		return servicioDto;
		
	}

}
