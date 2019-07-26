package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.Nave;
import com.wollcorp.beans.Servicio;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class NaveDaoImpl implements ITemplateDao {

	@Override
	public List<Nave> listar(String token) {
		
		List<Nave> naves = new ArrayList<Nave>();
		Nave nave = null;
		
		Connection conector = Conector.conectores.get(token);
		System.out.println(conector);
		String sql = "SP_OBTIENE_NAVES";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
						
			while(rs.next()) {
				
				nave = new Nave();
				
				nave.setCoNave(rs.getString("CO_NAVE"));
				nave.setNoNave(rs.getString("NO_NAVE"));
				nave.setAlNave(rs.getString("AL_NAVE"));
				nave.setFgActi(rs.getString("FG_ACTI_NAVE"));
				nave.setUsCreaNave(rs.getString("US_CREA_NAVE"));
				nave.setUsModiNave(rs.getString("US_MODI_NAVE"));
				nave.setFeCreaNave(rs.getTimestamp("FE_CREA_NAVE").toLocalDateTime());
				nave.setFeModiNave(rs.getTimestamp("FE_MODI_NAVE").toLocalDateTime());
				
				nave.setServicio(new Servicio());
				
				nave.getServicio().setCoServ(rs.getString("CO_SERV"));
				nave.getServicio().setNoServ(rs.getString("NO_SERV"));
				nave.getServicio().setFgActi(rs.getString("FG_ACTI_SERV"));
				nave.getServicio().setUsCreaServ(rs.getString("US_CREA_SERV"));
				nave.getServicio().setUsModiServ(rs.getString("US_MODI_SERV"));
				nave.getServicio().setFeCreaServ(rs.getTimestamp("FE_CREA_SERV").toLocalDateTime());
				nave.getServicio().setFeModiServ(rs.getTimestamp("FE_MODI_SERV").toLocalDateTime());
				
				naves.add(nave);
				
			}
			
		} catch(SQLException e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		} catch(NullPointerException e1) {
			
			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}
		
		return naves;
	}

	@Override
	public void registrar(Object objeto, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Object objeto, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Object codigo, String token) {
		// TODO Auto-generated method stub
		
	}

	

}
