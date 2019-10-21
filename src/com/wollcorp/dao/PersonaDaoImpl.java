package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wollcorp.beans.Persona;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class PersonaDaoImpl {

	public void actualizarPersona(Persona persona, String token) {

		Connection conector = Conector.conectores.get(token);

		String sql = "EXEC SP_ACTUALIZA_PERSONA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

		try {

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

			int rowsAffected = ps.executeUpdate();

			Log.mensaje = rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)";
			Log.registraInfo();

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		} catch (ClassCastException e1) {

			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		}

	}

	public void actualizarImagen(String ruta, String noFile, String token, String noUsua) {

		Connection conector = Conector.conectores.get(token);

		String sql = "EXEC SP_ACTUALIZA_IMAGEN_PERSONA ?, ?, ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, ruta);
			ps.setString(2, noFile);
			ps.setString(3, noUsua);

			int rowsAffected = ps.executeUpdate();

			Log.mensaje = rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)";
			Log.registraInfo();

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		} catch (ClassCastException e1) {

			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		}

	}

}
