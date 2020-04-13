package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Linea;
import com.wollcorp.beans.Nave;
import com.wollcorp.beans.NaveTemp;
import com.wollcorp.beans.Servicio;
import com.wollcorp.conectores.Conector;
import com.wollcorp.dto.NaveDTO;
import com.wollcorp.globales.Log;

public class NaveDaoImpl {

	public List<NaveTemp> listarNavesActivas(String token) throws SQLException {

		List<NaveTemp> naves = new ArrayList<NaveTemp>();
		NaveTemp nave = null;

		Connection conector = Conector.conectores.get(token);
		String sql = "SP_OBTIENE_NAVES_ACTIVAS";

		PreparedStatement ps = conector.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		Log.mensaje = "CONSULTA DE NAVES EXITOSA";
		Log.registraInfo();

		while (rs.next()) {

			nave = new NaveTemp();

			nave.setCoNave(rs.getString("CO_NAVE"));
			nave.setNoNave(rs.getString("NO_NAVE"));
			nave.setAlNave(rs.getString("AL_NAVE"));
			nave.setFgActi(rs.getString("FG_ACTI"));
			nave.setUsCreaNave(rs.getString("US_CREA"));
			nave.setUsModiNave(rs.getString("US_MODI"));
			nave.setFeCreaNave(rs.getTimestamp("FE_CREA").toLocalDateTime());
			nave.setFeModiNave(rs.getTimestamp("FE_MODI").toLocalDateTime());

			nave.setServicio(new Servicio());
			nave.getServicio().setCoServ(rs.getString("CO_SERV"));

			nave.setLinea(new Linea());
			nave.getLinea().setCoLine(rs.getString("CO_LINE"));
			nave.setFgPropLinea(rs.getString("FG_PROP"));

			naves.add(nave);

		}

		return naves;
	}
	
	
	public List<Nave> listarNaves(String token) throws SQLException {

		List<Nave> naves = new ArrayList<Nave>();
		Nave nave = null;

		Connection conector = Conector.conectores.get(token);
		String sql = "SP_OBTIENE_NAVES";

		PreparedStatement ps = conector.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			nave = new Nave();

			nave.setCoNave(rs.getString("CO_NAVE"));
			nave.setNoNave(rs.getString("NO_NAVE"));
			nave.setAlNave(rs.getString("AL_NAVE"));
			nave.setFgActi(rs.getString("FG_ACTI"));
			nave.setCoServ(rs.getString("CO_SERV"));
			nave.setCoLine(rs.getString("CO_LINE"));
			nave.setUsCrea(rs.getString("US_CREA"));
			nave.setUsModi(rs.getString("US_MODI"));
			nave.setFeCrea(rs.getTimestamp("FE_CREA").getTime());
			nave.setFeModi(rs.getTimestamp("FE_MODI").getTime());

			naves.add(nave);

		}

		return naves;
	}

	public String registrar(NaveDTO nave, String token) throws SQLException {
		
		String coNave = null;

		Connection conector = Conector.conectores.get(token);
		String sql = "SP_REGISTRA_NAVE ?, ?, ?, ?, ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		
		ps.setString(1, nave.getCoNave());
		ps.setString(2, nave.getNoNave());
		ps.setString(3, nave.getAlNave());
		ps.setString(4, nave.getCoServ());
		ps.setString(5, nave.getFgActi());
		ps.setString(6, nave.getCoLine());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			coNave = rs.getString("CO_NAVE");

		}

		return coNave;

	}


	public void eliminar(NaveTemp nave, String token) {
		// TODO Auto-generated method stub

	}

}
