package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.SubMenu;
import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class SubMenuDaoImpl implements ISubMenuDao {

	@Override
	public List<SubMenu> listarSubMenus(String coSubMenu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubMenu> obtenerSubMenusXPerfil(String coPerf) {
		
		List<SubMenu> subMenus = new ArrayList<SubMenu>();
		SubMenu subMenu = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		
		String sql = "SP_OBTIENE_SUBMENU_X_PERFIL ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, coPerf);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			while(rs.next()) {
				
				subMenu = new SubMenu();
				
				subMenu.setCoMenu(rs.getString("CO_MENU"));
				subMenu.setNoMenu(rs.getString("NO_MENU"));
				subMenu.setAliasMenu(rs.getString("ALIAS_MENU"));
				subMenu.setRutaMenu(rs.getString("RUTA_MENU"));
				subMenu.setUsCreaMenu(rs.getString("US_CREA_MENU"));
				subMenu.setUsModiMenu(rs.getString("US_MODI_MENU"));
				subMenu.setFeCreaMenu(rs.getTimestamp("FE_CREA_MENU").toLocalDateTime());
				subMenu.setFeModiMenu(rs.getTimestamp("FE_MODI_MENU").toLocalDateTime());
				
				subMenu.setCoSubMenu(rs.getString("CO_SUB_MENU"));
				subMenu.setNoSubMenu(rs.getString("NO_SUB_MENU"));
				subMenu.setAliasSubMenu(rs.getString("AL_SUB_MENU"));
				subMenu.setRutaSubMenu(rs.getString("RUTA_SUB_MENU"));
				subMenu.setUsCreaSubMenu(rs.getString("US_CREA_SUB_MENU"));
				subMenu.setUsModiSubMenu(rs.getString("US_MODI_SUB_MENU"));
				subMenu.setFeCreaSubMenu(rs.getTimestamp("FE_CREA_SUB_MENU").toLocalDateTime());
				subMenu.setFeModiSubMenu(rs.getTimestamp("FE_MODI_SUB_MENU").toLocalDateTime());
				
				subMenus.add(subMenu);
				
			}
			
		} catch (SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}catch (NullPointerException e1) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e1.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e1.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
		conector = null;
		
		return subMenus;
		
	}
	
	@Override
	public void registrarSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarSubMenu(int coSubMenu) {
		// TODO Auto-generated method stub
		
	}


}
