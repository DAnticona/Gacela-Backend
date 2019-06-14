package com.wollcorp.beans;

public class SubMenu extends Menu {
	
	private String subMenu;
	private String ruta;
	private String noSubMenu;
	
	public SubMenu() {
		
		super();
	
	}

	public String getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNoSubMenu() {
		return noSubMenu;
	}

	public void setNoSubMenu(String noSubMenu) {
		this.noSubMenu = noSubMenu;
	}

}
