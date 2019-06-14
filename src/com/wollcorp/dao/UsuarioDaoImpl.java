package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;
import com.wollcorp.beans.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	private Date fechaSistema = new Date();
	
	private int errorCode = 0;
	private String mensaje = null;
	
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
	public Usuario obtenerUsuario(String idUser) {
		
		Log log = new Log();
		
		Usuario usuario = new Usuario();
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get(0);
		
		String sql = "SELECT U.CO_USUA, U.ID_USUA," + 
						" P.CO_PERS, P.NU_DOCU," + 
						" P.NO_PERS, P.AP_PATE," +
						" P.AP_MATE, P.SEXO" + 
					" FROM USUA U" + 
					" INNER JOIN PERS P" + 
					" ON U.CO_PERS = P.CO_PERS" + 
					" WHERE U.ID_USUA = ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, idUser);
			
			ResultSet rs = ps.executeQuery();
			
			mensaje = "CONSULTA EXITOSA";
			log.registraInfo(fechaSistema, mensaje);
			
			while(rs.next()) {
				
				usuario.setCoUser(rs.getString("CO_USUA"));
				usuario.setIdUser(rs.getString("ID_USUA"));
				
				usuario.setCoPers(rs.getString("CO_PERS"));
				usuario.setNuDocu(rs.getString("NU_DOCU"));
				usuario.setNoPers(rs.getString("NO_PERS"));
				usuario.setApPate(rs.getString("AP_PATE"));
				usuario.setApMate(rs.getString("AP_MATE"));
				usuario.setSexo(rs.getString("SEXO"));
				
			}
			
			if(usuario.getCoUser() == null) {
				
				usuario = null;
				mensaje = "NO SE PUDO ENCONTRAR AL USUARIO EN LA BD";
				log.registraError(fechaSistema, mensaje, this.getClass().getName());
				
			} else {
				
				Globales.variablesGlobales.add(usuario);
				
				mensaje = "USUARIO ENCONTRADO EXITOSAMENTE EN BD";
				log.registraInfo(fechaSistema, mensaje);
				
			}
			
			
		} catch (SQLException e) {
			
			mensaje = "MESSAGE: " + e.getMessage() + 
					" - SQLSTATE: " + e.getSQLState() + 
					" - ERROR CODE: " + e.getErrorCode();
			
			errorCode = e.getErrorCode();
			
			log.registraError(fechaSistema, mensaje, this.getClass().getName());
			
		}
		
		conector = null;
		
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

	public int getErrorCode() {
		
		return errorCode;
	}

}
