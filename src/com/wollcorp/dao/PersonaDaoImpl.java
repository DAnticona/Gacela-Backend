package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wollcorp.beans.Persona;
import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class PersonaDaoImpl implements IPersonaDao{

	@Override
	public Persona buscarPersonas(String codigo) {
		
		Persona persona = null;
		
		String nuDocumento = null;
		String nombres = null;
		String apPaterno = null;
		String apMaterno = null;
		String sexo = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection)Globales.variablesGlobales.get("conector");
		
		String sql = "SELECT CO_PERS, NU_DOCU, NO_PERS, AP_PATE, AP_MATE, SEXO " + 
					"FROM PERS WHERE CO_PERS = ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, codigo);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			while(rs.next()) {
				
				codigo = rs.getString("CO_PERS");
				nuDocumento = rs.getString("NU_DOCU");
				nombres = rs.getString("NO_PERS");
				apPaterno = rs.getString("AP_PATE");
				apMaterno = rs.getString("AP_MATE");
				sexo = rs.getString("SEXO");
				
			}
			
			persona = new Persona();
			Globales.variablesGlobales.put("persona", persona);
			
		} catch (SQLException e) {
			/*
			mensaje = "MESSAGE: " + e.getMessage() + 
					" - SQLSTATE: " + e.getSQLState() + 
					" - ERROR CODE: " + e.getErrorCode();
			
			errorCode = e.getErrorCode();
			
			log.registraError(fechaSistema, mensaje, this.getClass().getName());
			*/
		}
		conector = null;
		
		return persona;
		
	}

	@Override
	public void registrarPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaPersona(String coPers) {
		// TODO Auto-generated method stub
		
	}
}
