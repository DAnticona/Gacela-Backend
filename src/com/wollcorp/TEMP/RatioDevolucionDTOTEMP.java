package com.wollcorp.TEMP;

import java.util.Date;

public class RatioDevolucionDTOTEMP {
	
	private int ra2Sd;
	private int ra4Sd;
	private int ra4Sh;
    private int nroDiasHabilesMes;
    private int nroDiasHoyARetorno;
    private String fechaRatioStr;
    private String usCrea;
    private String usModi;
    private Date feCrea;
    private Date feModi;
	
	public int getRa2Sd() {
		return ra2Sd;
	}
	public void setRa2Sd(int ra2Sd) {
		this.ra2Sd = ra2Sd;
	}
	public int getRa4Sd() {
		return ra4Sd;
	}
	public void setRa4Sd(int ra4Sd) {
		this.ra4Sd = ra4Sd;
	}
	public int getRa4Sh() {
		return ra4Sh;
	}
	public void setRa4Sh(int ra4Sh) {
		this.ra4Sh = ra4Sh;
	}
	public String getFechaRatioStr() {
		return fechaRatioStr;
	}
	public void setFechaRatioStr(String fechaRatioStr) {
		this.fechaRatioStr = fechaRatioStr;
	}
	public String getUsCrea() {
		return usCrea;
	}
	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}
	public String getUsModi() {
		return usModi;
	}
	public void setUsModi(String usModi) {
		this.usModi = usModi;
	}
	public Date getFeCrea() {
		return feCrea;
	}
	public void setFeCrea(Date feCrea) {
		this.feCrea = feCrea;
	}
	public Date getFeModi() {
		return feModi;
	}
	public void setFeModi(Date feModi) {
		this.feModi = feModi;
	}
	public int getNroDiasHabilesMes() {
		return nroDiasHabilesMes;
	}
	public void setNroDiasHabilesMes(int nroDiasHabilesMes) {
		this.nroDiasHabilesMes = nroDiasHabilesMes;
	}
	public int getNroDiasHoyARetorno() {
		return nroDiasHoyARetorno;
	}
	public void setNroDiasHoyARetorno(int nroDiasHoyARetorno) {
		this.nroDiasHoyARetorno = nroDiasHoyARetorno;
	}

}
