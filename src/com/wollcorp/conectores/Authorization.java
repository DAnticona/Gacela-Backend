package com.wollcorp.conectores;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class Authorization {
	

	
	public String generarToken(String noUsua) {
		
		String uid;
		Date iat;
		Date exp;
		String key;
		
		String token = null;
		
		uid = noUsua;
		iat = new Date();
		exp = null;
		key = "W0LLC0RP$GAC3L42019$";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(iat);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		exp = calendar.getTime();
		
		try {
			
		    Algorithm algorithm = Algorithm.HMAC256(key);
		    token = JWT.create()
		        .withClaim("uid", uid)
		        .withClaim("iat", iat)
		        .withClaim("exp", exp)
		        .sign(algorithm);
		    
		    
		} catch (JWTCreationException exception){
			
			token = null;
			((Log)Globales.variablesGlobales.get("log")).setMensaje("ERROR AL GENERAR EL TOKEN PARA EL USUARIO: " + noUsua);
			((Log)Globales.variablesGlobales.get("log")).setException(null);
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
		return token;
	}
	
	
	
    public static String[] decodeLogin(String auth) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
    	auth = auth.replaceFirst("[B|b]asic ", "");
        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
        
        //If the decode fails in any case
        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }
 
        //Now we can convert the byte[] into a splitted array :
        //  - the first one is login,
        //  - the second one password
        String[] login = new String(decodedBytes).split(":", 2);
        return login;
    }
    
    
    public static boolean tokenValido(String token) {
    	
    	boolean esValido = false;
    	
	    DecodedJWT jwt = JWT.decode(token);
	    
	    System.out.println("Algoritmo: " + jwt.getAlgorithm());
	    System.out.println("Type: " + jwt.getType());
	    System.out.println("uid: " + jwt.getClaim("uid").asString());
	    System.out.println("iat: " + jwt.getClaim("iat").asDate());
	    System.out.println("exp: " + jwt.getClaim("exp").asDate());
    	
    	return esValido;
    	
    }
	
	

}
