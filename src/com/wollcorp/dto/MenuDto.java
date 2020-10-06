package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.beans.Menu;

public class MenuDto {
	
	private List<Menu> menus;

	/**
	 * @return the menus
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
