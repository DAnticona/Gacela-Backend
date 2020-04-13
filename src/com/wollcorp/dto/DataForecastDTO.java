package com.wollcorp.dto;

import java.util.ArrayList;
import java.util.List;

public class DataForecastDTO {
	
	private List<NaveDTOTemp> naves;
	private List<ServicioDTO> servicios;
	
	public DataForecastDTO() {
		naves = new ArrayList<NaveDTOTemp>();
		servicios = new ArrayList<ServicioDTO>();
	}
	
	public List<NaveDTOTemp> getNaves() {
		return naves;
	}
	
	public void setNaves(List<NaveDTOTemp> naves) {
		this.naves = naves;
	}

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	
	
}
