package com.wollcorp.TEMP;

import java.util.List;

public class ForecastCabDTO {
	
	private String coForecast;
	private String coServ;
	private String fgProp;
	private String coNave;
	private List<ForecastDetDTO> detalle;
	
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
	public String getFgProp() {
		return fgProp;
	}
	public void setFgProp(String fgProp) {
		this.fgProp = fgProp;
	}
	public String getCoNave() {
		return coNave;
	}
	public void setCoNave(String coNave) {
		this.coNave = coNave;
	}
	public List<ForecastDetDTO> getDetalle() {
		return detalle;
	}
	public void setDetalles(List<ForecastDetDTO> detalle) {
		this.detalle = detalle;
	}
	

}
