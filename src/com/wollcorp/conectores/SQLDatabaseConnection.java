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
        
    public SQLDatabaseConnection(String user, String password) {
        
        this.driver = "jdbc:sqlserver://";
        this.server = "delfines\\exactus;";
        this.database = "database=GACELA;";
        this.user = "user=" + user + ";";
        this.password = "password=" + password + ";";
        
        this.connectionUrl = this.driver + this.server + this.database + this.user + this.password;
        
        this.error = null;

        try {
        	
        	this.connection = DriverManager.getConnection(connectionUrl);
            
        	System.out.println(getConnection());
        	
        }catch (SQLException e) {
        	
            this.error = "Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Error Code: " + e.getErrorCode();
            
        }
    }
    
    
    public void closeConnection() {
    	
    	this.error = null;
    	
    	try {
    		
    		getConnection().close();
    		
    	}catch(Exception e){
    		
    		this.error = "Message: " + e.getMessage();
    		
    	}
    	
    }
    

	public Connection getConnection() {
		return this.connection;
	}
	
	
    public String getError() {
		return this.error;
	}

}
