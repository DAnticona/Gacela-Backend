package com.wollcorp.controladores;

import java.util.List;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.forecast.Consolidado;
import com.wollcorp.beans.forecast.Resultado;
import com.wollcorp.beans.forecast.UbicLinPod;
import com.wollcorp.beans.forecast.UbicTipTamEst;
import com.wollcorp.controladores.conventersExcel.ForecastExcel;
import com.wollcorp.controladores.forecast.ForecastPWS2PartnerControlador;
import com.wollcorp.controladores.forecast.ForecastWSA1PartnerControlador;
import com.wollcorp.controladores.forecast.ForecastWSA2PartnerControlador;
import com.wollcorp.controladores.forecast.ForecastWSA3PartnerControlador;
import com.wollcorp.controladores.forecast.ForecastWSA4PartnerControlador;
import com.wollcorp.dao.ForecastDao;
import com.wollcorp.dao.UbicLinPodDao;
import com.wollcorp.dao.UbicTipTamEstDao;
import com.wollcorp.globales.Token;

public class ForecastControlador {

//	private List<NaveTemp> naves;
//	private List<Servicio> servicios;
//	private List<Puerto> puertosXServicio;

//	public DataForecastDTO getDatosForecast(String token) throws Exception {
//
//		DataForecastDTO dataForecastDTO = null;
//		NaveDTOTemp naveDTO = null;
//		ServicioDTO servicioDTO = null;
//		PuertoDTO puertoDTO = null;
//
//		if (Token.tokenValido(token)) {
//
//			dataForecastDTO = new DataForecastDTO();
//
//			naves = obtieneNaves(token);
//
//			if (naves != null) {
//
//				for (NaveTemp nave : naves) {
//
//					naveDTO = new NaveDTOTemp();
//
//					naveDTO.setCodigo(nave.getCoNave());
//					naveDTO.setLongName(nave.getNoNave());
//					naveDTO.setShortName(nave.getAlNave());
//					naveDTO.setFgActi(nave.getFgActi());
//					naveDTO.setServicio(nave.getServicio().getCoServ());
//
//					dataForecastDTO.getNaves().add(naveDTO);
//				}
//
//				servicios = obtieneServicios(token);
//
//				if (servicios != null) {
//
//					for (Servicio servicio : servicios) {
//
//						puertosXServicio = obtienePuertosXServicio(servicio.getCoServ(), token);
//
//						servicio.setPuertos(puertosXServicio);
//
//						servicioDTO = new ServicioDTO();
//						servicioDTO.setPuertos(new ArrayList<PuertoDTO>());
//
//						for (Puerto puerto : puertosXServicio) {
//
//							puertoDTO = new PuertoDTO();
//
//							puertoDTO.setCoPuer(puerto.getCoPuer());
//							puertoDTO.setNoPuer(puerto.getNoPuer());
//							puertoDTO.setCoSol(puerto.getCoSol());
//							puertoDTO.setCoIso(puerto.getCoIso());
//
//							servicioDTO.getPuertos().add(puertoDTO);
//
//						}
//
//						servicioDTO.setCoServ(servicio.getCoServ());
//						servicioDTO.setNoServ(servicio.getNoServ());
//
//						dataForecastDTO.getServicios().add(servicioDTO);
//
//					}
//
//				}
//
//			}
//
//		} else {
//
//			dataForecastDTO = null;
//
//		}
//
//		return dataForecastDTO;
//
//	}

