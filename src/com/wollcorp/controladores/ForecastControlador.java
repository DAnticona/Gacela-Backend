package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.Nave;
import com.wollcorp.beans.Puerto;
import com.wollcorp.beans.Servicio;
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
import com.wollcorp.dao.ForecastDaoImpl;
import com.wollcorp.dao.NaveDaoImpl;
import com.wollcorp.dao.ServicioDaoImpl;
import com.wollcorp.dao.UbicLinPodDao;
import com.wollcorp.dao.UbicTipTamEstDao;
import com.wollcorp.dto.DataForecastDTO;
import com.wollcorp.dto.NaveDTO;
import com.wollcorp.dto.PuertoDTO;
import com.wollcorp.dto.ServicioDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Token;

public class ForecastControlador {

	private List<Nave> naves;
	private List<Servicio> servicios;
	private List<Puerto> puertosXServicio;

	public DataForecastDTO getDatosForecast(String token) {

		DataForecastDTO dataForecastDTO = null;
		NaveDTO naveDTO = null;
		ServicioDTO servicioDTO = null;
		PuertoDTO puertoDTO = null;

		if (Token.tokenValido(token)) {

			dataForecastDTO = new DataForecastDTO();

			naves = obtieneNaves(token);

			if (naves != null) {

				for (Nave nave : naves) {

					naveDTO = new NaveDTO();

					naveDTO.setCodigo(nave.getCoNave());
					naveDTO.setLongName(nave.getNoNave());
					naveDTO.setShortName(nave.getAlNave());
					naveDTO.setFgActi(nave.getFgActi());
					naveDTO.setServicio(nave.getServicio().getCoServ());

					dataForecastDTO.getNaves().add(naveDTO);
				}

				servicios = obtieneServicios(token);

				if (servicios != null) {

					for (Servicio servicio : servicios) {

						puertosXServicio = obtienePuertosXServicio(servicio.getCoServ(), token);

						servicio.setPuertos(puertosXServicio);

						servicioDTO = new ServicioDTO();
						servicioDTO.setPuertos(new ArrayList<PuertoDTO>());

						for (Puerto puerto : puertosXServicio) {

							puertoDTO = new PuertoDTO();

							puertoDTO.setCoPuer(puerto.getCoPuer());
							puertoDTO.setNoPuer(puerto.getNoPuer());
							puertoDTO.setCoSol(puerto.getCoSol());
							puertoDTO.setCoIso(puerto.getCoIso());

							servicioDTO.getPuertos().add(puertoDTO);

						}

						servicioDTO.setCodigo(servicio.getCoServ());
						servicioDTO.setNombre(servicio.getNoServ());

						dataForecastDTO.getServicios().add(servicioDTO);

					}

				}

			}

		} else {

			dataForecastDTO = null;

		}

		return dataForecastDTO;

	}

