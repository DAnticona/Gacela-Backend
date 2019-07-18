package com.wollcorp;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Bitacora {
	
    private final static Logger LOG = Logger.getLogger("com.wollcorp");
    private final static Logger LOG_CONECTORES = Logger.getLogger("com.wollcorp.conectores");
    private final static Logger LOG_CONTROLADORES = Logger.getLogger("com.wollcorp.controladores");
    private final static Logger LOG_DAO = Logger.getLogger("com.wollcorp.dao");
    private final static Logger LOG_GLOBALES = Logger.getLogger("com.wollcorp.globales");
    private final static Logger LOG_RESTSERVICES = Logger.getLogger("com.wollcorp.restServices");

    private final static Logger LOGGER = Logger.getLogger("com.wollcorp.Bitacora");

    public void iniciarBitacora() {
        try {
            
        	// LOS HANDLER (MANEJADORES) INDICAN A DONDE ENVIAR LA SALIDA YA SEA A CONSOLA O ARCHIVO
            // EN ESTE CASO CONSOLEHANDER ENVIA LOS LOGS A LA CONSOLA
            Handler consoleHandler = new ConsoleHandler();
            
            // CON EL MANEJADOR DE ARCHIVO, INDICAMOS A DONDE SE ENVIARAN LOS LOGS
            // EL SEGUNDO ARGUMENTO CONTROLA SI SE SOBREESCRIBE EL ARCHIVO O SE AGREGAN LOS LOSG AL FINAL
            // PARA SOBREESCRIBIR PASE UN TRUE, PARA AGREGAR AL FINAL FALSE PARA SOBREESCRIBIR TODO EL ARCHIVO
            Handler fileHandler = new FileHandler("./bitacora.log", false);

            // EL FORMATEADOR INDICA COMO PRESENTAR LOS DATOS, EN ESTE CASO USAREMOS EL FORMARO SENCILLO
            // EL CUAL ES MAS FACIL DE LEER, SI NO USAMOS ESTO EL LOG ESTARA EN FORMATO XML POR DEFECTO
            SimpleFormatter simpleFormatter = new SimpleFormatter();

            // SE ESPECIFICA QUE FORMATEADOR USARA EL MANEJADOR (HANDLER) DE ARCHIVO
            fileHandler.setFormatter(simpleFormatter);

            // ASIGNAMOS LOS HANDLES PREVIAMENTE DECLARADOS AL LOG *RAIZ* ESTO ES MUY IMPORTANTE YA QUE
            // PERMITIRA QUE LOS LOGS DE TODAS Y CADA UNA DE LAS CLASES DEL PROGRAMA QUE ESTEN EN ESE PAQUETE
            // O SUS SUBPAQUETES SE ALMACENEN EN EL ARCHIVO Y APARESCAN EN CONSOLA
            LOG.addHandler(consoleHandler);
            LOG.addHandler(fileHandler);

            // INDICAMOS A PARTIR DE QUE NIVEL DESEAMOS MOSTRAR LOS LOGS, PODEMOS ESPECIFICAR UN NIVEL EN ESPECIFICO
            // PARA IGNORAR INFORMACION QUE NO NECESITEMOS
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            LOGGER.log(Level.INFO, "BITACORA INICIADA");


        } catch (IOException ex) {
        	
            LOGGER.log(Level.SEVERE, "ERROR DE IO");
            
        } catch (SecurityException ex) {
        	
            LOGGER.log(Level.SEVERE, "ERROR DE SEGURIDAD");
            
        }
        
    }

}
