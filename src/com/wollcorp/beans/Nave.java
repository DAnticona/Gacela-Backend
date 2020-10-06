package com.wollcorp.beans;

public class Nave {
	
    private String coNave;
	private String noNave;
	private String alNave;
	private String fgActi;
	private String usCrea;
	private String usModi;
	private Long feCrea;
	private Long feModi;
	private Servicio servicio;
	private Linea linea;
	/**
	 * @return the coNave
	 */
	public String getCoNave() {
		return this.coNave;
	}
	/**
	 * @param coNave the coNave to set
	 */
	public void setCoNave(String coNave) {
		this.coNave = coNave;
	}
	/**
	 * @return the noNave
	 */
	public String getNoNave() {
		return this.noNave;
	}
	/**
	 * @param noNave the noNave to set
	 */
	public void setNoNave(String noNave) {
		this.noNave = noNave;
	}
	/**
	 * @return the alNave
	 */
	public String getAlNave() {
		return this.alNave;
	}
	/**
	 * @param alNave the alNave to set
	 */
	public void setAlNave(String alNave) {
		this.alNave = alNave;
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
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return this.servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return the linea
	 */
	public Linea getLinea() {
		return this.linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	@Override
	public String toString() {
		return "Nave [coNave=" + this.coNave + ", noNave=" + this.noNave + ", alNave=" + this.alNave + ", fgActi="
				+ this.fgActi + ", usCrea=" + this.usCrea + ", usModi=" + this.usModi + ", feCrea=" + this.feCrea
				+ ", feModi=" + this.feModi + ", servicio=" + this.servicio + ", linea=" + this.linea + "]";
	}

}
