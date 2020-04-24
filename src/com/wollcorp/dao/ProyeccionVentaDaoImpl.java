package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.TEMP.ProyeccionVentaCabDTO;
import com.wollcorp.TEMP.ProyeccionVentaDetDTO;
import com.wollcorp.beans.NaveTemp;
import com.wollcorp.beans.ProyeccionGenerada;
import com.wollcorp.beans.ProyeccionVentaCab;
import com.wollcorp.beans.ProyeccionVentaDet;
import com.wollcorp.beans.Servicio;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class ProyeccionVentaDaoImpl {

	public List<ProyeccionGenerada> generaResumenProyeccion(String token, String coFile) throws SQLException {

		Connection conector = Conector.conectores.get(token);

		List<ProyeccionGenerada> proyeccionGenerada = new ArrayList<ProyeccionGenerada>();
		// proyeccionCab.setDetalles(new ArrayList<ProyeccionVentaDet>());

		// String sql = "EXEC SP_GENERA_PROYECCION_VENTA_X_FILE ?";
		String sql = "EXEC SP_GENERA_RESUMEN_PROYECCION ?";

		PreparedStatement ps = conector.prepareStatement(sql);

		ps.setString(1, coFile);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			ProyeccionGenerada det = new ProyeccionGenerada();

			det.setAlNave(rs.getString("NAVE"));
			det.setViaje(rs.getString("VIAJE"));
			det.setEta(rs.getDate("ETA"));
			det.setTpe(rs.getString("TPE"));
			det.setFgFarEast(rs.getString("FG_FAR_EAST"));
			det.setQty(rs.getInt("QTY"));
			det.setPick(rs.getInt("PICK"));

			proyeccionGenerada.add(det);

		}

		return proyeccionGenerada;

	}
	
	
	

	public List<ProyeccionVentaCab> listarProyeccionVenta(String token) throws SQLException {

		Connection conector = Conector.conectores.get(token);

		List<ProyeccionVentaCab> proyecciones = new ArrayList<ProyeccionVentaCab>();
		ProyeccionVentaCab proyeccion = null;

		String sql1 = "EXEC SP_OBTIENE_CAB_PROYECCIONES_VENTA";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {

			proyeccion = new ProyeccionVentaCab();

			proyeccion.setTipo(rs.getString("CO_TI_PROY"));
			proyeccion.setCoProyeccion(rs.getString("CO_PROY_VENTA"));
			proyeccion.setFeProyeccion(rs.getDate("FE_PROY"));
			proyeccion.setFgActi(rs.getString("FG_ACTI"));
			proyeccion.setNroSem(rs.getInt("NRO_SEM"));

			proyecciones.add(proyeccion);

		}

		conector = null;

		return proyecciones;
	}
	
	
	

	public ProyeccionVentaCab getProyeccion(String token, String coProyyeccion) throws SQLException {

		Connection conector = Conector.conectores.get(token);

		ProyeccionVentaCab proyeccion = new ProyeccionVentaCab();

		String sql1 = "EXEC SP_OBTIENE_CAB_PROYECCION_VENTA ?";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ps1.setString(1, coProyyeccion);

		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {

			// proyeccion = new ProyeccionVentaCab();

			proyeccion.setTipo(rs1.getString("CO_TI_PROY"));
			proyeccion.setCoProyeccion(rs1.getString("CO_PROY_VENTA"));
			proyeccion.setFeProyeccion(rs1.getDate("FE_PROY"));
			proyeccion.setFgActi(rs1.getString("FG_ACTI"));
			proyeccion.setUsCrea(rs1.getString("US_CREA"));
			proyeccion.setUsModi(rs1.getString("US_MODI"));
			proyeccion.setFeCrea(rs1.getTimestamp("FE_CREA"));
			proyeccion.setFeModi(rs1.getTimestamp("FE_MODI"));
			proyeccion.setNroSem(rs1.getInt("NRO_SEM"));
			proyeccion.setCoFile(rs1.getString("CO_FILE"));
			
			proyeccion.setTo2SdFe(rs1.getInt("TO_2SD_FE"));
			proyeccion.setTo2SdNoFe(rs1.getInt("TO_2SD_NO_FE"));
			proyeccion.setTo4SdFe(rs1.getInt("TO_4SD_FE"));
			proyeccion.setTo4SdNoFe(rs1.getInt("TO_4SD_NO_FE"));
			proyeccion.setTo4ShFe(rs1.getInt("TO_4SH_FE"));
			proyeccion.setTo4ShNoFe(rs1.getInt("TO_4SH_NO_FE"));
			proyeccion.setTo4RhFe(rs1.getInt("TO_4RH_FE"));
			proyeccion.setTo4RhNoFe(rs1.getInt("TO_4RH_NO_FE"));
			
			proyeccion.setTo2SdFePick(rs1.getInt("TO_2SD_FE_PICK"));
			proyeccion.setTo2SdNoFePick(rs1.getInt("TO_2SD_NO_FE_PICK"));
			proyeccion.setTo4SdFePick(rs1.getInt("TO_4SD_FE_PICK"));
			proyeccion.setTo4SdNoFePick(rs1.getInt("TO_4SD_NO_FE_PICK"));
			proyeccion.setTo4ShFePick(rs1.getInt("TO_4SH_FE_PICK"));
			proyeccion.setTo4ShNoFePick(rs1.getInt("TO_4SH_NO_FE_PICK"));
			proyeccion.setTo4RhFePick(rs1.getInt("TO_4RH_FE_PICK"));
			
			proyeccion.setTo4RhNoFe(rs1.getInt("TO_4RH_NO_FE_PICK"));
			proyeccion.setTo2SdBook(rs1.getInt("TO_2SD_BOOK"));
			proyeccion.setTo4SdBook(rs1.getInt("TO_4SD_BOOK"));
			proyeccion.setTo4ShBook(rs1.getInt("TO_4SH_BOOK"));
			proyeccion.setTo4RhBook(rs1.getInt("TO_4RH_BOOK"));
			
			proyeccion.setTo2SdPick(rs1.getInt("TO_2SD_PICK"));
			proyeccion.setTo4SdPick(rs1.getInt("TO_4SD_PICK"));
			proyeccion.setTo4ShPick(rs1.getInt("TO_4SH_PICK"));
			proyeccion.setTo4RhPick(rs1.getInt("TO_4RH_PICK"));

			// proyeccion.setDetalles(getProyeccionDet(token, coProyVenta));

		}
		
		
			
/*
		System.out.println(proyeccion.getTo2SdFe());
		System.out.println(proyeccion.getTo4SdFe());
		System.out.println(proyeccion.getTo4ShFe());
		System.out.println(proyeccion.getTo4RhFe());
		
		System.out.println(proyeccion.getTo2SdNoFe());
		System.out.println(proyeccion.getTo4SdNoFe());
		System.out.println(proyeccion.getTo4ShNoFe());
		System.out.println(proyeccion.getTo4RhNoFe());
		
		System.out.println(proyeccion.getTo2SdFePick());
		System.out.println(proyeccion.getTo4SdFePick());
		System.out.println(proyeccion.getTo4ShFePick());
		System.out.println(proyeccion.getTo4RhFePick());
		
		System.out.println(proyeccion.getTo2SdNoFePick());
		System.out.println(proyeccion.getTo4SdNoFePick());
		System.out.println(proyeccion.getTo4ShNoFePick());
		System.out.println(proyeccion.getTo4RhNoFePick());
*/		
		


		proyeccion.setDetalles(new ArrayList<ProyeccionVentaDet>());

		String sql2 = "EXEC SP_OBTIENE_DET_PROYECCION_VENTA ?";

		PreparedStatement ps2 = conector.prepareStatement(sql2);

		ps2.setString(1, coProyyeccion);

		ResultSet rs2 = ps2.executeQuery();

		while (rs2.next()) {

			ProyeccionVentaDet detalle = new ProyeccionVentaDet();

			NaveTemp nave = new NaveTemp();
			Servicio servicio = new Servicio();

			servicio.setCoServ(rs2.getString("CO_SERV"));
			servicio.setFgFarEast(rs2.getString("FG_FAR_EAST"));

			nave.setCoNave(rs2.getString("CO_NAVE"));
			nave.setAlNave(rs2.getString("AL_NAVE"));
			nave.setServicio(servicio);

			detalle.setIdItem(rs2.getInt("ID_ITEM"));
			detalle.setNave(nave);
			detalle.setViaje(rs2.getString("VIAJE"));
			detalle.setEta(rs2.getDate("ETA"));
			detalle.setCa2SdFe(rs2.getInt("CA_2SD_FE"));
			detalle.setCa2SdNoFe(rs2.getInt("CA_2SD_NO_FE"));
			detalle.setCa4SdFe(rs2.getInt("CA_4SD_FE"));
			detalle.setCa4SdNoFe(rs2.getInt("CA_4SD_NO_FE"));
			detalle.setCa4ShFe(rs2.getInt("CA_4SH_FE"));
			detalle.setCa4ShNoFe(rs2.getInt("CA_4SH_NO_FE"));
			detalle.setCa4RhFe(rs2.getInt("CA_4RH_FE"));
			detalle.setCa4RhNoFe(rs2.getInt("CA_4RH_NO_FE"));

			detalle.setCa2SdFePick(rs2.getInt("CA_2SD_FE_PICK"));
			detalle.setCa2SdNoFePick(rs2.getInt("CA_2SD_NO_FE_PICK"));
			detalle.setCa4SdFePick(rs2.getInt("CA_4SD_FE_PICK"));
			detalle.setCa4SdNoFePick(rs2.getInt("CA_4SD_NO_FE_PICK"));
			detalle.setCa4ShFePick(rs2.getInt("CA_4SH_FE_PICK"));
			detalle.setCa4ShNoFePick(rs2.getInt("CA_4SH_NO_FE_PICK"));
			detalle.setCa4RhFePick(rs2.getInt("CA_4RH_FE_PICK"));
			detalle.setCa4RhNoFePick(rs2.getInt("CA_4RH_NO_FE_PICK"));

			proyeccion.getDetalles().add(detalle);

		}

		return proyeccion;

	}

