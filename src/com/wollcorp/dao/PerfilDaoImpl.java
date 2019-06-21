package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<Perfil> obtenerPerfilesXUsuario(String coUsua) {
		
		List<Perfil> perfiles = new ArrayList<Perfil>();
		Perfil perfil;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		
		String sql = "EXEC SP_OBTIENE_PERFILES_X_USUARIO ?";
		
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
				perfil.setFeCreaPerf(new java.util.Date(rs.getTimestamp("FE_CREA_PERF").getTime()));
				perfil.setFeModiPerf(new java.util.Date(rs.getTimestamp("FE_MODI_PERF").getTime()));
				
				perfiles.add(perfil);
				
			}
			
		} catch (SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
		conector = null;
		
		return perfiles;
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

}
