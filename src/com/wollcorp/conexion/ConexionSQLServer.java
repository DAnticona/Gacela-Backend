package com.wollcorp.conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Clase de conexión a la base de datos
 * @author David Anticona - danticona@wollcorp.com
 * <ul>
 * 	<li><b>Created:</b> 17/10/2019</li>
 * 	<li><b>Modified:</b> 27/04/2020</li>
 * </ul>
 */
public class ConexionSQLServer {

	/**
	 * Almcaneca el path donde esta el archivo de propiedades.
	 */
	private static final String URL_CONFIG_FILE = "c:\\opt\\assets\\config\\config.properties";

	/**
	 * Almacena los pares token-conexion activos del sistema.
	 */
	public static Map<String, Connection> conectores = new HashMap<>();

	/**
	 * Almacena la configuración del driver de la base de datos.
	 */
	private static String driver;
	
	/**
	 * Almacena el nombre del servidor de base de datos.
	 */
	public static String serverName;
	
	/**
	 * Almacena el nombre de la base de datos.
	 */
	public static String databaseName;

	/**
	 * Método que genera una nueva conexión a la base de datos.
	 * @param user Nombre del usuario de base de datos.
	 * @param password Password del usuario de base de datos.
	 * @return Un objeto Connection.
	 * @throws IOException En caso haya problemas con el archivo de propiedades.
	 * @throws ClassNotFoundException En caso haya problema por la carga del
	 * driver
	 * @throws SQLException En caso haya problemas con la conexión a la base de
	 * datos
	 */
	public Connection nuevaConexion(String user, String password)
			throws IOException, ClassNotFoundException, SQLException {

		Properties propiedades = obtieneParametrosConexion();

		ConexionSQLServer.driver = propiedades.getProperty("driver");
		ConexionSQLServer.serverName = propiedades.getProperty("server");
		ConexionSQLServer.databaseName = propiedades.getProperty("database");

		String connectionUrl = driver + serverName + databaseName;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conexion = DriverManager
				.getConnection(connectionUrl, user, password);

		return conexion;

	}

	/**
	 * Método para cerrar una conexión a la base de datos.
	 * @param connection Objeto de conexión a cerrar.
	 * @throws SQLException En caso haya problemas con la conexión a la base de
	 * datos
	 */
	public void cerrarConexion(Connection connection) throws SQLException {

		connection.close();

	}

	/**
	 * Método que obtiene los parámetros del archivo de propiedades.
	 * @return El archivo de propiedades.
	 * @throws IOException En caso haya un problema en la lectura del archivo.
	 */
	private Properties obtieneParametrosConexion() throws IOException {

		InputStream inputStream = new FileInputStream(URL_CONFIG_FILE);

		Properties propiedades = new Properties();
		propiedades.load(inputStream);

		return propiedades;

	}

}
