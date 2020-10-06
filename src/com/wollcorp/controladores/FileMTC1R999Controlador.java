package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.FileCabMTC1R999;
import com.wollcorp.dao.FileMTC1R999Dao;
import com.wollcorp.dto.FileMTC1R999Dto;
import com.wollcorp.globales.Token;

public class FileMTC1R999Controlador {
	
	public FileMTC1R999Dto registrarFile(String token, FileCabMTC1R999 file) throws Exception  {
		
		FileMTC1R999Dto fileRes = null; 
		
		String coFile = null;
		
		if(Token.tokenValido(token)) {
			
			fileRes = new FileMTC1R999Dto();
			
			coFile = (new FileMTC1R999Dao()).registrarFile(file, token);
			
			file.setCoFile(coFile);
			
			fileRes.setFile(file);
			
		}
		
		return fileRes;
		
	}
	
	public FileMTC1R999Dto listarFilesCab(String token) throws Exception {
		
		FileMTC1R999Dto fileDto = new FileMTC1R999Dto(); 
		
		List<FileCabMTC1R999> files = null;
		
		if(Token.tokenValido(token)) {
			
			files = (new FileMTC1R999Dao()).listarFiles(token);
			
		}
		
		fileDto.setFiles(files);
		
		return fileDto;
	}
	
	
	public FileMTC1R999Dto getFile(String token, String coFile) throws Exception {
		
		FileMTC1R999Dto fileDto = new FileMTC1R999Dto();
		
		FileCabMTC1R999 file = null;
				
		
		if(Token.tokenValido(token)) {
			
			file = (new FileMTC1R999Dao()).getFileCab(token, coFile);
			
		}
		
		fileDto.setFile(file);
		
		return fileDto;
	}
	
	
	public FileMTC1R999Dto getFileActivo(String token) throws Exception {
		
		FileMTC1R999Dto fileDto = new FileMTC1R999Dto();
		
		FileCabMTC1R999 file = null;
				
		
		if(Token.tokenValido(token)) {
			
			file = (new FileMTC1R999Dao()).getFileCabActivo(token);
			
		}
		
		fileDto.setFile(file);
		
		return fileDto;
	}

}
