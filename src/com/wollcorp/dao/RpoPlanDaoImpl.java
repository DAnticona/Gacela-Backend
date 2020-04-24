package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.TEMP.RpoPlanDTO;
import com.wollcorp.beans.RpoPlan;
import com.wollcorp.conectores.Conector;

public class RpoPlanDaoImpl {
	
	public void registraRpoPlan(RpoPlanDTO rpo, String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);		
		
		String sql = "EXEC SP_REGISTRA_RPO_PLAN ?, ?, ?, ?, ?, ?, ?, ?";
		
		PreparedStatement ps2 = conector.prepareStatement(sql);
		
		ps2.setString(1, rpo.getCoRpo());
		ps2.setString(2, rpo.getAlNaveRpo());
		ps2.setString(3, rpo.getViajeRpo());
		ps2.setInt(4, rpo.getCa2SdRpo());
		ps2.setInt(5, rpo.getCa4SdRpo());
		ps2.setInt(6, rpo.getCa4ShRpo());
		ps2.setDate(7, new java.sql.Date(rpo.getEtaRpo().getTime()));
		ps2.setString(8, rpo.getFgActiRpo());
					
		ps2.executeUpdate();
		
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
			// rpoPlan.setCa4RhRpo(rs.getInt(7));
			rpoPlan.setEtaRpo(rs.getDate(8));
			rpoPlan.setFgActiRpo(rs.getString(9));
			
			rpoPlanes.add(rpoPlan);
			
		}
		
		return rpoPlanes;
		
	}
	
	public void eliminaRpoPlan(RpoPlanDTO rpo, String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);		
		
		String sql = "EXEC SP_ELIMINA_RPO_PLAN ?";
		
		PreparedStatement ps = conector.prepareStatement(sql);
		
		ps.setString(1, rpo.getCoRpo());
					
		ps.executeUpdate();
		
	}

}
