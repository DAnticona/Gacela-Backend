package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Linea;
import com.wollcorp.conexion.ConexionSQLServer;

public class LineaDaoImpl {
	
	public List<Linea> listarLineas(String token) throws SQLException {

		List<Linea> lineas = new ArrayList<Linea>();
		Linea linea = null;

		Connection conector = ConexionSQLServer.conectores.get(token);
		String sql = "SP_OBTIENE_LINEAS";

		PreparedStatement ps = conector.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			linea = new Linea();

			linea.setCoLine(rs.getString("CO_LINE"));
			linea.setNoLine(rs.getString("NO_LINE"));
			linea.setCoIso(rs.getString("CO_ISO"));
			linea.setCoSol(rs.getString("CO_SOL"));
			linea.setFgProp(rs.getString("FG_PROP"));
			linea.setUsCrea(rs.getString("US_CREA"));
			linea.setUsModi(rs.getString("US_MODI"));
			linea.setFeCrea(rs.getDate("FE_CREA"));
			linea.setFeModi(rs.getDate("FE_MODI"));
			linea.setFgActi(rs.getString("FG_ACTI"));
			linea.setNrOrde(rs.getInt("NR_ORDE"));

			lineas.add(linea);

		}

		return lineas;
	}

}

