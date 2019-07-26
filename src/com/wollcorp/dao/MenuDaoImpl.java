package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Menu;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class MenuDaoImpl implements IMenuDao {

	@Override
	public List<Menu> listarMenus(String coMenu, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> obtenerMenusXPerfil(String coPerf, String token) {
		
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu = null;
		
		//SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		Connection conector = Conector.conectores.get(token);
		
		String sql = "SP_OBTIENE_MENU_X_PERFIL ?";
			
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, coPerf);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
			
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
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		} catch (NullPointerException e1) {
			
			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
		conector = null;
		
		return menus;
		
	}
	
	@Override
	public void registrarMenu(Menu Menu, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarMenu(Menu Menu, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMenu(int coMenu, String token) {
		// TODO Auto-generated method stub
		
	}


}
