package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Puerto;
import com.wollcorp.beans.Servicio;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class ServicioDaoImpl {

	public List<Servicio> listar(String token) {

		List<Servicio> servicios = new ArrayList<Servicio>();
		Servicio servicio = null;
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "SP_OBTIENE_SERVICIOS";
		
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
				servicio.setFgFarEast(rs.getString("FG_FAR_EAST"));
				
				servicio.setPuertos(obtienePuertosXServicio(servicio.getCoServ(), token));
				
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

	public void registrar(Servicio servicio, String token) {
		// TODO Auto-generated method stub
		
	}

	public void modificar(Servicio servicio, String token) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Servicio servicio, String token) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Puerto> obtienePuertosXServicio(String coServ, String token) {
		
		List<Puerto> puertos = new ArrayList<Puerto>();
		Puerto puerto = null;

		Connection conector = Conector.conectores.get(token);
		String sql = "SP_OBTIENE_PUERTOS_X_SERVICIOS ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, coServ);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
						
			while(rs.next()) {
				
				puerto = new Puerto();
				
				puerto.setCoPuer(rs.getString("CO_PUER"));
				puerto.setNoPuer(rs.getString("NO_PUER"));
				puerto.setFgActi(rs.getString("FG_ACTI"));
				puerto.setCoSol(rs.getString("CO_SOL"));
				puerto.setCoIso(rs.getString("CO_ISO"));
				
				puertos.add(puerto);
				
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
		
		return puertos;
	}

}