//	public List<ProyeccionVentaDet> getProyeccionDet(String token, String coProyVenta) {
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		List<ProyeccionVentaDet> detalles = new ArrayList<ProyeccionVentaDet>();
//		
//		ProyeccionVentaDet detalle = null;
//		Nave nave = null;
//		Servicio servicio = null;
//		
//		String sql = "EXEC SP_OBTIENE_DET_PROYECCION_VENTA ?";
//		
//		try {
//			
//			PreparedStatement ps = conector.prepareStatement(sql);
//			
//			ps.setString(1, coProyVenta);
//
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				
//				detalle = new ProyeccionVentaDet();
//				nave = new Nave();
//				servicio = new Servicio();
//				
//				servicio.setCoServ(rs.getString("CO_SERV"));
//				servicio.setFgFarEast(rs.getString("FG_FAR_EAST"));
//				
//				nave.setCoNave(rs.getString("CO_NAVE"));
//				nave.setAlNave(rs.getString("AL_NAVE"));
//				nave.setServicio(servicio);
//								
//				detalle.setItem(rs.getInt("ITEM"));
//				detalle.setViaje(rs.getString("VIAJE"));
//				detalle.setEta(rs.getDate("ETA"));
//				detalle.setCa2SdFe(rs.getInt("CA_2SD_FE"));
//				detalle.setCa2SdNoFe(rs.getInt("CA_2SD_NO_FE"));
//				detalle.setCa4SdFe(rs.getInt("CA_4SD_FE"));
//				detalle.setCa4SdNoFe(rs.getInt("CA_4SD_NO_FE"));
//				detalle.setCa4ShFe(rs.getInt("CA_4SH_FE"));
//				detalle.setCa4ShNoFe(rs.getInt("CA_4SH_NO_FE"));
//				detalle.setNave(nave);
//				
//				detalles.add(detalle);
//				
//			}
//		
//		
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
//		
//		conector = null;
//		
//		
//		return detalles;
//	}

	public String registrarProyeccionCab(ProyeccionVentaCabDTO proyeccionVentaCab, String token) throws SQLException {

		Connection conector = Conector.conectores.get(token);

		String coProyeccion = null;

		String sql1 = "EXEC SP_REGISTRA_CAB_PROYECCION_VENTA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

		PreparedStatement ps1 = conector.prepareStatement(sql1);

		ps1.setString(1, proyeccionVentaCab.getCoProyeccion());
		ps1.setString(2, proyeccionVentaCab.getTipo());
		ps1.setDate(3, new java.sql.Date(proyeccionVentaCab.getFeProyeccion().getTime()));
		ps1.setString(4, proyeccionVentaCab.getFgActi());
		ps1.setString(5, proyeccionVentaCab.getCoFile());

		ps1.setInt(6, proyeccionVentaCab.getTo2SdNoFe());
		ps1.setInt(7, proyeccionVentaCab.getTo2SdFe());
		ps1.setInt(8, proyeccionVentaCab.getTo4SdNoFe());
		ps1.setInt(9, proyeccionVentaCab.getTo4SdFe());
		ps1.setInt(10, proyeccionVentaCab.getTo4ShNoFe());
		ps1.setInt(11, proyeccionVentaCab.getTo4ShFe());
		ps1.setInt(12, proyeccionVentaCab.getTo4RhNoFe());
		ps1.setInt(13, proyeccionVentaCab.getTo4RhFe());

		ps1.setInt(14, proyeccionVentaCab.getTo2SdNoFePick());
		ps1.setInt(15, proyeccionVentaCab.getTo2SdFePick());
		ps1.setInt(16, proyeccionVentaCab.getTo4SdNoFePick());
		ps1.setInt(17, proyeccionVentaCab.getTo4SdFePick());
		ps1.setInt(18, proyeccionVentaCab.getTo4ShNoFePick());
		ps1.setInt(19, proyeccionVentaCab.getTo4ShFePick());
		ps1.setInt(20, proyeccionVentaCab.getTo4RhNoFePick());
		ps1.setInt(21, proyeccionVentaCab.getTo4RhFePick());

		ps1.setInt(22, proyeccionVentaCab.getTo2SdBook());
		ps1.setInt(23, proyeccionVentaCab.getTo4SdBook());
		ps1.setInt(24, proyeccionVentaCab.getTo4ShBook());
		ps1.setInt(25, proyeccionVentaCab.getTo4RhBook());

		ps1.setInt(26, proyeccionVentaCab.getTo2SdPick());
		ps1.setInt(27, proyeccionVentaCab.getTo4SdPick());
		ps1.setInt(28, proyeccionVentaCab.getTo4ShPick());
		ps1.setInt(29, proyeccionVentaCab.getTo4RhPick());

		// System.out.println("antes del execute");
		ResultSet rs = ps1.executeQuery();

		// System.out.println("despues del execute");

		while (rs.next()) {

			// System.out.println("while");
			coProyeccion = rs.getString(1);

		}

		String sql2 = "EXEC SP_REGISTRA_DET_PROYECCION_VENTA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

		PreparedStatement ps2 = conector.prepareStatement(sql2);

		int rows = 0;

		for (ProyeccionVentaDetDTO detalle : proyeccionVentaCab.getDetalles()) {

			ps2.setString(1, proyeccionVentaCab.getTipo());
			ps2.setString(2, coProyeccion);
			ps2.setInt(3, detalle.getIdItem());
			ps2.setString(4, detalle.getAlNave());
			ps2.setString(5, detalle.getViaje());
			ps2.setDate(6, new java.sql.Date(detalle.getEta().getTime()));

			ps2.setInt(7, detalle.getCa2SdNoFe());
			ps2.setInt(8, detalle.getCa2SdFe());
			ps2.setInt(9, detalle.getCa4SdNoFe());
			ps2.setInt(10, detalle.getCa4SdFe());
			ps2.setInt(11, detalle.getCa4ShNoFe());
			ps2.setInt(12, detalle.getCa4ShFe());
			ps2.setInt(13, detalle.getCa4RhNoFe());
			ps2.setInt(14, detalle.getCa4RhFe());

			ps2.setInt(15, detalle.getCa2SdNoFePick());
			ps2.setInt(16, detalle.getCa2SdFePick());
			ps2.setInt(17, detalle.getCa4SdNoFePick());
			ps2.setInt(18, detalle.getCa4SdFePick());
			ps2.setInt(19, detalle.getCa4ShNoFePick());
			ps2.setInt(20, detalle.getCa4ShFePick());
			ps2.setInt(21, detalle.getCa4RhNoFePick());
			ps2.setInt(22, detalle.getCa4RhFePick());

			rows = rows + ps2.executeUpdate();

		}

		Log.mensaje = rows + " REGISTROS INSERTADOS";
		Log.registraInfo();

		conector = null;

		return coProyeccion;

	}

