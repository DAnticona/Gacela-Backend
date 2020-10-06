package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.wollcorp.beans.Usuario;
import com.wollcorp.conexion.ConexionSQLServer;

public class UsuarioDao {

	public List<Usuario> listarUsuarios() {
		return null;
	}

	public Usuario obtenerUsuarioPorUsername(Usuario usuario, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_OBTIENE_USUARIO ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, usuario.getNoUsua());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			usuario.setCoUsua(rs.getString("CO_USUA"));
			usuario.setNoUsua(rs.getString("NO_USUA"));
			usuario.setFeUltSes(rs.getTimestamp("FE_ULT_SES").toLocalDateTime());
			usuario.setUsCreaUsua(rs.getString("US_CREA_USUA"));
			usuario.setUsModiUsua(rs.getString("US_MODI_USUA"));
			usuario.setFeCreaUsua(rs.getTimestamp("FE_CREA_USUA").toLocalDateTime());
			usuario.setFeModiUsua(rs.getTimestamp("FE_MODI_USUA").toLocalDateTime());

			usuario.setCoPers(rs.getString("CO_PERS"));
			usuario.setTiDocu(rs.getString("CO_TI_DOCU"));
			usuario.setNuDocu(rs.getString("NU_DOCU"));
			usuario.setNoPers(rs.getString("NO_PERS"));
			usuario.setApPate(rs.getString("AP_PATE"));
			usuario.setApMate(rs.getString("AP_MATE"));
			usuario.setSexo(rs.getString("SEXO"));
			usuario.setFeNaci(rs.getDate("FE_NACI"));
			usuario.setUsCreaPers(rs.getString("US_CREA_PERS"));
			usuario.setUsModiPers(rs.getString("US_MODI_PERS"));
			usuario.setFeCreaPers(rs.getTimestamp("FE_CREA_PERS").toLocalDateTime());
			usuario.setFeModiPers(rs.getTimestamp("FE_MODI_PERS").toLocalDateTime());
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setRutaImagen(rs.getString("RUTA_IMAGEN"));
			usuario.setNoImagen(rs.getString("NO_IMAGEN"));

		}

		conector = null;
		return usuario;

	}

	public void registrarUsuario(Usuario usuario, String token) {

	}

	public void actualizarUsuario(Usuario usuario, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_ACTUALIZA_USUARIO ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		ps.setString(1, usuario.getCoUsua());
		ps.setTimestamp(2, Timestamp.valueOf(usuario.getFeUltSes()));

		ps.executeUpdate();

	}

	public void eliminarUsuario(String noUsua, String token) {

	}

}
