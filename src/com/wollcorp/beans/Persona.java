package com.wollcorp.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

/***********************************************************************
 * Module:  Persona.java
 * Author:  danticona
 * Purpose: Defines the Class Pers
 ***********************************************************************/

public class Persona {

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
	
	
	public LocalDate getFeNaci() {
		return feNaci;
	}
	
	
	public void setFeNaci(LocalDate feNaci) {
		this.feNaci = feNaci;
	}
	
	
	public String getUsCreaPers() {
		return usCreaPers;
	}
	
	
	public void setUsCreaPers(String usCreaPers) {
		this.usCreaPers = usCreaPers;
	}
	
	
	public String getUsModiPers() {
		return usModiPers;
	}
	
	
	public void setUsModiPers(String usModiPers) {
		this.usModiPers = usModiPers;
	}
	
	
	public LocalDateTime getFeCreaPers() {
		return feCreaPers;
	}
	
	
	public void setFeCreaPers(LocalDateTime feCreaPers) {
		this.feCreaPers = feCreaPers;
	}
	
	
	public LocalDateTime getFeModiPers() {
		return feModiPers;
	}
	
	
	public void setFeModiPers(LocalDateTime feModiPers) {
		this.feModiPers = feModiPers;
	}

}
