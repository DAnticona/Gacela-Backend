package com.wollcorp.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    
    //Variable de conexion
	private Connection connection;

	//Cadena de Conexion
	private String connectionUrl;
	
	//Parametros de conexion
    private String driver;
    private String server;
    private String database;
    private String user;
    private String password;
    
    //Errores
    private String error = null;
        
    public SQLDatabaseConnection() {
        
        driver = "jdbc:sqlserver://";
        server = "delfines\\exactus;";
        database = "database=GACELA;";
        user = "user=supervisor;";
        password = "password=system;";
        
        connectionUrl = driver + server + database + user + password;
        
        error = null;

        try {
        	
        	connection = DriverManager.getConnection(connectionUrl);
            
        	System.out.println(getConnection());
        	
        }catch (SQLException e) {
        	
            error = "Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Error Code: " + e.getErrorCode();
            
        }
    }
    
    
    public void closeConnection() {
    	
    	error = null;
    	
    	try {
    		
    		getConnection().close();
    		
    	}catch(Exception e){
    		
    		error = "Message: " + e.getMessage();
    		
    	}
    	
    }
    

	public Connection getConnection() {
		return connection;
	}
	
	
    public String getError() {
		return error;
	}

}