//	public String actualizaProyeccionCab(ProyeccionVentaCabDTO proyeccionVentaCab, String token) {
//		
//		Connection conector = Conector.conectores.get(token);
//
//		String sql1 = "EXEC SP_ACTUALIZA_CAB_PROYECCION_VENTA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
//		
//		try {
//			
//			PreparedStatement ps1 = conector.prepareStatement(sql1);
//			
//			ps1.setString(1, proyeccionVentaCab.getTipo());
//			ps1.setString(2, proyeccionVentaCab.getCodigo());
//			ps1.setString(3, proyeccionVentaCab.getFgActi());
//			ps1.setString(4, proyeccionVentaCab.getCoFileSol());
//			ps1.setInt(5, proyeccionVentaCab.getCa2SdNoFe());
//			ps1.setInt(6, proyeccionVentaCab.getCa2SdFe());
//			ps1.setInt(7, proyeccionVentaCab.getCa4SdNoFe());
//			ps1.setInt(8, proyeccionVentaCab.getCa4SdFe());
//			ps1.setInt(9, proyeccionVentaCab.getCa4ShNoFe());
//			ps1.setInt(10, proyeccionVentaCab.getCa4ShNoFe());
//			ps1.setInt(11, proyeccionVentaCab.getTo2SdBook());
//			ps1.setInt(12, proyeccionVentaCab.getTo4SdBook());
//			ps1.setInt(13, proyeccionVentaCab.getTo4ShBook());
//			
//			ps1.executeQuery();
//			
//			String sql2 = "EXEC SP_REGISTRA_DET_PROYECCION_VENTA ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
//
//			PreparedStatement ps2 = conector.prepareStatement(sql2);
//
//			int rows = 0;
//
//			for (ProyeccionVentaDetDTO detalle : proyeccionVentaCab.getDetalles()) {
//				
//				ps2.setString(1, proyeccionVentaCab.getTipo());
//				ps2.setString(2, proyeccionVentaCab.getCodigo());
//				ps2.setInt(3, detalle.getItem());
//				ps2.setString(4, detalle.getAlNave());
//				ps2.setString(5, detalle.getViaje());
//				ps2.setDate(6, new java.sql.Date(detalle.getEta().getTime()));
//				ps2.setInt(7, detalle.getCa2SdNoFe());
//				ps2.setInt(8, detalle.getCa2SdFe());
//				ps2.setInt(9, detalle.getCa4SdNoFe());
//				ps2.setInt(10, detalle.getCa4SdFe());
//				ps2.setInt(11, detalle.getCa4ShNoFe());
//				ps2.setInt(12, detalle.getCa4ShFe());
//
//				rows = rows + ps2.executeUpdate();
//
//			}
//
//			Log.mensaje = rows + " REGISTROS INSERTADOS";
//			Log.registraInfo();
//			
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
//
//		conector = null;
//
//		return proyeccionVentaCab.getCodigo();
//		
//	}

