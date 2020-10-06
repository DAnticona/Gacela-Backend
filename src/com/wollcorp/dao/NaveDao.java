package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Linea;
import com.wollcorp.beans.Nave;
// import com.wollcorp.beans.NaveTemp;
import com.wollcorp.beans.Servicio;
import com.wollcorp.conexion.ConexionSQLServer;

public class NaveDao {

//	public List<NaveTemp> listarNavesActivas(String token) throws SQLException {
//
//		List<NaveTemp> naves = new ArrayList<NaveTemp>();
//		NaveTemp nave = null;
//
//		Connection conector = ConexionSQLServer.conectores.get(token);
//		String sql = "SP_OBTIENE_NAVES_ACTIVAS";
//
//		PreparedStatement ps = conector.prepareStatement(sql);
//
//		ResultSet rs = ps.executeQuery();
//
//		Log.mensaje = "CONSULTA DE NAVES EXITOSA";
//		Log.registraInfo();
//
//		while (rs.next()) {
//
//			nave = new NaveTemp();
//
//			nave.setCoNave(rs.getString("CO_NAVE"));
//			nave.setNoNave(rs.getString("NO_NAVE"));
//			nave.setAlNave(rs.getString("AL_NAVE"));
//			nave.setFgActi(rs.getString("FG_ACTI"));
//			nave.setUsCreaNave(rs.getString("US_CREA"));
//			nave.setUsModiNave(rs.getString("US_MODI"));
//			nave.setFeCreaNave(rs.getTimestamp("FE_CREA").toLocalDateTime());
//			nave.setFeModiNave(rs.getTimestamp("FE_MODI").toLocalDateTime());
//
//			nave.setServicio(new Servicio());
//			nave.getServicio().setCoServ(rs.getString("CO_SERV"));
//
//			nave.setLinea(new Linea());
//			nave.getLinea().setCoLine(rs.getString("CO_LINE"));
//			nave.setFgPropLinea(rs.getString("FG_PROP"));
//
//			naves.add(nave);
//
//		}
//
//		return naves;
//	}
	
	
	public List<Nave> obtenerNaves(String token) throws SQLException {

		List<Nave> naves = new ArrayList<Nave>();
		Nave nave = null;

		Connection conector = ConexionSQLServer.conectores.get(token);
		String sql = "SP_OBTIENE_NAVES";

		PreparedStatement ps = conector.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			Servicio servicio = new Servicio();
			
			servicio.setCoServ(rs.getString("CO_SERV"));
			servicio.setNoServ(rs.getString("NO_SERV"));
			servicio.setFgFarEast(rs.getString("FG_FAR_EAST"));
			
			Linea linea = new Linea();
			
			linea.setCoLine(rs.getString("CO_LINE"));
			linea.setNoLine(rs.getString("NO_LINE"));
			linea.setCoIso(rs.getString("CO_ISO"));
			linea.setCoSol(rs.getString("CO_SOL"));
			linea.setFgProp(rs.getString("FG_PROP"));

			nave = new Nave();

			nave.setCoNave(rs.getString("CO_NAVE"));
			nave.setNoNave(rs.getString("NO_NAVE"));
			nave.setAlNave(rs.getString("AL_NAVE"));
			nave.setFgActi(rs.getString("FG_ACTI"));
			nave.setUsCrea(rs.getString("US_CREA"));
			nave.setUsModi(rs.getString("US_MODI"));
			nave.setFeCrea(rs.getTimestamp("FE_CREA").getTime());
			nave.setFeModi(rs.getTimestamp("FE_MODI").getTime());
			nave.setServicio(servicio);
			nave.setLinea(linea);

			naves.add(nave);

		}

		return naves;
	}

	public String registrarNave(Nave nave, String token) throws SQLException {
		
		String coNave = null;

		Connection conector = ConexionSQLServer.conectores.get(token);
		String sql = "SP_REGISTRA_NAVE ?, ?, ?, ?, ?, ?";

		PreparedStatement ps = conector.prepareStatement(sql);
		
		ps.setString(1, nave.getCoNave());
		ps.setString(2, nave.getNoNave());
		ps.setString(3, nave.getAlNave());
		ps.setString(4, nave.getServicio().getCoServ());
		ps.setString(5, nave.getFgActi());
		ps.setString(6, nave.getLinea().getCoLine());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			coNave = rs.getString("CO_NAVE");

		}
		
		return coNave;

	}


}
