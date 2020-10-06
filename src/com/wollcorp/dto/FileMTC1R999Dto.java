package com.wollcorp.dto;

import java.util.List;

import com.wollcorp.beans.FileCabMTC1R999;

public class FileMTC1R999Dto {
	
	private List<FileCabMTC1R999> files;
	private FileCabMTC1R999 file;
	/**
	 * @return the files
	 */
	public List<FileCabMTC1R999> getFiles() {
		return this.files;
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(List<FileCabMTC1R999> files) {
		this.files = files;
	}
	/**
	 * @return the file
	 */
	public FileCabMTC1R999 getFile() {
		return this.file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(FileCabMTC1R999 file) {
		this.file = file;
	}

}
