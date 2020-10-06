package com.wollcorp.controladores;

import java.sql.SQLException;

import com.wollcorp.dao.PasswordDao;
import com.wollcorp.globales.Login;

public class PasswordControlador {

	public void cambiaPassword(String token, String auth, String newPassword) throws SQLException {

		String noUsua = Login.decode(auth)[0];
		String oldPas = Login.decode(auth)[1];
		String newPas = Login.decode(newPassword)[0];

		(new PasswordDao()).cambiaPassword(noUsua, oldPas, newPas, token);

	}

}
