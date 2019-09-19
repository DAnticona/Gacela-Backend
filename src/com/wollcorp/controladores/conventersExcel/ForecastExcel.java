package com.wollcorp.controladores.conventersExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.forecast.Consolidado;
import com.wollcorp.beans.forecast.Resultado;
import com.wollcorp.beans.forecast.UbicLinPod;
import com.wollcorp.beans.forecast.UbicTipTamEst;
import com.wollcorp.globales.Log;

public class ForecastExcel {

	public static String filePath = "c:\\opt\\assets\\reports\\";

	private String templateWSA1 = "c:\\opt\\assets\\templates\\forecastWSA1.xlsx";
	private String templateWSA1Partner = "c:\\opt\\assets\\templates\\forecastWSA1Partner.xlsx";

	private String templateWSA2 = "c:\\opt\\assets\\templates\\forecastWSA2.xlsx";

	public String generaExcelForecastWSA1(List<Resultado> resultado, List<UbicLinPod> rows, List<UbicTipTamEst> cols,
			ForecastCab forecastCab, String coFcst, List<Consolidado> consolidado) {

		// String template = "c:\\opt\\assets\\templates\\forecastWSA1.xlsx";
		String fileName = "ForecastWSA1_" + coFcst + ".xlsx";
		String filePath = ForecastExcel.filePath + fileName;

		String titulo1 = "BOOKING FOR " + forecastCab.getNoServ();
		String titulo2 = "VESSEL: " + forecastCab.getNoNave();

		try {
			Path origenPath = FileSystems.getDefault().getPath(templateWSA1);
			Path destinoPath = FileSystems.getDefault().getPath(filePath);

			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			FileInputStream fis = new FileInputStream(new File(filePath));

			List<Resultado> res = null;

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet1 = wb.getSheetAt(0);
			XSSFSheet sheet2 = wb.getSheetAt(1);

			XSSFRow fila;
			XSSFCell celda;

			for (UbicLinPod row : rows) {

				for (UbicTipTamEst col : cols) {

					res = resultado.stream()
							.filter(r -> r.getCoLinea().equals(row.getCoLine()) && r.getPod().equals(row.getCoPuerSol())
									&& r.getTipo().equals(col.getCoSolTipo()) && r.getSize().equals(col.getTaCont())
									&& r.getEstado().equals(col.getNoEstCont()))
							.collect(Collectors.toList());

					if (!res.isEmpty()) {

						for (Resultado i : res) {

							if (!i.getTipo().equals("TTL") && !i.getPod().equals("TTLGW")) {

								fila = sheet1.getRow(row.getRow());
								celda = fila.getCell(col.getCol());
								celda.setCellValue(i.getCantidad());

							} else {

								fila = sheet1.getRow(row.getRow());
								celda = fila.getCell(col.getCol());
								celda.setCellValue((double) i.getPesoKg() / 1000);

							}

						}

					}

				}

			}

			// CONSOLIDADO
			if (!consolidado.isEmpty()) {

				List<Consolidado> reefers = null;
				Map<String, Map<String, Integer>> reefersTemp = new HashMap<String, Map<String, Integer>>();

				reefers = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("R"))
						.collect(Collectors.toList());

				for (Consolidado r : reefers) {

					String key = " X " + r.getSize() + r.getTipo() + "-" + r.getEstado() + " /PECAL-" + r.getPod()
							+ " / WGT ";

					if (reefersTemp.containsKey(key)) {

						reefersTemp.get(key).put("CANTIDAD", reefersTemp.get(key).get("CANTIDAD") + r.getCantidad());

						reefersTemp.get(key).put("PESO", reefersTemp.get(key).get("PESO") + r.getPesoKg());

					} else {

						Map<String, Integer> values = new HashMap<String, Integer>();

						values.put("CANTIDAD", r.getCantidad());
						values.put("PESO", r.getPesoKg());

						reefersTemp.put(key, values);

					}

				}

				int rowConsolidado = 93;

				for (Map.Entry<String, Map<String, Integer>> entry : reefersTemp.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(rowConsolidado);
					celda = fila.getCell(15);
					String valor = entry.getValue().get("CANTIDAD") + entry.getKey()
							+ (double) entry.getValue().get("PESO") / 1000;
					celda.setCellValue(valor);
					rowConsolidado = rowConsolidado + 1;

				}

				// DG CARGO
				List<Consolidado> cargos = null;
				Map<String, Integer> cargosTemp = new HashMap<String, Integer>();
				cargos = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("C"))
						.collect(Collectors.toList());

				rowConsolidado = 93;

				for (Consolidado c : cargos) {

					String key = " X " + c.getSize() + c.getTipo() + "-" + c.getEstado() + " /PECAL-" + c.getPod()
							+ " / IMO " + c.getImo() + " UN " + c.getUn();

					if (cargosTemp.containsKey(key)) {

						cargosTemp.put(key, cargosTemp.get(key) + c.getCantidad());

					} else {

						cargosTemp.put(key, c.getCantidad());

					}

				}

				rowConsolidado = 93;

				for (Map.Entry<String, Integer> entry : cargosTemp.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(rowConsolidado);
					celda = fila.getCell(20);
					String valor = entry.getValue() + entry.getKey();
					celda.setCellValue(valor);
					rowConsolidado = rowConsolidado + 1;

				}

				// ESCRIBIENDO HOJA 2 - REEFER - COSCO - 000002

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000002"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(0);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - WHL - 000004

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000004"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(1);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - PIL - 000005

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000005"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(2);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - YML - 000003

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000003"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(3);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - CMA APL - 000006

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000006"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(4);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - EMC EVERGREEN - 000001

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000001"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(5);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}

				// ESCRIBIENDO HOJA 2 - CARGO - COSCO - 000002

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000002"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(0);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - WHL - 000004

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000004"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(1);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - PIL - 000005

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000005"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(2);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - YML - 000003

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000003"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(3);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - CMA APL - 000006

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000006"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(4);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - EMC EVERGREEN - 000001

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000001"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(5);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}

			}

