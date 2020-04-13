package com.wollcorp.beans;

import java.util.List;

public class ForecastCab {
	
	private String coForecast;
	private String coServ;
	private String noServ;
	private String coNave;
	private String noNave;
	private String alNave;
	private String fgProp;
	private List<ForecastDet> detalles;
	
	public String getCoForecast() {
		return coForecast;
	}
	public void setCoForecast(String coForecast) {
		this.coForecast = coForecast;
	}
	public String getCoServ() {
		return coServ;
	}
	public void setCoServ(String coServ) {
		this.coServ = coServ;
	}
	public String getCoNave() {
		return coNave;
	}
	public void setCoNave(String coNave) {
		this.coNave = coNave;
	}
	public List<ForecastDet> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<ForecastDet> detalles) {
		this.detalles = detalles;
	}
	public String getNoServ() {
		return noServ;
	}
	public void setNoServ(String noServ) {
		this.noServ = noServ;
	}
	public String getNoNave() {
		return noNave;
	}
	public void setNoNave(String noNave) {
		this.noNave = noNave;
	}
	public String getAlNave() {
		return alNave;
	}
	public void setAlNave(String alNave) {
		this.alNave = alNave;
	}
	public String getFgProp() {
		return fgProp;
	}
	public void setFgProp(String fgProp) {
		this.fgProp = fgProp;
	}
	
}
