package forTrash;

import com.wollcorp.beans.Perfil;
import com.wollcorp.dao.PerfilDao;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.globales.Token;

public class PerfilControlador {
	
	
	
	public PerfilRes getPerfilxUsuario(String token, String noUsua) throws Exception {
		PerfilRes perfilRes = new PerfilRes();
		
		Perfil perfil;
		
		PerfilDTO perfilDTO;
		
		if(Token.tokenValido(token)) {
			
			perfilDTO = new PerfilDTO();
			
			perfil = (new PerfilDao()).obtenerPerfilXUsuario(noUsua, token);
			
			perfilDTO.setCoPerf(perfil.getCoPerf());
			perfilDTO.setNoPerf(perfil.getNoPerf());
			
			perfilRes.setPerfil(perfilDTO);
			
		} else {
			
			perfilDTO = null;
			
			perfilRes.setError(new ErrorDto());
			perfilRes.getError().setMensaje("Token inválido");
			
		}
		
		
		
		return perfilRes;
		
	}
	
	
	

}
