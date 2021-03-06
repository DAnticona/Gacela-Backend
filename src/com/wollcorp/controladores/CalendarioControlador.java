package com.wollcorp.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wollcorp.TEMP.CalendarioDTO;
import com.wollcorp.beans.Calendario;
import com.wollcorp.dao.CalendarioDaoImpl;
import com.wollcorp.dto.CalendarioRes;
import com.wollcorp.globales.Token;

public class CalendarioControlador {
	
	public CalendarioRes getCalendario(String token, Date fechaIni, Date fechaFin) throws Exception{
		
		CalendarioRes calendarioRes = new CalendarioRes();
		
		List<Calendario> calendario;
		
		
		List<CalendarioDTO> calendarioDTO = new ArrayList<CalendarioDTO>();
		CalendarioDTO fechaDTO;
		
		if(Token.tokenValido(token)) {
			
			calendario = (new CalendarioDaoImpl()).listaCalendarios(token, fechaIni, fechaFin);
			
			for(Calendario fecha : calendario) {
				
				fechaDTO = new CalendarioDTO();
				
				fechaDTO.setCodigo(fecha.getCodigo());
				fechaDTO.setDiaSem(fecha.getDiaSem());
				fechaDTO.setDia(fecha.getDia());
				fechaDTO.setMes(fecha.getMes());
				fechaDTO.setAno(fecha.getAno());
				fechaDTO.setFecha(fecha.getFecha());
				fechaDTO.setFgFeriado(fecha.getFgFeriado());
				fechaDTO.setNroSemAno(fecha.getNroSemAno());
				
				calendarioDTO.add(fechaDTO);
				
			}
			
		} else {
			
			calendarioDTO = null;
			
		}
		
		calendarioRes.setCalendario(calendarioDTO);
		
		return calendarioRes;
		
	}

}
