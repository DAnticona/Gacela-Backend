package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Puerto;
import com.wollcorp.beans.Servicio;
import com.wollcorp.dao.ServicioDaoImpl;
import com.wollcorp.dto.PuertoDTO;
import com.wollcorp.dto.ServicioDTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ServicioRes;

public class ServicioControlador {
	
	public ServicioRes listaServicios(String token) {
		
		ServicioRes servicioRes = new ServicioRes();
		
		List<Servicio> servicios;
		List<ServicioDTO> serviciosDTO;
		
		
		
		if(Token.tokenValido(token)) {
			
			servicios = (new ServicioDaoImpl()).listar(token);
			
			serviciosDTO = new ArrayList<ServicioDTO>();
			
			for(Servicio s : servicios) {
				
				ServicioDTO sDTO = new ServicioDTO();
				
				sDTO.setCoServ(s.getCoServ());
				sDTO.setNoServ(s.getNoServ());
				sDTO.setFgFarEast(s.getFgFarEast());
				
				sDTO.setPuertos(new ArrayList<PuertoDTO>());
				
				for(Puerto p : s.getPuertos()) {
					
					PuertoDTO pDTO = new PuertoDTO();
					
					pDTO.setCoPuer(p.getCoPuer());
					pDTO.setCoIso(p.getCoIso());
					pDTO.setCoSol(p.getCoSol());
					pDTO.setNoPuer(p.getNoPuer());
					
					sDTO.getPuertos().add(pDTO);
					
				}
				
				serviciosDTO.add(sDTO);
				
				
			}
			
		} else {
			
			serviciosDTO = null;
			
		}
		
		servicioRes.setServicios(serviciosDTO);
		
		return servicioRes;
		
	}

}
