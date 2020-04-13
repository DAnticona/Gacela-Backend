package com.wollcorp.restServices.responses;

import com.wollcorp.dto.RpoPlanesDTO;

public class RpoPlanRes {
	
	private RpoPlanesDTO planes;
	private ErrorRes error;
	

	public RpoPlanesDTO getPlanes() {
		return planes;
	}
	public void setPlanes(RpoPlanesDTO planes) {
		this.planes = planes;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
