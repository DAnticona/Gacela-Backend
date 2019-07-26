package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import com.wollcorp.globales.Log;

public class Conector {
	
	public static HashMap<String, Connection> conectores = new HashMap<String, Connection>();

	//CADENA DE CONEXION
	private String connectionUrl;
	
	//private final static Logger LOGGER = Logger.getLogger("com.wollcorp.conectores.SQLDatabaseConnection");
        
    public Connection openConnection(String user, String password) {
    	
        //VARIABLE DE CONEXION
    	Connection connection = null;
    	
        connectionUrl = "jdbc:sqlserver://gaceladb;database=GACELA";

        try {
        	//CARGA CLASE DE MAVEN
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        	
        	//CONECTA
        	connection = DriverManager.getConnection(connectionUrl, user, password);
        	
        	//LOGGER.log(Level.INFO, "USUARIO:" + user + " - CONECTADO A LA BD");
        	Log.mensaje = "USUARIO:" + user + " - CONECTADO A LA BD";
        	Log.registraInfo();
		
        } catch (SQLException e) {
        	
        	Log.mensaje= e.getMessage();
        	Log.exception = e.toString();
        	Log.codigo = e.getErrorCode();
        	Log.estado = e.getSQLState();
        	Log.nombreClase = this.getClass().getName();
        	Log.registraError();
            
        } catch (ClassNotFoundException e) {
        	
        	Log.mensaje= e.getMessage();
        	Log.exception = e.getException().toString();
        	Log.codigo = 0;
        	Log.estado = null;
        	Log.nombreClase = this.getClass().getName();
        	Log.registraError();
        	
		}
        
        return connection;
    }
    
    
    public void closeConnection(Connection connection) {
    	
    	try {
    		
    		connection.close();
    		
        	Log.mensaje = "CONEXIÓN CERRADA";
        	Log.registraInfo();
    		
    		
    	} catch(Exception e){
    		
    		Log.mensaje= e.getMessage();
        	Log.exception = e.toString();
        	Log.codigo = 0;
        	Log.estado = null;
        	Log.nombreClase = this.getClass().getName();
        	Log.registraError();
    		
    	}
    }

}
