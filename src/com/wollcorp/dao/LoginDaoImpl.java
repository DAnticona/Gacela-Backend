package com.wollcorp.dao;

import java.sql.Connection;

import com.wollcorp.conectores.Conector;

public class LoginDaoImpl implements ILoginDao {
	
	private Conector conector = new Conector();

	@Override
	public Connection conectarBD(String noUsua, String paUsua) {
		
		return conector.openConnection(noUsua, paUsua);
		
	}

	@Override
	public boolean desconectarBD(String coUsua, String token) {
		
		Connection connection = Conector.conectores.get(token);
		
		return conector.closeConnection(connection);
		
	}

}
