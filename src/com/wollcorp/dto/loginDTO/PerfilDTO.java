package com.wollcorp.dto.loginDTO;

import java.util.List;

public class PerfilDTO {
	
	private String coPerf;
	private String noPerf;
	private List<MenuDTO> menus;
	
	public String getCoPerf() {
		return coPerf;
	}
	public void setCoPerf(String coPerf) {
		this.coPerf = coPerf;
	}
	public String getNoPerf() {
		return noPerf;
	}
	public void setNoPerf(String noPerf) {
		this.noPerf = noPerf;
	}
	public List<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}

}
