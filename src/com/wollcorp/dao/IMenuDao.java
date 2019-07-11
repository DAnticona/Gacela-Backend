package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Menu;

public interface IMenuDao {
	   
	public List<Menu> listarMenus(String coMenu);
	
	public List<Menu> obtenerMenusXPerfil(String coPerf);
	   
	public void registrarMenu(Menu menu);

	public void modificarMenu(Menu menu);
	   
	public void eliminarMenu(int coMenu);
}
