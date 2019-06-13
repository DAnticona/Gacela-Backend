package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import com.wollcorp.globales.Log;

public class SQLDatabaseConnection {
    
    //Variable de conexion
	private Connection connection;

	//Cadena de Conexion
	private String connectionUrl;
    
    private Date fechaSistema = new Date();
    
    //Clase que registra los eventos
    private Log log = new Log();
    
    //Mensaje de respuesta
    private String mensaje; 
    private int errorCode;
        
    public SQLDatabaseConnection(String user, String password) {
    	
        //connectionUrl = "jdbc:sqlserver://delfines\\exactus;database=GACELA";
    	connectionUrl = "jdbc:sqlserver://localhost\\EXACTUS:1433;database=GACELA";
        mensaje = null;

        try {
        	//Carga clase de Maven
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        	
        	//Conecta
        	connection = DriverManager.getConnection(connectionUrl, user, password);
        	
        	mensaje = "USUARIO:" + user + 
					" - CONECTADO A LA BD";
        	
        	log.registraInfo(fechaSistema, mensaje);
		
        } catch (SQLException e) {
        	
        	errorCode = e.getErrorCode();
        	
        	mensaje = "EXCEPTION : " + e.toString() +
		 			" - MESSAGE: " + e.getMessage() + 
		 			" - SQLSTATE: " + e.getSQLState() + 
		 			" - ERROR CODE: " + e.getErrorCode();
        	
        	log.registraError(fechaSistema, mensaje, this.getClass().getName());
            
        } catch (ClassNotFoundException e) {
        	
        	errorCode = -1;
        	
        	mensaje = "EXCEPTION: " + e.toString() + 
					" - MESSAGE: " + e.getMessage();
        	
        	log.registraError(fechaSistema, mensaje, this.getClass().getName());
        	
		}
    }
    
    
    public void closeConnection() {
    	
    	mensaje = null;
    	
    	try {
    		
    		getConnection().close();
    		
    		mensaje = "CONEXIÓN CERRADA";
    		
    		log.registraInfo(fechaSistema, mensaje);
    		
    	} catch(Exception e){
    		
    		errorCode = -1;
    		
    		mensaje = "EXCEPTION: " + e.toString() + 
					" - MESSAGE: " + e.getMessage();
    		
    		log.registraError(fechaSistema, mensaje, this.getClass().getName());
    		
    	}
    }
    
    
	public Connection getConnection() {
		
		return connection;
		
	}


	public String getMensaje() {
		
		return mensaje;
		
	}


	public int getErrorCode() {
		
		return errorCode;
		
	}

}
