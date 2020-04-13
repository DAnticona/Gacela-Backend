package com.wollcorp.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Nave;
import com.wollcorp.beans.NaveTemp;
import com.wollcorp.dao.NaveDaoImpl;
import com.wollcorp.dto.NaveDTO;
import com.wollcorp.dto.NaveDTOTemp;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.NaveRes;

public class NaveControlador {
	
	public NaveRes getNavesActivas(String token) throws SQLException {
		
		NaveRes naveRes = new NaveRes();
		
		List<NaveTemp> naves;
		
		List<NaveDTOTemp> navesDTO = new ArrayList<NaveDTOTemp>();
		NaveDTOTemp naveDTO;
		
		if(Token.tokenValido(token)) {
			
			naves = (new NaveDaoImpl()).listarNavesActivas(token);
			
			for(NaveTemp n : naves) {
				
				naveDTO = new NaveDTOTemp();
				
				naveDTO.setCodigo(n.getCoNave());
				naveDTO.setShortName(n.getAlNave());
				naveDTO.setLongName(n.getNoNave());
				naveDTO.setFgActi(n.getFgActi());
				naveDTO.setServicio(n.getServicio().getCoServ());
				naveDTO.setLinea(n.getLinea().getCoLine());
				naveDTO.setFgPropLinea(n.getFgPropLinea());
				
				navesDTO.add(naveDTO);
				
			}
			
		} else {
			
			naveDTO = null;
			
		}
		
		naveRes.setNavesTemp(navesDTO);
		
		return naveRes;
		
	}
	
	
	public NaveRes getNaves(String token) throws SQLException {
		
		NaveRes naveRes = new NaveRes();
		
		List<Nave> naves;
		
		List<NaveDTO> navesDTO = new ArrayList<NaveDTO>();
		NaveDTO naveDTO;
		
		if(Token.tokenValido(token)) {
			
			naves = (new NaveDaoImpl()).listarNaves(token);
			
			for(Nave n : naves) {
				
				naveDTO = new NaveDTO();
				
				naveDTO.setCoNave(n.getCoNave());
				naveDTO.setAlNave(n.getAlNave());
				naveDTO.setNoNave(n.getNoNave());
				naveDTO.setFgActi(n.getFgActi());
				naveDTO.setCoServ(n.getCoServ());
				naveDTO.setCoLine(n.getCoLine());
				naveDTO.setUsCrea(n.getUsCrea());
				naveDTO.setUsModi(n.getUsModi());
				naveDTO.setFeCrea(n.getFeCrea());
				naveDTO.setFeModi(n.getFeModi());
				
				navesDTO.add(naveDTO);
				
			}
			
		} else {
			
			naveDTO = null;
			
		}
		
		naveRes.setNaves(navesDTO);
		
		return naveRes;
		
	}
	
	
	public NaveRes registraNave(String token, NaveDTO nave) throws SQLException {
		
		NaveRes naveRes = new NaveRes();
		
		naveRes.setNave(new NaveDTO());
		
		if(Token.tokenValido(token)) {
			
			naveRes.getNave().setCoNave((new NaveDaoImpl()).registrar(nave, token));
			
		}
		
		return naveRes;
		
	}

}
