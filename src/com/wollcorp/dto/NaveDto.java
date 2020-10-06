package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.beans.Nave;

public class NaveDto {
	
	private List<Nave> naves;
	private Nave nave;

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
	 * @return the naves
	 */
	public List<Nave> getNaves() {
		return this.naves;
	}

	/**
	 * @param naves the naves to set
	 */
	public void setNaves(List<Nave> naves) {
		this.naves = naves;
	}
	
}
