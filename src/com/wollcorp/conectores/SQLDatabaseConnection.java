package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class SQLDatabaseConnection {

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
        
        return connection;
    }
    
    
    public void closeConnection(Connection connection) {
    	
    	try {
    		
    		connection.close();
    		
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

}
