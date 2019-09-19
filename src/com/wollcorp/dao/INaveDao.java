package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Nave;

public interface INaveDao {
	
	public List<Nave> listar(String token);
	   
	public void registrar(Nave menu, String token);

	public void modificar(Nave menu, String token);
	   
	public void eliminar(Nave menu, String token);
	
}
