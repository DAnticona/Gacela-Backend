package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    
    //Variable de conexion
	private Connection connection;

	//Cadena de Conexion
	private String connectionUrl;
    
    //Errores
    private String error = null;
    
    //Informativos
    private String info = null;
        
    public SQLDatabaseConnection(String user, String password) {
    	
        connectionUrl = "jdbc:sqlserver://delfines\\exactus;database=GACELA";
        
        info = null;
        
        error = null;

        try {
        	//Carga clase de Maven
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        	
        	//Conecta
        	connection = DriverManager.getConnection(connectionUrl,user,password);
        	
        	info = "Conectado a la BD - Connection: " + connection;
		
        } catch (SQLException e) {
        	
            error = "Exception: " + e.toString() + 
            			 "\nMessage: " + e.getMessage() + 
            			 "\nSQLState: " + e.getSQLState() + 
            			 "\nError Code: " + e.getErrorCode();
            
        } catch (ClassNotFoundException e) {
        	
        	error = "Exception: " + e.toString() + 
        				"\nMessage: " + e.getMessage();
        	
		}
    }
    
    
    public void closeConnection() {
    	
    	error = null;
    	
    	try {
    		
    		getConnection().close();
    		
    	}catch(Exception e){
    		
    		error = "Exception: " + e.toString() + "Message: " + e.getMessage();
    		
    	}
    }
    
    
	public Connection getConnection() {
		return connection;
	}
	
	
    public String getError() {
		return error;
	}


	public String getInfo() {
		
		return info;
		
	}

}
