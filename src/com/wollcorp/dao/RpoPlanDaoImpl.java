package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.RpoPlan;
import com.wollcorp.conectores.Conector;
import com.wollcorp.dto.RpoPlanDTO;

public class RpoPlanDaoImpl {
	
	public void registraRpoPlan(List<RpoPlanDTO> planes, String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);
		
		String sql1 = "DELETE FROM RPO_PLAN";
		
		PreparedStatement ps1 = conector.prepareStatement(sql1);
		
		ps1.executeUpdate();
		
		for(RpoPlanDTO rpoPlan : planes) {
			
			String sql2 = "EXEC SP_REGISTRA_RPO_PLAN ?, ?, ?, ?, ?, ?, ?, ?, ?";
			
			PreparedStatement ps2 = conector.prepareStatement(sql2);
			
			ps2.setString(1, rpoPlan.getCoRpo());
			ps2.setString(2, rpoPlan.getAlNaveRpo());
			ps2.setString(3, rpoPlan.getViajeRpo());
			ps2.setInt(4, rpoPlan.getCa2SdRpo());
			ps2.setInt(5, rpoPlan.getCa4SdRpo());
			ps2.setInt(6, rpoPlan.getCa4ShRpo());
			ps2.setInt(7, rpoPlan.getCa4RhRpo());
			ps2.setDate(8, new java.sql.Date(rpoPlan.getEtaRpo().getTime()));
			ps2.setString(9, rpoPlan.getFgActiRpo());
						
			ps2.executeUpdate();
			
		}
		
	}
	
	
	
	public List<RpoPlan> obtieneRpoPlan(String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_RPO_PLAN";
		
		PreparedStatement ps = conector.prepareStatement(sql);
					
		ResultSet rs = ps.executeQuery();
		
		List<RpoPlan> rpoPlanes = new ArrayList<RpoPlan>();
		
		// rpoPlanes.setPlanes(new ArrayList<RpoPlan>());
 
		while (rs.next()) {
			
			RpoPlan rpoPlan = new RpoPlan();
			
			rpoPlan.setCoRpo(rs.getString(1));
			rpoPlan.setAlNaveRpo(rs.getString(2));
			rpoPlan.setViajeRpo(rs.getString(3));
			rpoPlan.setCa2SdRpo(rs.getInt(4));
			rpoPlan.setCa4SdRpo(rs.getInt(5));
			rpoPlan.setCa4ShRpo(rs.getInt(6));
			rpoPlan.setCa4RhRpo(rs.getInt(7));
			rpoPlan.setEtaRpo(rs.getDate(8));
			rpoPlan.setFgActiRpo(rs.getString(9));
			
			rpoPlanes.add(rpoPlan);
			
		}
		
		return rpoPlanes;
		
	}

}
