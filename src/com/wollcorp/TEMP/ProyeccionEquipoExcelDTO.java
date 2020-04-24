package com.wollcorp.TEMP;

public class ProyeccionEquipoExcelDTO {
	
	private ProyeccionEquipoCabDTO proyEquipo;
	private ProyeccionEquipoCabDTO proyVenta;
	// private List<RpoPlanDTO> rpoPlanes;
	
	public ProyeccionEquipoCabDTO getProyEquipo() {
		return proyEquipo;
	}
	public void setProyEquipo(ProyeccionEquipoCabDTO proyEquipo) {
		this.proyEquipo = proyEquipo;
	}
	public ProyeccionEquipoCabDTO getProyVenta() {
		return this.proyVenta;
	}
	public void setProyVenta(ProyeccionEquipoCabDTO proyVenta) {
		this.proyVenta = proyVenta;
	}

}
