package com.wollcorp.dto;

import java.util.ArrayList;
import java.util.List;

public class DataForecastDTO {
	
	private List<NaveDTO> naves;
	private List<ServicioDTO> servicios;
	
	public DataForecastDTO() {
		naves = new ArrayList<NaveDTO>();
		servicios = new ArrayList<ServicioDTO>();
	}
	
	public List<NaveDTO> getNaves() {
		return naves;
	}
	
	public void setNaves(List<NaveDTO> naves) {
		this.naves = naves;
	}

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	
	
}
