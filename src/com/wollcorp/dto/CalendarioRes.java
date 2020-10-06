package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.TEMP.CalendarioDTO;

public class CalendarioRes {
	
	private List<CalendarioDTO> calendario;
	private ErrorDto error;
	
	public List<CalendarioDTO> getCalendario() {
		return calendario;
	}
	public void setCalendario(List<CalendarioDTO> calendario) {
		this.calendario = calendario;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
