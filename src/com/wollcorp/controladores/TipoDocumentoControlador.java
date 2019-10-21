package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Menu;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.beans.TipoDocumento;
import com.wollcorp.dao.MenuDaoImpl;
import com.wollcorp.dao.TipoDocumentoDaoImpl;
import com.wollcorp.dto.MenuDTO;
import com.wollcorp.dto.SubMenuDTO;
import com.wollcorp.dto.TipoDocumentoDTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.MenuRes;
import com.wollcorp.restServices.responses.TipoDocumentoRes;

public class TipoDocumentoControlador {
	
	
	public TipoDocumentoRes getTipoDocumento(String token) {

		TipoDocumentoRes tipoDocumentoRes = new TipoDocumentoRes();

		List<TipoDocumento> tidocs;
		
		List<TipoDocumentoDTO> tidocsDTO = new ArrayList<TipoDocumentoDTO>();

		if (Token.tokenValido(token)) {

			tidocs = (new TipoDocumentoDaoImpl()).listarTiposDocumento(token);
			
			for (TipoDocumento td : tidocs) {

				TipoDocumentoDTO tdDTO = new TipoDocumentoDTO();

				tdDTO.setCoTiDocu(td.getCoTiDocu());
				tdDTO.setNoTiDocu(td.getNoTiDocu());
				tdDTO.setAlTiDocu(td.getAlTiDocu());


				tidocsDTO.add(tdDTO);

			}
			

		} else {

			tidocsDTO = null;
		}

		tipoDocumentoRes.setTipoDocumento(tidocsDTO);

		return tipoDocumentoRes;

	}

}
