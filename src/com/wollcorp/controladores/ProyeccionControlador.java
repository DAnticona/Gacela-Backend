package com.wollcorp.controladores;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import com.wollcorp.TEMP.ProyeccionEquipoCabDTO;
import com.wollcorp.TEMP.ProyeccionEquipoDetDTO;
import com.wollcorp.TEMP.ProyeccionGeneradaDTO;
import com.wollcorp.TEMP.ProyeccionVentaCabDTO;
import com.wollcorp.TEMP.ProyeccionVentaDetDTO;
import com.wollcorp.beans.ProyeccionGenerada;
import com.wollcorp.beans.ProyeccionVentaCab;
import com.wollcorp.beans.ProyeccionVentaDet;
import com.wollcorp.dao.ProyeccionEquipoDaoImpl;
import com.wollcorp.dao.ProyeccionVentaDaoImpl;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.ErrorRes;
import com.wollcorp.restServices.responses.ProyeccionRes;

public class ProyeccionControlador {
	
	
	public ProyeccionRes generaResumenProyeccion(String token, String coFile) throws SQLException {

		ProyeccionRes proyeccionRes = new ProyeccionRes();

		if (Token.tokenValido(token)) {

			List<ProyeccionGenerada> proyeccionGenerada = (new ProyeccionVentaDaoImpl()).generaResumenProyeccion(token, coFile);
			
			List<ProyeccionGeneradaDTO> proyeccionGeneradaDTO = new ArrayList<ProyeccionGeneradaDTO>();

			for (ProyeccionGenerada p : proyeccionGenerada) {

				ProyeccionGeneradaDTO det = new ProyeccionGeneradaDTO();

				det.setAlNave(p.getAlNave());
				det.setViaje(p.getViaje());
				det.setEta(p.getEta());
				det.setFgFarEast(p.getFgFarEast());
				det.setTpe(p.getTpe());
				det.setQty(p.getQty());
				det.setPick(p.getPick());

				proyeccionGeneradaDTO.add(det);

			}

			proyeccionRes.setProyeccionGenerada(proyeccionGeneradaDTO);

		} else {

			proyeccionRes.setError(new ErrorRes());

			proyeccionRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return proyeccionRes;

	}

	public ProyeccionRes listarProyeccionesVenta(String token) throws SQLException {

		ProyeccionRes proyeccionVentaRes = new ProyeccionRes();

		List<ProyeccionVentaCab> proyecciones = new ArrayList<ProyeccionVentaCab>();

		List<ProyeccionVentaCabDTO> proyeccionesDTO = new ArrayList<ProyeccionVentaCabDTO>();
		ProyeccionVentaCabDTO proyeccion;

		if (Token.tokenValido(token)) {

			proyecciones = (new ProyeccionVentaDaoImpl()).listarProyeccionVenta(token);

			for (ProyeccionVentaCab p : proyecciones) {

				proyeccion = new ProyeccionVentaCabDTO();

				proyeccion.setTipo(p.getTipo());
				proyeccion.setCoProyeccion(p.getCoProyeccion());
				proyeccion.setFeProyeccion(p.getFeProyeccion());
				proyeccion.setFgActi(p.getFgActi());
				proyeccion.setNroSem(p.getNroSem());

				proyeccionesDTO.add(proyeccion);

			}

		} else {

			proyeccionesDTO = null;

		}

		proyeccionVentaRes.setListaProyeccionesVenta(proyeccionesDTO);

		return proyeccionVentaRes;

	}

	public ProyeccionRes getProyeccionVenta(String token, String coProyeccion) throws SQLException {

		ProyeccionRes proyeccionVentaRes = new ProyeccionRes();

		ProyeccionVentaCab proyeccion;

		ProyeccionVentaCabDTO proyeccionDTO;
		ProyeccionVentaDetDTO proyeccionDetDTO;

		if (Token.tokenValido(token)) {

			proyeccionDTO = new ProyeccionVentaCabDTO();

			proyeccion = (new ProyeccionVentaDaoImpl()).getProyeccion(token, coProyeccion);

			proyeccionDTO.setTipo(proyeccion.getTipo());
			proyeccionDTO.setCoProyeccion(proyeccion.getCoProyeccion());
			proyeccionDTO.setFeProyeccion(proyeccion.getFeProyeccion());
			proyeccionDTO.setFgActi(proyeccion.getFgActi());
			proyeccionDTO.setNroSem(proyeccion.getNroSem());
			proyeccionDTO.setCoFile(proyeccion.getCoFile());

			proyeccionDTO.setUsCrea(proyeccion.getUsCrea());
			proyeccionDTO.setUsModi(proyeccion.getUsModi());
			proyeccionDTO.setFeCrea(proyeccion.getFeCrea());
			proyeccionDTO.setFeModi(proyeccion.getFeModi());

			proyeccionDTO.setTo2SdFe(proyeccion.getTo2SdFe());
			proyeccionDTO.setTo2SdNoFe(proyeccion.getTo2SdNoFe());
			proyeccionDTO.setTo4SdFe(proyeccion.getTo4SdFe());
			proyeccionDTO.setTo4SdNoFe(proyeccion.getTo4SdNoFe());
			proyeccionDTO.setTo4ShFe(proyeccion.getTo4ShFe());
			proyeccionDTO.setTo4ShNoFe(proyeccion.getTo4ShNoFe());
			proyeccionDTO.setTo4RhFe(proyeccion.getTo4RhFe());
			proyeccionDTO.setTo4RhNoFe(proyeccion.getTo4RhNoFe());

			proyeccionDTO.setTo2SdFePick(proyeccion.getTo2SdFePick());
			proyeccionDTO.setTo2SdNoFePick(proyeccion.getTo2SdNoFePick());
			proyeccionDTO.setTo4SdFePick(proyeccion.getTo4SdFePick());
			proyeccionDTO.setTo4SdNoFePick(proyeccion.getTo4SdNoFePick());
			proyeccionDTO.setTo4ShFePick(proyeccion.getTo4ShFePick());
			proyeccionDTO.setTo4ShNoFePick(proyeccion.getTo4ShNoFePick());
			proyeccionDTO.setTo4RhFePick(proyeccion.getTo4RhFePick());
			proyeccionDTO.setTo4RhNoFePick(proyeccion.getTo4RhNoFePick());

			proyeccionDTO.setTo2SdBook(proyeccion.getTo2SdBook());
			proyeccionDTO.setTo4SdBook(proyeccion.getTo4SdBook());
			proyeccionDTO.setTo4ShBook(proyeccion.getTo4ShBook());
			proyeccionDTO.setTo4RhBook(proyeccion.getTo4RhBook());

			proyeccionDTO.setTo2SdPick(proyeccion.getTo2SdPick());
			proyeccionDTO.setTo4SdPick(proyeccion.getTo4SdPick());
			proyeccionDTO.setTo4ShPick(proyeccion.getTo4ShPick());
			proyeccionDTO.setTo4RhPick(proyeccion.getTo4RhPick());

			proyeccionDTO.setDetalles(new ArrayList<ProyeccionVentaDetDTO>());

			for (ProyeccionVentaDet pd : proyeccion.getDetalles()) {

				proyeccionDetDTO = new ProyeccionVentaDetDTO();

				proyeccionDetDTO.setIdItem(pd.getIdItem());
				proyeccionDetDTO.setAlNave(pd.getNave().getAlNave());
				proyeccionDetDTO.setViaje(pd.getViaje());
				proyeccionDetDTO.setEta(pd.getEta());

				proyeccionDetDTO.setCa2SdFe(pd.getCa2SdFe());
				proyeccionDetDTO.setCa2SdNoFe(pd.getCa2SdNoFe());
				proyeccionDetDTO.setCa4SdFe(pd.getCa4SdFe());
				proyeccionDetDTO.setCa4SdNoFe(pd.getCa4SdNoFe());
				proyeccionDetDTO.setCa4ShFe(pd.getCa4ShFe());
				proyeccionDetDTO.setCa4ShNoFe(pd.getCa4ShNoFe());
				proyeccionDetDTO.setCa4RhFe(pd.getCa4RhFe());
				proyeccionDetDTO.setCa4RhNoFe(pd.getCa4RhNoFe());

				proyeccionDetDTO.setCa2SdFePick(pd.getCa2SdFePick());
				proyeccionDetDTO.setCa2SdNoFePick(pd.getCa2SdNoFePick());
				proyeccionDetDTO.setCa4SdFePick(pd.getCa4SdFePick());
				proyeccionDetDTO.setCa4SdNoFePick(pd.getCa4SdNoFePick());
				proyeccionDetDTO.setCa4ShFePick(pd.getCa4ShFePick());
				proyeccionDetDTO.setCa4ShNoFePick(pd.getCa4ShNoFePick());
				proyeccionDetDTO.setCa4RhFePick(pd.getCa4RhFePick());
				proyeccionDetDTO.setCa4RhNoFePick(pd.getCa4RhNoFePick());

				proyeccionDTO.getDetalles().add(proyeccionDetDTO);

			}

		} else {

			proyeccionDTO = null;

		}

		proyeccionVentaRes.setProyeccionVenta(proyeccionDTO);

		return proyeccionVentaRes;

	}

	public ProyeccionRes registraProyeccionVenta(String token, ProyeccionVentaCabDTO proyeccionVentaCab)
			throws SQLException {

		ProyeccionRes proyeccionVentaRes = new ProyeccionRes();

		ProyeccionVentaCabDTO proyeccionVentaDTO = new ProyeccionVentaCabDTO();

		String coProyVenta = null;

		if (Token.tokenValido(token)) {

			coProyVenta = (new ProyeccionVentaDaoImpl()).registrarProyeccionCab(proyeccionVentaCab, token);

			proyeccionVentaDTO.setCoProyeccion(coProyVenta);

			proyeccionVentaRes.setProyeccionVenta(proyeccionVentaDTO);

		} else {

			proyeccionVentaDTO = null;

			proyeccionVentaRes.setError(new ErrorRes());

			proyeccionVentaRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return proyeccionVentaRes;
	}

	public ProyeccionRes registraProyeccionEquipo(String token, ProyeccionEquipoCabDTO proyeccionEquipoCab) throws SQLException {

		ProyeccionRes proyeccionEquipoRes = new ProyeccionRes();

		ProyeccionEquipoCabDTO proyeccionEquipoDTO = new ProyeccionEquipoCabDTO();

		String coProyEquipo = null;

		if (Token.tokenValido(token)) {

			coProyEquipo = (new ProyeccionEquipoDaoImpl()).registrarProyeccionEquiposCab(token, proyeccionEquipoCab);

			proyeccionEquipoDTO.setCoProyEqui(coProyEquipo);

			proyeccionEquipoRes.setProyeccionEquipo(proyeccionEquipoDTO);

		} else {

			proyeccionEquipoDTO = null;

			proyeccionEquipoRes.setError(new ErrorRes());

			proyeccionEquipoRes.getError().setMensaje("TOKEN INVÁLIDO");

		}

		return proyeccionEquipoRes;
	}
	
	
	
//	public ProyeccionRes registraRatioDevolucion(String token, RatioDevolucionDTOTEMP ratio) throws SQLException {
//
//		ProyeccionRes proyeccionRes = new ProyeccionRes();
//
//		if (Token.tokenValido(token)) {
//
//			(new RatioDevolucionDaoImpl()).registraRatioTEMP(ratio, token);
//
//		} else {
//
//			proyeccionRes.setError(new ErrorRes());
//
//			proyeccionRes.getError().setMensaje("TOKEN INVÁLIDO");
//
//		}
//
//		return proyeccionRes;
//
//	}
	
	
	
//	public ProyeccionRes obtieneRatioDevolucion(String token) throws SQLException {
//
//		ProyeccionRes proyeccionRes = new ProyeccionRes();
//
//		if (Token.tokenValido(token)) {
//
//			RatioDevolucionTEMP ratio = (new RatioDevolucionDaoImpl()).obtieneRatioTEMP(token);
//			
//			RatioDevolucionDTOTEMP ratioDTO = new RatioDevolucionDTOTEMP();
//			
//			ratioDTO.setRa2Sd(ratio.getRa2Sd());
//			ratioDTO.setRa4Sd(ratio.getRa4Sd());
//			ratioDTO.setRa4Sh(ratio.getRa4Sh());
//			ratioDTO.setUsCrea(ratio.getUsCrea());
//			ratioDTO.setUsModi(ratio.getUsModi());
//			ratioDTO.setFeCrea(ratio.getFeCrea());
//			ratioDTO.setFeModi(ratio.getFeModi());
//			
//			proyeccionRes.setRatio(ratioDTO);
//
//		} else {
//
//			proyeccionRes.setError(new ErrorRes());
//
//			proyeccionRes.getError().setMensaje("TOKEN INVÁLIDO");
//
//		}
//
//		return proyeccionRes;
//
//	}
	
	
	
	
	

	public String generaExcel(String token, ProyeccionEquipoCabDTO proyeccionEquipo,
			ProyeccionEquipoCabDTO proyeccionVenta) {
		
		List<ProyeccionEquipoDetDTO> rpoPlan = proyeccionEquipo.getDetalles().stream()
										.filter(x -> x.getFgRpoPlan().equals("S")).collect(Collectors.toList());
		
		List<ProyeccionEquipoDetDTO> detalle = proyeccionEquipo.getDetalles().stream()
										.filter(x -> x.getFgRpoPlan().equals("N")).collect(Collectors.toList());

		// System.out.println("Generando Excel para Proyeccion Equipos");

		String templateProspectPecalDry = "c:\\opt\\assets\\templates\\ProspectPecalDry.xlsx";

		String fileName = "ProspectPecalDry_" + Instant.now().toEpochMilli() + ".xlsx";

		String filePath = "c:\\opt\\assets\\reports\\" + fileName;

		try {

			Path origenPath = FileSystems.getDefault().getPath(templateProspectPecalDry);
			Path destinoPath = FileSystems.getDefault().getPath(filePath);

			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			FileInputStream fis = new FileInputStream(new File(filePath));

			// List<Resultado> res = null;

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFCellStyle estilo;

			// ESCRIBIENDO PROYECCION DE EQUIPOS

			XSSFSheet sheet1 = wb.getSheetAt(0);
			// XSSFSheet sheet2 = wb.getSheetAt(1);

			XSSFRow fila;
			XSSFCell celda;

			// CABECERAS

			fila = sheet1.createRow(0);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			fila = sheet1.getRow(0);

			celda = fila.getCell(1);
			celda.setCellValue("2SD");
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));

			celda = fila.getCell(5);
			celda.setCellValue("4SD");
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

			celda = fila.getCell(9);
			celda.setCellValue("4SH");
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 9, 12));

