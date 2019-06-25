package com.wollcorp.beans;

import java.time.LocalDateTime;

public class SubMenu extends Menu {
	
	private String coSubMenu;
	private String noSubMenu;
	private String aliasSubMenu;
	private String rutaSubMenu;
	private String usCreaSubMenu;
	private String usModiSubMenu;
	private LocalDateTime feCreaSubMenu;
	private LocalDateTime feModiSubMenu;
	   
	public String getCoSubMenu() {
		return coSubMenu;
	}
	
	public void setCoSubMenu(String coSubMenu) {
		this.coSubMenu = coSubMenu;
	}
	
	public String getNoSubMenu() {
		return noSubMenu;
	}
	
	public void setNoSubMenu(String noSubMenu) {
		this.noSubMenu = noSubMenu;
	}
	
	public String getUsCreaSubMenu() {
		return usCreaSubMenu;
	}
	
	public void setUsCreaSubMenu(String usCreaSubMenu) {
		this.usCreaSubMenu = usCreaSubMenu;
	}
	
	public String getUsModiSubMenu() {
		return usModiSubMenu;
	}
	
	public void setUsModiSubMenu(String usModiSubMenu) {
		this.usModiSubMenu = usModiSubMenu;
	}
	
	public LocalDateTime getFeCreaSubMenu() {
		return feCreaSubMenu;
	}
	
	public void setFeCreaSubMenu(LocalDateTime feCreaSubMenu) {
		this.feCreaSubMenu = feCreaSubMenu;
	}
	
	public LocalDateTime getFeModiSubMenu() {
		return feModiSubMenu;
	}
	
	public void setFeModiSubMenu(LocalDateTime feModiSubMenu) {
		this.feModiSubMenu = feModiSubMenu;
	}

	public String getAliasSubMenu() {
		return aliasSubMenu;
	}

	public void setAliasSubMenu(String aliasSubMenu) {
		this.aliasSubMenu = aliasSubMenu;
	}

	public String getRutaSubMenu() {
		return rutaSubMenu;
	}

	public void setRutaSubMenu(String rutaSubMenu) {
		this.rutaSubMenu = rutaSubMenu;
	}
	
}
