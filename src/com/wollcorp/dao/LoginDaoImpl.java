package com.wollcorp.dao;

import com.wollcorp.beans.Login;
import com.wollcorp.conectores.SQLDatabaseConnection;

public class LoginDaoImpl implements ILoginDao {
 
	@Override
	public boolean login(Login login) {
		SQLDatabaseConnection conector = new SQLDatabaseConnection(login.getUsuario(), login.getPassword());
		
		if(conector.getError() != null) {
			return false;
		} else {
			return true;
		}
	}

}
