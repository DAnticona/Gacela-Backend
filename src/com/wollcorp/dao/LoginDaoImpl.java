package com.wollcorp.dao;

import java.sql.Connection;

import com.wollcorp.conectores.SQLDatabaseConnection;

public class LoginDaoImpl implements ILoginDao {

	@Override
	public Connection conectarBD(String noUsua, String paUsua) {
		
		SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection();
		
		return sqlDatabaseConnection.openConnection(noUsua, paUsua);
	}

}
