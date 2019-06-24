package com.wollcorp.beans;

import java.util.Date;
import java.util.List;

public class Usuario extends Persona{
	
	private String coUsua;
	private String noUsua;
	private Date feUltSes;
	private String usCreaUsua;
	private String usModiUsua;
	private Date feCreaUsua;
	private Date feModiUsua;
	private Perfil perfil;
	private List<SubMenu> subMenus;

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
	public Date getFeUltSes() {
		return feUltSes;
	}
	public void setFeUltSes(Date feUltSes) {
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
	public Date getFeCreaUsua() {
		return feCreaUsua;
	}
	public void setFeCreaUsua(Date feCreaUsua) {
		this.feCreaUsua = feCreaUsua;
	}
	public Date getFeModiUsua() {
		return feModiUsua;
	}
	public void setFeModiUsua(Date feModiUsua) {
		this.feModiUsua = feModiUsua;
	}
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

}
