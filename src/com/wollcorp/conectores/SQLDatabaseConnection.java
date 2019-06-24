package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class SQLDatabaseConnection {
    
    //Variable de conexion
	private Connection connection;

	//Cadena de Conexion
	private String connectionUrl;
        
    public SQLDatabaseConnection(String user, String password) {
    	
        connectionUrl = "jdbc:sqlserver://delfines\\exactus;database=GACELA";
    	//connectionUrl = "jdbc:sqlserver://localhost\\EXACTUS:1433;database=GACELA";

        try {
        	//Carga clase de Maven
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        	
        	//Conecta
        	connection = DriverManager.getConnection(connectionUrl, user, password);
        	
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
