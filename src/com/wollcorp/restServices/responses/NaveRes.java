package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.TEMP.NaveDTO;
import com.wollcorp.TEMP.NaveDTOTemp;

public class NaveRes {
	
	private List<NaveDTOTemp> navesTemp;
	private List<NaveDTO> naves;
	private NaveDTO nave;
	private ErrorRes error;
	
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}
	public List<NaveDTOTemp> getNavesTemp() {
		return navesTemp;
	}
	public void setNavesTemp(List<NaveDTOTemp> navesTemp) {
		this.navesTemp = navesTemp;
	}
	public List<NaveDTO> getNaves() {
		return naves;
	}
	public void setNaves(List<NaveDTO> naves) {
		this.naves = naves;
	}
	public NaveDTO getNave() {
		return nave;
	}
	public void setNave(NaveDTO nave) {
		this.nave = nave;
	}
	
}
