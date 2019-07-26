package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.Nave;
import com.wollcorp.beans.Servicio;
import com.wollcorp.dao.NaveDaoImpl;
import com.wollcorp.dao.ServicioDaoImpl;
import com.wollcorp.dto.DataForecastDTO;
import com.wollcorp.dto.NaveDTO;
import com.wollcorp.dto.ServicioDTO;
import com.wollcorp.globales.Token;

public class ForecastControlador {
	
	private List<Nave> naves;
	private List<Servicio> servicios;
	
	public DataForecastDTO getDatosForecast(String token) {
		
		DataForecastDTO dataForecastDTO = null;
		NaveDTO naveDTO = null;
		ServicioDTO servicioDTO = null;
		
		if(Token.tokenValido(token)) {
			
			dataForecastDTO = new DataForecastDTO();
			
			naves = obtieneNaves(token);
			
			if(naves != null) {
				
				for(Nave nave:naves) {
					
					naveDTO = new NaveDTO();
					
					naveDTO.setCodigo(nave.getCoNave());
					naveDTO.setLongName(nave.getNoNave());
					naveDTO.setShortName(nave.getAlNave());
					naveDTO.setFgActi(nave.getFgActi());
					naveDTO.setServicio(nave.getServicio().getCoServ());
					
					dataForecastDTO.getNaves().add(naveDTO);
				}
				
				servicios = obtieneServicios(token);
				
				if(servicios != null) {
					
					for(Servicio servicio:servicios) {
						
						servicioDTO = new ServicioDTO();
						
						servicioDTO.setCodigo(servicio.getCoServ());
						servicioDTO.setNombre(servicio.getNoServ());
						
						dataForecastDTO.getServicios().add(servicioDTO);
						
					}
					
				}
				
			}
			
		} else {
			
			dataForecastDTO = null;
			
		}
		
		return dataForecastDTO;
		
	}
	
	private List<Nave> obtieneNaves(String token) {
		
		return (new NaveDaoImpl()).listar(token);
		
	}
	
	
	private List<Servicio> obtieneServicios(String token){
		
		return (new ServicioDaoImpl()).listar(token);
		
	}
	
}
