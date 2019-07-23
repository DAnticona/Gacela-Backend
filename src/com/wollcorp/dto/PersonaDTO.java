package com.wollcorp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonaDTO {

	private String coPers;
	private String nuDocu;
	private String noPers;
	private String apPate;
	private String apMate;
	private String sexo;
	private LocalDate feNaci;
	private String usCreaPers;
	private String usModiPers;
	private LocalDateTime feCreaPers;
	private LocalDateTime feModiPers;
	
	public void setCoPers(String coPers) {
		this.coPers = coPers;
	}
	public void setNuDocu(String nuDocu) {
		this.nuDocu = nuDocu;
	}
	public void setNoPers(String noPers) {
		this.noPers = noPers;
	}
	public void setApPate(String apPate) {
		this.apPate = apPate;
	}
	public void setApMate(String apMate) {
		this.apMate = apMate;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setFeNaci(LocalDate feNaci) {
		this.feNaci = feNaci;
	}
	public void setUsCreaPers(String usCreaPers) {
		this.usCreaPers = usCreaPers;
	}
	public void setUsModiPers(String usModiPers) {
		this.usModiPers = usModiPers;
	}
	public void setFeCreaPers(LocalDateTime feCreaPers) {
		this.feCreaPers = feCreaPers;
	}
	public void setFeModiPers(LocalDateTime feModiPers) {
		this.feModiPers = feModiPers;
	}
	
	
	
}
