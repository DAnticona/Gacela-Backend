package com.wollcorp.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.FileCabMTC1R999;
import com.wollcorp.beans.FileDetMTC1R999;
import com.wollcorp.dao.FileMTC1R999DaoImpl;
import com.wollcorp.dto.FileCabMTC1R999DTO;
import com.wollcorp.dto.FileDetMTC1R999DTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.FileMTC1R999Res;

public class FileMTC1R999Controlador {
	
	public FileMTC1R999Res guardarFile(String token, FileCabMTC1R999DTO fileCab) throws SQLException  {
		
		FileMTC1R999Res fileRes = new FileMTC1R999Res(); 
		
		// FileCabMTC1R999DTO proyeccionFileDTO = new FileCabMTC1R999DTO();
		
		String coFile = null;
		
		if(Token.tokenValido(token)) {
			
			coFile = (new FileMTC1R999DaoImpl()).registraFile(fileCab, token);
			
			fileCab.setCoFile(coFile);
			
			fileRes.setFileCab(fileCab);
			
		} else {
			
			fileCab = null;
			
			fileRes.setError(new ErrorRes());
			
			fileRes.getError().setMensaje("TOKEN INVÁLIDO");
			
		}
		
		return fileRes;
		
	}
	
	public FileMTC1R999Res listarFilesCab(String token) throws SQLException {
		
		FileMTC1R999Res fileRes = new FileMTC1R999Res(); 
		
		List<FileCabMTC1R999> filesCab = new ArrayList<FileCabMTC1R999>();
		
		List<FileCabMTC1R999DTO> filesCabDTO = new ArrayList<FileCabMTC1R999DTO>();
		FileCabMTC1R999DTO fileCab;
		
		if(Token.tokenValido(token)) {
			
			filesCab = (new FileMTC1R999DaoImpl()).listarFiles(token);
			
			for(FileCabMTC1R999 p : filesCab) {
				
				fileCab = new FileCabMTC1R999DTO();
				
				fileCab.setCoFile(p.getCoFile());
				fileCab.setNoFile(p.getNoFile());
				fileCab.setFeCargaFile(p.getFeCargaFile());
				fileCab.setFgActi(p.getFgActi());
				
				filesCabDTO.add(fileCab);
				
			}
			
		} else {
			
			filesCabDTO = null;
			
		}
		
		fileRes.setFilesCab(filesCabDTO);
		
		return fileRes;
	}
	
	
	public FileMTC1R999Res getFile(String token, String coFile) throws SQLException {
		
		FileMTC1R999Res fileRes = new FileMTC1R999Res();
		
		FileCabMTC1R999 fileCab;
		
		FileCabMTC1R999DTO fileCabDTO = null;
		FileDetMTC1R999DTO detDTO = null;
				
		
		if(Token.tokenValido(token)) {
			
			fileCabDTO = new FileCabMTC1R999DTO();
			fileCabDTO.setDetalle(new ArrayList<FileDetMTC1R999DTO>());
			
			fileCab = (new FileMTC1R999DaoImpl()).getFileCab(token, coFile);
			
			fileCabDTO.setCoFile(fileCab.getCoFile());
			fileCabDTO.setNoFile(fileCab.getNoFile());
			fileCabDTO.setFeCargaFile(fileCab.getFeCargaFile());
			fileCabDTO.setFgActi(fileCab.getFgActi());
			
			for(FileDetMTC1R999 d : fileCab.getDetalle()) {
				
				detDTO = new FileDetMTC1R999DTO();
				
				detDTO.setCoFile(d.getCoFile());
				detDTO.setIdItem(d.getIdItem());
				detDTO.setDepot(d.getDepot());
				detDTO.setNave(d.getNave());
				detDTO.setViaje(d.getViaje());
				detDTO.setVsl_voy_s(d.getVsl_voy_s());
				detDTO.setBooking_no(d.getBooking_no());
				detDTO.setRvs(d.getRvs());
				detDTO.setQty(d.getQty());
				detDTO.setPick(d.getPick());
				detDTO.setBalance(d.getBalance());
				detDTO.setMode(d.getMode());
				detDTO.setMta(d.getMta());
				detDTO.setTpe(d.getTpe());
				detDTO.setRct(d.getRct());
				detDTO.setPol(d.getPol());
				detDTO.setPod(d.getPod());
				detDTO.setDly(d.getDly());
				detDTO.setRelease(d.getRelease());
				detDTO.setCut_off(d.getCut_off());
				detDTO.setDry_use(d.getDry_use());
				detDTO.setPre_cool(d.getPre_cool());
				detDTO.setTemp(d.getTemp());
				detDTO.setVent(d.getVent());
				detDTO.setCommodity(d.getCommodity());
				detDTO.setSpecial_handling(d.getSpecial_handling());
				detDTO.setCustomer_ac(d.getCustomer_ac());
				detDTO.setCustomer_name(d.getCustomer_name());
				detDTO.setRemark(d.getRemark());
				
				fileCabDTO.getDetalle().add(detDTO);
				
			}
			
		} else {
			
			fileCabDTO = null;
			
		}
		
		fileRes.setFileCab(fileCabDTO);
		
		return fileRes;
	}
	
	
	public FileMTC1R999Res getFileActivo(String token) throws SQLException {
		
		FileMTC1R999Res fileRes = new FileMTC1R999Res();
		
		FileCabMTC1R999 fileCab;
		
		FileCabMTC1R999DTO fileCabDTO = null;
		FileDetMTC1R999DTO detDTO = null;
				
		
		if(Token.tokenValido(token)) {
			
			fileCabDTO = new FileCabMTC1R999DTO();
			fileCabDTO.setDetalle(new ArrayList<FileDetMTC1R999DTO>());
			
			fileCab = (new FileMTC1R999DaoImpl()).getFileCabActivo(token);
			
			fileCabDTO.setCoFile(fileCab.getCoFile());
			fileCabDTO.setNoFile(fileCab.getNoFile());
			fileCabDTO.setFeCargaFile(fileCab.getFeCargaFile());
			fileCabDTO.setFgActi(fileCab.getFgActi());
			
			for(FileDetMTC1R999 d : fileCab.getDetalle()) {
				
				detDTO = new FileDetMTC1R999DTO();
				
				detDTO.setCoFile(d.getCoFile());
				detDTO.setIdItem(d.getIdItem());
				detDTO.setDepot(d.getDepot());
				detDTO.setNave(d.getNave());
				detDTO.setViaje(d.getViaje());
				detDTO.setVsl_voy_s(d.getVsl_voy_s());
				detDTO.setBooking_no(d.getBooking_no());
				detDTO.setRvs(d.getRvs());
				detDTO.setQty(d.getQty());
				detDTO.setPick(d.getPick());
				detDTO.setBalance(d.getBalance());
				detDTO.setMode(d.getMode());
				detDTO.setMta(d.getMta());
				detDTO.setTpe(d.getTpe());
				detDTO.setRct(d.getRct());
				detDTO.setPol(d.getPol());
				detDTO.setPod(d.getPod());
				detDTO.setDly(d.getDly());
				detDTO.setRelease(d.getRelease());
				detDTO.setCut_off(d.getCut_off());
				detDTO.setDry_use(d.getDry_use());
				detDTO.setPre_cool(d.getPre_cool());
				detDTO.setTemp(d.getTemp());
				detDTO.setVent(d.getVent());
				detDTO.setCommodity(d.getCommodity());
				detDTO.setSpecial_handling(d.getSpecial_handling());
				detDTO.setCustomer_ac(d.getCustomer_ac());
				detDTO.setCustomer_name(d.getCustomer_name());
				detDTO.setRemark(d.getRemark());
				
				fileCabDTO.getDetalle().add(detDTO);
				
			}
			
		} else {
			
			fileCabDTO = null;
			
		}
		
		fileRes.setFileCab(fileCabDTO);
		
		return fileRes;
	}

}
