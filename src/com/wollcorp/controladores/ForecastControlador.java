package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Nave;
import com.wollcorp.beans.Puerto;
import com.wollcorp.beans.Servicio;
import com.wollcorp.dao.NaveDaoImpl;
import com.wollcorp.dao.ServicioDaoImpl;
import com.wollcorp.dto.DataForecastDTO;
import com.wollcorp.dto.NaveDTO;
import com.wollcorp.dto.PuertoDTO;
import com.wollcorp.dto.ServicioDTO;
import com.wollcorp.globales.Token;

public class ForecastControlador {
	
	private List<Nave> naves;
	private List<Servicio> servicios;
	private List<Puerto> puertosXServicio;
	
	public DataForecastDTO getDatosForecast(String token) {
		
		DataForecastDTO dataForecastDTO = null;
		NaveDTO naveDTO = null;
		ServicioDTO servicioDTO = null;
		PuertoDTO puertoDTO = null;
		
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
						
						puertosXServicio = obtienePuertosXServicio(servicio.getCoServ(), token);
						
						servicio.setPuertos(puertosXServicio);
						
						servicioDTO = new ServicioDTO();
						servicioDTO.setPuertos(new ArrayList<PuertoDTO>());
						
						for (Puerto puerto:puertosXServicio) {
							
							System.out.println(puerto.getCoPuer());
							
							puertoDTO = new PuertoDTO();
							
							puertoDTO.setCoPuer(puerto.getCoPuer());
							puertoDTO.setNoPuer(puerto.getNoPuer());
							puertoDTO.setCoSol(puerto.getCoSol());
							puertoDTO.setCoIso(puerto.getCoIso());
							
							servicioDTO.getPuertos().add(puertoDTO);
							
						}
						
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
	
	private List<Puerto> obtienePuertosXServicio(String coServ, String token) {
		return (new ServicioDaoImpl().obtienePuertosXServicio(coServ, token));
	}
	
}
