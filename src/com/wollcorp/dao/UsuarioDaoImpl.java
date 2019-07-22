package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
		
		//SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		Connection conector = Globales.conectores.get(Globales.tokens.get(0));
		
		String sql = "EXEC SP_OBTIENE_USUARIO ?";
			
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, noUsua);
			
			ResultSet rs = ps.executeQuery();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje("CONSULTA EXITOSA");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
			while(rs.next()) {
				
				usuario = new Usuario();
				
				usuario.setCoUsua(rs.getString("CO_USUA"));
				usuario.setNoUsua(rs.getString("NO_USUA"));
				usuario.setFeUltSes(rs.getTimestamp("FE_ULT_SES").toLocalDateTime());
				usuario.setUsCreaUsua(rs.getString("US_CREA_USUA"));
				usuario.setUsModiUsua(rs.getString("US_MODI_USUA"));
				usuario.setFeCreaUsua(rs.getTimestamp("FE_CREA_USUA").toLocalDateTime());
				usuario.setFeModiUsua(rs.getTimestamp("FE_MODI_USUA").toLocalDateTime());
				
				usuario.setCoPers(rs.getString("CO_PERS"));
				usuario.setNuDocu(rs.getString("NU_DOCU"));
				usuario.setNoPers(rs.getString("NO_PERS"));
				usuario.setApPate(rs.getString("AP_PATE"));
				usuario.setApMate(rs.getString("AP_MATE"));
				usuario.setSexo(rs.getString("SEXO"));
				usuario.setFeNaci(rs.getDate("FE_NACI").toLocalDate());
				usuario.setUsCreaPers(rs.getString("US_CREA_PERS"));
				usuario.setUsModiPers(rs.getString("US_MODI_PERS"));
				usuario.setFeCreaPers(rs.getTimestamp("FE_CREA_PERS").toLocalDateTime());
				usuario.setFeModiPers(rs.getTimestamp("FE_MODI_PERS").toLocalDateTime());
				
				usuario.setPerfil(new Perfil());
				
				usuario.getPerfil().setCoPerf(rs.getString("CO_PERF"));
				usuario.getPerfil().setNoPerf(rs.getString("NO_PERF"));
				usuario.getPerfil().setUsCreaPerf(rs.getString("US_CREA_PERF"));
				usuario.getPerfil().setUsModiPerf(rs.getString("US_MODI_PERF"));
				usuario.getPerfil().setFeCreaPerf(rs.getTimestamp("FE_CREA_PERF").toLocalDateTime());
				usuario.getPerfil().setFeModiPerf(rs.getTimestamp("FE_MODI_PERF").toLocalDateTime());
				
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
		
		return usuario;
		
	}
	
	
	@Override
	public void registrarUsuario(String codigo, String idUsuario, String nombre) {
		

		
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		
		//SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		Connection conector = Globales.conectores.get(Globales.tokens.get(0));
		
		String sql = "EXEC SP_ACTUALIZA_USUARIO ?, ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, usuario.getCoUsua());
			ps.setTimestamp(2, Timestamp.valueOf(usuario.getFeUltSes()));
			
			int rowsAffected = ps.executeUpdate();
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje(rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)");
			((Log)Globales.variablesGlobales.get("log")).registraInfo();
			
		} catch(SQLException e) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(e.getErrorCode());
			((Log)Globales.variablesGlobales.get("log")).setEstado(e.getSQLState());
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		} catch (ClassCastException e1) {
			
			((Log)Globales.variablesGlobales.get("log")).setMensaje (e1.getMessage());
			((Log)Globales.variablesGlobales.get("log")).setException(e1.toString());
			((Log)Globales.variablesGlobales.get("log")).setCodigo(-1);
			((Log)Globales.variablesGlobales.get("log")).setEstado(null);
			((Log)Globales.variablesGlobales.get("log")).setNombreClase(this.getClass().getName());
			((Log)Globales.variablesGlobales.get("log")).registraError();
			
		}
		
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		
		/*
		usuarios.remove(usuario.getCodigo());
		System.out.println("Usuario con código: " + usuario.getCodigo() + " eliminado satisfactoriamente");
		*/
		
	}

}
