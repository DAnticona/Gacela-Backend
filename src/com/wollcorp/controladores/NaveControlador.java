package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.Nave;
import com.wollcorp.dao.NaveDao;
import com.wollcorp.dto.NaveDto;
import com.wollcorp.globales.Token;

public class NaveControlador {
	
//	public NaveRes getNavesActivas(String token) throws Exception {
//		
//		NaveRes naveRes = new NaveRes();
//		
//		List<NaveTemp> naves;
//		
//		List<NaveDTOTemp> navesDTO = new ArrayList<NaveDTOTemp>();
//		NaveDTOTemp naveDTO;
//		
//		if(Token.tokenValido(token)) {
//			
//			naves = (new NaveDaoImpl()).listarNavesActivas(token);
//			
//			for(NaveTemp n : naves) {
//				
//				naveDTO = new NaveDTOTemp();
//				
//				naveDTO.setCodigo(n.getCoNave());
//				naveDTO.setShortName(n.getAlNave());
//				naveDTO.setLongName(n.getNoNave());
//				naveDTO.setFgActi(n.getFgActi());
//				naveDTO.setServicio(n.getServicio().getCoServ());
//				naveDTO.setLinea(n.getLinea().getCoLine());
//				naveDTO.setFgPropLinea(n.getFgPropLinea());
//				
//				navesDTO.add(naveDTO);
//				
//			}
//			
//		} else {
//			
//			naveDTO = null;
//			
//		}
//		
//		naveRes.setNavesTemp(navesDTO);
//		
//		return naveRes;
//		
//	}
	
	
	public NaveDto obtenerNaves(String token) throws Exception {
		
		NaveDto naveDto = new NaveDto();
		
		List<Nave> naves = null;
		
		if(Token.tokenValido(token)) {
			
			naves = (new NaveDao()).obtenerNaves(token);
			
		}
		
		naveDto.setNaves(naves);
		
		return naveDto;
		
	}
	
	
	public NaveDto registrarNave(String token, Nave nave) throws Exception {
		
		NaveDto naveDto = null;
		
		if(Token.tokenValido(token)) {
			
			naveDto = new NaveDto();
			naveDto.setNave(new Nave());
			
			naveDto.getNave().setCoNave((new NaveDao()).registrarNave(nave, token));
			
		}
		
		return naveDto;
		
	}

}
