package com.wollcorp.beans;

import java.time.LocalDateTime;

public class Usuario extends Persona{
	
	private String coUsua;
	private String noUsua;
	private LocalDateTime feUltSes;
	private String usCreaUsua;
	private String usModiUsua;
	private LocalDateTime feCreaUsua;
	private LocalDateTime feModiUsua;
	private Perfil perfil;

	public Usuario() {
		
		super();
		
	}
	   
	public String getCoUsua() {
		return coUsua;
	}
	public void setCoUsua(String coUsua) {
		this.coUsua = coUsua;
	}
	public String getNoUsua() {
		return noUsua;
	}
	public void setNoUsua(String noUsua) {
		this.noUsua = noUsua;
	}
	public LocalDateTime getFeUltSes() {
		return feUltSes;
	}
	public void setFeUltSes(LocalDateTime feUltSes) {
		this.feUltSes = feUltSes;
	}
	public String getUsCreaUsua() {
		return usCreaUsua;
	}
	public void setUsCreaUsua(String usCreaUsua) {
		this.usCreaUsua = usCreaUsua;
	}
	public String getUsModiUsua() {
		return usModiUsua;
	}
	public void setUsModiUsua(String usModiUsua) {
		this.usModiUsua = usModiUsua;
	}
	public LocalDateTime getFeCreaUsua() {
		return feCreaUsua;
	}
	public void setFeCreaUsua(LocalDateTime feCreaUsua) {
		this.feCreaUsua = feCreaUsua;
	}
	public LocalDateTime getFeModiUsua() {
		return feModiUsua;
	}
	public void setFeModiUsua(LocalDateTime feModiUsua) {
		this.feModiUsua = feModiUsua;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
