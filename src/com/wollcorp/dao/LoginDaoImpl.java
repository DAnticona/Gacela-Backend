package com.wollcorp.dao;

import com.wollcorp.beans.Login;
import com.wollcorp.conectores.SQLDatabaseConnection;

public class LoginDaoImpl implements ILoginDao {
	
	private SQLDatabaseConnection conector = null;

	@Override
	public boolean login(Login login) {
		
		conector = new SQLDatabaseConnection(login.getUsuario(), login.getPassword());
		
		if(conector.getConnection() == null) { //No se pudo conectar - Existe un Error
			
			return false;
			
		} else { //Conectado: Login Existe en BD
			
			return true;
			
		}
	}
	
	public SQLDatabaseConnection getConector() {
		
		return conector;
		
	}

}
