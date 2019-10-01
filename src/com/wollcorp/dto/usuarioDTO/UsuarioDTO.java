package com.wollcorp.dto.usuarioDTO;

import com.wollcorp.dto.PersonaDTO;

public class UsuarioDTO extends PersonaDTO {
	
	private String noUsua;
	private String noPerf;
	
	public UsuarioDTO() {
		
		super();
		
	}
	
	public String getNoUsua() {
		return noUsua;
	}
	public void setNoUsua(String noUsua) {
		this.noUsua = noUsua;
	}
	public String getNoPerf() {
		return noPerf;
	}

	public void setNoPerf(String noPerf) {
		this.noPerf = noPerf;
	}


}
