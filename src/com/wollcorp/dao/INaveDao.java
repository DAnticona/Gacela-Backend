package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Nave;

public interface INaveDao {
	
	public List<Nave> listarNaves(String coMenu, String token);
	   
	public void registrarNave(Nave menu, String token);

	public void modificarNave(Nave menu, String token);
	   
	public void eliminarNave(int coMenu, String token);
	
}
