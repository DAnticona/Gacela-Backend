package com.wollcorp.controladores;

import java.sql.SQLException;

import com.wollcorp.dao.PasswordDaoImpl;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Login;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.PasswordRes;

public class PasswordControlador {
	
	public PasswordRes cambiaPassword(String token, String auth, String newPassword) {
		
		PasswordRes passwordRes = new PasswordRes();
		
		String noUsua = Login.decode(auth)[0];
		String oldPas = Login.decode(auth)[1];
		String newPas = Login.decode(newPassword)[0];
		
		try {
			
			(new PasswordDaoImpl()).cambiaPassword(noUsua, oldPas, newPas, token);
			
			passwordRes.setError(null);
			
			
		} catch (SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
			passwordRes.setError(new ErrorRes());
			
			passwordRes.getError().setMensaje("Contraseña incorrecta");
			
		}
		
		return passwordRes;
		
	}

}
