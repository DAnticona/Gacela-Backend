package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wollcorp.beans.Perfil;
import com.wollcorp.conexion.ConexionSQLServer;

public class PerfilDao {

	public List<Perfil> obtenerPerfiles(String coPerf) {

		return null;

	}

	public Perfil obtenerPerfil(String coPerf) {
		return null;
	}

	public void registrarPerfil(Perfil perfil) {

	}

	public void modificarPerfil(Perfil perfil) {

	}

	public void eliminarPerfil(String coPerf) {
		// TODO Auto-generated method stub

	}

	public Perfil obtenerPerfilXUsuario(String noUsua, String token) throws SQLException {

		Perfil perfil = null;

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_OBTIENE_PERFIL_X_USUARIO ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, noUsua);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			perfil = new Perfil();

			perfil.setCoPerf(rs.getString("CO_PERF"));
			perfil.setNoPerf(rs.getString("NO_PERF"));
			perfil.setUsCreaPerf(rs.getString("US_CREA"));
			perfil.setUsModiPerf(rs.getString("US_MODI"));
			perfil.setFeCreaPerf(rs.getTimestamp("FE_CREA").toLocalDateTime());
			perfil.setFeModiPerf(rs.getTimestamp("FE_MODI").toLocalDateTime());

		}

		conector = null;

		return perfil;
	}

}
