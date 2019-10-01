package com.wollcorp.dto.loginDTO;

import java.util.List;

public class MenuDTO {
	
	private String coMenu;
	private String noMenu;
	private String alMenu;
	private String ruta;
	private List<SubMenuDTO> subMenus;
	
	public String getCoMenu() {
		return coMenu;
	}
	public void setCoMenu(String coMenu) {
		this.coMenu = coMenu;
	}
	public String getNoMenu() {
		return noMenu;
	}
	public void setNoMenu(String noMenu) {
		this.noMenu = noMenu;
	}
	public String getAlMenu() {
		return alMenu;
	}
	public void setAlMenu(String alMenu) {
		this.alMenu = alMenu;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public List<SubMenuDTO> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<SubMenuDTO> subMenus) {
		this.subMenus = subMenus;
	}
	
	

}
