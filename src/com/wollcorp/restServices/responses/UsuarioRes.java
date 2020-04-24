package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.TEMP.TipoDocumentoDTO;
import com.wollcorp.dto.usuarioDTO.UsuarioDTO;

public class UsuarioRes {
	
	private UsuarioDTO usuario;
	private List<TipoDocumentoDTO> tidocs;
	private ErrorRes error;
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}
	public List<TipoDocumentoDTO> getTidocs() {
		return tidocs;
	}
	public void setTidocs(List<TipoDocumentoDTO> tidocs) {
		this.tidocs = tidocs;
	}

}
