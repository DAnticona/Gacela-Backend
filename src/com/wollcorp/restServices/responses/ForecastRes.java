package com.wollcorp.restServices.responses;

public class ForecastRes {
	
	private String fileName;
	private ErrorRes error;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
