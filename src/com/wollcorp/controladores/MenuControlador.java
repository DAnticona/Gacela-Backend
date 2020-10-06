package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.Menu;
import com.wollcorp.beans.SubMenu;
import com.wollcorp.dao.MenuDao;
import com.wollcorp.dto.MenuDto;
import com.wollcorp.globales.Token;

public class MenuControlador {

	public MenuDto obtenerMenusXPerfil(String token, String coPerf) throws Exception {

		MenuDto menuDto = new MenuDto();

		List<Menu> menus = null;

		if (Token.tokenValido(token)) {

			menus = (new MenuDao()).obtenerMenusXPerfil(coPerf, token);

			for (int i = 0; i < menus.size(); i++) {

				List<SubMenu> sm = (new MenuDao()).obtenerSubMenusXPerfil(menus.get(i).getCoMenu(), coPerf, token);

				menus.get(i).setSubMenus(sm);

			}
		}
		
		menuDto.setMenus(menus);
		return menuDto;
	}

//	private List<MenuDTOTEMP> generaMenus(List<Menu> menus) {
//
//		List<MenuDTOTEMP> menusDTO = new ArrayList<MenuDTOTEMP>();
//
//		for (Menu m : menus) {
//
//			MenuDTOTEMP menuDTO = new MenuDTOTEMP();
//
//			menuDTO.setCoMenu(m.getCoMenu());
//			menuDTO.setNoMenu(m.getNoMenu());
//			menuDTO.setAlMenu(m.getAlMenu());
//			menuDTO.setRuta(m.getRuta());
//
//			List<SubMenuDTO> subMenusDTO = new ArrayList<SubMenuDTO>();
//
//			for (SubMenu sm : m.getSubMenus()) {
//
//				SubMenuDTO subMenuDTO = new SubMenuDTO();
//
//				subMenuDTO.setCoSubMenu(sm.getCoMenu());
//				subMenuDTO.setNoSubMenu(sm.getNoMenu());
//				subMenuDTO.setAlSubMenu(sm.getAlMenu());
//				subMenuDTO.setRuta(sm.getRuta());
//				subMenuDTO.setCoMenuPadre(m.getCoMenu());
//				subMenuDTO.setIcono(sm.getIcono());
//
//				subMenusDTO.add(subMenuDTO);
//
//			}
//
//			menuDTO.setSubMenus(subMenusDTO);
//
//			menusDTO.add(menuDTO);
//
//		}
//
//		return menusDTO;
//
//	}

}
