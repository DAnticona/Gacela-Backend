package com.wollcorp.dto;

import com.wollcorp.TEMP.RpoPlanesDTO;

public class RpoPlanRes {
	
	private RpoPlanesDTO planes;
	private ErrorDto error;
	

	public RpoPlanesDTO getPlanes() {
		return planes;
	}
	public void setPlanes(RpoPlanesDTO planes) {
		this.planes = planes;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
