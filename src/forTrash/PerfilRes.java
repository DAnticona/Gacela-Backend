package forTrash;

import com.wollcorp.dto.ErrorDto;

public class PerfilRes {
	
	private PerfilDTO perfil;
	private ErrorDto error;
	
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
