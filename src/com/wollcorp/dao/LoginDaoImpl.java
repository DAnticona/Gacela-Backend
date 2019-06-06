package com.wollcorp.dao;

import com.wollcorp.beans.Login;
import com.wollcorp.conectores.SQLDatabaseConnection;

public class LoginDaoImpl implements ILoginDao {
	
	private String error = null;
	private String info = null;
	
	private SQLDatabaseConnection conector = null;

	@Override
	public boolean login(Login login) {
		
		error = null;
		info = null;
		
		conector = new SQLDatabaseConnection(login.getUsuario(), login.getPassword());
		
		if(conector.getError() != null) {
			
			info = conector.getInfo();
			
			return false;
			
		} else {
			
			error = conector.getError();
			
			return true;
			
		}
	}
	
	public SQLDatabaseConnection getConector() {
		
		return conector;
		
	}
	
	public String getError() {
		
		return error;
		
	}

	public String getInfo() {
		
		return info;
		
	}

}
