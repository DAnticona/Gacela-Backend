package com.wollcorp.TEMP;

import com.wollcorp.restServices.responses.ErrorRes;

public class UsuarioDTO extends PersonaDTO{
	
	private String coUsua;
	private String noUsua;
	private PerfilDTO perfil;
	private ErrorRes error;
	
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

	public ErrorRes getError() {
		return error;
	}

	public void setError(ErrorRes error) {
		this.error = error;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	
}
