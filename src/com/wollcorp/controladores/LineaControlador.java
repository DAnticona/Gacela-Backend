package com.wollcorp.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Linea;
import com.wollcorp.dao.LineaDaoImpl;
import com.wollcorp.dto.LineaDTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.LineaRes;

public class LineaControlador {
	
	public LineaRes obtieneLineas(String token) throws SQLException {
		
		LineaRes lineaRes = new LineaRes();
		
		List<LineaDTO> lineasDTO = new ArrayList<LineaDTO>();
		
		if(Token.tokenValido(token)) {
			
			List<Linea> lineas = (new LineaDaoImpl()).listarLineas(token);
			
			for(Linea l : lineas) {
				
				LineaDTO lineaDTO = new LineaDTO();
				
				lineaDTO.setCoLine(l.getCoLine());
				lineaDTO.setNoLine(l.getNoLine());
				lineaDTO.setCoIso(l.getCoIso());
				lineaDTO.setCoSol(l.getCoSol());
				lineaDTO.setFgProp(l.getFgProp());
				lineaDTO.setUsCrea(l.getUsCrea());
				lineaDTO.setUsModi(l.getUsModi());
				lineaDTO.setFeCrea(l.getFeCrea());
				lineaDTO.setFeModi(l.getFeModi());
				lineaDTO.setFgActi(l.getFgActi());
				lineaDTO.setNrOrde(l.getNrOrde());
				
				lineasDTO.add(lineaDTO);
			}
			
			
		} else {
			
			lineasDTO = null;
		}
		
		lineaRes.setLineas(lineasDTO);
		
		return lineaRes;
	}

}
