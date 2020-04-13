/**
 * 
 */
package com.wollcorp.dto;

import java.util.Date;

/**
 * @author David Anticona
 * @version 1.0
 */
public class RatioDevolucionDTO {
	
	private int ratio2Sd;
	private int ratio4Sd;
	private int ratio4Sh;
	private String usCrea;
	private String usModi;
	private Date feCrea;
	private Date feModi;
	
	public int getRatio2Sd() {
		return this.ratio2Sd;
	}
	public void setRatio2Sd(int ratio2Sd) {
		this.ratio2Sd = ratio2Sd;
	}
	public int getRatio4Sd() {
		return this.ratio4Sd;
	}
	public void setRatio4Sd(int ratio4Sd) {
		this.ratio4Sd = ratio4Sd;
	}
	public int getRatio4Sh() {
		return this.ratio4Sh;
	}
	public void setRatio4Sh(int ratio4Sh) {
		this.ratio4Sh = ratio4Sh;
	}
	public String getUsCrea() {
		return this.usCrea;
	}
	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}
	public String getUsModi() {
		return this.usModi;
	}
	public void setUsModi(String usModi) {
		this.usModi = usModi;
	}
	public Date getFeCrea() {
		return this.feCrea;
	}
	public void setFeCrea(Date feCrea) {
		this.feCrea = feCrea;
	}
	public Date getFeModi() {
		return this.feModi;
	}
	public void setFeModi(Date feModi) {
		this.feModi = feModi;
	}

}
