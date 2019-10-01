package com.wollcorp.conectores;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import com.wollcorp.globales.Log;

public class Conector {

	Properties config = new Properties();
	InputStream configInput = null;

	public static HashMap<String, Connection> conectores = new HashMap<String, Connection>();

	// CADENA DE CONEXION
	private String connectionUrl;
	private String driver;
	private String server;
	private String database;

	// private final static Logger LOGGER =
	// Logger.getLogger("com.wollcorp.conectores.SQLDatabaseConnection");

	public Connection openConnection(String user, String password) {
		
		// RECUPERA LOS DATOS DE CONEXIÓN DEL ARCHIVO DE PROPIEDADES
		getConfig();

		// VARIABLE DE CONEXION
		Connection connection = null;

		driver = config.getProperty("driver");//"jdbc:sqlserver://";
		server = config.getProperty("server");//"gaceladb;";
		database = config.getProperty("database");//"database=GACELA";

		connectionUrl = driver + server + database;

		// connectionUrl = "jdbc:sqlserver://gaceladb;database=GACELA";

		try {
			// CARGA CLASE DE MAVEN
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// CONECTA
			connection = DriverManager.getConnection(connectionUrl, user, password);

			// LOGGER.log(Level.INFO, "USUARIO:" + user + " - CONECTADO A LA BD");
			Log.mensaje = "USUARIO:" + user + " - CONECTADO A LA BD";
			Log.registraInfo();
			

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
			connection = null;

		} catch (ClassNotFoundException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.getException().toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
			connection = null;

		}
		
		return connection;

		
	}

	public boolean closeConnection(Connection connection) {

		try {

			connection.close();

			Log.mensaje = "CONEXIÓN CERRADA";
			Log.registraInfo();

			return true;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			return false;
		}

	}

	private void getConfig() {

		try {
			
			configInput = new FileInputStream("c:\\opt\\assets\\config\\config.properties");
			config.load(configInput);
			
		} catch (Exception e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}

	}

	public String getServer() {
		return server;
	}

	public String getDatabase() {
		return database;
	}

}
