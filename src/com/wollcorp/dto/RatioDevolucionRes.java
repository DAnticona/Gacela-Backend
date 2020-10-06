/**
 * 
 */
package com.wollcorp.dto;

import com.wollcorp.TEMP.RatioDevolucionDTO;

/**
 * Clase usada para enviar datos de ratio de devolucion
 * de las proyecciones al Front End.
 * 
 * @author David Anticona
 * @version 1.0
 *
 */
public class RatioDevolucionRes {
	
	private RatioDevolucionDTO ratio;
	private ErrorDto error;
	
	public RatioDevolucionDTO getRatio() {
		return this.ratio;
	}
	public void setRatio(RatioDevolucionDTO ratio) {
		this.ratio = ratio;
	}
	public ErrorDto getError() {
		return this.error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}

}
