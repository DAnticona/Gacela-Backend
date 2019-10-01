package com.wollcorp.controladores.forecast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wollcorp.beans.forecast.ForecastWSA4Partner;
import com.wollcorp.beans.forecast.ForecastWSA4PartnerCargo;
import com.wollcorp.dao.ForecastDaoImpl;
import com.wollcorp.globales.Log;

public class ForecastWSA4PartnerControlador {
	
	public String generaForecastWSA4Partner(String coFcst, String noNave, String token) {

		List<ForecastWSA4Partner> forecast;
		List<ForecastWSA4PartnerCargo> cargos;

		String fileName = null;

		forecast = obtieneForecastWSA4Partner(coFcst, token);

		if (!forecast.isEmpty()) {

			cargos = obtieneForecastWSA4PartnerCargo(coFcst, token);

			fileName = generaExcelForecastWSA4Partner(forecast, noNave, coFcst, cargos);

		}

		return fileName;

	}
	
	
	
	

	private List<ForecastWSA4Partner> obtieneForecastWSA4Partner(String coFcst, String token) {

		return (new ForecastDaoImpl()).obtieneForecastWSA4Partner(coFcst, token);

	}
	
	

	private List<ForecastWSA4PartnerCargo> obtieneForecastWSA4PartnerCargo(String coFcst, String token) {

		return (new ForecastDaoImpl()).obtieneForecastWSA4PartnerCargo(coFcst, token);

	}
	
	
	private String generaExcelForecastWSA4Partner(List<ForecastWSA4Partner> data, String noNave, String coFcst,
			List<ForecastWSA4PartnerCargo> cargos) {
		
		String fileName = "ForecastWSA4Partner_" + Instant.now().toEpochMilli() + ".xlsx";
		String filePath = "c:\\opt\\assets\\reports\\" + fileName;
		String templateWSA4Partner = "c:\\opt\\assets\\templates\\forecastWSA4Partner.xlsx";
		
		// System.out.println("Codigo Forecast: " + coFcst);
		
		List<String> cabeceras = new ArrayList<String>();
		
		cabeceras.add("GUAYAQUIL (GGJ)");
		cabeceras.add("LAZARO CARDENAS (LLZ2)");
		cabeceras.add("MANZANILLO (MNZ)");
		cabeceras.add("ENSENADA (ESE)");
		cabeceras.add("YOKOHAMA (YOK)");
		cabeceras.add("PUSAN (P2H)");
		cabeceras.add("KAOHSIUNG (KAO)");
		cabeceras.add("HONG KONG (HKG)");
		cabeceras.add("SHEKOU (CIW)");
		cabeceras.add("NINGBO (NTB)");
		cabeceras.add("SHANGHAI (YAN)");
		
		int totalPuertos = 11;
		
		try {
			
			Log.mensaje = "GENERANDO EXCEL...";
			Log.registraInfo();
			
			Path origenPath = FileSystems.getDefault().getPath(templateWSA4Partner);
			Path destinoPath = FileSystems.getDefault().getPath(filePath);

			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			FileInputStream fis = new FileInputStream(new File(filePath));

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			
			XSSFCell cell;
			
			// ESCRIBIENDO TITULO - NOMBRE DE LA NAVE
			cell = sheet1.getRow(3).getCell(4);
			cell.setCellValue(noNave);
			

			// LLENANDO LOS DATOS FULL
			List<ForecastWSA4Partner> dataFull = data.stream()
														.filter(x -> x.getEstado().equals("F"))
														.collect(Collectors.toList());
			
			int r = 8;
			int cabecera = 7;
			
			for(int i = 0; i < totalPuertos; i++) {
				
				for(int j = 0; j < dataFull.size(); j++) {
					
					int c = 1;
					cell = sheet1.getRow(r).getCell(c);
					String celda = cell.getStringCellValue();
					
					if(celda.trim().equals(dataFull.get(j).getPod().trim())) {
						
						// 20'std
						cell = sheet1.getRow(cabecera).getCell(c + 1);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataFull.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 1);
							cell.setCellValue(dataFull.get(j).getCantidad());
							
						}
						
						
						// 40'std
						cell = sheet1.getRow(cabecera).getCell(c + 2);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataFull.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 2);
							cell.setCellValue(dataFull.get(j).getCantidad());
							
						}
						
						
						// 40'hc
						cell = sheet1.getRow(cabecera).getCell(c + 3);
						celda = cell.getStringCellValue();
						// System.out.println(celda);
						// System.out.println(dataFull.get(j).getTipo());
						
						if(celda.trim().equals(dataFull.get(j).getTipo().trim())) {
							
							// System.out.println("Cantidad: " + dataFull.get(j).getCantidad());
							
							cell = sheet1.getRow(r).getCell(c + 3);
							cell.setCellValue(dataFull.get(j).getCantidad());
							
						}
						
						
						// 40'rfr
						cell = sheet1.getRow(cabecera).getCell(c + 5);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataFull.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 5);
							cell.setCellValue(dataFull.get(j).getCantidad());
							
						}
						
						
						// Tons
						cell = sheet1.getRow(cabecera).getCell(c + 8);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals("Tons")) {
							
							cell = sheet1.getRow(r).getCell(c + 8);
							double peso = cell.getNumericCellValue();
							double valor = (double)(dataFull.get(j).getPesoKg())/1000;
							
							// System.out.println(dataFull.get(j).getPod() + " :");
							// System.out.println("Peso: " + peso);
							
							if(peso == 0) {
								
								cell.setCellValue(valor);
								// System.out.println("Peso Unit: " + valor);
								
							} else {
								
								// double valor = peso + dataFull.get(j).getPesoKg();
								cell.setCellValue(peso + valor);
								// System.out.println("Peso Anterior: " + peso);
								// System.out.println("Peso Actual: " + valor);
								// System.out.println("Peso Total: " + (peso + valor));
								
							}
							
							
						}
						
					}
					
				}
				
				r++;
				
			}
			
			
			// LLENANDO LOS DATOS EMPTY
			List<ForecastWSA4Partner> dataEmpty = data.stream()
														.filter(x -> x.getEstado().equals("E"))
														.collect(Collectors.toList());
			
			r = 8;
			cabecera = 7;
			
			for(int i = 0; i < totalPuertos; i++) {
				
				for(int j = 0; j < dataEmpty.size(); j++) {
					
					int c = 10;
					cell = sheet1.getRow(r).getCell(c);
					String celda = cell.getStringCellValue();
					
					if(celda.trim().equals(dataEmpty.get(j).getPod().trim())) {
						
						// 20'std
						cell = sheet1.getRow(cabecera).getCell(c + 1);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataEmpty.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 1);
							cell.setCellValue(dataEmpty.get(j).getCantidad());
							
						}
						
						
						// 40'std
						cell = sheet1.getRow(cabecera).getCell(c + 2);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataEmpty.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 2);
							cell.setCellValue(dataEmpty.get(j).getCantidad());
							
						}
						
						
						// 40'hc
						cell = sheet1.getRow(cabecera).getCell(c + 3);
						celda = cell.getStringCellValue();
						System.out.println(celda);
						System.out.println(dataFull.get(j).getTipo());
						
						if(celda.trim().equals(dataEmpty.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 3);
							cell.setCellValue(dataEmpty.get(j).getCantidad());
							
						}
						
						
						// 40'rfr
						cell = sheet1.getRow(cabecera).getCell(c + 5);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals(dataEmpty.get(j).getTipo().trim())) {
							
							cell = sheet1.getRow(r).getCell(c + 5);
							cell.setCellValue(dataEmpty.get(j).getCantidad());
							
						}
						
						
						// Tons
						cell = sheet1.getRow(cabecera).getCell(c + 8);
						celda = cell.getStringCellValue();
						
						if(celda.trim().equals("Tons")) {
							
							cell = sheet1.getRow(r).getCell(c + 8);
							double peso = cell.getNumericCellValue();
							double valor = (double)(dataEmpty.get(j).getPesoKg())/1000;
							
							// System.out.println(dataFull.get(j).getPod() + " :");
							// System.out.println("Peso: " + peso);
							
							if(peso == 0) {
								
								cell.setCellValue(valor);
								// System.out.println("Peso Unit: " + valor);
								
							} else {
								
								// double valor = peso + dataFull.get(j).getPesoKg();
								cell.setCellValue(peso + valor);
								// System.out.println("Peso Anterior: " + peso);
								// System.out.println("Peso Actual: " + valor);
								// System.out.println("Peso Total: " + (peso + valor));
								
							}
							
							
						}
						
					}
					
				}
				
				r++;
				
			}
			
			
			
			// ESCRIBIENDO CARGOS - DG
			r = 27;
			List<ForecastWSA4PartnerCargo> dg = cargos.stream().filter(x -> x.getTipo().equals("DG"))
					.collect(Collectors.toList());
			
			if(dg.isEmpty()) {
				
				// cell = sheet1.createRow(r).createCell(1);
				
				cell = sheet1.getRow(r).getCell(1);
				cell.setCellValue("-");
				
			} else {
				
				// System.out.println(dg.size());
				
				for (int i = 0; i < dg.size(); i++) {
					
					// System.out.println(dg.get(i).getDescripcion());

					// cell = sheet1.createRow(r).createCell(1);
					cell = sheet1.getRow(r).getCell(1);
					cell.setCellValue(dg.get(i).getDescripcion());
					
					r++;

				}
				
			}
			
			
			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);
			wb.close();
			fos.close();

			Log.mensaje = "EXCEL CREADO...";
			Log.registraInfo();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return fileName;

	}
	
}
