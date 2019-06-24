package com.wollcorp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wollcorp.conectores.SQLDatabaseConnection;
import com.wollcorp.globales.Globales;
import com.wollcorp.globales.Log;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	@Override
	public List<Usuario> listarUsuarios(){
		return null;
	}
	
	@Override
	public Usuario obtenerUsuario(String noUsua) {
		
		Usuario usuario = null;
		
		SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		
		String sql = "EXEC SP_OBTIENE_USUARIO ?";
			
		try {
			
			PreparedStatement ps = conector.getConnection().prepareStatement(sql);
			ps.setString(1, noUsua);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
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
				usuario.setFeNaci(rs.getDate("FE_NACI"));
				usuario.setUsCreaPers(rs.getString("US_CREA_PERS"));
				usuario.setUsModiPers(rs.getString("US_MODI_PERS"));
				usuario.setFeCreaPers(new java.util.Date(rs.getTimestamp("FE_CREA_PERS").getTime()));
				usuario.setFeModiPers(new java.util.Date(rs.getTimestamp("FE_MODI_PERS").getTime()));
				
				usuario.setPerfil(new Perfil());
				
				usuario.getPerfil().setCoPerf(rs.getString("CO_PERF"));
				usuario.getPerfil().setNoPerf(rs.getString("NO_PERF"));
				usuario.getPerfil().setUsCreaPerf(rs.getString("US_CREA_PERF"));
				usuario.getPerfil().setUsModiPerf(rs.getString("US_MODI_PERF"));
				usuario.getPerfil().setFeCreaPerf(new java.util.Date(rs.getTimestamp("FE_CREA_PERF").getTime()));
				usuario.getPerfil().setFeModiPerf(new java.util.Date(rs.getTimestamp("FE_MODI_PERF").getTime()));
				
			}
			
		} catch (SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
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

}
