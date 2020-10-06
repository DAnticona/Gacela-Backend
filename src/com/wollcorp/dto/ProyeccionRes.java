package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.TEMP.ProyeccionEquipoCabDTO;
import com.wollcorp.TEMP.ProyeccionGeneradaDTO;
import com.wollcorp.TEMP.ProyeccionVentaCabDTO;
import com.wollcorp.TEMP.RatioDevolucionDTOTEMP;

public class ProyeccionRes {
	
	private List<ProyeccionGeneradaDTO> proyeccionGenerada;
	private List<ProyeccionVentaCabDTO> listaProyeccionesVenta;
	private List<ProyeccionEquipoCabDTO> listaProyeccionesEquipos;
	private ProyeccionVentaCabDTO proyeccionVenta;
	private ProyeccionEquipoCabDTO proyeccionEquipo;
	private RatioDevolucionDTOTEMP ratio;
	private String ExcelName;
	private ErrorDto error;
	
	public List<ProyeccionGeneradaDTO> getProyeccionGenerada() {
		return proyeccionGenerada;
	}
	public void setProyeccionGenerada(List<ProyeccionGeneradaDTO> proyeccionGenerada) {
		this.proyeccionGenerada = proyeccionGenerada;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}
	public ProyeccionEquipoCabDTO getProyeccionEquipo() {
		return proyeccionEquipo;
	}
	public void setProyeccionEquipo(ProyeccionEquipoCabDTO proyeccionEquipo) {
		this.proyeccionEquipo = proyeccionEquipo;
	}
	public String getExcelName() {
		return ExcelName;
	}
	public void setExcelName(String excelName) {
		ExcelName = excelName;
	}
	public RatioDevolucionDTOTEMP getRatio() {
		return ratio;
	}
	public void setRatio(RatioDevolucionDTOTEMP ratio) {
		this.ratio = ratio;
	}
	public List<ProyeccionVentaCabDTO> getListaProyeccionesVenta() {
		return this.listaProyeccionesVenta;
	}
	public void setListaProyeccionesVenta(List<ProyeccionVentaCabDTO> listaProyeccionesVenta) {
		this.listaProyeccionesVenta = listaProyeccionesVenta;
	}
	public List<ProyeccionEquipoCabDTO> getListaProyeccionesEquipos() {
		return this.listaProyeccionesEquipos;
	}
	public void setListaProyeccionesEquipos(List<ProyeccionEquipoCabDTO> listaProyeccionesEquipos) {
		this.listaProyeccionesEquipos = listaProyeccionesEquipos;
	}
	public ProyeccionVentaCabDTO getProyeccionVenta() {
		return this.proyeccionVenta;
	}
	public void setProyeccionVenta(ProyeccionVentaCabDTO proyeccionVenta) {
		this.proyeccionVenta = proyeccionVenta;
	}

}
