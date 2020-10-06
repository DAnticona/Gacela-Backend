/**
 * 
 */
package com.wollcorp.controladores;

import java.sql.SQLException;

import com.wollcorp.TEMP.RatioDevolucionDTO;
import com.wollcorp.beans.RatioDevolucion;
import com.wollcorp.dao.RatioDevolucionDaoImpl;
import com.wollcorp.dto.ErrorDto;
import com.wollcorp.dto.RatioDevolucionRes;
import com.wollcorp.globales.Token;

/**
 * Controlador para los ratios de devolución usados en
 * las proyecciones de equipos.
 * Creado el 21/04/2020
 * @author David Anticona
 * @version 1.0
 * 
 */
public class RatioDevolucionControlador {
	
	/**
	 * Controlador que registra los ratios de devolución
	 * usados en las proyecciones de equipos.
	 * @param token Es el token de seguridads otorgado por el backend.
	 * @param ratio Es el objeto RatioDevolucion enviado desde el FrontEnd a ser registrado.
	 * @return ratioDevolucionRes Objeto de respuesta. 
	 * @throws Exception 
	 */
	public RatioDevolucionRes registraRatioDevolucion(String token, RatioDevolucionDTO ratio) throws Exception {
		
		RatioDevolucionRes ratioDevolucionRes = new RatioDevolucionRes();

		if (Token.tokenValido(token)) {

			(new RatioDevolucionDaoImpl()).registraRatio(ratio, token);

		} else {

			ratioDevolucionRes.setError(new ErrorDto());

			ratioDevolucionRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return ratioDevolucionRes;

	}
	
	
	/**
	 * Obtiene los ratios de devolución de la capa DAO, los almacena en la clase DTO. 
	 * @param token 
	 * @return ratioDevolucionRes Objeto que contiene los datos obtenidos.
	 * @throws Exception 
	 */
	public RatioDevolucionRes obtieneRatioDevolucion(String token) throws Exception {

		RatioDevolucionRes ratioDevolucionRes = new RatioDevolucionRes();

		if (Token.tokenValido(token)) {

			RatioDevolucion ratio = (new RatioDevolucionDaoImpl()).obtieneRatio(token);
			
			RatioDevolucionDTO ratioDTO = new RatioDevolucionDTO();
			
			ratioDTO.setCoRatio(ratio.getCoRatio());
			ratioDTO.setRatio2Sd(ratio.getRatio2Sd());
			ratioDTO.setRatio4Sd(ratio.getRatio4Sd());
			ratioDTO.setRatio4Sh(ratio.getRatio4Sh());
			ratioDTO.setUsCrea(ratio.getUsCrea());
			ratioDTO.setUsModi(ratio.getUsModi());
			ratioDTO.setFeCrea(ratio.getFeCrea());
			ratioDTO.setFeModi(ratio.getFeModi());
			
			ratioDevolucionRes.setRatio(ratioDTO);

		} else {

			ratioDevolucionRes.setError(new ErrorDto());

			ratioDevolucionRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return ratioDevolucionRes;

	}

}
