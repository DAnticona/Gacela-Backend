package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class PasswordDaoImpl {

	public void cambiaPassword(String noUsua, String oldPas, String newPas, String token) throws SQLException {

		Connection conector = Conector.conectores.get(token);

		String sql = "EXEC SP_CAMBIA_PASSWORD ?, ?, ?";

//		try {

			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, oldPas);
			ps.setString(2, newPas);
			ps.setString(3, noUsua);
			

			int rowsAffected = ps.executeUpdate();

			Log.mensaje = rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)";
			Log.registraInfo();

//		} catch (SQLException e) {
//
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.codigo = e.getErrorCode();
//			Log.estado = e.getSQLState();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//		} catch (ClassCastException e1) {
//
//			Log.mensaje = e1.getMessage();
//			Log.exception = e1.toString();
//			Log.codigo = 0;
//			Log.estado = null;
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//		}

	}

}
