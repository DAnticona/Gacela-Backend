package com.wollcorp.dto;

public class ForecastDto {
	
	private String fileName;
	private ErrorDto error;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
