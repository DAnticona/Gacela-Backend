package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.TEMP.MenuDTO;

public class MenuRes {
	
	private List<MenuDTO> menus;
	private ErrorRes error;
	
	public List<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
