package com.wollcorp.dao;

import com.wollcorp.beans.Persona;

public interface IPersonaDao {

	   public Persona buscarPersonas(String coPers);
		   
		   
	   public void registrarPersona(Persona persona);
		   
		   
	   public void modificarPersona(Persona persona);
		   
		   
	   public void eliminaPersona(String coPers);
}
