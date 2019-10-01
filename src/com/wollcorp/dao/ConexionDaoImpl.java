package com.wollcorp.dao;

import java.sql.Connection;

import com.wollcorp.beans.Conexion;
import com.wollcorp.conectores.Conector;

public class ConexionDaoImpl {
	
	private Conector conector = new Conector();
	private Connection connection;

	public Conexion conectarBD(String noUsua, String paUsua) {
		
		Conexion conexion = new Conexion();
		
		System.out.println(noUsua);
		System.out.println(paUsua);
		
		connection = conector.openConnection(noUsua, paUsua);
		
		if(connection != null ) {
			
			conexion.setConector(connection);
			conexion.setServidor(conector.getServer());
			conexion.setDataBase(conector.getDatabase());
			
		} else {
			
			conexion = null;
			
		}
		
		
		
		return conexion;
		
	}

	public boolean desconectarBD(String coUsua, String token) {
		
		Connection connection = Conector.conectores.get(token);
		
		return conector.closeConnection(connection);
		
	}

}