//	public List<ProyeccionFileCab> listarFiles(String token) {
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		List<ProyeccionFileCab> files = new ArrayList<ProyeccionFileCab>();
//		ProyeccionFileCab file = null;
//		
//		String sql1 = "EXEC SP_OBTIENE_CAB_PROYECCIONES_FILE";
//		
//		try {
//			
//			PreparedStatement ps1 = conector.prepareStatement(sql1);
//
//			ResultSet rs = ps1.executeQuery();
//			
//			while (rs.next()) {
//				
//				file = new ProyeccionFileCab();
//				
//				file.setCodigo(rs.getString("CO_PROY_FILE"));
//				file.setNoFile(rs.getString("NO_FILE"));
//				file.setFeCargaFile(rs.getDate("FE_CARGA_FILE"));
//				file.setFgActi(rs.getString("FG_ACTI"));
//				
//				files.add(file);
//				
//			}
//			
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
//		
//		conector = null;
//		
//		return files;
//	}

//	public ProyeccionFileCab getProyeccionFileCab(String token, String coProyFile) {
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		ProyeccionFileCab fileCab = null;
//		
//		String sql1 = "EXEC SP_OBTIENE_CAB_FILE_MTC1R999 ?";
//		
//		try {
//			
//			PreparedStatement ps1 = conector.prepareStatement(sql1);
//			
//			ps1.setString(1, coProyFile);
//
//			ResultSet rs = ps1.executeQuery();
//			
//			while (rs.next()) {
//				
//				fileCab = new ProyeccionFileCab();
//				
//				fileCab.setCodigo(rs.getString("CO_FILE"));
//				fileCab.setNoFile(rs.getString("NO_FILE"));
//				fileCab.setFeCargaFile(rs.getDate("FE_CARGA_FILE"));
//				fileCab.setFgActi(rs.getString("FG_ACTI"));
//				
//				fileCab.setDetalle(getProyeccionFileDet(token, coProyFile));
//				
//				
//			}
//			
//			
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
//		
//		return fileCab;
//		
//	}

