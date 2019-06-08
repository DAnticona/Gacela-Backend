package com.wollcorp.beans;

public class Persona {
	
	private String coPers;
	private String nuDocu;
	private String noPers;
	private String apPate;
	private String apMate;
	private String sexo;
	
	public Persona() {
		
	}
	
	public Persona(String coPers, String nuDocu, String noPers, String apPate, String apMate, String sexo) {
		this.coPers = coPers;
		this.nuDocu = nuDocu;
		this.noPers = noPers;
		this.apPate = apPate;
		this.apMate = apMate;
		this.sexo = sexo;
	}

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
	


}
