package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.forecast.UbicLinPod;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class UbicLinPodDao {
	
	public List<UbicLinPod> listar(String coServ, String token) {
		
		List<UbicLinPod> rows = new ArrayList<UbicLinPod>();
		
		UbicLinPod row = null;
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_ROWS_FCST ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			
			ps.setString(1, coServ);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
						
			while(rs.next()) {
				
				row = new UbicLinPod();
				
				row.setCoLine(rs.getString("CO_LINE"));
				row.setNoLine(rs.getString("NO_LINE"));
				row.setCoPuer(rs.getString("CO_PUER"));
				row.setCoPuerSol(rs.getString("CO_PUER_SOL"));
				row.setRow(rs.getInt("ROW_WSA1_E"));
				
				rows.add(row);
				
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
		
		return rows;
		
	}

}
