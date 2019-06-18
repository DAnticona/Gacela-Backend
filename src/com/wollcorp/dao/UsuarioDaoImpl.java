package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;
import com.wollcorp.beans.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	Log log = new Log();
	
	public UsuarioDaoImpl() {
		
	}
	
	@Override
	public List<Usuario> obtenerUsuarios(String noUsua) {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get(0);
		
		String sql = "EXEC SP_OBTIENE_USUARIO ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, noUsua);
			
			ResultSet rs = ps.executeQuery();
			
			log.setMensaje("CONSULTA EXITOSA");
			log.setCodigoError(0);
			log.setEstadoError(null);
			log.setNombreClase(null);
			log.registraInfo();
			
			while(rs.next()) {
				
				usuario = new Usuario();
				
				usuario.setCoUsua(rs.getString("CO_USUA"));
				usuario.setNoUsua(rs.getString("NO_USUA"));
				usuario.setFeUltSes(rs.getDate("FE_ULT_SES"));
				usuario.setUsCreaUsua(rs.getString("US_CREA_USUA"));
				usuario.setUsModiUsua(rs.getString("US_MODI_USUA"));
				usuario.setFeCreaUsua(rs.getDate("FE_CREA_USUA"));
				usuario.setFeModiUsua(rs.getDate("FE_MODI_USUA"));
				
				usuario.setCoPers(rs.getString("CO_PERS"));
				usuario.setNuDocu(rs.getString("NU_DOCU"));
				usuario.setNoPers(rs.getString("NO_PERS"));
				usuario.setApPate(rs.getString("AP_PATE"));
				usuario.setApMate(rs.getString("AP_MATE"));
				usuario.setSexo(rs.getString("SEXO"));
				
				usuarios.add(usuario);
				
			}
			
			if(usuarios.isEmpty()) {
				
				log.setMensaje ("NINGUN USUARIO ENCONTRADO EN BASE DE DATOS");
				log.setCodigoError(-100);
				log.setEstadoError(null);
				log.setNombreClase(this.getClass().getName());
				log.registraError();
				
			} else {
				
				log.setMensaje (usuarios.size() + " USUARIOS ENCONTRADOS EN BD");
				log.setCodigoError(-200);
				log.setEstadoError(null);
				log.setNombreClase(this.getClass().getName());
				log.registraError();
				
			}
			
			
		} catch (SQLException e) {
			
			log.setMensaje (e.getMessage());
			log.setCodigoError(e.getErrorCode());
			log.setEstadoError(e.getSQLState());
			log.setNombreClase(this.getClass().getName());
			log.registraError();
			
		}
		
		conector = null;
		
		return usuarios;
		
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

}
