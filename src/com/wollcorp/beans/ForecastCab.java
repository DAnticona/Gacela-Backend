package com.wollcorp.beans;

import java.util.List;

public class ForecastCab {
	
	private String coForecast;
	private Servicio servicio;
	private Nave nave;
	private String fgProp;
	private List<ForecastDet> detalle;
	/**
	 * @return the coForecast
	 */
	public String getCoForecast() {
		return this.coForecast;
	}
	/**
	 * @param coForecast the coForecast to set
	 */
	public void setCoForecast(String coForecast) {
		this.coForecast = coForecast;
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
	 * @return the nave
	 */
	public Nave getNave() {
		return this.nave;
	}
	/**
	 * @param nave the nave to set
	 */
	public void setNave(Nave nave) {
		this.nave = nave;
	}
	/**
	 * @return the fgProp
	 */
	public String getFgProp() {
		return this.fgProp;
	}
	/**
	 * @param fgProp the fgProp to set
	 */
	public void setFgProp(String fgProp) {
		this.fgProp = fgProp;
	}
	/**
	 * @return the detalles
	 */
	public List<ForecastDet> getDetalle() {
		return this.detalle;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalle(List<ForecastDet> detalle) {
		this.detalle = detalle;
	}
	
	@Override
	public String toString() {
		return "ForecastCab [coForecast=" + this.coForecast + ", servicio=" + this.servicio + ", nave=" + this.nave
				+ ", fgProp=" + this.fgProp + "]";
	}
	
	
	
}
