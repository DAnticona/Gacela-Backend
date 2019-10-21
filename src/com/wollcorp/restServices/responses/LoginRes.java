package com.wollcorp.restServices.responses;

import com.wollcorp.dto.ConexionDTO;
import com.wollcorp.dto.UsuarioDTO;

public class LoginRes {

	private ConexionDTO conexion;
	// private UsuarioDTO usuario;
	private ErrorRes error;
	
	public ConexionDTO getConexion() {
		return conexion;
	}
	public void setConexion(ConexionDTO conexion) {
		this.conexion = conexion;
	}
	public ErrorRes getError() {
		return error;
	}
	public void setError(ErrorRes error) {
		this.error = error;
	}
//	public UsuarioDTO getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(UsuarioDTO usuario) {
//		this.usuario = usuario;
//	}

}
