package com.wollcorp.controladores;

import com.wollcorp.TEMP.PerfilDTO;
import com.wollcorp.beans.Perfil;
import com.wollcorp.dao.PerfilDaoImpl;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.PerfilRes;

public class PerfilControlador {
	
	
	
	public PerfilRes getPerfilxUsuario(String token, String noUsua) {
		PerfilRes perfilRes = new PerfilRes();
		
		Perfil perfil;
		
		PerfilDTO perfilDTO;
		
		if(Token.tokenValido(token)) {
			
			perfilDTO = new PerfilDTO();
			
			perfil = (new PerfilDaoImpl()).obtenerPerfilXUsuario(noUsua, token);
			
			perfilDTO.setCoPerf(perfil.getCoPerf());
			perfilDTO.setNoPerf(perfil.getNoPerf());
			
			perfilRes.setPerfil(perfilDTO);
			
		} else {
			
			perfilDTO = null;
			
			perfilRes.setError(new ErrorRes());
			perfilRes.getError().setMensaje("Token inválido");
			
		}
		
		
		
		return perfilRes;
		
	}
	
	
	

}
