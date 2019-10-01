package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.dto.loginDTO.UsuarioDTO;
import com.wollcorp.restServices.responses.ErrorRes;

public class MiPerfilDTO {
	
	private UsuarioDTO usuario;
	private ErrorRes error;
	private List<TipoDocumentoDTO> tiposDocumento;
	
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
	public List<TipoDocumentoDTO> getTiposDocumento() {
		return tiposDocumento;
	}
	public void setTiposDocumento(List<TipoDocumentoDTO> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

}
