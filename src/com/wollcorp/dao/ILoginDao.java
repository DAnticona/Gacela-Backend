package com.wollcorp.dao;

import java.sql.Connection;

public interface ILoginDao {
	
	public Connection conectarBD(String coUsua, String paUsua);
	
	public boolean desconectarBD(String coUsua, String token);

}
