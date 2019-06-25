package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wollcorp.beans.Perfil;
import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class PerfilDaoImpl implements IPerfilDao {

	@Override
	public List<Perfil> obtenerPerfiles(String coPerf) {
		
		return null;
		
	}
	
	@Override
	public Perfil obtenerPerfil(String coPerf) {
		return null;
	}

	@Override
	public void registrarPerfil(Perfil perfil) {
		
		
	}

	@Override
	public void modificarPerfil(Perfil perfil) {
		
		
	}

	@Override
	public void eliminarPerfil(String coPerf) {
		// TODO Auto-generated method stub
		
	}
	
	public Perfil obtenerPerfilXUsuario(String coUsua) {
		
		Perfil perfil = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		
		String sql = "EXEC SP_OBTIENE_PERFIL_X_USUARIO ?";
		
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, coUsua);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			while(rs.next()) {
				
				perfil = new Perfil();
				
				perfil.setCoPerf(rs.getString("CO_PERF"));
				perfil.setNoPerf(rs.getString("NO_PERF"));
				perfil.setUsCreaPerf(rs.getString("US_CREA_PERF"));
				perfil.setUsModiPerf(rs.getString("US_MODI_PERF"));
				perfil.setFeCreaPerf(rs.getTimestamp("FE_CREA_PERF").toLocalDateTime());
				perfil.setFeModiPerf(rs.getTimestamp("FE_MODI_PERF").toLocalDateTime());
				
			}
			
		} catch (SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}catch (NullPointerException e1) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e1.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e1.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
		conector = null;
		
		return perfil;
	}

}