	public String procesaDataFile(String token, ForecastCab forecastCab) {

		String coFcst = null;
		List<Resultado> resultado = null;
		List<UbicLinPod> rows = null;
		List<UbicTipTamEst> cols = null;
		List<Consolidado> consolidado = null; 

		String fileName = null;

		Log.mensaje = "PROCESANDO FORECAST";
		Log.registraInfo();

		if (Token.tokenValido(token)) {

			Log.mensaje = "TOKEN VÁLIDO";
			Log.registraInfo();

			Log.mensaje = "REGISTRANDO FORECAST EN BD";
			Log.registraInfo();

			coFcst = registrarForecast(forecastCab, token);

			if (coFcst != null) {

				Log.mensaje = "GENERANDO RESUMEN DEL FORECAST";
				Log.registraInfo();
				
				switch (forecastCab.getCoServ()) {
				
				case "000002":
					fileName = (new ForecastWSA1PartnerControlador()).generaForecastWSA1Partner(coFcst, forecastCab.getAlNave(), token);
					break;
				
				case "000004":
					
					fileName = (new ForecastWSA2PartnerControlador()).generaForecastWSA2Partner(coFcst, forecastCab.getAlNave(), token);
					break;
					
				case "000005":
					
					fileName = (new ForecastWSA3PartnerControlador()).generaForecastWSA3Partner(coFcst, forecastCab.getAlNave(), token);
					break;
					
				case "000006":
					
					fileName = (new ForecastWSA4PartnerControlador()).generaForecastWSA4Partner(coFcst, forecastCab.getNoNave(), token);
					break;
					
				case "000007":
					fileName = (new ForecastPWS2PartnerControlador()).generaForecastPWS2Partner(coFcst, forecastCab.getNoNave(), token);
					break;
					
				default:
					resultado = generaResumenForecast(coFcst, token);
					break;
				}

				if (resultado != null) {
					
					ForecastExcel fcstExcel = new ForecastExcel();

					if (forecastCab.getCoServ().equals("000001")) { // WSA1 - LOCAL

						Log.mensaje = "OBTENIENDO UBICACIONES DEL REPORTE FORECAST";
						Log.registraInfo();

						rows = obtieneRowsLinPod(forecastCab.getCoServ(), token);

						cols = obtieneColsTipTamEst(forecastCab.getCoServ(), token);

						if (!rows.isEmpty() && !cols.isEmpty()) {

							Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST";
							Log.registraInfo();

							consolidado = generaConsolidadoForecast(coFcst, token);

							if (consolidado.isEmpty()) {

								Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
								Log.registraInfo();

							}

							fileName = fcstExcel.generaExcelForecastWSA1(resultado, rows, cols, forecastCab, coFcst, consolidado);

						}

					} else if (forecastCab.getCoServ().equals("000002")) { // WSA1 - PARTNER 

						Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST PARTNER";
						Log.registraInfo();

						consolidado = generaConsolidadoForecast(coFcst, token);

						if (consolidado.isEmpty()) {

							Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
							Log.registraInfo();

						}

						fileName = fcstExcel.generaExcelForecastWSA1Partner(resultado, forecastCab, coFcst, consolidado);

					} else if (forecastCab.getCoServ().equals("000003")) { // WSA2 - LOCAL
						
						Log.mensaje = "OBTENIENDO UBICACIONES DEL REPORTE FORECAST";
						Log.registraInfo();

						rows = obtieneRowsLinPod(forecastCab.getCoServ(), token);

						cols = obtieneColsTipTamEst(forecastCab.getCoServ(), token);

						if (!rows.isEmpty() && !cols.isEmpty()) {

							Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST";
							Log.registraInfo();

							consolidado = generaConsolidadoForecast(coFcst, token);

							if (consolidado.isEmpty()) {

								Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
								Log.registraInfo();

							}
							
							Log.mensaje = "GENERANDO EXCEL...";
							Log.registraInfo();

							fileName = fcstExcel.generaExcelForecastWSA2(resultado, rows, cols, forecastCab, coFcst, consolidado);

						}
						
					} else if (forecastCab.getCoServ().equals("000004")) { // WSA2 - LOCAL
						
						Log.mensaje = "GENERANDO CONSOLIDADO DEL FORECAST PARTNER";
						Log.registraInfo();

						consolidado = generaConsolidadoForecast(coFcst, token);

						if (consolidado.isEmpty()) {

							Log.mensaje = "NO HAY DATOS DE IMO, UN Y TEMPERATURA";
							Log.registraInfo();

						}

						// fileName = fcstExcel.generaExcelForecastWSA2Partner(resultado, forecastCab, coFcst, consolidado);
						
					}

				}

			}

		}
		
		borraForecast(coFcst, token);

		return fileName;

	}
	
	private void borraForecast(String coFcst, String token) {
		
		(new ForecastDaoImpl()).eliminar(coFcst, token);
		
	}

	private List<Nave> obtieneNaves(String token) {

		return (new NaveDaoImpl()).listar(token);

	}

	private List<Servicio> obtieneServicios(String token) {

		return (new ServicioDaoImpl()).listar(token);

	}

	private List<Puerto> obtienePuertosXServicio(String coServ, String token) {

		return (new ServicioDaoImpl().obtienePuertosXServicio(coServ, token));

	}

	private String registrarForecast(ForecastCab forecastCab, String token) {

		return (new ForecastDaoImpl()).registrar(forecastCab, token);

	}

	private List<Resultado> generaResumenForecast(String coFcst, String token) {
		
		return (new ForecastDaoImpl()).obtieneForecast(coFcst, token);
		
	}

	private List<Consolidado> generaConsolidadoForecast(String coFcst, String token) {

		return (new ForecastDaoImpl()).obtieneConsolidadoForecast(coFcst, token);

	}

	private List<UbicLinPod> obtieneRowsLinPod(String coServ, String token) {

		return (new UbicLinPodDao()).listar(coServ, token);

	}

	private List<UbicTipTamEst> obtieneColsTipTamEst(String coServ, String token) {

		return (new UbicTipTamEstDao()).listar(coServ, token);

	}
	

}
