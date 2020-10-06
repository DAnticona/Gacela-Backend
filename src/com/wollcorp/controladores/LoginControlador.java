package com.wollcorp.controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.wollcorp.conexion.ConexionSQLServer;
import com.wollcorp.dto.ConexionDto;
import com.wollcorp.globales.Login;
import com.wollcorp.globales.Token;

/**
 * Controlador del servicio Login (LoginService.java)
 * 
 * @author David Anticona - danticona@wollcorp.com
 * <ul>
 * 	<li><b>Created:</b> 17/10/2019</li>
 * 	<li><b>Modified:</b> 27/04/2020</li>
 * </ul>
 */
public class LoginControlador {

	private String token;
	private Connection conexion;

	/**
	 * Procedimiento para validar el login enviado al servicio LoginService,
	 * es el punto inicial de toda la validación
	 * 
	 * @param login
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ConexionDto validarLogin(String authorization)
			throws ClassNotFoundException, IOException, SQLException {

		String usuario;
		String password;

		ConexionDto conexionDto = new ConexionDto();

		usuario = Login.decode(authorization)[0];
		password = Login.decode(authorization)[1];

		conexion = (new ConexionSQLServer()).nuevaConexion(usuario, password);
		this.token = Token.generarToken(usuario);

		Token.tokens.add(token);
		ConexionSQLServer.conectores.put(this.token, this.conexion);

		conexionDto.setServidor(ConexionSQLServer.serverName);
		conexionDto.setDataBase(ConexionSQLServer.databaseName);
		conexionDto.setToken(this.token);
		conexionDto.setNoUsua(usuario);

		return conexionDto;

	}

}