	public String generarReporteForecast(String token, ForecastCab forecastCab) throws Exception {

		String coForecast = null;
		List<Resultado> resultado = null;
		List<UbicLinPod> rows = null;
		List<UbicTipTamEst> cols = null;
		List<Consolidado> consolidado = null;

		String fileName = null;

		if (Token.tokenValido(token)) {

			// coForecast = registrarForecast(forecastCab, token);
			coForecast = (new ForecastDao()).registrar(forecastCab, token);
			
			// System.out.println(coForecast);

			if (coForecast != null) {
				
				ForecastCab fc = (new ForecastDao()).obtenerForecastCab(coForecast, token);
				
				if(fc.getFgProp().equals("S")) { // SI ES FORECAST LOCAL
					
					resultado = (new ForecastDao()).generaForecastLocal(coForecast, token);
					
					ForecastExcel fcstExcel = new ForecastExcel();

					if (forecastCab.getServicio().getCoServ().equals("000001")) { // WSA1 - LOCAL

						// rows = obtieneRowsLinPod(forecastCab.getServicio().getCoServ(), token);
						rows = (new UbicLinPodDao()).listar(forecastCab.getServicio().getCoServ(), token);

						// cols = obtieneColsTipTamEst(forecastCab.getServicio().getCoServ(), token);
						cols = (new UbicTipTamEstDao()).listar(forecastCab.getServicio().getCoServ(), token);

						if (!rows.isEmpty() && !cols.isEmpty()) {

							// consolidado = generaConsolidadoForecast(coForecast, token);
							consolidado = (new ForecastDao()).obtieneConsolidadoForecast(coForecast, token);

							if (consolidado.isEmpty()) {

							}

							fileName = fcstExcel.generaExcelForecastWSA1(resultado, rows, cols, fc, coForecast, consolidado);

						}

					} else if (forecastCab.getServicio().getCoServ().equals("000003")) { // WSA2 - LOCAL

						// rows = obtieneRowsLinPod(forecastCab.getServicio().getCoServ(), token);
						rows = (new UbicLinPodDao()).listar(forecastCab.getServicio().getCoServ(), token);

						// cols = obtieneColsTipTamEst(forecastCab.getServicio().getCoServ(), token);
						cols = (new UbicTipTamEstDao()).listar(forecastCab.getServicio().getCoServ(), token);

						if (!rows.isEmpty() && !cols.isEmpty()) {

							// consolidado = generaConsolidadoForecast(coForecast, token);
							consolidado = (new ForecastDao()).obtieneConsolidadoForecast(coForecast, token);

							fileName = fcstExcel.generaExcelForecastWSA2(resultado, rows, cols, fc, coForecast, consolidado);

						}
						
					}
					
				} else { // SI ES FORECAST PARTNER
					
					switch (forecastCab.getServicio().getCoServ()) {
					
						case "000001": // WSA1 - PARTNER
							fileName = (new ForecastWSA1PartnerControlador())
											.generaForecastWSA1Partner(coForecast, fc.getNave().getAlNave(), token);
							break;
						
						case "000003": // WSA2 - PARTNER
							
							fileName = (new ForecastWSA2PartnerControlador())
											.generaForecastWSA2Partner(coForecast, fc.getNave().getAlNave(), token);
							break;
							
						case "000005": // WSA3 - PARTNER
							
							fileName = (new ForecastWSA3PartnerControlador())
											.generaForecastWSA3Partner(coForecast, fc.getNave().getAlNave(), token);
							break;
							
						case "000006": // WSA4 - PARTNER
							
							fileName = (new ForecastWSA4PartnerControlador())
											.generaForecastWSA4Partner(coForecast, fc.getNave().getNoNave(), token);
							break;
							
						case "000007": // PWS2 - PARTNER
							fileName = (new ForecastPWS2PartnerControlador())
											.generaForecastPWS2Partner(coForecast, fc.getNave().getNoNave(), token);
							break;
					}
					
				}
				
				
//				switch (forecastCab.getCoServ()) {
//				
//					case "000002":
//						fileName = (new ForecastWSA1PartnerControlador()).generaForecastWSA1Partner(coForecast, fc.getAlNave(), token);
//						break;
//					
//					case "000004":
//						
//						fileName = (new ForecastWSA2PartnerControlador()).generaForecastWSA2Partner(coForecast, fc.getAlNave(), token);
//						break;
//						
//					case "000005":
//						
//						fileName = (new ForecastWSA3PartnerControlador()).generaForecastWSA3Partner(coForecast, fc.getAlNave(), token);
//						break;
//						
//					case "000006":
//						
//						fileName = (new ForecastWSA4PartnerControlador()).generaForecastWSA4Partner(coForecast, fc.getAlNave(), token);
//						break;
//						
//					case "000007":
//						fileName = (new ForecastPWS2PartnerControlador()).generaForecastPWS2Partner(coForecast, fc.getNoNave(), token);
//						break;
//						
//					//default:
//						// resultado = (new ForecastDaoImpl()).obtieneForecast(coForecast, token);
//						// break;
//				}

//				if (resultado != null) {
//					
//					ForecastExcel fcstExcel = new ForecastExcel();
//
//					if (forecastCab.getCoServ().equals("000001")) { // WSA1 - LOCAL
//
//						Log.mensaje = "OBTENIENDO UBICACIONES DEL REPORTE FORECAST";
//						Log.registraInfo();
//
//						rows = obtieneRowsLinPod(forecastCab.getCoServ(), token);
//
//						cols = obtieneColsTipTamEst(forecastCab.getCoServ(), token);
//
//						if (!rows.isEmpty() && !cols.isEmpty()) {
//
//							Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST";
//							Log.registraInfo();
//
//							consolidado = generaConsolidadoForecast(coForecast, token);
//
//							if (consolidado.isEmpty()) {
//
//								Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
//								Log.registraInfo();
//
//							}
//
//							fileName = fcstExcel.generaExcelForecastWSA1(resultado, rows, cols, fc, coForecast, consolidado);
//
//						}
//
//					} else if (forecastCab.getCoServ().equals("000002")) { // WSA1 - PARTNER 
//
//						Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST PARTNER";
//						Log.registraInfo();
//
//						consolidado = generaConsolidadoForecast(coForecast, token);
//
//						if (consolidado.isEmpty()) {
//
//							Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
//							Log.registraInfo();
//
//						}
//
//						fileName = fcstExcel.generaExcelForecastWSA1Partner(resultado, fc, coForecast, consolidado);
//
//					} else if (forecastCab.getCoServ().equals("000003")) { // WSA2 - LOCAL
//						
//						Log.mensaje = "OBTENIENDO UBICACIONES DEL REPORTE FORECAST";
//						Log.registraInfo();
//
//						rows = obtieneRowsLinPod(forecastCab.getCoServ(), token);
//
//						cols = obtieneColsTipTamEst(forecastCab.getCoServ(), token);
//
//						if (!rows.isEmpty() && !cols.isEmpty()) {
//
//							Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST";
//							Log.registraInfo();
//
//							consolidado = generaConsolidadoForecast(coForecast, token);
//
//							if (consolidado.isEmpty()) {
//
//								Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
//								Log.registraInfo();
//
//							}
//							
//							Log.mensaje = "GENERANDO EXCEL...";
//							Log.registraInfo();
//
//							fileName = fcstExcel.generaExcelForecastWSA2(resultado, rows, cols, fc, coForecast, consolidado);
//
//						}
//						
//					} else if (forecastCab.getCoServ().equals("000004")) { // WSA2 - LOCAL
//						
//						Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST PARTNER";
//						Log.registraInfo();
//
//						consolidado = generaConsolidadoForecast(coForecast, token);
//
//						if (consolidado.isEmpty()) {
//
//							Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
//							Log.registraInfo();
//
//						}
//
//						// fileName = fcstExcel.generaExcelForecastWSA2Partner(resultado, fc, coForecast, consolidado);
//						
//					}
//
//				}

			}

		}
		
		(new ForecastDao()).eliminar(coForecast, token);

		return fileName;

	}
	
//	private void borraForecast(String coFcst, String token) throws SQLException {
//		
//		(new ForecastDao()).eliminar(coFcst, token);
//		
//	}

//	private List<NaveTemp> obtieneNaves(String token) throws SQLException {
//
//		return (new NaveDao()).listarNavesActivas(token);
//
//	}

//	private List<Servicio> obtieneServicios(String token) throws SQLException {
//
//		return (new ServicioDao()).listar(token);
//
//	}
//
//	private List<Puerto> obtienePuertosXServicio(String coServ, String token) {
//
//		return (new ServicioDao().obtienePuertosXServicio(coServ, token));
//
//	}

//	private String registrarForecast(ForecastCab forecastCab, String token) throws SQLException {
//
//		return (new ForecastDao()).registrar(forecastCab, token);
//
//	}

//	private List<Resultado> generaResumenForecast(String coFcst, String token) {
//		
//		return (new ForecastDaoImpl()).obtieneForecast(coFcst, token);
//		
//	}

//	private List<Consolidado> generaConsolidadoForecast(String coFcst, String token) throws SQLException {
//
//		return (new ForecastDao()).obtieneConsolidadoForecast(coFcst, token);
//
//	}

//	private List<UbicLinPod> obtieneRowsLinPod(String coServ, String token) {
//
//		return (new UbicLinPodDao()).listar(coServ, token);
//
//	}

//	private List<UbicTipTamEst> obtieneColsTipTamEst(String coServ, String token) {
//
//		return (new UbicTipTamEstDao()).listar(coServ, token);
//
//	}
	

}
