package com.wollcorp.TEMP;

import java.util.Date;

public class ProyeccionGeneradaDTO {
	
	private int idItem;	
	private String alNave;
	private String viaje;
	private Date eta;
	private String tpe;
	private String fgFarEast;
	private int qty;
	private int pick;
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getAlNave() {
		return alNave;
	}
	public void setAlNave(String alNave) {
		this.alNave = alNave;
	}
	public String getViaje() {
		return viaje;
	}
	public void setViaje(String viaje) {
		this.viaje = viaje;
	}
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public String getTpe() {
		return tpe;
	}
	public void setTpe(String tpe) {
		this.tpe = tpe;
	}
	public String getFgFarEast() {
		return fgFarEast;
	}
	public void setFgFarEast(String fgFarEast) {
		this.fgFarEast = fgFarEast;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPick() {
		return pick;
	}
	public void setPick(int pick) {
		this.pick = pick;
	}

}
