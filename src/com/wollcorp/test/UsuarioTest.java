/**
 * 
 */
package com.wollcorp.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.wollcorp.beans.Usuario;
import com.wollcorp.conexion.ConexionSQLServer;
import com.wollcorp.dao.UsuarioDao;
import com.wollcorp.globales.Token;

/**
 * @author danticona
 *
 */
public class UsuarioTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String noUsua = "supervisor";
		String password = "system";
		
		try {
			Connection conexion = (new ConexionSQLServer()).nuevaConexion(noUsua, password);
			String token = Token.generarToken(noUsua);
			Token.tokens.add(token);
			ConexionSQLServer.conectores.put(token, conexion);
			
			UsuarioDao usuarioDao = new UsuarioDao();
			
			Usuario usuario = new Usuario(noUsua);
			
			usuario = usuarioDao.obtenerUsuarioPorUsername(usuario, token);
			
			System.out.println(usuario);
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
