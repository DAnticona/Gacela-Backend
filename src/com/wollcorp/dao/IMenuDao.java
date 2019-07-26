package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Menu;

public interface IMenuDao {
	   
	public List<Menu> listarMenus(String coMenu, String token);
	
	public List<Menu> obtenerMenusXPerfil(String coPerf, String token);
	   
	public void registrarMenu(Menu menu, String token);

	public void modificarMenu(Menu menu, String token);
	   
	public void eliminarMenu(int coMenu, String token);
}
