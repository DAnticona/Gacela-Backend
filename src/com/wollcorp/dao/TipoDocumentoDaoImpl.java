package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.TipoDocumento;
import com.wollcorp.conexion.ConexionSQLServer;
import com.wollcorp.globales.Log;

public class TipoDocumentoDaoImpl {

	public List<TipoDocumento> listarTiposDocumento(String token) throws SQLException {

		List<TipoDocumento> tiposDocumento = new ArrayList<TipoDocumento>();
		TipoDocumento tipoDocumento = null;
		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_OBTIENE_TIPOS_DOCUMENTOS";

		PreparedStatement ps = conector.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		Log.mensaje = "CONSULTA EXITOSA";
		Log.registraInfo();

		while (rs.next()) {

			tipoDocumento = new TipoDocumento();

			tipoDocumento.setCoTiDocu(rs.getString("CO_TI_DOCU"));
			tipoDocumento.setNoTiDocu(rs.getString("NO_TI_DOCU"));
			tipoDocumento.setAlTiDocu(rs.getString("AL_TI_DOCU"));
			tipoDocumento.setUsCrea(rs.getString("US_CREA"));
			tipoDocumento.setUsModi(rs.getString("US_MODI"));
			tipoDocumento.setFeCrea(rs.getTimestamp("FE_CREA").toLocalDateTime());
			tipoDocumento.setFeModi(rs.getTimestamp("FE_MODI").toLocalDateTime());

			tiposDocumento.add(tipoDocumento);

		}

		conector = null;
		return tiposDocumento;

	}

}
