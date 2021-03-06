package com.wollcorp.controladores.forecast;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.wollcorp.beans.forecast.ForecastWSA1Partner;
import com.wollcorp.beans.forecast.ForecastWSA1PartnerCargo;
import com.wollcorp.dao.ForecastDao;
import com.wollcorp.globales.Log;

public class ForecastWSA1PartnerControlador {
	
	public String generaForecastWSA1Partner(String coFcst, String alNave, String token) throws SQLException {

		List<ForecastWSA1Partner> forecast;
		List<ForecastWSA1PartnerCargo> cargos;

		String fileName = null;

		forecast = obtieneForecastWSA1Partner(coFcst, token);

		if (!forecast.isEmpty()) {

			cargos = obtieneForecastWSA1PartnerCargo(coFcst, token);

			fileName = generaExcelForecastWSA1Partner(forecast, alNave, coFcst, cargos);

		}

		return fileName;

	}

	private List<ForecastWSA1Partner> obtieneForecastWSA1Partner(String coFcst, String token) throws SQLException {

		return (new ForecastDao()).obtieneForecastWSA1Partner(coFcst, token);

	}

	private List<ForecastWSA1PartnerCargo> obtieneForecastWSA1PartnerCargo(String coFcst, String token) throws SQLException {

		return (new ForecastDao()).obtieneForecastWSA1PartnerCargo(coFcst, token);

	}

