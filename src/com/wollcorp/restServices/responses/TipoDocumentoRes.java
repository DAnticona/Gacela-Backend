package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.dto.TipoDocumentoDTO;

public class TipoDocumentoRes {
	
	private List<TipoDocumentoDTO> tipoDocumento;
	private ErrorRes error;
	
	public List<TipoDocumentoDTO> getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(List<TipoDocumentoDTO> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
