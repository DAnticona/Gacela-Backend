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
import com.wollcorp.conexion.ConexionSQLServer;
/**
 * Clase utilitaria, genera el token de seguridad y valida los mismos.
 * @author David Anticona - danticona@wollcorp.com
 * <ul>
 * 	<li><b>Created:</b> 17/10/2019</li>
 * 	<li><b>Modified:</b> 27/04/2020</li>
 * </ul>
 */
public class Token {

	public static List<String> tokens = new ArrayList<String>();

	/**
	 * M�todo que genera un token para la seguridad del sistema.
	 * @param noUsua
	 * @return El token generado.
	 * @throws JWTCreationException
	 */
	public static String generarToken(String noUsua)
			throws JWTCreationException {

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

		Algorithm algorithm = Algorithm.HMAC256(key);
		token = JWT.create()
				.withClaim("uid", uid)
				.withClaim("iat", iat)
				.withClaim("exp", exp)
				.sign(algorithm);

		return token;
	}

	/**
	 * Valida si el token enviado por el Frontend es v�lido.
	 * 
	 * @param token: string enviado desde el frontend.
	 * @return true = token v�lido, false = token inv�lido.
	 * @throws Exception en caso el token sea inv�lido.
	 */
	public static boolean tokenValido(String token) throws Exception {

		boolean esValido = false;

		DecodedJWT jwt = JWT.decode(token);
		/*
		 * System.out.println("Algoritmo: " + jwt.getAlgorithm());
		 * System.out.println("Type: " + jwt.getType()); System.out.println("uid: " +
		 * jwt.getClaim("uid").asString()); System.out.println("iat: " +
		 * jwt.getClaim("iat").asDate()); System.out.println(new Date());
		 * System.out.println("exp: " + jwt.getClaim("exp").asDate());
		 */

		List<String> tkn = Token.tokens.stream()
				.filter(x -> x.equals(token))
				.collect(Collectors.toList());

		if (!tkn.isEmpty()) {
			if (ConexionSQLServer.conectores.get(token) != null) {

				if ((new Date()).compareTo(jwt.getClaim("exp").asDate()) < 0) {
					esValido = true;
				} 
				else {
					throw new Exception("Token inv�lido");
				}

			}
			else {
				throw new Exception("Token inv�lido");
			}
		}
		else {
			throw new Exception("Token inv�lido");
		}

		return esValido;

	}

}