			// TITULOS fe NONfe PICKED UP
			fila = sheet1.createRow(1);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosSubTitulo(wb, "ETA");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(1);

			celda = fila.getCell(1);
			celda.setCellValue("Non F.E.");

			celda = fila.getCell(2);
			celda.setCellValue("Picked up");

			celda = fila.getCell(3);
			celda.setCellValue("F.E.");

			celda = fila.getCell(4);
			celda.setCellValue("Picked up");

			celda = fila.getCell(5);
			celda.setCellValue("Non F.E.");

			celda = fila.getCell(6);
			celda.setCellValue("Picked up");

			celda = fila.getCell(7);
			celda.setCellValue("F.E.");

			celda = fila.getCell(8);
			celda.setCellValue("Picked up");

			celda = fila.getCell(9);
			celda.setCellValue("Non F.E.");

			celda = fila.getCell(10);
			celda.setCellValue("Picked up");

			celda = fila.getCell(11);
			celda.setCellValue("F.E.");

			celda = fila.getCell(12);
			celda.setCellValue("Picked up");

			celda = fila.getCell(13);
			celda.setCellValue("ETA");

			// STOCK
			fila = sheet1.createRow(2);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosSubTitulo(wb, "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(2);

			celda = fila.getCell(0);
			celda.setCellValue("STOCK");

			celda = fila.getCell(1);
			celda.setCellValue(proyeccionEquipo.getStock2Sd());
			sheet1.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));

			celda = fila.getCell(5);
			celda.setCellValue(proyeccionEquipo.getStock4Sd());
			sheet1.addMergedRegion(new CellRangeAddress(2, 2, 5, 8));

			celda = fila.getCell(9);
			celda.setCellValue(proyeccionEquipo.getStock4Sh());
			sheet1.addMergedRegion(new CellRangeAddress(2, 2, 9, 12));

			
			
			// MK
			fila = sheet1.createRow(3);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosSubTitulo(wb, "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(3);

			celda = fila.getCell(0);
			celda.setCellValue("MK");

			celda = fila.getCell(1);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(3, 3, 1, 4));

			celda = fila.getCell(5);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(3, 3, 5, 8));

			celda = fila.getCell(9);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(3, 3, 9, 12));

			
			
			
			// T/S
			fila = sheet1.createRow(4);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosSubTitulo(wb, "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(4);

			celda = fila.getCell(0);
			celda.setCellValue("T/S");

			celda = fila.getCell(1);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(4, 4, 1, 4));

			celda = fila.getCell(5);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(4, 4, 5, 8));

			celda = fila.getCell(9);
			celda.setCellValue(0);
			sheet1.addMergedRegion(new CellRangeAddress(4, 4, 9, 12));

			
			
			// DETALLE
			int k = 5;
			int filaInicioDetalle = k + 1;
			int filaFinDetalle = k;
			for (int f = 0; f < detalle.size(); f++) {

				fila = sheet1.createRow(k);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				fila = sheet1.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue(detalle.get(f).getAlNave() + " " + detalle.get(f).getViaje());
				XSSFCellStyle style = creaEstilosDetalle(wb, "NAVE", "");
				celda.setCellStyle(style);

				celda = fila.getCell(1);
				celda.setCellValue(detalle.get(f).getCa2SdNoFe());
				style = creaEstilosDetalle(wb, "DETALLE", "2SD");
				celda.setCellStyle(style);

				celda = fila.getCell(2);
				celda.setCellValue(detalle.get(f).getCa2SdNoFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "2SD");
				celda.setCellStyle(style);

				celda = fila.getCell(3);
				celda.setCellValue(detalle.get(f).getCa2SdFe());
				style = creaEstilosDetalle(wb, "DETALLE", "2SD");
				celda.setCellStyle(style);

				celda = fila.getCell(4);
				celda.setCellValue(detalle.get(f).getCa2SdFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "2SD");
				celda.setCellStyle(style);

				celda = fila.getCell(5);
				celda.setCellValue(detalle.get(f).getCa4SdNoFe());
				style = creaEstilosDetalle(wb, "DETALLE", "4SD");
				celda.setCellStyle(style);

				celda = fila.getCell(6);
				celda.setCellValue(detalle.get(f).getCa4SdNoFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "4SD");
				celda.setCellStyle(style);

				celda = fila.getCell(7);
				celda.setCellValue(detalle.get(f).getCa4SdFe());
				style = creaEstilosDetalle(wb, "DETALLE", "4SD");
				celda.setCellStyle(style);

				celda = fila.getCell(8);
				celda.setCellValue(detalle.get(f).getCa4SdFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "4SD");
				celda.setCellStyle(style);

				celda = fila.getCell(9);
				celda.setCellValue(detalle.get(f).getCa4ShNoFe());
				style = creaEstilosDetalle(wb, "DETALLE", "4SH");
				celda.setCellStyle(style);

				celda = fila.getCell(10);
				celda.setCellValue(detalle.get(f).getCa4ShNoFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "4SH");
				celda.setCellStyle(style);

				celda = fila.getCell(11);
				celda.setCellValue(detalle.get(f).getCa4ShFe());
				style = creaEstilosDetalle(wb, "DETALLE", "4SH");
				celda.setCellStyle(style);

				celda = fila.getCell(12);
				celda.setCellValue(detalle.get(f).getCa4ShFePick());
				style = creaEstilosDetalle(wb, "DETALLE", "4SH");
				celda.setCellStyle(style);

				celda = fila.getCell(13);
				celda.setCellValue(
						new SimpleDateFormat("dd-MMM").format(detalle.get(f).getEta()));
				style = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(style);

				k++;
				filaFinDetalle = k;
				

			}

			
			
			// SUM
			fila = sheet1.createRow(k);
			int filaSum = k + 1;

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(k);

			celda = fila.getCell(0);
			celda.setCellValue("Sum");

			celda = fila.getCell(1);
			celda.setCellFormula("sum(B" + filaInicioDetalle + ":B" + filaFinDetalle + ")");

			celda = fila.getCell(2);
			celda.setCellFormula("sum(C" + filaInicioDetalle + ":C" + filaFinDetalle + ")");

			celda = fila.getCell(3);
			celda.setCellFormula("sum(D" + filaInicioDetalle + ":D" + filaFinDetalle + ")");

			celda = fila.getCell(4);
			celda.setCellFormula("sum(E" + filaInicioDetalle + ":E" + filaFinDetalle + ")");

			celda = fila.getCell(5);
			celda.setCellFormula("sum(F" + filaInicioDetalle + ":F" + filaFinDetalle + ")");

			celda = fila.getCell(6);
			celda.setCellFormula("sum(G" + filaInicioDetalle + ":G" + filaFinDetalle + ")");

			celda = fila.getCell(7);
			celda.setCellFormula("sum(H" + filaInicioDetalle + ":H" + filaFinDetalle + ")");

			celda = fila.getCell(8);
			celda.setCellFormula("sum(I" + filaInicioDetalle + ":I" + filaFinDetalle + ")");

			celda = fila.getCell(9);
			celda.setCellFormula("sum(J" + filaInicioDetalle + ":J" + filaFinDetalle + ")");

			celda = fila.getCell(10);
			celda.setCellFormula("sum(K" + filaInicioDetalle + ":K" + filaFinDetalle + ")");

			celda = fila.getCell(11);
			celda.setCellFormula("sum(L" + filaInicioDetalle + ":L" + filaFinDetalle + ")");

			celda = fila.getCell(12);
			celda.setCellFormula("sum(M" + filaInicioDetalle + ":M" + filaFinDetalle + ")");

			k++;

			// TT BOOKING
			fila = sheet1.createRow(k);
			int filaBook = k + 1;

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(k);

			celda = fila.getCell(0);
			celda.setCellValue("TT Booking");

			celda = fila.getCell(1);
			celda.setCellFormula("B" + filaSum + "+D" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

			celda = fila.getCell(5);
			celda.setCellFormula("F" + filaSum + "+H" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

			celda = fila.getCell(9);
			celda.setCellFormula("J" + filaSum + "+L" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

			k++;

			
			
			
			// TT PICK UP
			fila = sheet1.createRow(k);
			int filaPick = k + 1;

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(k);

			celda = fila.getCell(0);
			celda.setCellValue("TT Picked up");

			celda = fila.getCell(1);
			celda.setCellFormula("C" + filaSum + "+E" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

			celda = fila.getCell(5);
			celda.setCellFormula("G" + filaSum + "+I" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

			celda = fila.getCell(9);
			celda.setCellFormula("K" + filaSum + "+M" + filaSum);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

			k++;
			
			
			
			
			
			
			
			
			// RATIO DEVOLUCION
			fila = sheet1.createRow(k);
			int filaRatio = k + 1;
			
			SimpleDateFormat formateador = new SimpleDateFormat("MMM-dd");
			
			// List<ProyeccionEquipoDetDTO> detalleRpoPlan = proyeccionEquipo.getDetalles().stream().filter(detalle -> detalle.getFgRpoPlan().equals("S")).collect(Collectors.toList());
			
			// proyeccionEquipo.getRatioDevolucion().getFechaRatioStr()

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(k);

			celda = fila.getCell(0);
			celda.setCellValue("Empty return until " + formateador.format(proyeccionEquipo.getFeEmptyReturn()) + " (Empty return one week before last vessel ETA");

			celda = fila.getCell(1);
			celda.setCellFormula("ROUND(" + proyeccionEquipo.getRatio2Sd() + "/" + proyeccionEquipo.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

			celda = fila.getCell(5);
			celda.setCellFormula("ROUND(" + proyeccionEquipo.getRatio4Sd() + "/" + proyeccionEquipo.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

			celda = fila.getCell(9);
			celda.setCellFormula("ROUND(" + proyeccionEquipo.getRatio4Sh()+ "/" + proyeccionEquipo.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 9, 12));
			
			celda = fila.getCell(13);
			celda.setCellValue(proyeccionEquipo.getNroDiasAlRetorno());

			k++;
			
			
			
			

			// RPO PLAN
			int filaRpoPlanIni = k + 1;
			int filaRpoPlanFin = k;
			Collections.sort(rpoPlan, (x, y) -> x.getEta().compareTo(y.getEta()));
			for (ProyeccionEquipoDetDTO rp : rpoPlan) {

				fila = sheet1.createRow(k);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet1.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue(rp.getAlNave() + " " + rp.getViaje() + " " + "rpo plan");

				celda = fila.getCell(1);
				celda.setCellValue(rp.getCaRpo2Sd());
				sheet1.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(rp.getCaRpo4Sd());
				sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(rp.getCaRpo4Sh());
				sheet1.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

				celda = fila.getCell(13);
				celda.setCellValue(new SimpleDateFormat("dd-MMM").format(rp.getEta()));

				k++;
				
				filaRpoPlanFin = k;

			}

			// AVAILABLE
			fila = sheet1.createRow(k);

			for (int i = 0; i < 14; i++) {

				celda = fila.createCell(i);

			}

			celda = fila.getCell(0);
			estilo = creaEstilosSubTituloLeft(wb);
			celda.setCellStyle(estilo);

			for (int i = 1; i < 5; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "2SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 5; i < 9; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SD");
				celda.setCellStyle(estilo);

			}

			for (int i = 9; i < 13; i++) {

				celda = fila.getCell(i);
				estilo = creaEstilosSubTitulo(wb, "4SH");
				celda.setCellStyle(estilo);

			}

			celda = fila.getCell(13);
			estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
			celda.setCellStyle(estilo);

			fila = sheet1.getRow(k);

			celda = fila.getCell(0);
			celda.setCellValue("Available");

			
			
			String formulaAvailable = "B3+B4+B5-B" + filaBook + "+B" + filaPick + "+B" + filaRatio;
			int m = filaRpoPlanIni;
			
			for(int i = 0; i < rpoPlan.size(); i++) {
				
				formulaAvailable = formulaAvailable + "+B" + m;
				m++;
				
			}
			
			celda = fila.getCell(1);
			celda.setCellFormula(formulaAvailable);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

			
			
			formulaAvailable = "F3+F4+F5-F" + filaBook + "+F" + filaPick + "+F" + filaRatio;
			m = filaRpoPlanIni;
			
			for(int i = 0; i < rpoPlan.size(); i++) {
				
				formulaAvailable = formulaAvailable + "+F" + m;
				m++;
				
			}
			celda = fila.getCell(5);
			celda.setCellFormula(formulaAvailable);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

			
			formulaAvailable = "J3+J4+J5-J" + filaBook + "+J" + filaPick  + "+J" + filaRatio;
			m = filaRpoPlanIni;
			
			for(int i = 0; i < rpoPlan.size(); i++) {
				
				formulaAvailable = formulaAvailable + "+J" + m;
				m++;
				
			}
			celda = fila.getCell(9);
			celda.setCellFormula(formulaAvailable);
			sheet1.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

			k++;

			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();
			
			
			
			
			
			// SHEET 2
			
			if(proyeccionVenta != null) {
				
				XSSFSheet sheet2 = wb.getSheetAt(1);
				List<ProyeccionEquipoDetDTO> detalleVenta = proyeccionVenta.getDetalles().stream()
						.filter(x -> x.getFgRpoPlan().equals("N")).collect(Collectors.toList());

				// CABECERAS

				fila = sheet2.createRow(0);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				fila = sheet2.getRow(0);

				celda = fila.getCell(1);
				celda.setCellValue("2SD");
				sheet2.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue("4SD");
				sheet2.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue("4SH");
				sheet2.addMergedRegion(new CellRangeAddress(0, 0, 9, 12));

				
				
				
				// TITULOS fe NONfe PICKED UP
				fila = sheet2.createRow(1);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosSubTitulo(wb, "ETA");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(1);

				celda = fila.getCell(1);
				celda.setCellValue("Non F.E.");

				celda = fila.getCell(2);
				celda.setCellValue("Picked up");

				celda = fila.getCell(3);
				celda.setCellValue("F.E.");

				celda = fila.getCell(4);
				celda.setCellValue("Picked up");

				celda = fila.getCell(5);
				celda.setCellValue("Non F.E.");

				celda = fila.getCell(6);
				celda.setCellValue("Picked up");

				celda = fila.getCell(7);
				celda.setCellValue("F.E.");

				celda = fila.getCell(8);
				celda.setCellValue("Picked up");

				celda = fila.getCell(9);
				celda.setCellValue("Non F.E.");

				celda = fila.getCell(10);
				celda.setCellValue("Picked up");

				celda = fila.getCell(11);
				celda.setCellValue("F.E.");

				celda = fila.getCell(12);
				celda.setCellValue("Picked up");

				celda = fila.getCell(13);
				celda.setCellValue("ETA");
				
				
				
				// STOCK
				fila = sheet2.createRow(2);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosSubTitulo(wb, "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(2);

				celda = fila.getCell(0);
				celda.setCellValue("STOCK");

				celda = fila.getCell(1);
				celda.setCellValue(proyeccionVenta.getStock2Sd());
				sheet2.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(proyeccionVenta.getStock4Sd());
				sheet2.addMergedRegion(new CellRangeAddress(2, 2, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(proyeccionVenta.getStock4Sh());
				sheet2.addMergedRegion(new CellRangeAddress(2, 2, 9, 12));

				
				
				// MK
				fila = sheet2.createRow(3);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosSubTitulo(wb, "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(3);

				celda = fila.getCell(0);
				celda.setCellValue("MK");

				celda = fila.getCell(1);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(3, 3, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(3, 3, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(3, 3, 9, 12));

				
				
				
				// T/S
				fila = sheet2.createRow(4);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosSubTitulo(wb, "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(4);

				celda = fila.getCell(0);
				celda.setCellValue("T/S");

				celda = fila.getCell(1);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(4, 4, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(4, 4, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(0);
				sheet2.addMergedRegion(new CellRangeAddress(4, 4, 9, 12));
				
				
				
				
				
				
				// DETALLE
				k = 5;
				filaInicioDetalle = k + 1;
				filaFinDetalle = k;
				Collections.sort(detalleVenta, (x, y) -> x.getEta().compareTo(y.getEta()));
				for (int f = 0; f < detalleVenta.size(); f++) {

					fila = sheet2.createRow(k);

					for (int i = 0; i < 14; i++) {

						celda = fila.createCell(i);

					}

					fila = sheet2.getRow(k);

					celda = fila.getCell(0);
					celda.setCellValue(detalleVenta.get(f).getAlNave() + " " + detalleVenta.get(f).getViaje());
					XSSFCellStyle style = creaEstilosDetalle(wb, "NAVE", "");
					celda.setCellStyle(style);

					celda = fila.getCell(1);
					celda.setCellValue(detalleVenta.get(f).getCa2SdNoFe());
					style = creaEstilosDetalle(wb, "DETALLE", "2SD");
					celda.setCellStyle(style);

					celda = fila.getCell(2);
					celda.setCellValue(detalleVenta.get(f).getCa2SdNoFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "2SD");
					celda.setCellStyle(style);

					celda = fila.getCell(3);
					celda.setCellValue(detalleVenta.get(f).getCa2SdFe());
					style = creaEstilosDetalle(wb, "DETALLE", "2SD");
					celda.setCellStyle(style);

					celda = fila.getCell(4);
					celda.setCellValue(detalleVenta.get(f).getCa2SdFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "2SD");
					celda.setCellStyle(style);

					celda = fila.getCell(5);
					celda.setCellValue(detalleVenta.get(f).getCa4SdNoFe());
					style = creaEstilosDetalle(wb, "DETALLE", "4SD");
					celda.setCellStyle(style);

					celda = fila.getCell(6);
					celda.setCellValue(detalleVenta.get(f).getCa4SdNoFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "4SD");
					celda.setCellStyle(style);

					celda = fila.getCell(7);
					celda.setCellValue(detalleVenta.get(f).getCa4SdFe());
					style = creaEstilosDetalle(wb, "DETALLE", "4SD");
					celda.setCellStyle(style);

					celda = fila.getCell(8);
					celda.setCellValue(detalleVenta.get(f).getCa4SdFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "4SD");
					celda.setCellStyle(style);

					celda = fila.getCell(9);
					celda.setCellValue(detalleVenta.get(f).getCa4ShNoFe());
					style = creaEstilosDetalle(wb, "DETALLE", "4SH");
					celda.setCellStyle(style);

					celda = fila.getCell(10);
					celda.setCellValue(detalleVenta.get(f).getCa4ShNoFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "4SH");
					celda.setCellStyle(style);

					celda = fila.getCell(11);
					celda.setCellValue(detalleVenta.get(f).getCa4ShFe());
					style = creaEstilosDetalle(wb, "DETALLE", "4SH");
					celda.setCellStyle(style);

					celda = fila.getCell(12);
					celda.setCellValue(detalleVenta.get(f).getCa4ShFePick());
					style = creaEstilosDetalle(wb, "DETALLE", "4SH");
					celda.setCellStyle(style);

					celda = fila.getCell(13);
					celda.setCellValue(new SimpleDateFormat("dd-MMM").format(detalleVenta.get(f).getEta()));
					style = creaEstilosDetalle(wb, "DETALLE", "ETADET");
					celda.setCellStyle(style);

					k++;
					filaFinDetalle = k;

				}

				
				
				
				
				
				// SUM
				fila = sheet2.createRow(k);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue("Sum");

				celda = fila.getCell(1);
				celda.setCellFormula("sum(B" + filaInicioDetalle + ":B" + filaFinDetalle + ")");

				celda = fila.getCell(2);
				celda.setCellFormula("sum(C" + filaInicioDetalle + ":C" + filaFinDetalle + ")");

				celda = fila.getCell(3);
				celda.setCellFormula("sum(D" + filaInicioDetalle + ":D" + filaFinDetalle + ")");

				celda = fila.getCell(4);
				celda.setCellFormula("sum(E" + filaInicioDetalle + ":E" + filaFinDetalle + ")");

				celda = fila.getCell(5);
				celda.setCellFormula("sum(F" + filaInicioDetalle + ":F" + filaFinDetalle + ")");

				celda = fila.getCell(6);
				celda.setCellFormula("sum(G" + filaInicioDetalle + ":G" + filaFinDetalle + ")");

				celda = fila.getCell(7);
				celda.setCellFormula("sum(H" + filaInicioDetalle + ":H" + filaFinDetalle + ")");

				celda = fila.getCell(8);
				celda.setCellFormula("sum(I" + filaInicioDetalle + ":I" + filaFinDetalle + ")");

				celda = fila.getCell(9);
				celda.setCellFormula("sum(J" + filaInicioDetalle + ":J" + filaFinDetalle + ")");

				celda = fila.getCell(10);
				celda.setCellFormula("sum(K" + filaInicioDetalle + ":K" + filaFinDetalle + ")");

				celda = fila.getCell(11);
				celda.setCellFormula("sum(L" + filaInicioDetalle + ":L" + filaFinDetalle + ")");

				celda = fila.getCell(12);
				celda.setCellFormula("sum(M" + filaInicioDetalle + ":M" + filaFinDetalle + ")");

				k++;

				
				
				
				
				// TT BOOKING
				fila = sheet2.createRow(k);
				filaBook = k + 1;

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(k);
				celda = fila.getCell(0);
				celda.setCellValue("TT Booking");

				celda = fila.getCell(1);
				celda.setCellValue(proyeccionVenta.getTo2SdBook());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(proyeccionVenta.getTo4SdBook());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(proyeccionVenta.getTo4ShBook());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

				k++;

				
				
				
				// TT PICK UP
				fila = sheet2.createRow(k);
				filaPick = k + 1;

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue("TT Picked up");

				celda = fila.getCell(1);
				celda.setCellValue(proyeccionVenta.getTo2SdPick());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

				celda = fila.getCell(5);
				celda.setCellValue(proyeccionVenta.getTo4SdPick());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

				celda = fila.getCell(9);
				celda.setCellValue(proyeccionVenta.getTo4ShPick());
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

				k++;
				
				
				
				
				
				
				
				// RATIO DEVOLUCION
				fila = sheet2.createRow(k);
				filaRatio = k + 1;
				
				formateador = new SimpleDateFormat("MMM-dd");
				
				// List<ProyeccionEquipoDetDTO> detalleRpoPlan = proyeccionEquipo.getDetalles().stream().filter(detalle -> detalle.getFgRpoPlan().equals("S")).collect(Collectors.toList());
				
				// proyeccionEquipo.getRatioDevolucion().getFechaRatioStr()

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue("Empty return until " + formateador.format(proyeccionVenta.getFeEmptyReturn()) + " (Empty return one week before last vessel ETA");

				celda = fila.getCell(1);
				celda.setCellFormula("ROUND(" + proyeccionVenta.getRatio2Sd() + "/" + proyeccionVenta.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

				celda = fila.getCell(5);
				celda.setCellFormula("ROUND(" + proyeccionVenta.getRatio4Sd() + "/" + proyeccionVenta.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

				celda = fila.getCell(9);
				celda.setCellFormula("ROUND(" + proyeccionVenta.getRatio4Sh()+ "/" + proyeccionVenta.getNroDiasHabiles() + "*N" + (k+1) + ",0)");
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 9, 12));
				
				celda = fila.getCell(13);
				celda.setCellValue(proyeccionVenta.getNroDiasAlRetorno());

				k++;
				
				
				
				
				
				
				// RPO PLAN
				filaRpoPlanIni = k + 1;
				filaRpoPlanFin = k;
				Collections.sort(rpoPlan, (x, y) -> x.getEta().compareTo(y.getEta()));
				for (ProyeccionEquipoDetDTO rp : rpoPlan) {

					fila = sheet2.createRow(k);

					for (int i = 0; i < 14; i++) {

						celda = fila.createCell(i);

					}

					celda = fila.getCell(0);
					estilo = creaEstilosSubTituloLeft(wb);
					celda.setCellStyle(estilo);

					for (int i = 1; i < 5; i++) {

						celda = fila.getCell(i);
						estilo = creaEstilosSubTitulo(wb, "2SD");
						celda.setCellStyle(estilo);

					}

					for (int i = 5; i < 9; i++) {

						celda = fila.getCell(i);
						estilo = creaEstilosSubTitulo(wb, "4SD");
						celda.setCellStyle(estilo);

					}

					for (int i = 9; i < 13; i++) {

						celda = fila.getCell(i);
						estilo = creaEstilosSubTitulo(wb, "4SH");
						celda.setCellStyle(estilo);

					}

					celda = fila.getCell(13);
					estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
					celda.setCellStyle(estilo);

					fila = sheet2.getRow(k);

					celda = fila.getCell(0);
					celda.setCellValue(rp.getAlNave() + " " + rp.getViaje() + " " + "rpo plan");

					celda = fila.getCell(1);
					celda.setCellValue(rp.getCaRpo2Sd());
					sheet2.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

					celda = fila.getCell(5);
					celda.setCellValue(rp.getCaRpo4Sd());
					sheet2.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

					celda = fila.getCell(9);
					celda.setCellValue(rp.getCaRpo4Sh());
					sheet2.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

					celda = fila.getCell(13);
					celda.setCellValue(new SimpleDateFormat("dd-MMM").format(rp.getEta()));

					k++;
					
					filaRpoPlanFin = k;

				}
				
				
				
				
				
				
				// AVAILABLE
				fila = sheet2.createRow(k);

				for (int i = 0; i < 14; i++) {

					celda = fila.createCell(i);

				}

				celda = fila.getCell(0);
				estilo = creaEstilosSubTituloLeft(wb);
				celda.setCellStyle(estilo);

				for (int i = 1; i < 5; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "2SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 5; i < 9; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SD");
					celda.setCellStyle(estilo);

				}

				for (int i = 9; i < 13; i++) {

					celda = fila.getCell(i);
					estilo = creaEstilosSubTitulo(wb, "4SH");
					celda.setCellStyle(estilo);

				}

				celda = fila.getCell(13);
				estilo = creaEstilosDetalle(wb, "DETALLE", "ETADET");
				celda.setCellStyle(estilo);

				fila = sheet2.getRow(k);

				celda = fila.getCell(0);
				celda.setCellValue("Available");

				
				
				formulaAvailable = "B3+B4+B5-B" + filaBook + "+B" + filaPick + "+B" + filaRatio;
				m = filaRpoPlanIni;
				
				for(int i = 0; i < rpoPlan.size(); i++) {
					
					formulaAvailable = formulaAvailable + "+B" + m;
					m++;
					
				}
				
				celda = fila.getCell(1);
				celda.setCellFormula(formulaAvailable);
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 1, 4));

				
				
				formulaAvailable = "F3+F4+F5-F" + filaBook + "+F" + filaPick + "+F" + filaRatio;
				m = filaRpoPlanIni;
				
				for(int i = 0; i < rpoPlan.size(); i++) {
					
					formulaAvailable = formulaAvailable + "+F" + m;
					m++;
					
				}
				celda = fila.getCell(5);
				celda.setCellFormula(formulaAvailable);
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 5, 8));

				
				formulaAvailable = "J3+J4+J5-J" + filaBook + "+J" + filaPick  + "+J" + filaRatio;
				m = filaRpoPlanIni;
				
				for(int i = 0; i < rpoPlan.size(); i++) {
					
					formulaAvailable = formulaAvailable + "+J" + m;
					m++;
					
				}
				celda = fila.getCell(9);
				celda.setCellFormula(formulaAvailable);
				sheet2.addMergedRegion(new CellRangeAddress(k, k, 9, 12));

				k++;
				
				
				
				
				sheet2.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();
				
			}
			
			

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

			wb.close();

			fos.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return fileName;

	}

	private XSSFCellStyle creaEstilosTitulo(XSSFWorkbook wb, String tipo) {

		XSSFCellStyle style = wb.createCellStyle();

		// BOORDES
		XSSFColor colorBorde = new XSSFColor(Color.BLACK);

		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderColor(BorderSide.TOP, colorBorde);

		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderColor(BorderSide.LEFT, colorBorde);

		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderColor(BorderSide.BOTTOM, colorBorde);

		style.setBorderRight(BorderStyle.MEDIUM);
		style.setBorderColor(BorderSide.RIGHT, colorBorde);

		// FUENTE
		XSSFFont tituloFont = wb.createFont();
		tituloFont.setBold(true);
		tituloFont.setFontHeight(12);
		tituloFont.setColor(new XSSFColor(Color.BLACK));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		if (tipo.equals("2SD")) {

			XSSFColor colorFondo = new XSSFColor(new Color(42, 197, 239));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("4SD")) {

			XSSFColor colorFondo = new XSSFColor(new Color(137, 214, 133));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("4SH")) {

			XSSFColor colorFondo = new XSSFColor(new Color(242, 160, 99));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		}

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosSubTitulo(XSSFWorkbook wb, String tipo) {

		XSSFCellStyle style = wb.createCellStyle();

		// BOORDES
		XSSFColor colorBorde = new XSSFColor(Color.BLACK);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderColor(BorderSide.TOP, colorBorde);

		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderColor(BorderSide.LEFT, colorBorde);

		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderColor(BorderSide.BOTTOM, colorBorde);

		style.setBorderRight(BorderStyle.THIN);
		style.setBorderColor(BorderSide.RIGHT, colorBorde);

		// FUENTE
		XSSFFont tituloFont = wb.createFont();
		tituloFont.setBold(true);
		tituloFont.setFontHeight(12);
		tituloFont.setColor(new XSSFColor(Color.BLACK));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		if (tipo.equals("2SD")) {

			XSSFColor colorFondo = new XSSFColor(new Color(42, 197, 239));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("4SD")) {

			XSSFColor colorFondo = new XSSFColor(new Color(137, 214, 133));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("4SH")) {

			XSSFColor colorFondo = new XSSFColor(new Color(242, 160, 99));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("ETA")) {

			XSSFColor colorFondo = new XSSFColor(new Color(0, 128, 0));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		} else if (tipo.equals("ETADET")) {

			XSSFColor colorFondo = new XSSFColor(new Color(255, 255, 0));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

		}

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosSubTituloLeft(XSSFWorkbook wb) {

		XSSFCellStyle style = wb.createCellStyle();

		// BOORDES
		XSSFColor colorBorde = new XSSFColor(Color.BLACK);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderColor(BorderSide.TOP, colorBorde);

		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderColor(BorderSide.LEFT, colorBorde);

		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderColor(BorderSide.BOTTOM, colorBorde);

		style.setBorderRight(BorderStyle.THIN);
		style.setBorderColor(BorderSide.RIGHT, colorBorde);

		// FUENTE
		XSSFFont tituloFont = wb.createFont();
		tituloFont.setFontHeight(12);
		tituloFont.setColor(new XSSFColor(Color.BLACK));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		XSSFColor colorFondo = new XSSFColor(new Color(0, 128, 0));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorFondo);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosDetalle(XSSFWorkbook wb, String tipo1, String tipo2) {

		XSSFCellStyle style = wb.createCellStyle();

		// BOORDES
		XSSFColor colorBorde = new XSSFColor(Color.BLACK);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderColor(BorderSide.TOP, colorBorde);

		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderColor(BorderSide.LEFT, colorBorde);

		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderColor(BorderSide.BOTTOM, colorBorde);

		style.setBorderRight(BorderStyle.THIN);
		style.setBorderColor(BorderSide.RIGHT, colorBorde);

		// FUENTE
		XSSFFont tituloFont = wb.createFont();
		tituloFont.setFontHeight(12);
		tituloFont.setColor(new XSSFColor(Color.BLACK));

		style.setFont(tituloFont);

		if (tipo1.equals("NAVE")) {

			// COLOR DE FONDO
			XSSFColor colorFondo = new XSSFColor(new Color(0, 128, 0));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(colorFondo);

			// ALINEACION DEL TEXTO
			style.setAlignment(HorizontalAlignment.LEFT);

		} else if (tipo1.equals("DETALLE")) {

			style.setAlignment(HorizontalAlignment.CENTER);

			if (tipo2.equals("2SD")) {

				// COLOR DE FONDO
				XSSFColor colorFondo = new XSSFColor(new Color(42, 197, 239));
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setFillForegroundColor(colorFondo);

			} else if (tipo2.equals("4SD")) {

				// COLOR DE FONDO
				XSSFColor colorFondo = new XSSFColor(new Color(137, 214, 133));
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setFillForegroundColor(colorFondo);

			} else if (tipo2.equals("4SH")) {

				// COLOR DE FONDO
				XSSFColor colorFondo = new XSSFColor(new Color(242, 160, 99));
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setFillForegroundColor(colorFondo);

			} else if (tipo2.equals("ETADET")) {

				// COLOR DE FONDO
				XSSFColor colorFondo = new XSSFColor(new Color(255, 255, 0));
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setFillForegroundColor(colorFondo);

			}

		}

		return style;

	}

}
