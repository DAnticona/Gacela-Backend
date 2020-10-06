package com.wollcorp.beans;

import java.util.Date;
import java.util.List;

public class FileCabMTC1R999 {
	
    private String coFile;
    private String noFile;
    private Date feCargaFile;
    private String fgActi;
    private String usCrea;
    private String usModi;
    private Date feCrea;
    private Date feModi;
    private List<FileDetMTC1R999> detalle;
    
	public String getCoFile() {
		return coFile;
	}
	public void setCoFile(String coFile) {
		this.coFile = coFile;
	}
	public String getNoFile() {
		return noFile;
	}
	public void setNoFile(String noFile) {
		this.noFile = noFile;
	}
	public Date getFeCargaFile() {
		return feCargaFile;
	}
	public void setFeCargaFile(Date feCargaFile) {
		this.feCargaFile = feCargaFile;
	}
	public String getFgActi() {
		return fgActi;
	}
	public void setFgActi(String fgActi) {
		this.fgActi = fgActi;
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
	public List<FileDetMTC1R999> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<FileDetMTC1R999> detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "FileCabMTC1R999 [coFile=" + this.coFile + ", noFile=" + this.noFile + ", feCargaFile="
				+ this.feCargaFile + ", fgActi=" + this.fgActi + ", usCrea=" + this.usCrea + ", usModi=" + this.usModi
				+ ", feCrea=" + this.feCrea + ", feModi=" + this.feModi + "]";
	}

}
