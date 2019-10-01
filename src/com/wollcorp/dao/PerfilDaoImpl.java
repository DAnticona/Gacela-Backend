package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wollcorp.beans.Perfil;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class PerfilDaoImpl {

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
	
	public Perfil obtenerPerfilXUsuario(String coUsua, String token) {
		
		Perfil perfil = null;
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_PERFIL_X_USUARIO ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, coUsua);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
			
			while(rs.next()) {
				
				perfil = new Perfil();
				
				perfil.setCoPerf(rs.getString("CO_PERF"));
				perfil.setNoPerf(rs.getString("NO_PERF"));
				perfil.setUsCreaPerf(rs.getString("US_CREA"));
				perfil.setUsModiPerf(rs.getString("US_MODI"));
				perfil.setFeCreaPerf(rs.getTimestamp("FE_CREA").toLocalDateTime());
				perfil.setFeModiPerf(rs.getTimestamp("FE_MODI").toLocalDateTime());
				
			}
			
		} catch (SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}catch (NullPointerException e1) {
			
			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
		conector = null;
		
		return perfil;
	}

}
