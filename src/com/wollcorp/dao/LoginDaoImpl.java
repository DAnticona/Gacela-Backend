package com.wollcorp.dao;

import java.sql.Connection;

import com.wollcorp.conectores.Conector;

public class LoginDaoImpl implements ILoginDao {

	@Override
	public Connection conectarBD(String noUsua, String paUsua) {
		
		Conector sqlDatabaseConnection = new Conector();
		
		return sqlDatabaseConnection.openConnection(noUsua, paUsua);
	}

}
