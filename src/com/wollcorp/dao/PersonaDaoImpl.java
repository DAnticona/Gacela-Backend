package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.wollcorp.beans.Persona;
import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class PersonaDaoImpl implements IPersonaDao{
	
	private Date fechaSistema = new Date();
	
	private int errorCode = 0;
	private String mensaje = null;

	@Override
	public Persona obtenerPersona(String codigo) {
		
		Log log = new Log();
		
		Persona persona = null;
		
		String nuDocumento = null;
		String nombres = null;
		String apPaterno = null;
		String apMaterno = null;
		String sexo = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get(0);
		
		String sql = "SELECT CO_PERS, NU_DOCU, NO_PERS, AP_PATE, AP_MATE, SEXO " + 
					"FROM PERS WHERE CO_PERS = ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, codigo);
			
			ResultSet rs = ps.executeQuery();
			
			mensaje = "CONSULTA EXITOSA";
			log.registraInfo(fechaSistema, mensaje);
			
			while(rs.next()) {
				
				codigo = rs.getString("CO_PERS");
				nuDocumento = rs.getString("NU_DOCU");
				nombres = rs.getString("NO_PERS");
				apPaterno = rs.getString("AP_PATE");
				apMaterno = rs.getString("AP_MATE");
				sexo = rs.getString("SEXO");
				
			}
			
			persona = new Persona(codigo, nuDocumento, nombres, apPaterno, apMaterno, sexo);
			Globales.variablesGlobales.add(persona);
			
		} catch (SQLException e) {
			
			mensaje = "MESSAGE: " + e.getMessage() + 
					" - SQLSTATE: " + e.getSQLState() + 
					" - ERROR CODE: " + e.getErrorCode();
			
			errorCode = e.getErrorCode();
			
			log.registraError(fechaSistema, mensaje, this.getClass().getName());
			
		}
		conector = null;
		
		return persona;
		
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	

}
