package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wollcorp.TEMP.RatioDevolucionDTO;
import com.wollcorp.beans.RatioDevolucion;
import com.wollcorp.conectores.Conector;

public class RatioDevolucionDaoImpl {
	
	public void registraRatio(RatioDevolucionDTO ratio, String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);
		
		String sql1 = "EXEC SP_REGISTRA_RATIO_DEVOLUCION ?, ?, ?";
		
		PreparedStatement ps1 = conector.prepareStatement(sql1);
		
		ps1.setInt(1, ratio.getRatio2Sd());
		ps1.setInt(2, ratio.getRatio4Sd());
		ps1.setInt(3, ratio.getRatio4Sh());
		
		ps1.executeUpdate();
		
	}
	
	
	
	public RatioDevolucion obtieneRatio(String token) throws SQLException {
		
		RatioDevolucion ratio = new RatioDevolucion();
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_RATIO_DEVOLUCION";
		
		PreparedStatement ps = conector.prepareStatement(sql);
					
		ResultSet rs = ps.executeQuery();
 
		while (rs.next()) {
			
			ratio.setCoRatio(rs.getString(1));
			ratio.setRatio2Sd(rs.getInt(2));
			ratio.setRatio4Sd(rs.getInt(3));
			ratio.setRatio4Sh(rs.getInt(4));
			ratio.setUsCrea(rs.getString(5));
			ratio.setUsModi(rs.getString(6));
			ratio.setFeCrea(rs.getDate(7));
			ratio.setFeModi(rs.getDate(8));
			
		}
		
		return ratio;
		
	}
	
//	public void registraRatioTEMP(RatioDevolucionDTOTEMP ratio, String token) throws SQLException {
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		String sql1 = "EXEC SP_REGISTRA_RATIO_DEVOLUCION ?, ?, ?";
//		
//		PreparedStatement ps1 = conector.prepareStatement(sql1);
//		
//		ps1.setInt(1, ratio.getRa2Sd());
//		ps1.setInt(2, ratio.getRa4Sd());
//		ps1.setInt(3, ratio.getRa4Sh());
//		
//		ps1.executeUpdate();
//		
//	}
	
	
	
//	public RatioDevolucionTEMP obtieneRatioTEMP(String token) throws SQLException {
//		
//		RatioDevolucionTEMP ratio = new RatioDevolucionTEMP();
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		String sql = "EXEC SP_OBTIENE_RATIO_DEVOLUCION";
//		
//		PreparedStatement ps = conector.prepareStatement(sql);
//					
//		ResultSet rs = ps.executeQuery();
// 
//		while (rs.next()) {
//			
//			ratio.setRa2Sd(rs.getInt(1));
//			ratio.setRa4Sd(rs.getInt(2));
//			ratio.setRa4Sh(rs.getInt(3));
//			ratio.setUsCrea(rs.getString(4));
//			ratio.setUsModi(rs.getString(5));
//			ratio.setFeCrea(rs.getDate(6));
//			ratio.setFeModi(rs.getDate(7));
//			
//		}
//		
//		return ratio;
//		
//	}

}
