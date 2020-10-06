package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.TEMP.ProyeccionFileCabDTO;

public class ProyeccionFileRes {
	
	private List<ProyeccionFileCabDTO> proyecciones;
	private ProyeccionFileCabDTO proyeccion;
	private ErrorDto error;
	
	public ProyeccionFileCabDTO getProyeccion() {
		return proyeccion;
	}
	public void setProyeccion(ProyeccionFileCabDTO proyeccion) {
		this.proyeccion = proyeccion;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}
	public List<ProyeccionFileCabDTO> getProyecciones() {
		return proyecciones;
	}
	public void setProyecciones(List<ProyeccionFileCabDTO> proyecciones) {
		this.proyecciones = proyecciones;
	}
	

}
