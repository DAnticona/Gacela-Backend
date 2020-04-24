package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.TEMP.LineaDTO;

public class LineaRes {
	
	private List<LineaDTO> lineas;
	private ErrorRes error;
	
	public List<LineaDTO> getLineas() {
		return lineas;
	}
	public void setLineas(List<LineaDTO> lineas) {
		this.lineas = lineas;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
