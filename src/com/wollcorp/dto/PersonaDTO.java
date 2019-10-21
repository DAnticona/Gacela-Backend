package com.wollcorp.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class PersonaDTO {

	private String coPers;
	private String tiDocu;
	private String nuDocu;
	private String noPers;
	private String apPate;
	private String apMate;
	private String sexo;
	private Date feNaci;
	private String email;
	private String rutaImagen;
	
	public String getCoPers() {
		return coPers;
	}
	public void setCoPers(String coPers) {
		this.coPers = coPers;
	}
	public String getNuDocu() {
		return nuDocu;
	}
	public void setNuDocu(String nuDocu) {
		this.nuDocu = nuDocu;
	}
	public String getNoPers() {
		return noPers;
	}
	public void setNoPers(String noPers) {
		this.noPers = noPers;
	}
	public String getApPate() {
		return apPate;
	}
	public void setApPate(String apPate) {
		this.apPate = apPate;
	}
	public String getApMate() {
		return apMate;
	}
	public void setApMate(String apMate) {
		this.apMate = apMate;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFeNaci() {
		return feNaci;
	}
	public void setFeNaci(Date feNaci) {
		this.feNaci = feNaci;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTiDocu() {
		return tiDocu;
	}
	public void setTiDocu(String tiDocu) {
		this.tiDocu = tiDocu;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	
}
