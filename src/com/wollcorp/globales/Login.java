package com.wollcorp.globales;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author danticona
 *
 */
public class Login {
	
	
	
	/**
	 * Decodifica la cabecera authorization para obtener el usuario y el password para BD
	 * @param auth: de la cabecera authorization basic:...
	 * @return un arreglo de String en donde el primer elemento es el usuario y el segundo es el password
	 */
    public static String[] decode(String auth) {
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
	

}
