package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Servicio;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class ServicioDaoImpl implements ITemplateDao{

	@Override
	public List<Servicio> listar(String token) {

		List<Servicio> servicios = new ArrayList<Servicio>();
		Servicio servicio = null;
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "SP_OBTIENE_SERVICIOS_FORECAST";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
						
			while(rs.next()) {
				
				servicio = new Servicio();
				
				servicio.setCoServ(rs.getString("CO_SERV"));
				servicio.setNoServ(rs.getString("NO_SERV"));
				servicio.setFgActi(rs.getString("FG_ACTI"));
				servicio.setUsCreaServ(rs.getString("US_CREA"));
				servicio.setUsModiServ(rs.getString("US_MODI"));
				servicio.setFeCreaServ(rs.getTimestamp("FE_CREA").toLocalDateTime());
				servicio.setFeModiServ(rs.getTimestamp("FE_MODI").toLocalDateTime());
				
				servicios.add(servicio);
				
			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		} catch(NullPointerException e1) {
			
			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
		return servicios;
	}

	@Override
	public void registrar(Object objeto, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Object objeto, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Object codigo, String token) {
		// TODO Auto-generated method stub
		
	}

}
