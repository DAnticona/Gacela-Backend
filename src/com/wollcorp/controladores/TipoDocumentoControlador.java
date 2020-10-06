package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.TipoDocumento;
import com.wollcorp.dao.TipoDocumentoDaoImpl;
import com.wollcorp.dto.TipoDocumentoDto;
import com.wollcorp.globales.Token;

public class TipoDocumentoControlador {
	
	public TipoDocumentoDto listarTiposDocumento(String token) throws Exception {

		TipoDocumentoDto tipoDocumentoDto = new TipoDocumentoDto();

		List<TipoDocumento> tiDocs = null;

		if (Token.tokenValido(token)) {
			tiDocs = (new TipoDocumentoDaoImpl()).listarTiposDocumento(token);
		}

		tipoDocumentoDto.setTiposDocumento(tiDocs);

		return tipoDocumentoDto;

	}

}
