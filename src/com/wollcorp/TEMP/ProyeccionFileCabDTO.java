package com.wollcorp.TEMP;

import java.util.Date;
import java.util.List;

public class ProyeccionFileCabDTO {
	
	private String codigo;
	private String NoFile;
	private Date feCargaFile;
	private String fgActi;
	private String usCrea;
	private String usModi;
	private String feCrea;
	private String feModi;
	private List<ProyeccionFileDetDTO> detalle;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNoFile() {
		return NoFile;
	}
	public void setNoFile(String noFile) {
		NoFile = noFile;
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
	public String getFeCrea() {
		return feCrea;
	}
	public void setFeCrea(String feCrea) {
		this.feCrea = feCrea;
	}
	public String getFeModi() {
		return feModi;
	}
	public void setFeModi(String feModi) {
		this.feModi = feModi;
	}
	public List<ProyeccionFileDetDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<ProyeccionFileDetDTO> detalle) {
		this.detalle = detalle;
	}
	
}
