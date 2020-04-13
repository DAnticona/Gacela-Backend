package com.wollcorp.dto;

import java.util.List;

public class ProyeccionEquipoExcelDTO {
	
	private ProyeccionEquipoCabDTO proyEquipo;
	private ProyeccionVentaCabDTO proyVenta;
	// private List<RpoPlanDTO> rpoPlanes;
	
	public ProyeccionEquipoCabDTO getProyEquipo() {
		return proyEquipo;
	}
	public void setProyEquipo(ProyeccionEquipoCabDTO proyEquipo) {
		this.proyEquipo = proyEquipo;
	}
	public ProyeccionVentaCabDTO getProyVenta() {
		return proyVenta;
	}
	public void setProyVenta(ProyeccionVentaCabDTO proyVenta) {
		this.proyVenta = proyVenta;
	}

}
