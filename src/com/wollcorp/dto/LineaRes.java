package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.TEMP.LineaDTO;

public class LineaRes {
	
	private List<LineaDTO> lineas;
	private ErrorDto error;
	
	public List<LineaDTO> getLineas() {
		return lineas;
	}
	public void setLineas(List<LineaDTO> lineas) {
		this.lineas = lineas;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
