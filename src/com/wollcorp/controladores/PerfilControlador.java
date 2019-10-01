package com.wollcorp.controladores;

import com.wollcorp.beans.Perfil;
import com.wollcorp.dao.PerfilDaoImpl;
import com.wollcorp.dto.loginDTO.PerfilDTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.PerfilRes;

public class PerfilControlador {
	
	
	
	public PerfilRes getPerfilxUsuario(String token, String coUsua) {
		PerfilRes perfilRes = new PerfilRes();
		
		Perfil perfil;
		
		PerfilDTO perfilDTO;
		
		if(Token.tokenValido(token)) {
			
			perfilDTO = new PerfilDTO();
			
			perfil = (new PerfilDaoImpl()).obtenerPerfilXUsuario(coUsua, token);
			
			perfilDTO.setCoPerf(perfil.getCoPerf());
			perfilDTO.setNoPerf(perfil.getNoPerf());
			
		} else {
			
			perfilDTO = null;
		}
		
		perfilRes.setPerfil(perfilDTO);
		
		return perfilRes;
		
	}
	
	
	

}
