package com.wollcorp.globales;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Globales {
	
	public static HashMap<String, Object> variablesGlobales = new HashMap<String, Object>();
	public static List<String> tokens = new ArrayList<String>();
	public static HashMap<String, Connection> conectores = new HashMap<String, Connection>();

}
