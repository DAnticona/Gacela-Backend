package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Menu;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;

public class MenuDaoImpl implements IMenuDao {

	@Override
	public List<Menu> listarMenus(String coMenu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> obtenerMenusXPerfil(String coPerf) {
		
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu = null;
		
		//SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		Connection conector = Globales.conectores.get(Globales.tokens.get(0));
		
		String sql = "SP_OBTIENE_MENU_X_PERFIL ?";
			
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, coPerf);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			while(rs.next()) {
				
				menu = new Menu();
				
				menu.setCoMenu(rs.getString("CO_MENU"));
				menu.setNoMenu(rs.getString("NO_MENU"));
				menu.setLvMenu(rs.getInt("LV_MENU"));
				menu.setAlMenu(rs.getString("AL_MENU"));
				menu.setRuta(rs.getString("RUTA"));
				menu.setUsCreaMenu(rs.getString("US_CREA_MENU"));
				menu.setUsModiMenu(rs.getString("US_MODI_MENU"));
				menu.setFeCreaMenu(rs.getTimestamp("FE_CREA_MENU").toLocalDateTime());
				menu.setFeModiMenu(rs.getTimestamp("FE_MODI_MENU").toLocalDateTime());
				menu.setCoPadr(rs.getString("CO_PADR"));
				menu.setNrOrde(rs.getInt("NR_ORDE"));
				
				menus.add(menu);
				
			}
			
		} catch (SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		} catch (NullPointerException e1) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e1.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e1.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
		conector = null;
		
		return menus;
		
	}
	
	@Override
	public void registrarMenu(Menu Menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarMenu(Menu Menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMenu(int coMenu) {
		// TODO Auto-generated method stub
		
	}


}
