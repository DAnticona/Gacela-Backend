package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.SubMenu;

public interface ISubMenuDao {
	   
	public List<SubMenu> listarSubMenus(String coSubMenu);
	
	public List<SubMenu> obtenerSubMenusXPerfil(String coPerf);
	   
	public void registrarSubMenu(SubMenu subMenu);

	public void modificarSubMenu(SubMenu subMenu);
	   
	public void eliminarSubMenu(int coSubMenu);
}
