package com.wollcorp.controladores;

import java.sql.Connection;
import java.sql.SQLException;

import com.wollcorp.conexion.ConexionSQLServer;
import com.wollcorp.globales.Token;

public class LogoutControlador {

	public void cerrarConexion(String noUsua, String token) throws Exception {

		if (Token.tokenValido(token)) {
			
			Connection conexion = ConexionSQLServer.conectores.get(token);
			(new ConexionSQLServer()).cerrarConexion(conexion);

		}

	}

}
