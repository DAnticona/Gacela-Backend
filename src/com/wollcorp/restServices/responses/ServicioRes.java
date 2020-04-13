package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.dto.ServicioDTO;

public class ServicioRes {
	
	private List<ServicioDTO> servicios;
	private ErrorRes error;
	
	public List<ServicioDTO> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
