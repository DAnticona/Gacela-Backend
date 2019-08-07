package com.wollcorp.dto;

import java.util.List;

public class ServicioDTO {
	private String codigo;
	private String nombre;
	private List<PuertoDTO> puertos;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<PuertoDTO> getPuertos() {
		return puertos;
	}
	public void setPuertos(List<PuertoDTO> puertos) {
		this.puertos = puertos;
	}
}
