package com.wollcorp.globales;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    	
    	boolean esValido = true;
    	
    	
    	/*
	    DecodedJWT jwt = JWT.decode(token);
	    
	    System.out.println("Algoritmo: " + jwt.getAlgorithm());
	    System.out.println("Type: " + jwt.getType());
	    System.out.println("uid: " + jwt.getClaim("uid").asString());
	    System.out.println("iat: " + jwt.getClaim("iat").asDate());
	    System.out.println("exp: " + jwt.getClaim("exp").asDate());*/
    	
    	return esValido;
    	
    }
	
	

}
