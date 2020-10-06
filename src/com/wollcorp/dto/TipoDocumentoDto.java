package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.beans.TipoDocumento;

public class TipoDocumentoDto {
	
	private List<TipoDocumento> tiposDocumento;

	/**
	 * @return the tiposDocumento
	 */
	public List<TipoDocumento> getTiposDocumento() {
		return tiposDocumento;
	}

	/**
	 * @param tiposDocumento the tiposDocumento to set
	 */
	public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

}
