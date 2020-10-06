package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wollcorp.beans.Calendario;
import com.wollcorp.conexion.ConexionSQLServer;
import com.wollcorp.globales.Log;

public class CalendarioDaoImpl {

	public List<Calendario> listaCalendarios(String token, Date fechaIni, Date fechaFin) throws SQLException {

		List<Calendario> calendarios = new ArrayList<Calendario>();
		Calendario calendario = null;

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "SP_OBTIENE_CALENDARIO ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		
		ps.setDate(1, new java.sql.Date(fechaIni.getTime()));
		ps.setDate(2, new java.sql.Date(fechaFin.getTime()));

		ResultSet rs = ps.executeQuery();

		Log.mensaje = "CONSULTA CALENDARIO EXITOSA";
		Log.registraInfo();

		while (rs.next()) {

			calendario = new Calendario();

			calendario.setCodigo(rs.getString("CO_CALE"));
			calendario.setDiaSem(rs.getInt("CO_DIA_SEM"));
			calendario.setDia(rs.getInt("DIA"));
			calendario.setMes(rs.getInt("MES"));
			calendario.setAno(rs.getInt("ANO"));
			calendario.setFecha(rs.getDate("FECHA"));
			calendario.setFgFeriado(rs.getString("FG_FERIADO"));
			calendario.setNroSemAno(rs.getInt("NRO_SEM_ANO"));

			calendarios.add(calendario);

		}

		conector = null;

		return calendarios;

	}

}
