package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.FileCabMTC1R999;
import com.wollcorp.beans.FileDetMTC1R999;
import com.wollcorp.conexion.ConexionSQLServer;

public class FileMTC1R999Dao {

	public String registrarFile(FileCabMTC1R999 file, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String coFile = null;

		String sql1 = "EXEC SP_REGISTRA_CAB_FILE_MTC1R999 ?, ?, ?";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ps1.setString(1, file.getNoFile());
		ps1.setDate(2, new java.sql.Date(file.getFeCargaFile().getTime()));
		ps1.setString(3, file.getFgActi());

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {

			coFile = rs.getString(1);

		}

		String sql2 = "EXEC SP_REGISTRA_DET_FILE_MTC1R999 ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

		PreparedStatement ps2 = conector.prepareStatement(sql2);

		for (FileDetMTC1R999 detalle : file.getDetalle()) {

			ps2.setString(1, coFile);
			ps2.setInt(2, detalle.getIdItem());
			ps2.setString(3, detalle.getDepot());
			ps2.setString(4, detalle.getNave());
			ps2.setString(5, detalle.getViaje());
			ps2.setString(6, detalle.getVsl_voy_s());
			ps2.setString(7, detalle.getBooking_no());
			ps2.setString(8, detalle.getRvs());
			ps2.setInt(9, detalle.getQty());
			ps2.setInt(10, detalle.getPick());
			ps2.setInt(11, detalle.getBalance());
			ps2.setString(12, detalle.getMode());
			ps2.setString(13, detalle.getMta());
			ps2.setString(14, detalle.getTpe());
			ps2.setString(15, detalle.getRct());
			ps2.setString(16, detalle.getPol());
			ps2.setString(17, detalle.getPod());
			ps2.setString(18, detalle.getDly());
			ps2.setDate(19, new java.sql.Date(detalle.getRelease().getTime()));
			ps2.setDate(20, new java.sql.Date(detalle.getCut_off().getTime()));
			ps2.setString(21, detalle.getDry_use());
			ps2.setString(22, detalle.getPre_cool());
			ps2.setString(23, detalle.getTemp());
			ps2.setInt(24, detalle.getVent());
			ps2.setString(25, detalle.getCommodity());
			ps2.setString(26, detalle.getSpecial_handling());
			ps2.setString(27, detalle.getCustomer_ac());
			ps2.setString(28, detalle.getCustomer_name());
			ps2.setString(29, detalle.getRemark());

			ps2.executeUpdate();

		}

		conector = null;

		return coFile;

	}

	public List<FileCabMTC1R999> listarFiles(String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<FileCabMTC1R999> files = new ArrayList<FileCabMTC1R999>();
		FileCabMTC1R999 file = null;

		String sql1 = "EXEC SP_OBTIENE_CAB_FILES_MTC1R999";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {

			file = new FileCabMTC1R999();

			file.setCoFile(rs.getString("CO_FILE"));
			file.setNoFile(rs.getString("NO_FILE"));
			file.setFeCargaFile(rs.getDate("FE_CARGA_FILE"));
			file.setFgActi(rs.getString("FG_ACTI"));

			files.add(file);

		}

		conector = null;

		return files;

	}

	public FileCabMTC1R999 getFileCab(String token, String coFile) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		FileCabMTC1R999 file = null;

		String sql1 = "EXEC SP_OBTIENE_CAB_FILE_MTC1R999 ?";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ps1.setString(1, coFile);

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {

			file = new FileCabMTC1R999();

			file.setCoFile(rs.getString("CO_FILE"));
			file.setNoFile(rs.getString("NO_FILE"));
			file.setFeCargaFile(rs.getDate("FE_CARGA_FILE"));
			file.setFgActi(rs.getString("FG_ACTI"));

			// files.add(file);

		}
		
		file.setDetalle(getFileDet(token, coFile));

		conector = null;

		return file;

	}
	
	
	public FileCabMTC1R999 getFileCabActivo(String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		FileCabMTC1R999 file = null;

		String sql1 = "EXEC SP_OBTIENE_CAB_FILE_MTC1R999_ACTIVO";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {

			file = new FileCabMTC1R999();

			file.setCoFile(rs.getString("CO_FILE"));
			file.setNoFile(rs.getString("NO_FILE"));
			file.setFeCargaFile(rs.getDate("FE_CARGA_FILE"));
			file.setFgActi(rs.getString("FG_ACTI"));

		}
		
		file.setDetalle(getFileDet(token, file.getCoFile()));

		conector = null;

		return file;

	}

	public List<FileDetMTC1R999> getFileDet(String token, String coFile) throws SQLException {
		
		Connection conector = ConexionSQLServer.conectores.get(token);
		
		List<FileDetMTC1R999> detalles = new ArrayList<FileDetMTC1R999>();
		FileDetMTC1R999 det = null;
		
		String sql1 = "EXEC SP_OBTIENE_DET_FILE_MTC1R999 ?";
		
		PreparedStatement ps1 = conector.prepareStatement(sql1);
		ps1.setString(1, coFile);

		ResultSet rs = ps1.executeQuery();
		
		while (rs.next()) {
			
			det = new FileDetMTC1R999();
			
			det.setCoFile(rs.getString("CO_FILE"));
			det.setIdItem(rs.getInt("ID_ITEM"));
			det.setDepot(rs.getString("DEPOT"));
			det.setNave(rs.getString("NAVE"));
			det.setViaje(rs.getString("VIAJE"));
			det.setVsl_voy_s(rs.getString("VSL_VOY_S"));
			det.setBooking_no(rs.getString("BOOKING_NRO"));
			det.setRvs(rs.getString("RVS"));
			det.setQty(rs.getInt("QTY"));
			det.setPick(rs.getInt("PICK"));
			det.setBalance(rs.getInt("BALANCE"));
			det.setMode(rs.getString("MODE"));
			det.setMta(rs.getString("MTA"));
			det.setTpe(rs.getString("TPE"));
			det.setRct(rs.getString("RCT"));
			det.setPol(rs.getString("POL"));
			det.setPod(rs.getString("POD"));
			det.setDly(rs.getString("DLY"));
			det.setRelease(rs.getDate("RELEASE"));
			det.setCut_off(rs.getDate("CUT_OFF"));
			det.setDry_use(rs.getString("DRY_USE"));
			det.setPre_cool(rs.getString("PRE_COOL"));
			det.setTemp(rs.getString("TEMP"));
			det.setVent(rs.getInt("VENT"));
			det.setCommodity(rs.getString("COMMODITY"));
			det.setSpecial_handling(rs.getString("SPECIAL_HANDLING"));
			det.setCustomer_ac(rs.getString("CUSTOMER_AC"));
			det.setCustomer_name(rs.getString("CUSTOMER_NAME"));
			det.setRemark(rs.getString("REMARK"));
			
			detalles.add(det);
			
		}
		
		conector = null;
		
		return detalles;
		
	}

}