			// ESCRIBIENDO DATOS DE TITULOS
			fila = sheet1.getRow(1);
			celda = fila.getCell(0);
			celda.setCellValue(titulo1);

			fila = sheet1.getRow(2);
			celda = fila.getCell(0);
			celda.setCellValue(titulo2);

			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

			wb.close();

			fos.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return fileName;

	}

	public String generaExcelForecastWSA1Partner(List<Resultado> resultado, ForecastCab forecastCab, String coFcst,
			List<Consolidado> consolidado) {

		String fileName = "ForecastWSA1Partner" + coFcst + ".xlsx";
		String filePath = ForecastExcel.filePath + fileName;

		String titulo1 = "MV. " + forecastCab.getAlNave();

		Path origenPath = FileSystems.getDefault().getPath(templateWSA1Partner);
		Path destinoPath = FileSystems.getDefault().getPath(filePath);

		try {

			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			FileInputStream fis = new FileInputStream(new File(filePath));

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet1 = wb.getSheetAt(0);

			XSSFRow fila;
			XSSFCell celda;

			resultado = resultado.stream()
					.filter(x -> !x.getPod().equals("TTL") && !x.getPod().equals("TTLGW") && !x.getTipo().equals("TTL")
							&& !x.getTipo().equals("TEUS") && !x.getSize().equals("TEUS") && !x.getSize().equals("TTL"))
					.collect(Collectors.toList());

			Map<String, Resultado> resumen = new TreeMap<String, Resultado>();

			for (Resultado r : resultado) {

				String estado = null;

				if (r.getEstado().equals("F")) {

					estado = "0";

				} else {

					estado = "1";

				}

				String key = r.getSize() + r.getTipo() + estado + r.getPod();

				if (resumen.containsKey(key)) {

					resumen.get(key).setCantidad(resumen.get(key).getCantidad() + r.getCantidad());
					resumen.get(key).setPesoKg(resumen.get(key).getPesoKg() + r.getPesoKg());

				} else {

					Resultado valor = new Resultado();

					valor.setCoLinea(null);
					valor.setNoLinea(null);
					valor.setPod(r.getPod());
					valor.setSize(r.getSize());
					valor.setTipo(r.getTipo());
					valor.setEstado(estado);
					valor.setCantidad(r.getCantidad());
					valor.setPesoKg(r.getPesoKg());

					resumen.put(key, valor);

				}

			}

			int r = 2;

			for (Map.Entry<String, Resultado> entry : resumen.entrySet()) {

				// NRO COLUMNA
				fila = sheet1.getRow(r);
				celda = fila.getCell(0);
				celda.setCellValue(r - 1);

				// POD
				fila = sheet1.getRow(r);
				celda = fila.getCell(1);
				celda.setCellValue(entry.getValue().getPod());

				// EQP CLASS - SIZE TIPO
				fila = sheet1.getRow(r);
				celda = fila.getCell(2);
				celda.setCellValue(entry.getValue().getSize().substring(0, 1) + entry.getValue().getTipo());

				// QTY - CANTIDAD
				fila = sheet1.getRow(r);
				celda = fila.getCell(3);
				celda.setCellValue(entry.getValue().getCantidad());

				// WGT - PESO
				fila = sheet1.getRow(r);
				celda = fila.getCell(4);
				celda.setCellValue((double) entry.getValue().getPesoKg() / 1000);

				// STATUS - ESTADO
				String estado;
				if (entry.getValue().getEstado().equals("0")) {

					estado = "FULL";

				} else {

					estado = "EMPTY";

				}

				fila = sheet1.getRow(r);
				celda = fila.getCell(6);
				celda.setCellValue(estado);

				r++;

			}

			// CONSOLIDADO
			if (!consolidado.isEmpty()) {

				Map<String, Consolidado> resumen2 = new TreeMap<String, Consolidado>();

				List<Consolidado> reefers = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("R"))
						.collect(Collectors.toList());

				for (Consolidado reef : reefers) {

					String key = reef.getSize() + reef.getTipo() + reef.getPod();

					if (resumen2.containsKey(key)) {

						resumen2.get(key).setCantidad(resumen2.get(key).getCantidad() + reef.getCantidad());
						resumen2.get(key).setPesoKg(resumen2.get(key).getPesoKg() + reef.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(reef.getPod());
						valor.setSize(reef.getSize());
						valor.setTipo(reef.getTipo());
						valor.setCantidad(reef.getCantidad());
						valor.setPesoKg(reef.getPesoKg());

						resumen2.put(key, valor);
					}

				}

				int r2 = 1;

				for (Map.Entry<String, Consolidado> entry : resumen2.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(r2);
					celda = fila.getCell(8);
					String valor = entry.getValue().getCantidad() + "x" + entry.getValue().getSize().substring(0, 1)
							+ entry.getValue().getTipo() + "/POD " + entry.getValue().getPod() + "/ "
							+ (double) entry.getValue().getPesoKg() / 1000 + " TN";
					celda.setCellValue(valor);
					r2++;

				}

				resumen2 = new TreeMap<String, Consolidado>();

				List<Consolidado> cargos = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("C"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String key = car.getSize() + car.getTipo() + car.getPod() + car.getImo() + car.getUn();

					if (resumen2.containsKey(key)) {

						resumen2.get(key).setCantidad(resumen2.get(key).getCantidad() + car.getCantidad());
						resumen2.get(key).setPesoKg(resumen2.get(key).getPesoKg() + car.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(car.getPod());
						valor.setSize(car.getSize());
						valor.setTipo(car.getTipo());
						valor.setImo(car.getImo());
						valor.setUn(car.getUn());
						valor.setCantidad(car.getCantidad());
						valor.setPesoKg(car.getPesoKg());

						resumen2.put(key, valor);
					}

				}

				r2 = 1;

				for (Map.Entry<String, Consolidado> entry : resumen2.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(r2);
					celda = fila.getCell(14);
					String valor = entry.getValue().getCantidad() + "x" + entry.getValue().getSize().substring(0, 1)
							+ entry.getValue().getTipo() + "/POD " + entry.getValue().getPod() + "/IMO "
							+ entry.getValue().getImo() + " UN " + entry.getValue().getUn() + " "
							+ (double) entry.getValue().getPesoKg() / 1000 + " TN";
					celda.setCellValue(valor);
					r2++;

				}

			}

			// ESCRIBIENDO DATOS DE TITULOS
			fila = sheet1.getRow(0);
			celda = fila.getCell(0);
			celda.setCellValue(titulo1);

			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

			wb.close();

			fos.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return fileName;

	}

	public String generaExcelForecastWSA2(List<Resultado> resultado, List<UbicLinPod> rows, List<UbicTipTamEst> cols,
			ForecastCab forecastCab, String coFcst, List<Consolidado> consolidado) {

		String fileName = "ForecastWSA2_" + coFcst + ".xlsx";
		String filePath = ForecastExcel.filePath + fileName;

		String titulo1 = "BOOKING FOR " + forecastCab.getNoServ();
		String titulo2 = "VESSEL: " + forecastCab.getNoNave();

		try {
			System.out.println("Antes de Copiar archivos");
			Path origenPath = FileSystems.getDefault().getPath(templateWSA2);
			Path destinoPath = FileSystems.getDefault().getPath(filePath);

			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			FileInputStream fis = new FileInputStream(new File(filePath));

			List<Resultado> res = null;

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet1 = wb.getSheetAt(0);
			XSSFSheet sheet2 = wb.getSheetAt(1);

			XSSFRow fila;
			XSSFCell celda;

			for (UbicLinPod row : rows) {

				for (UbicTipTamEst col : cols) {

					res = resultado.stream()
							.filter(r -> r.getCoLinea().equals(row.getCoLine()) && r.getPod().equals(row.getCoPuerSol())
									&& r.getTipo().equals(col.getCoSolTipo()) && r.getSize().equals(col.getTaCont())
									&& r.getEstado().equals(col.getNoEstCont()))
							.collect(Collectors.toList());

					if (!res.isEmpty()) {

						for (Resultado i : res) {

							if (!i.getTipo().equals("TTL") && !i.getPod().equals("TTLGW")) {

								fila = sheet1.getRow(row.getRow());
								celda = fila.getCell(col.getCol());
								celda.setCellValue(i.getCantidad());

							} else {

								fila = sheet1.getRow(row.getRow());
								celda = fila.getCell(col.getCol());
								celda.setCellValue((double) i.getPesoKg() / 1000);

							}

						}

					}

				}

			}

			// CONSOLIDADO
			if (!consolidado.isEmpty()) {

				Map<String, Consolidado> resumen = new TreeMap<String, Consolidado>();

				List<Consolidado> reefers = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("R"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String key = ref.getSize() + ref.getTipo() + ref.getEstado() + ref.getPod();

					if (resumen.containsKey(key)) {

						resumen.get(key).setCantidad(resumen.get(key).getCantidad() + ref.getCantidad());
						resumen.get(key).setPesoKg(resumen.get(key).getPesoKg() + ref.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(ref.getPod());
						valor.setSize(ref.getSize());
						valor.setTipo(ref.getTipo());
						valor.setImo(ref.getImo());
						valor.setUn(ref.getUn());
						valor.setEstado(ref.getEstado());
						valor.setCantidad(ref.getCantidad());
						valor.setPesoKg(ref.getPesoKg());

						resumen.put(key, valor);

					}

				}

				int rowConsolidado = 107;

				for (Map.Entry<String, Consolidado> entry : resumen.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(rowConsolidado);
					celda = fila.getCell(15);

					String valor = entry.getValue().getCantidad() + " X " + entry.getValue().getSize()
							+ entry.getValue().getTipo() + "-" + entry.getValue().getEstado() + " /PECAL-"
							+ entry.getValue().getPod() + " / WGT " + (double) entry.getValue().getPesoKg() / 1000;

					celda.setCellValue(valor);

					rowConsolidado = rowConsolidado + 1;

				}

				// DG CARGO
				resumen = new TreeMap<String, Consolidado>();

				List<Consolidado> cargos = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("C"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String key = car.getSize() + car.getTipo() + car.getEstado() + car.getPod() + car.getImo()
							+ car.getUn();

					if (resumen.containsKey(key)) {

						resumen.get(key).setCantidad(resumen.get(key).getCantidad() + car.getCantidad());
						resumen.get(key).setPesoKg(resumen.get(key).getPesoKg() + car.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(car.getPod());
						valor.setSize(car.getSize());
						valor.setTipo(car.getTipo());
						valor.setImo(car.getImo());
						valor.setUn(car.getUn());
						valor.setEstado(car.getEstado());
						valor.setCantidad(car.getCantidad());
						valor.setPesoKg(car.getPesoKg());

						resumen.put(key, valor);

					}

				}

				rowConsolidado = 107;

				for (Map.Entry<String, Consolidado> entry : resumen.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(rowConsolidado);
					celda = fila.getCell(20);

					String valor = entry.getValue().getCantidad() + " X " + entry.getValue().getSize()
							+ entry.getValue().getTipo() + "-" + entry.getValue().getEstado() + " /PECAL-"
							+ entry.getValue().getPod() + " / IMO " + entry.getValue().getImo() + " UN "
							+ entry.getValue().getUn();

					celda.setCellValue(valor);
					rowConsolidado = rowConsolidado + 1;

				}

				// ESCRIBIENDO HOJA 2 - REEFER - COSCO - 000002

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000002"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(0);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - WHL - 000004

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000004"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(1);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - PIL - 000005

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000005"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(2);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - YML - 000003

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000003"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(3);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - CMA APL - 000006

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000006"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(4);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - REEFER - EMC EVERGREEN - 000001

				rowConsolidado = 2;

				reefers = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("R") && x.getCoLinea().equals("000001"))
						.collect(Collectors.toList());

				for (Consolidado ref : reefers) {

					String contenido = ref.getCantidad() + " X " + ref.getSize() + ref.getTipo() + "-" + ref.getEstado()
							+ " /PECAL-" + ref.getPod() + " / WGT " + ((double) ref.getPesoKg() / 1000) + " TN";

					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(5);
					celda.setCellValue(contenido);

					rowConsolidado = rowConsolidado + 1;

				}

				// ESCRIBIENDO HOJA 2 - CARGO - COSCO - 000002

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000002"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(0);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - WHL - 000004

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000004"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(1);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - PIL - 000005

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000005"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(2);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - YML - 000003

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000003"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(3);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - CMA APL - 000006

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000006"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(4);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}
				// ESCRIBIENDO HOJA 2 - CARGO - EMC EVERGREEN - 000001

				rowConsolidado = 13;

				cargos = consolidado.stream()
						.filter(x -> x.getTipoConsolidado().equals("C") && x.getCoLinea().equals("000001"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String contenido = car.getCantidad() + " X " + car.getSize() + car.getTipo() + "-" + car.getEstado()
							+ " /PECAL-" + car.getPod() + " / IMO " + car.getImo() + " UN " + car.getUn();

					// CARGO DETAIL
					fila = sheet2.getRow(rowConsolidado);
					celda = fila.getCell(5);
					celda.setCellValue(contenido);
					rowConsolidado = rowConsolidado + 1;

				}

			}

			// ESCRIBIENDO DATOS DE TITULOS
			fila = sheet1.getRow(1);
			celda = fila.getCell(0);
			celda.setCellValue(titulo1);

			fila = sheet1.getRow(2);
			celda = fila.getCell(0);
			celda.setCellValue(titulo2);

			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();
			sheet2.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

			wb.close();

			fos.close();

		} catch (IOException e) {

			Log.codigo = 0;
			Log.exception = null;
			Log.mensaje = e.getMessage();
			Log.estado = null;
			Log.nombreClase = this.getClass().toString();
			Log.registraError();

		} catch (NullPointerException npe) {

			Log.codigo = 0;
			Log.exception = null;
			Log.mensaje = npe.getMessage();
			Log.estado = null;
			Log.nombreClase = this.getClass().toString();
			Log.registraError();

		}

		System.out.println(fileName);

		return fileName;

	}
	/*
	public String generaExcelForecastWSA2Partner(List<Resultado> resultado, ForecastCab forecastCab, String coFcst,
			List<Consolidado> consolidado) {
		
		String fileName = "ForecastWSA2Partner_" + coFcst + ".xlsx";
		String filePath = ForecastExcel.filePath + fileName;

		String titulo1 = "MV. " + forecastCab.getAlNave();
		String hoja1 = "FORECAST";
		
		int rowTitulo = 0;
		int colTitulo = 0;
		
		int rowCabecera = 1;
		int colCabecera = 0;
		
		int rowData = 2;
		int colData = 0;
		int nro = 1;

		// Path origenPath = FileSystems.getDefault().getPath(templateWSA2Partner);
		// Path destinoPath = FileSystems.getDefault().getPath(filePath);
		
		try {

			// Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

			// FileInputStream fis = new FileInputStream(new File(filePath));

			Log.mensaje = "ORDENANDO DATA...";
			Log.registraInfo();

			resultado = resultado.stream()
					.filter(x -> !x.getPod().equals("TTL") && !x.getPod().equals("TTLGW") && !x.getTipo().equals("TTL")
							&& !x.getTipo().equals("TEUS") && !x.getSize().equals("TEUS") && !x.getSize().equals("TTL"))
					.collect(Collectors.toList());

			Map<String, Resultado> resumen = new TreeMap<String, Resultado>();

			for (Resultado r : resultado) {

				String estado = null;

				if (r.getEstado().equals("F")) {

					estado = "0";

				} else {

					estado = "1";

				}

				String key = r.getSize() + r.getTipo() + estado + r.getPod();

				if (resumen.containsKey(key)) {

					resumen.get(key).setCantidad(resumen.get(key).getCantidad() + r.getCantidad());
					resumen.get(key).setPesoKg(resumen.get(key).getPesoKg() + r.getPesoKg());

				} else {

					Resultado valor = new Resultado();

					valor.setCoLinea(null);
					valor.setNoLinea(null);
					valor.setPod(r.getPod());
					valor.setSize(r.getSize());
					valor.setTipo(r.getTipo());
					valor.setEstado(estado);
					valor.setCantidad(r.getCantidad());
					valor.setPesoKg(r.getPesoKg());

					resumen.put(key, valor);

				}

			}
			
			
			Log.mensaje = "GENERANDO EXCEL...";
			Log.registraInfo();
			
			XSSFWorkbook wb = new XSSFWorkbook();

			XSSFSheet sheet1 = wb.createSheet(hoja1);

			XSSFRow row;
			XSSFCell cell;

			for (Map.Entry<String, Resultado> entry : resumen.entrySet()) {

				// COLUMNA NRO
				row = sheet1.createRow(rowData);
				cell = row.createCell(colData);
				cell.setCellValue(nro);

				// POD
				fila = sheet1.getRow(r);
				celda = fila.getCell(1);
				celda.setCellValue(entry.getValue().getPod());

				// EQP CLASS - SIZE TIPO
				fila = sheet1.getRow(r);
				celda = fila.getCell(2);
				celda.setCellValue(entry.getValue().getSize().substring(0, 1) + entry.getValue().getTipo());

				// QTY - CANTIDAD
				fila = sheet1.getRow(r);
				celda = fila.getCell(3);
				celda.setCellValue(entry.getValue().getCantidad());

				// WGT - PESO
				fila = sheet1.getRow(r);
				celda = fila.getCell(4);
				celda.setCellValue((double) entry.getValue().getPesoKg() / 1000);

				// STATUS - ESTADO
				String estado;
				if (entry.getValue().getEstado().equals("0")) {

					estado = "FULL";

				} else {

					estado = "EMPTY";

				}

				fila = sheet1.getRow(r);
				celda = fila.getCell(6);
				celda.setCellValue(estado);

				r++;

			}

			// CONSOLIDADO
			if (!consolidado.isEmpty()) {

				Map<String, Consolidado> resumen2 = new TreeMap<String, Consolidado>();

				List<Consolidado> reefers = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("R"))
						.collect(Collectors.toList());

				for (Consolidado reef : reefers) {

					String key = reef.getSize() + reef.getTipo() + reef.getPod();

					if (resumen2.containsKey(key)) {

						resumen2.get(key).setCantidad(resumen2.get(key).getCantidad() + reef.getCantidad());
						resumen2.get(key).setPesoKg(resumen2.get(key).getPesoKg() + reef.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(reef.getPod());
						valor.setSize(reef.getSize());
						valor.setTipo(reef.getTipo());
						valor.setCantidad(reef.getCantidad());
						valor.setPesoKg(reef.getPesoKg());

						resumen2.put(key, valor);
					}

				}

				int r2 = 1;

				for (Map.Entry<String, Consolidado> entry : resumen2.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(r2);
					celda = fila.getCell(8);
					String valor = entry.getValue().getCantidad() + "x" + entry.getValue().getSize().substring(0, 1)
							+ entry.getValue().getTipo() + "/POD " + entry.getValue().getPod() + "/ "
							+ (double) entry.getValue().getPesoKg() / 1000 + " TN";
					celda.setCellValue(valor);
					r2++;

				}

				resumen2 = new TreeMap<String, Consolidado>();

				List<Consolidado> cargos = consolidado.stream().filter(x -> x.getTipoConsolidado().equals("C"))
						.collect(Collectors.toList());

				for (Consolidado car : cargos) {

					String key = car.getSize() + car.getTipo() + car.getPod() + car.getImo() + car.getUn();

					if (resumen2.containsKey(key)) {

						resumen2.get(key).setCantidad(resumen2.get(key).getCantidad() + car.getCantidad());
						resumen2.get(key).setPesoKg(resumen2.get(key).getPesoKg() + car.getPesoKg());

					} else {

						Consolidado valor = new Consolidado();

						valor.setPod(car.getPod());
						valor.setSize(car.getSize());
						valor.setTipo(car.getTipo());
						valor.setImo(car.getImo());
						valor.setUn(car.getUn());
						valor.setCantidad(car.getCantidad());
						valor.setPesoKg(car.getPesoKg());

						resumen2.put(key, valor);
					}

				}

				r2 = 1;

				for (Map.Entry<String, Consolidado> entry : resumen2.entrySet()) {

					// REEFER DETAIL
					fila = sheet1.getRow(r2);
					celda = fila.getCell(14);
					String valor = entry.getValue().getCantidad() + "x" + entry.getValue().getSize().substring(0, 1)
							+ entry.getValue().getTipo() + "/POD " + entry.getValue().getPod() + "/IMO "
							+ entry.getValue().getImo() + " UN " + entry.getValue().getUn() + " "
							+ (double) entry.getValue().getPesoKg() / 1000 + " TN";
					celda.setCellValue(valor);
					r2++;

				}

			}

			// ESCRIBIENDO DATOS DE TITULOS
			fila = sheet1.getRow(0);
			celda = fila.getCell(0);
			celda.setCellValue(titulo1);

			sheet1.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateAll();

			File file = new File(filePath);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

			wb.close();

			fos.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return fileName;
		
	}*/

}
