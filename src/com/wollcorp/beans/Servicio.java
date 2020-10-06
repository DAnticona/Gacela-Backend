package com.wollcorp.beans;

import java.util.List;

public class Servicio {
	
	private String coServ;
	private String noServ;
	private String alServ;
	private String fgActi;
	private String fgFarEast;
	private String usCrea;
	private String usModi;
	private Long feCrea;
	private Long feModi;
	private List<Puerto> puertos;
	/**
	 * @return the coServ
	 */
	public String getCoServ() {
		return this.coServ;
	}
	/**
	 * @param coServ the coServ to set
	 */
	public void setCoServ(String coServ) {
		this.coServ = coServ;
	}
	/**
	 * @return the noServ
	 */
	public String getNoServ() {
		return this.noServ;
	}
	/**
	 * @param noServ the noServ to set
	 */
	public void setNoServ(String noServ) {
		this.noServ = noServ;
	}
	/**
	 * @return the alServ
	 */
	public String getAlServ() {
		return this.alServ;
	}
	/**
	 * @param alServ the alServ to set
	 */
	public void setAlServ(String alServ) {
		this.alServ = alServ;
	}
	/**
	 * @return the fgActi
	 */
	public String getFgActi() {
		return this.fgActi;
	}
	/**
	 * @param fgActi the fgActi to set
	 */
	public void setFgActi(String fgActi) {
		this.fgActi = fgActi;
	}
	/**
	 * @return the fgFarEast
	 */
	public String getFgFarEast() {
		return this.fgFarEast;
	}
	/**
	 * @param fgFarEast the fgFarEast to set
	 */
	public void setFgFarEast(String fgFarEast) {
		this.fgFarEast = fgFarEast;
	}
	/**
	 * @return the usCrea
	 */
	public String getUsCrea() {
		return this.usCrea;
	}
	/**
	 * @param usCrea the usCrea to set
	 */
	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}
	/**
	 * @return the usModi
	 */
	public String getUsModi() {
		return this.usModi;
	}
	/**
	 * @param usModi the usModi to set
	 */
	public void setUsModi(String usModi) {
		this.usModi = usModi;
	}
	/**
	 * @return the feCrea
	 */
	public Long getFeCrea() {
		return this.feCrea;
	}
	/**
	 * @param feCrea the feCrea to set
	 */
	public void setFeCrea(Long feCrea) {
		this.feCrea = feCrea;
	}
	/**
	 * @return the feModi
	 */
	public Long getFeModi() {
		return this.feModi;
	}
	/**
	 * @param feModi the feModi to set
	 */
	public void setFeModi(Long feModi) {
		this.feModi = feModi;
	}
	/**
	 * @return the puertos
	 */
	public List<Puerto> getPuertos() {
		return this.puertos;
	}
	/**
	 * @param puertos the puertos to set
	 */
	public void setPuertos(List<Puerto> puertos) {
		this.puertos = puertos;
	}
	@Override
	public String toString() {
		return "Servicio [coServ=" + this.coServ + ", noServ=" + this.noServ + ", alServ=" + this.alServ + ", fgActi="
				+ this.fgActi + ", fgFarEast=" + this.fgFarEast + ", usCrea=" + this.usCrea + ", usModi=" + this.usModi
				+ ", feCrea=" + this.feCrea + ", feModi=" + this.feModi + "]";
	}

}
