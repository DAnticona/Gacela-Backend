package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class SQLDatabaseConnection {
    
    //VARIABLE DE CONEXION
	private Connection connection;

	//CADENA DE CONEXION
	private String connectionUrl;
	
	//private final static Logger LOGGER = Logger.getLogger("com.wollcorp.conectores.SQLDatabaseConnection");
        
    public SQLDatabaseConnection(String user, String password) {
    	
        connectionUrl = "jdbc:sqlserver://gaceladb;database=GACELA";

        try {
        	//CARGA CLASE DE MAVEN
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        	
        	//CONECTA
        	connection = DriverManager.getConnection(connectionUrl, user, password);
        	
        	//LOGGER.log(Level.INFO, "USUARIO:" + user + " - CONECTADO A LA BD");
        	((Log)Globales.variablesGlobales.get("log")).setMensaje("USUARIO:" + user + " - CONECTADO A LA BD");
        	((Log)Globales.variablesGlobales.get("log")).registraInfo();
		
        } catch (SQLException e) {
        	
        	((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
        	((Log)Globales.variablesGlobales.get("log")).setException (e.toString());
        	((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
        	((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
        	((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
        	((Log)Globales.variablesGlobales.get("log")).registraError();
            
        } catch (ClassNotFoundException e) {
        	
        	((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
        	((Log)Globales.variablesGlobales.get("log")).setException (e.getException().toString());
        	((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
        	((Log)Globales.variablesGlobales.get("log")).setEstado(null);
        	((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
        	((Log)Globales.variablesGlobales.get("log")).registraError();
        	
		}
    }
    
    
    public void closeConnection() {
    	
    	try {
    		
    		getConnection().close();
    		
        	((Log)Globales.variablesGlobales.get("log")).setMensaje("CONEXIÓN CERRADA");
        	((Log)Globales.variablesGlobales.get("log")).registraInfo();
    		
    		
    	} catch(Exception e){
    		
        	((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
        	((Log)Globales.variablesGlobales.get("log")).setException (e.toString());
        	((Log)Globales.variablesGlobales.get("log")).setCodigo(-10);
        	((Log)Globales.variablesGlobales.get("log")).setEstado(null);
        	((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
        	((Log)Globales.variablesGlobales.get("log")).registraError();
    		
    	}
    }
    
    
	public Connection getConnection() {
		
		return connection;
		
	}

}
