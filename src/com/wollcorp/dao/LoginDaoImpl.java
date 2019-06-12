package com.wollcorp.dao;

import com.wollcorp.beans.Login;
import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;

public class LoginDaoImpl implements ILoginDao {
	
	private SQLDatabaseConnection conector = null;

	@Override
	public boolean login(Login login) {
		
		conector = new SQLDatabaseConnection(login.getUsuario(), login.getPassword());
		
		if(conector.getConnection() == null) { //No se pudo conectar - Existe un Error
			
			return false;
			
		} else { //Conectado: Login Existe en BD
						
			//Registra el conector en las variables Globales del Sistema
			Globales.variablesGlobales.add(conector);
			
			return true;
			
		}
	}
	
	public SQLDatabaseConnection getConector() {
		
		return conector;
		
	}

}
