package com.wollcorp.globales;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wollcorp.conectores.Conector;

public class Token {
	
	public static List<String> tokens = new ArrayList<String>();
	
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
		    
		    
		} catch (JWTCreationException e){
			
			token = null;
			Log.mensaje = "ERROR AL GENERAR EL TOKEN PARA EL USUARIO: " + noUsua;
			Log.exception = e.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
		System.out.println(token);
		
		return token;
	}
	
    
    
    
    /**
     * Valida si el token enviado por el Frontend es válido
     * @param token: string enviado desde el frontend
     * @return true = token válido, false = token inválido
     */
    public static boolean tokenValido(String token) {
    	
    	boolean esValido = false;
    	
	    DecodedJWT jwt = JWT.decode(token);
	    /*
	    System.out.println("Algoritmo: " + jwt.getAlgorithm());
	    System.out.println("Type: " + jwt.getType());
	    System.out.println("uid: " + jwt.getClaim("uid").asString());
	    System.out.println("iat: " + jwt.getClaim("iat").asDate());
	    System.out.println(new Date());
	    System.out.println("exp: " + jwt.getClaim("exp").asDate());
	    */
	    
	    // if(Token.tokens.get)
	    List<String> tkn = Token.tokens.stream().filter(x -> x.equals(token)).collect(Collectors.toList());
	    if(!tkn.isEmpty()) {
	    	if(Conector.conectores.get(token) != null) {
	    		
	    		if((new Date()).compareTo(jwt.getClaim("exp").asDate()) < 0) {
	    			
	    			esValido = true;
	    			Log.mensaje = "TOKEN VÁLIDO";
	    			Log.registraInfo();
	    			
	    		} else {
	    			
	    			Log.mensaje = "EL TIEMPO DE CONEXIÓN HA EXPIRADO";
	    			Log.registraError();
	    			
	    		}
	    		
	    	} else {
	    		
	    		Log.mensaje = "TOKEN NO VÁLIDO";
	    		Log.registraError();
	    		
	    	}
	    } else {
    		Log.mensaje = "TOKEN NO ENCONTRADO";
    		Log.registraError(); 
	    }
    	
    	return esValido;
    	
    }
    
}
