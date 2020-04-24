package com.wollcorp.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.TEMP.RpoPlanDTO;
import com.wollcorp.TEMP.RpoPlanesDTO;
import com.wollcorp.beans.RpoPlan;
import com.wollcorp.dao.RpoPlanDaoImpl;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.RpoPlanRes;

public class RpoPlanControlador {
	
	public RpoPlanRes registraRpoPlan(String token, RpoPlanDTO rpo) throws SQLException {
		
		RpoPlanRes rpoPlanRes = new RpoPlanRes();

		if (Token.tokenValido(token)) {

			(new RpoPlanDaoImpl()).registraRpoPlan(rpo, token);

		} else {

			rpoPlanRes.setError(new ErrorRes());

			rpoPlanRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return rpoPlanRes;
	}
	
	
	public RpoPlanRes obtieneRpoPlan(String token) throws SQLException {
		
		RpoPlanRes rpoPlanRes = new RpoPlanRes();
		
		List<RpoPlan> rpoPlanes;
		
		RpoPlanesDTO rpoPlanesDTO = new RpoPlanesDTO();

		if (Token.tokenValido(token)) {

			rpoPlanes = (new RpoPlanDaoImpl()).obtieneRpoPlan(token);
			
			rpoPlanesDTO.setPlanes(new ArrayList<RpoPlanDTO>());
			
			for(RpoPlan p : rpoPlanes) {
				
				RpoPlanDTO rpoPlanDTO = new RpoPlanDTO();
				
				rpoPlanDTO.setCoRpo(p.getCoRpo());
				rpoPlanDTO.setAlNaveRpo(p.getAlNaveRpo());
				rpoPlanDTO.setViajeRpo(p.getViajeRpo());
				rpoPlanDTO.setCa2SdRpo(p.getCa2SdRpo());
				rpoPlanDTO.setCa4SdRpo(p.getCa4SdRpo());
				rpoPlanDTO.setCa4ShRpo(p.getCa4ShRpo());
				// rpoPlanDTO.setCa4RhRpo(p.getCa4RhRpo());
				rpoPlanDTO.setEtaRpo(p.getEtaRpo());
				rpoPlanDTO.setFgActiRpo(p.getFgActiRpo());
				
				rpoPlanesDTO.getPlanes().add(rpoPlanDTO);
				
			}
			
			rpoPlanRes.setPlanes(rpoPlanesDTO);

		} else {

			rpoPlanRes.setError(new ErrorRes());

			rpoPlanRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return rpoPlanRes;
	}
	
	public RpoPlanRes eliminaRpoPlan(String token, RpoPlanDTO rpo) throws SQLException {
		
		RpoPlanRes rpoPlanRes = new RpoPlanRes();

		if (Token.tokenValido(token)) {

			(new RpoPlanDaoImpl()).eliminaRpoPlan(rpo, token);

		} else {

			rpoPlanRes.setError(new ErrorRes());

			rpoPlanRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return rpoPlanRes;
	}

}
