package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.TEMP.MenuDTO;
import com.wollcorp.TEMP.SubMenuDTO;
import com.wollcorp.beans.Menu;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.dao.MenuDaoImpl;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.MenuRes;

public class MenuControlador {

	public MenuRes getMenuXPerfil(String token, String coPerf) {

		MenuRes menuRes = new MenuRes();

		List<Menu> menus;
		
		List<MenuDTO> menusDTO;

		if (Token.tokenValido(token)) {

			menus = (new MenuDaoImpl()).obtenerMenusXPerfil(coPerf, token);

			for (int i = 0; i < menus.size(); i++) {

				List<SubMenu> sm = (new MenuDaoImpl()).obtenerSubMenusXPerfil(menus.get(i).getCoMenu(), coPerf, token);

				menus.get(i).setSubMenus(sm);

			}
			
			menusDTO = generaMenus(menus);

		} else {

			menusDTO = null;
		}

		menuRes.setMenus(menusDTO);

		return menuRes;

	}

	private List<MenuDTO> generaMenus(List<Menu> menus) {

		List<MenuDTO> menusDTO = new ArrayList<MenuDTO>();

		for (Menu m : menus) {

			MenuDTO menuDTO = new MenuDTO();

			menuDTO.setCoMenu(m.getCoMenu());
			menuDTO.setNoMenu(m.getNoMenu());
			menuDTO.setAlMenu(m.getAlMenu());
			menuDTO.setRuta(m.getRuta());

			List<SubMenuDTO> subMenusDTO = new ArrayList<SubMenuDTO>();

			for (SubMenu sm : m.getSubMenus()) {

				SubMenuDTO subMenuDTO = new SubMenuDTO();

				subMenuDTO.setCoSubMenu(sm.getCoMenu());
				subMenuDTO.setNoSubMenu(sm.getNoMenu());
				subMenuDTO.setAlSubMenu(sm.getAlMenu());
				subMenuDTO.setRuta(sm.getRuta());
				subMenuDTO.setCoMenuPadre(m.getCoMenu());
				subMenuDTO.setIcono(sm.getIcono());

				subMenusDTO.add(subMenuDTO);

			}

			menuDTO.setSubMenus(subMenusDTO);

			menusDTO.add(menuDTO);

		}

		return menusDTO;

	}

}
