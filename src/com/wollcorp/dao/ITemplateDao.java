package com.wollcorp.dao;

import java.util.List;

public interface ITemplateDao {
	
	public List<?> listar(String token);
	   
	public void registrar(Object objeto, String token);

	public void modificar(Object objeto, String token);
	   
	public void eliminar(Object codigo, String token);
	
}