	private String generaExcelForecastWSA1Partner(List<ForecastWSA1Partner> data, String alNave, String coFcst,
			List<ForecastWSA1PartnerCargo> cargos) {

		String fileName = "ForecastWSA1Partner_" + Instant.now().toEpochMilli() + ".xlsx";
		String filePath = "c:\\opt\\assets\\reports\\" + fileName;

		String titulo1 = "MV. " + alNave;
		String hoja1 = "FORECAST";

		String tituloRH = "4RH CARGO";
		String tituloDG = "DG CARGO DETAIL";

		List<String> cabeceras = new ArrayList<String>();

		int rowTitulo = 0;
		int colTitulo = 0;

		int rowCabecera = 1;

		int rowData = 2;
		int colData = 0;
		int nro = 1;

		// LEYENDO DATOS DE CABECERA
		cabeceras.add("N");
		cabeceras.add("POD");
		cabeceras.add("TYPE");
		cabeceras.add("QTY");
		cabeceras.add("WGT");
		cabeceras.add("OOG");
		cabeceras.add("STATUS");

		try {

			Log.mensaje = "GENERANDO EXCEL PARA FORECAST " + coFcst;
			Log.registraInfo();

			XSSFWorkbook wb = new XSSFWorkbook();

			XSSFSheet sheet1 = wb.createSheet(hoja1);

			XSSFRow row;
			XSSFCell cell;

			// ESCRIBIENDO TITULOS
			row = sheet1.createRow(rowTitulo);
			XSSFCellStyle styleTitulo = creaEstilosTitulo(wb);

			for (int i = colTitulo; i < cabeceras.size(); i++) {

				cell = row.createCell(i);
				cell.setCellStyle(styleTitulo);

			}

			sheet1.addMergedRegion(new CellRangeAddress(rowTitulo, rowTitulo, colTitulo, cabeceras.size() - 1));

			cell = row.getCell(colTitulo);
			cell.setCellValue(titulo1);

			// ESCRIBIENDO CABECERAS
			row = sheet1.createRow(rowCabecera);
			XSSFCellStyle styleCabecera = creaEstilosCabecera(wb);

			for (int i = 0; i < cabeceras.size(); i++) {

				cell = row.createCell(i);
				cell.setCellValue(cabeceras.get(i));
				cell.setCellStyle(styleCabecera);

			}

			// ESCRIBIENDO DATA
			XSSFCellStyle styleData = creaEstilosData(wb);

			for (ForecastWSA1Partner forecast : data) {

				// N - NUMERO
				row = sheet1.createRow(rowData);
				cell = row.createCell(colData);
				cell.setCellValue(nro);
				cell.setCellStyle(styleData);

				// POD - PUERTO
				cell = row.createCell(colData + 1);
				cell.setCellValue(forecast.getPod());
				cell.setCellStyle(styleData);

				// TYPE - SIZE TIPO
				cell = row.createCell(colData + 2);
				cell.setCellValue(forecast.getTipo());
				cell.setCellStyle(styleData);

				// QTY - CANTIDAD
				cell = row.createCell(colData + 3);
				cell.setCellValue(forecast.getCantidad());
				cell.setCellStyle(styleData);

				// WGT - PESO
				cell = row.createCell(colData + 4);
				cell.setCellValue((double) forecast.getPesoKg() / 1000);
				cell.setCellStyle(styleData);

				// OOG - COLUMNA VACIA
				cell = row.createCell(colData + 5);
				cell.setCellStyle(styleData);

				// STATUS - ESTADO
				String estado;
				if (forecast.getEstado().equals("F")) {

					estado = "FULL";

				} else {

					estado = "EMPTY";

				}

				cell = row.createCell(colData + 6);
				cell.setCellValue(estado);
				cell.setCellStyle(styleData);

				nro++;
				rowData++;

			}

			// ESCRIBIENDO CARGOS - RH - DG

			List<ForecastWSA1PartnerCargo> rh = cargos.stream().filter(x -> x.getTipo().equals("RH"))
					.collect(Collectors.toList());
			List<ForecastWSA1PartnerCargo> dg = cargos.stream().filter(x -> x.getTipo().equals("DG"))
					.collect(Collectors.toList());

			// ESCRIBIENTO TITULO RH
			int rowRH = sheet1.getLastRowNum() + 3;
			int colRH = 0;

			row = sheet1.createRow(rowRH);
			XSSFCellStyle styleTituloRH = creaEstilosTituloRH(wb);

			for (int i = 0; i < cabeceras.size() - 1; i++) {

				cell = row.createCell(i);
				cell.setCellStyle(styleTituloRH);

			}

			sheet1.addMergedRegion(new CellRangeAddress(rowRH, rowRH, colRH, cabeceras.size() - 2));
			cell = row.getCell(colRH);
			cell.setCellValue(tituloRH);

			// ESCRIBIENDO DATA RH
			rowRH++;

			for (int i = colRH; i < rh.size(); i++) {

				row = sheet1.createRow(rowRH);
				
				for (int c = 0; c < cabeceras.size() - 1; c++) {

					cell = row.createCell(c);
					cell.setCellStyle(styleData);

				}
				
				sheet1.addMergedRegion(new CellRangeAddress(rowRH, rowRH, colRH, cabeceras.size() - 2));
				cell = row.getCell(colRH);
				cell.setCellValue(rh.get(i).getDescripcion());

				rowRH++;

			}

			// ESCRIBIENDO TITULO DG
			int rowDG = sheet1.getLastRowNum() + 1;
			int colDG = 0;

			row = sheet1.createRow(rowDG);
			XSSFCellStyle styleTituloDG = creaEstilosTituloDG(wb);
			for (int i = 0; i < cabeceras.size() - 1; i++) {

				cell = row.createCell(i);
				cell.setCellStyle(styleTituloDG);

			}

			sheet1.addMergedRegion(new CellRangeAddress(rowDG, rowDG, colDG, cabeceras.size() - 2));
			cell = row.getCell(colDG);
			cell.setCellValue(tituloDG);

			rowDG++;

			for (int i = colDG; i < dg.size(); i++) {

				row = sheet1.createRow(rowDG);
				
				for(int c = colDG; c < cabeceras.size() - 1; c++) {
					
					cell = row.createCell(c);
					cell.setCellStyle(styleData);
					
				}
				
				sheet1.addMergedRegion(new CellRangeAddress(rowDG, rowDG, colDG, cabeceras.size() - 2));
				cell = row.getCell(colDG);
				cell.setCellValue(dg.get(i).getDescripcion());


				
				rowDG++;

			}

			// sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);
			wb.close();
			fos.close();

			Log.mensaje = "EXCEL CREADO...";
			Log.registraInfo();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return fileName;

	}

	private XSSFCellStyle creaEstilosTitulo(XSSFWorkbook wb) {

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
		tituloFont.setFontHeight(20);
		tituloFont.setColor(new XSSFColor(Color.WHITE));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		XSSFColor colorFondo = new XSSFColor(new Color(247, 94, 037));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorFondo);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosCabecera(XSSFWorkbook wb) {

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
		XSSFColor color = new XSSFColor(Color.WHITE);

		XSSFFont tituloFont = wb.createFont();
		tituloFont.setBold(true);
		tituloFont.setFontHeight(13);
		tituloFont.setColor(color);

		style.setFont(tituloFont);

		// COLOR DE FONDO
		XSSFColor colorFondo = new XSSFColor(new Color(247, 94, 037));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorFondo);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosData(XSSFWorkbook wb) {

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
		XSSFFont fuente = wb.createFont();
		fuente.setBold(true);
		fuente.setFontHeight(10);

		style.setFont(fuente);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosTituloRH(XSSFWorkbook wb) {

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
		tituloFont.setFontHeight(10);
		tituloFont.setColor(new XSSFColor(new Color(38, 157, 32)));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		XSSFColor colorFondo = new XSSFColor(new Color(252, 213, 180));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorFondo);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

	private XSSFCellStyle creaEstilosTituloDG(XSSFWorkbook wb) {

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
		tituloFont.setFontHeight(10);
		tituloFont.setColor(new XSSFColor(Color.RED));

		style.setFont(tituloFont);

		// COLOR DE FONDO
		XSSFColor colorFondo = new XSSFColor(Color.YELLOW);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorFondo);

		// ALINEACION DEL TEXTO
		style.setAlignment(HorizontalAlignment.CENTER);

		return style;

	}

}
