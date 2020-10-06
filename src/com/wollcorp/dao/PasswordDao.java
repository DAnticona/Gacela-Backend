package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wollcorp.conexion.ConexionSQLServer;

public class PasswordDao {

	public void cambiaPassword(String noUsua, String oldPas, String newPas, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_CAMBIA_PASSWORD ?, ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, oldPas);
		ps.setString(2, newPas);
		ps.setString(3, noUsua);

		ps.executeUpdate();

	}

}
