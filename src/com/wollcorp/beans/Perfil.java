package com.wollcorp.beans;

import java.time.LocalDateTime;
import java.util.List;

public class Perfil {
	
	private String coPerf;
	private String noPerf;
	private List<Menu> menus;
	private String usCreaPerf;
	private String usModiPerf;
	private LocalDateTime feCreaPerf;
	private LocalDateTime feModiPerf;
	
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
	public String getUsCreaPerf() {
		return usCreaPerf;
	}
	public void setUsCreaPerf(String usCreaPerf) {
		this.usCreaPerf = usCreaPerf;
	}
	public String getUsModiPerf() {
		return usModiPerf;
	}
	public void setUsModiPerf(String usModiPerf) {
		this.usModiPerf = usModiPerf;
	}
	public LocalDateTime getFeCreaPerf() {
		return feCreaPerf;
	}
	public void setFeCreaPerf(LocalDateTime feCreaPerf) {
		this.feCreaPerf = feCreaPerf;
	}
	public LocalDateTime getFeModiPerf() {
		return feModiPerf;
	}
	public void setFeModiPerf(LocalDateTime feModiPerf) {
		this.feModiPerf = feModiPerf;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
