package forTrash;

import com.wollcorp.dto.ErrorDto;

public class UsuarioDTO 
//extends PersonaDTO
{
	
	private String coUsua;
	private String noUsua;
	//private PerfilDTO perfil;
	private ErrorDto error;
	
	public UsuarioDTO() {
		
		super();
		
	}
	
	public String getCoUsua() {
		return coUsua;
	}
	public void setCoUsua(String coUsua) {
		this.coUsua = coUsua;
	}
	public String getNoUsua() {
		return noUsua;
	}
	public void setNoUsua(String noUsua) {
		this.noUsua = noUsua;
	}

	public ErrorDto getError() {
		return error;
	}

	public void setError(ErrorDto error) {
		this.error = error;
	}

//	public PerfilDTO getPerfil() {
//		return perfil;
//	}
//
//	public void setPerfil(PerfilDTO perfil) {
//		this.perfil = perfil;
//	}
	
}
