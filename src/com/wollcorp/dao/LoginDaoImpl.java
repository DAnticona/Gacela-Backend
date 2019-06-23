package com.wollcorp.dao;

import com.wollcorp.beans.Login;
import com.wollcorp.conectores.SQLDatabaseConnection;

public class LoginDaoImpl implements ILoginDao {
	
	private SQLDatabaseConnection conector = null;

	@Override
	public boolean isConnected(Login login) {
		
		conector = new SQLDatabaseConnection(login.getNoUsua(), login.getPasUsua());
		
		if(conector.getConnection() == null) { //NO SE PUDO CONECTAR - EXISTE UN ERROR
			
			return false;
			
		} else { //CONECTADO - LOGIN EXISTE EN LA BD
			
			return true;
			
		}
	}
	
	public SQLDatabaseConnection getConector() {
		
		return conector;
		
	}

}