//	public List<ProyeccionFileDet> getProyeccionFileDet(String token, String coProyFile) {
//		
//		Connection conector = Conector.conectores.get(token);
//		
//		List<ProyeccionFileDet> detalles = new ArrayList<ProyeccionFileDet>();
//		
//		ProyeccionFileDet detalle = null;
//		Nave nave = null;
//		
//		String sql = "EXEC SP_GENERA_PROYECCION_VENTA_X_FILE ?";
//		
//		try {
//			
//			PreparedStatement ps = conector.prepareStatement(sql);
//			
//			ps.setString(1, coProyFile);
//
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				
//				detalle = new ProyeccionFileDet();
//				nave = new Nave();
//				
//				nave.setCoNave(rs.getString("CO_NAVE"));
//				nave.setAlNave(rs.getString("AL_NAVE"));
//								
//				detalle.setCodigo(coProyFile);
//				detalle.setItem(rs.getInt("ITEM"));
//				detalle.setViaje(rs.getString("VIAJE"));
//				detalle.setEta(rs.getDate("ETA"));
//				
//				detalle.setCa2SdFe(rs.getInt("CA_2SD_FE"));
//				detalle.setCa2SdNoFe(rs.getInt("CA_2SD_NO_FE"));
//				// detalle.setCa2SdFePick(rs.getInt("CA_2SD_FE_PICK"));
//				// detalle.setCa2SdNoFePick(rs.getInt("CA_2SD_NO_FE_PICK"));
//				detalle.setCa4SdFe(rs.getInt("CA_4SD_FE"));
//				detalle.setCa4SdNoFe(rs.getInt("CA_4SD_NO_FE"));
//				// detalle.setCa4SdFePick(rs.getInt("CA_4SD_FE_PICK"));
//				// detalle.setCa4SdNoFePick(rs.getInt("CA_4SD_NO_FE_PICK"));
//				detalle.setCa4ShFe(rs.getInt("CA_4SH_FE"));
//				detalle.setCa4ShNoFe(rs.getInt("CA_4SH_NO_FE"));
//				// detalle.setCa4ShFePick(rs.getInt("CA_4SH_FE_PICK"));
//				// detalle.setCa4ShNoFePick(rs.getInt("CA_4SH_NO_FE_PICK"));
//				// detalle.setCa4RhFe(rs.getInt("CA_4RH_FE"));
//				// detalle.setCa4RhNoFe(rs.getInt("CA_4RH_NO_FE"));
//				// detalle.setCa4RhFePick(rs.getInt("CA_4RH_FE_PICK"));
//				// detalle.setCa4RhNoFePick(rs.getInt("CA_4RH_NO_FE_PICK"));
//				detalle.setNave(nave);
//				
//				detalles.add(detalle);
//				
//			}
//		
//		
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
//		
//		conector = null;
//		
//		
//		return detalles;
//	}

}
