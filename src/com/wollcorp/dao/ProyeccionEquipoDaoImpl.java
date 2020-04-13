package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wollcorp.conectores.Conector;
import com.wollcorp.dto.ProyeccionEquipoCabDTO;
import com.wollcorp.dto.ProyeccionEquipoDetDTO;

public class ProyeccionEquipoDaoImpl {
	
	public String registrarProyeccionEquiposCab(String token, ProyeccionEquipoCabDTO proyeccionEquipoCab) throws SQLException{
		
		Connection conector = Conector.conectores.get(token);

		String coProyEqui = null;

		String sql1 = "EXEC SP_REGISTRA_CAB_PROYECCION_EQUIPOS ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		
		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ps1.setString(1, proyeccionEquipoCab.getCoTiProy());
		ps1.setString(2, proyeccionEquipoCab.getCoProyEqui());
		ps1.setString(3, proyeccionEquipoCab.getCoProyVenta());
		ps1.setString(4, proyeccionEquipoCab.getCoFile());
		ps1.setDate(5, new java.sql.Date(proyeccionEquipoCab.getFeProy().getTime()));
		ps1.setString(6, proyeccionEquipoCab.getFgActi());
		ps1.setInt(7, proyeccionEquipoCab.getNroSem());
		ps1.setInt(8, proyeccionEquipoCab.getStock2Sd());
		ps1.setInt(9, proyeccionEquipoCab.getStock4Sd());
		ps1.setInt(10, proyeccionEquipoCab.getStock4Sh());
		
		ps1.setInt(11, proyeccionEquipoCab.getStock4Sh());
		ps1.setInt(12, proyeccionEquipoCab.getStock4Sh());
		ps1.setInt(13, proyeccionEquipoCab.getStock4Sh());
		
		ps1.setInt(14, proyeccionEquipoCab.getTo2SdNoFe());
		ps1.setInt(15, proyeccionEquipoCab.getTo2SdNoFePick());
		ps1.setInt(16, proyeccionEquipoCab.getTo2SdFe());
		ps1.setInt(17, proyeccionEquipoCab.getTo2SdFePick());
		ps1.setInt(18, proyeccionEquipoCab.getTo4SdNoFe());
		ps1.setInt(19, proyeccionEquipoCab.getTo4SdNoFePick());
		ps1.setInt(20, proyeccionEquipoCab.getTo4SdFe());
		ps1.setInt(21, proyeccionEquipoCab.getTo4SdFePick());
		ps1.setInt(22, proyeccionEquipoCab.getTo4ShNoFe());
		ps1.setInt(23, proyeccionEquipoCab.getTo4ShNoFePick());
		ps1.setInt(24, proyeccionEquipoCab.getTo4ShFe());
		ps1.setInt(25, proyeccionEquipoCab.getTo4ShFePick());
		ps1.setInt(26, proyeccionEquipoCab.getTo2SdBook());
		ps1.setInt(27, proyeccionEquipoCab.getTo4SdBook());
		ps1.setInt(28, proyeccionEquipoCab.getTo4ShBook());
		ps1.setInt(29, proyeccionEquipoCab.getTo2SdPick());
		ps1.setInt(30, proyeccionEquipoCab.getTo4SdPick());
		ps1.setInt(31, proyeccionEquipoCab.getTo4ShPick());
		ps1.setDate(32, new java.sql.Date(proyeccionEquipoCab.getFeEmptyReturn().getTime()));
		
		ps1.setInt(33, proyeccionEquipoCab.getNroDiasHabiles());
		
		ps1.setInt(34, proyeccionEquipoCab.getCa2SdEmptyRet());
		ps1.setInt(35, proyeccionEquipoCab.getCa4SdEmptyRet());
		ps1.setInt(36, proyeccionEquipoCab.getCa4ShEmptyRet());
		ps1.setInt(37, proyeccionEquipoCab.getAvailable2Sd());
		ps1.setInt(38, proyeccionEquipoCab.getAvailable4Sd());
		ps1.setInt(39, proyeccionEquipoCab.getAvailable4Sh());
		
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {

			coProyEqui = rs1.getString(1);

		}
		
		this.registrarProyeccionEquiposDet(token, proyeccionEquipoCab.getDetalles());

		conector = null;
				
		return coProyEqui;
	}
	
	
	
	public void registrarProyeccionEquiposDet (String token, List<ProyeccionEquipoDetDTO> detalles) throws SQLException{
		
		Connection conector = Conector.conectores.get(token);
		
		String sql1 = "DELETE FROM DET_PROY_EQUI WHERE CO_TI_PROY = ? AND CO_PROY_EQUI = ?";
		
		PreparedStatement ps1 = conector.prepareStatement(sql1);
		
		ps1.executeUpdate();
		
		String sql2 = "EXEC SP_REGISTRA_DET_PROYECCION_EQUIPOS ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		
		PreparedStatement ps2 = conector.prepareStatement(sql2);
		
		int rows = 0;

		for (ProyeccionEquipoDetDTO detalle : detalles) {

			ps2.setString(1, detalle.getCoTiProy());
			ps2.setString(2, detalle.getCoProyEqui());
			ps2.setInt(3, detalle.getIdItem());
			ps2.setString(4, detalle.getAlNave());
			ps2.setString(5, detalle.getViaje());
			ps2.setString(6, detalle.getFgRpoPlan());
			ps2.setInt(7, detalle.getCa2SdNoFe());
			ps2.setInt(8, detalle.getCa2SdNoFePick());
			ps2.setInt(9, detalle.getCa2SdFe());
			ps2.setInt(10, detalle.getCa2SdFePick());
			ps2.setInt(11, detalle.getCa4SdNoFe());
			ps2.setInt(12, detalle.getCa4SdNoFePick());
			ps2.setInt(13, detalle.getCa4SdFe());
			ps2.setInt(14, detalle.getCa4SdFePick());
			ps2.setInt(15, detalle.getCa4ShNoFe());
			ps2.setInt(16, detalle.getCa4ShNoFePick());
			ps2.setInt(17, detalle.getCa4ShFe());
			ps2.setInt(18, detalle.getCa4ShFePick());
			ps2.setInt(19, detalle.getCaRpo2Sd());
			ps2.setInt(20, detalle.getCaRpo4Sd());
			ps2.setInt(21, detalle.getCaRpo4Sh());
			ps2.setDate(22, new java.sql.Date(detalle.getEta().getTime()));
			
			rows = rows + ps2.executeUpdate();

		}
		
	}

}
