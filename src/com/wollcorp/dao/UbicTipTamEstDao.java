package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.forecast.UbicTipTamEst;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class UbicTipTamEstDao {
	
public List<UbicTipTamEst> listar(String coServ, String token) {
		
		List<UbicTipTamEst> cols = new ArrayList<UbicTipTamEst>();
		
		UbicTipTamEst col = null;
		
		Connection conector = Conector.conectores.get(token);
		
		String sql = "EXEC SP_OBTIENE_COLS_FCST ?";
		
		try {
			
			PreparedStatement ps = conector.prepareStatement(sql);
			
			ps.setString(1, coServ);
			
			ResultSet rs = ps.executeQuery();
			
			Log.mensaje = "CONSULTA EXITOSA";
			Log.registraInfo();
						
			while(rs.next()) {
				
				col = new UbicTipTamEst();
				
				col.setCoSolTipo(rs.getString("CO_SOL_TIPO"));
				col.setTaCont(rs.getString("TA_CONT"));
				col.setNoEstCont(rs.getString("NO_ES_CONT"));
				col.setCol(rs.getInt("COL_WSA1_E"));
				
				cols.add(col);
				
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
		
		return cols;
		
	}

}
