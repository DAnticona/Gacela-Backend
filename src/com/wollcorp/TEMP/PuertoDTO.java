package com.wollcorp.TEMP;

import java.util.List;

import forTrash.ServicioDTO;

public class PuertoDTO {
	
	private String coPuer;
	private String noPuer;
	private String coSol;
	private String coIso;
	private String fgActi;
	private List<ServicioDTO> servicios;
	
	public String getCoPuer() {
		return coPuer;
	}
	public void setCoPuer(String coPuer) {
		this.coPuer = coPuer;
	}
	public String getNoPuer() {
		return noPuer;
	}
	public void setNoPuer(String noPuer) {
		this.noPuer = noPuer;
	}
	public String getCoSol() {
		return coSol;
	}
	public void setCoSol(String coSol) {
		this.coSol = coSol;
	}
	public String getCoIso() {
		return coIso;
	}
	public void setCoIso(String coIso) {
		this.coIso = coIso;
	}
	public String getFgActi() {
		return fgActi;
	}
	public void setFgActi(String fgActi) {
		this.fgActi = fgActi;
	}
	public List<ServicioDTO> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	
}
