package com.wollcorp.dto;

public class SubMenuDTO {
	
	private String idSubMenu;
	private String nombre;
	private String alias;
	private String ruta;
	private String idMenuPadre;
	
	public String getIdSubMenu() {
		return idSubMenu;
	}
	public void setIdSubMenu(String idSubMenu) {
		this.idSubMenu = idSubMenu;
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
	public String getIdMenuPadre() {
		return idMenuPadre;
	}
	public void setIdMenuPadre(String idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
