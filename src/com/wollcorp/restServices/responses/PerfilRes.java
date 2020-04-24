package com.wollcorp.restServices.responses;

import com.wollcorp.TEMP.PerfilDTO;

public class PerfilRes {
	
	private PerfilDTO perfil;
	private ErrorRes error;
	
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
