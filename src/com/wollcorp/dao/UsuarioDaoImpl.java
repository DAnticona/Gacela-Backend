package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.wollcorp.globales.Log;
import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.Usuario;
import com.wollcorp.conectores.Conector;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	@Override
	public List<Usuario> listarUsuarios(){
		return null;
	}
	
	@Override
	public Usuario obtenerUsuario(String noUsua, String token) {
		
		Usuario usuario = null;
		//SQLDatabaseConnection conector = (SQLDatabaseConnection) Globales.variablesGlobales.get("conector");
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_USUARIO ?";
			
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, noUsua);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
			
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
		return usuario;
		
	}
	
	
	@Override
	public void registrarUsuario(Usuario usuario, String token) {
		

		
	}
	
	
	
	

	@Override
	public void actualizarUsuario(Usuario usuario, String token) {
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_ACTUALIZA_USUARIO ?, ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			ps.setString(1, usuario.getCoUsua());
			ps.setTimestamp(2, Timestamp.valueOf(usuario.getFeUltSes()));
			
			int rowsAffected = ps.executeUpdate();
			
			Log.mensaje = rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)";
			Log.registraInfo();
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		} catch (ClassCastException e1) {
			
			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
	}

	@Override
	public void eliminarUsuario(String noUsua, String token) {
		
		/*
		usuarios.remove(usuario.getCodigo());
		System.out.println("Usuario con código: " + usuario.getCodigo() + " eliminado satisfactoriamente");
		*/
		
	}

}
