package com.wollcorp.beans;

import java.time.LocalDateTime;
import java.util.List;

public class Menu {

	private String coMenu;
	private String noMenu;
	private int lvMenu;
	private String alMenu;
	private String ruta;
	private String usCreaMenu;
	private String usModiMenu;
	private LocalDateTime feCreaMenu;
	private LocalDateTime feModiMenu;
	private String coPadr;
	private int nrOrde;
	private List<SubMenu> subMenus;
	
	public Menu() {
	}
	public Menu(String coMenu) {
		super();
		this.coMenu = coMenu;
	}

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
	public int getLvMenu() {
		return lvMenu;
	}
	public void setLvMenu(int lvMenu) {
		this.lvMenu = lvMenu;
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
	public String getUsCreaMenu() {
		return usCreaMenu;
	}
	public void setUsCreaMenu(String usCreaMenu) {
		this.usCreaMenu = usCreaMenu;
	}
	public String getUsModiMenu() {
		return usModiMenu;
	}
	public void setUsModiMenu(String usModiMenu) {
		this.usModiMenu = usModiMenu;
	}
	public LocalDateTime getFeCreaMenu() {
		return feCreaMenu;
	}
	public void setFeCreaMenu(LocalDateTime feCreaMenu) {
		this.feCreaMenu = feCreaMenu;
	}
	public LocalDateTime getFeModiMenu() {
		return feModiMenu;
	}
	public void setFeModiMenu(LocalDateTime feModiMenu) {
		this.feModiMenu = feModiMenu;
	}
	public String getCoPadr() {
		return coPadr;
	}
	public void setCoPadr(String coPadr) {
		this.coPadr = coPadr;
	}
	public int getNrOrde() {
		return nrOrde;
	}
	public void setNrOrde(int nrOrde) {
		this.nrOrde = nrOrde;
	}
	public List<SubMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}
	@Override
	public String toString() {
		return "Menu [coMenu=" + this.coMenu + ", noMenu=" + this.noMenu + ", lvMenu=" + this.lvMenu + ", alMenu="
				+ this.alMenu + ", ruta=" + this.ruta + ", usCreaMenu=" + this.usCreaMenu + ", usModiMenu="
				+ this.usModiMenu + ", feCreaMenu=" + this.feCreaMenu + ", feModiMenu=" + this.feModiMenu + ", coPadr="
				+ this.coPadr + ", nrOrde=" + this.nrOrde + "]";
	}
}
