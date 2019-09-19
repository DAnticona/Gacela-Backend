package com.wollcorp.dto;

import java.util.List;

public class MenuDTO {
	
	private String idMenu;
	private String nombre;
	private String alias;
	private String ruta;
	private List<SubMenuDTO> subMenus;
	
	public String getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
