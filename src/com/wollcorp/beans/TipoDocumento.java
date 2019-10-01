package com.wollcorp.beans;

import java.time.LocalDateTime;

public class TipoDocumento {
	
	private String coTiDocu;
	private String noTiDocu;
	private String alTiDocu;
	private String usModi;
	private String usCrea;
	private LocalDateTime feCrea;
	private LocalDateTime feModi;
	
	public String getCoTiDocu() {
		return coTiDocu;
	}
	public void setCoTiDocu(String coTiDocu) {
		this.coTiDocu = coTiDocu;
	}
	public String getNoTiDocu() {
		return noTiDocu;
	}
	public void setNoTiDocu(String noTiDocu) {
		this.noTiDocu = noTiDocu;
	}
	public String getAlTiDocu() {
		return alTiDocu;
	}
	public void setAlTiDocu(String alTiDocu) {
		this.alTiDocu = alTiDocu;
	}
	public String getUsModi() {
		return usModi;
	}
	public void setUsModi(String usModi) {
		this.usModi = usModi;
	}
	public String getUsCrea() {
		return usCrea;
	}
	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}
	public LocalDateTime getFeCrea() {
		return feCrea;
	}
	public void setFeCrea(LocalDateTime feCrea) {
		this.feCrea = feCrea;
	}
	public LocalDateTime getFeModi() {
		return feModi;
	}
	public void setFeModi(LocalDateTime feModi) {
		this.feModi = feModi;
	}

}
