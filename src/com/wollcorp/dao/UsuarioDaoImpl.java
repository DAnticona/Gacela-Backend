package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.beans.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	//List<Usuario> usuarios;
	
	Date fechaSistema = new Date();
	
	String error = null;
	
	public UsuarioDaoImpl() {
		
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		/*
		SQLDatabaseConnection conector = new SQLDatabaseConnection();
		
		if (conector.getError() == null) {
			
			System.out.println(fechaSistema + "- INFO -" + conector.getConnection());
			
		} else {
			
			System.err.println(fechaSistema + "- ERROR -" + conector.getError());
			
		}
		*/
		
		return null;
		
	}
	
	@Override
	public Usuario obtenerUsuario(String coUser) {
		
		Usuario usuario = null;
		
		String idUser = null;
		String noUser = null;
		
		SQLDatabaseConnection conector = null;
		
		String sql = "SELECT ID_USER, NO_USER FROM USUARIOS WHERE CO_USER = ?";
		
		if (conector.getError() == null) {
			
			System.out.println(fechaSistema + " - INFO - " + conector.getConnection());
			
			try {
				
				PreparedStatement ps = conector.getConnection().prepareStatement(sql);
				ps.setString(1, coUser);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					idUser = rs.getString("ID_USER");
					noUser = rs.getString("NO_USER");
					
				}
				
				usuario = new Usuario(coUser, idUser, noUser);
				
			} catch (SQLException e) {
				
				error = "Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Error Code: " + e.getErrorCode();
				
			}
			
		} else {
			System.err.println(fechaSistema + "- ERROR -" + conector.getError());
		}
		
		conector.closeConnection();
		
		return usuario;
		
	}
	
	@Override
	public void registrarUsuario(String codigo, String idUsuario, String nombre) {
		
		/*
		Usuario usuarioNuevo = new Usuario(codigo, idUsuario, nombre);
		usuarios.put(codigo, usuarioNuevo);
		System.out.println("Usuario con código: " + codigo + " registrado satisfactoriamente");
		*/
		
	}

	@Override
	public void actualizarUsuario(Usuario usuario, String nombre) {
		
		/*
		usuarios.get(usuario.getCodigo()).setNombre(nombre);
		System.out.println("Usuario con código: " + usuario.getCodigo() + " actualizado satisfactoriamente");
		*/
		
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		
		/*
		usuarios.remove(usuario.getCodigo());
		System.out.println("Usuario con código: " + usuario.getCodigo() + " eliminado satisfactoriamente");
		*/
		
	}

	@Override
	public String getError() {
		
		return error;
	}

}
