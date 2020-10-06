package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.beans.Servicio;

public class ServicioDto {
	
	private List<Servicio> servicios;

	/**
	 * @return the servicios
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

}
