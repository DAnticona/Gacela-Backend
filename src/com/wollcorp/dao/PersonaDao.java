package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wollcorp.beans.Persona;
import com.wollcorp.conexion.ConexionSQLServer;

public class PersonaDao {

	public void actualizarPersona(Persona persona, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_ACTUALIZA_PERSONA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, persona.getCoPers());
		ps.setString(2, persona.getNuDocu());
		ps.setString(3, persona.getNoPers());
		ps.setString(4, persona.getApPate());
		ps.setString(5, persona.getApMate());
		ps.setString(6, persona.getSexo());
		ps.setDate(7, new java.sql.Date((persona.getFeNaci().getTime())));
		ps.setString(8, persona.getEmail());
		ps.setString(9, persona.getTiDocu());
		ps.setString(10, persona.getRutaImagen());

		ps.executeUpdate();

	}

	public void actualizarImagen(String ruta, String noFile, String token, String noUsua) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_ACTUALIZA_IMAGEN_PERSONA ?, ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, ruta);
		ps.setString(2, noFile);
		ps.setString(3, noUsua);

		ps.executeUpdate();

	}

}
