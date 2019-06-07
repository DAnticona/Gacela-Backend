package com.wollcorp.beans;

public class Persona {
	
	private String codigo;
	private String nuDocumento;
	private String nombres;
	private String apPaterno;
	private String apMaterno;
	private String sexo;
	
	public Persona() {
		
	}
	
	public Persona(String codigo, String nuDocumento, String nombres, String apPaterno, String apMaterno, String sexo) {
		this.codigo = codigo;
		this.nuDocumento = nuDocumento;
		this.nombres = nombres;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.sexo = sexo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNuDocumento() {
		return nuDocumento;
	}
	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
