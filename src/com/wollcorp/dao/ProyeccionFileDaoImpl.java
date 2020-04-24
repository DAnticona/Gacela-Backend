package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wollcorp.TEMP.ProyeccionFileCabDTO;
import com.wollcorp.TEMP.ProyeccionFileDetDTO;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class ProyeccionFileDaoImpl {
	
	public String guardaProyeccionFile(ProyeccionFileCabDTO proyeccionFileCab, String token) throws SQLException {
		
		Connection conector = Conector.conectores.get(token);
		
		String coProyFile = null;
		
		String sql1 = "EXEC SP_REGISTRA_CAB_PROYECCION_FILE ?, ?, ?";
		
//		try {
			
			PreparedStatement ps1 = conector.prepareStatement(sql1);
			
			ps1.setString(1, proyeccionFileCab.getNoFile());
			ps1.setDate(2, new java.sql.Date(proyeccionFileCab.getFeCargaFile().getTime()));
			ps1.setString(3, proyeccionFileCab.getFgActi());
						
			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				
				coProyFile = rs.getString(1);
				
			}
			
			String sql2 = "EXEC SP_REGISTRA_DET_PROYECCION_FILE ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

			PreparedStatement ps2 = conector.prepareStatement(sql2);

			int rows = 0;

			for (ProyeccionFileDetDTO detalle : proyeccionFileCab.getDetalle()) {
				
				ps2.setString(1, coProyFile);
				ps2.setString(2, detalle.getAlNave());
				ps2.setString(3, detalle.getViaje());
				ps2.setDate(4, new java.sql.Date(detalle.getEta().getTime()));
				ps2.setInt(5, detalle.getCa2SdNoFe());
				ps2.setInt(6, detalle.getCa2SdNoFePick());
				ps2.setInt(7, detalle.getCa2SdFe());
				ps2.setInt(8, detalle.getCa2SdFePick());
				ps2.setInt(9, detalle.getCa4SdNoFe());
				ps2.setInt(10, detalle.getCa4SdNoFePick());
				ps2.setInt(11, detalle.getCa4SdFe());
				ps2.setInt(12, detalle.getCa4SdFePick());
				ps2.setInt(13, detalle.getCa4ShNoFe());
				ps2.setInt(14, detalle.getCa4ShNoFePick());
				ps2.setInt(15, detalle.getCa4ShFe());
				ps2.setInt(16, detalle.getCa4ShFePick());
				ps2.setInt(17, detalle.getCa4RhNoFe());
				ps2.setInt(18, detalle.getCa4RhNoFePick());
				ps2.setInt(19, detalle.getCa4RhFe());
				ps2.setInt(20, detalle.getCa4RhFePick());
				
				rows = rows + ps2.executeUpdate();

			}

			Log.mensaje = rows + " REGISTROS INSERTADOS";
			Log.registraInfo();
			
//		} catch (SQLException e) {
//
//			Log.mensaje = e.getMessage();
//			Log.exception = e.toString();
//			Log.codigo = e.getErrorCode();
//			Log.estado = e.getSQLState();
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//		} catch (NullPointerException e1) {
//
//			Log.mensaje = e1.getMessage();
//			Log.exception = e1.toString();
//			Log.codigo = 0;
//			Log.estado = null;
//			Log.nombreClase = this.getClass().getName();
//			Log.registraError();
//
//		}

		conector = null;

		return coProyFile;
		
	}

}
