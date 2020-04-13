/**
 * 
 */
package com.wollcorp.restServices.responses;

import com.wollcorp.dto.RatioDevolucionDTO;

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
	private ErrorRes error;
	
	public RatioDevolucionDTO getRatio() {
		return this.ratio;
	}
	public void setRatio(RatioDevolucionDTO ratio) {
		this.ratio = ratio;
	}
	public ErrorRes getError() {
		return this.error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}

}
