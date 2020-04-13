package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.dto.CalendarioDTO;

public class CalendarioRes {
	
	private List<CalendarioDTO> calendario;
	private ErrorRes error;
	
	public List<CalendarioDTO> getCalendario() {
		return calendario;
	}
	public void setCalendario(List<CalendarioDTO> calendario) {
		this.calendario = calendario;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
