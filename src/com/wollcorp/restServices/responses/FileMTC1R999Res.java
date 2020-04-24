package com.wollcorp.restServices.responses;

import java.util.List;

import com.wollcorp.TEMP.FileCabMTC1R999DTO;

public class FileMTC1R999Res {
	
	private List<FileCabMTC1R999DTO> filesCab;
	private FileCabMTC1R999DTO fileCab;
	private ErrorRes error;
	
	public List<FileCabMTC1R999DTO> getFilesCab() {
		return filesCab;
	}
	public void setFilesCab(List<FileCabMTC1R999DTO> filesCab) {
		this.filesCab = filesCab;
	}
	public FileCabMTC1R999DTO getFileCab() {
		return fileCab;
	}
	public void setFileCab(FileCabMTC1R999DTO fileCab) {
		this.fileCab = fileCab;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
