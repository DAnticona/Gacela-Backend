package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.TEMP.ForecastCabDTO;
import com.wollcorp.TEMP.ForecastDetDTO;
import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.forecast.Consolidado;
import com.wollcorp.beans.forecast.ForecastPWS2Partner;
import com.wollcorp.beans.forecast.ForecastPWS2PartnerCargo;
import com.wollcorp.beans.forecast.ForecastWSA1Partner;
import com.wollcorp.beans.forecast.ForecastWSA1PartnerCargo;
import com.wollcorp.beans.forecast.ForecastWSA2Partner;
import com.wollcorp.beans.forecast.ForecastWSA2PartnerCargo;
import com.wollcorp.beans.forecast.ForecastWSA3Partner;
import com.wollcorp.beans.forecast.ForecastWSA3PartnerCargo;
import com.wollcorp.beans.forecast.ForecastWSA4Partner;
import com.wollcorp.beans.forecast.ForecastWSA4PartnerCargo;
import com.wollcorp.beans.forecast.Resultado;
import com.wollcorp.conectores.Conector;
import com.wollcorp.globales.Log;

public class ForecastDaoImpl {

	public List<ForecastCab> listar(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String registrar(ForecastCabDTO forecastCab, String token) {

		Connection conector = Conector.conectores.get(token);

		String coFcst = null;

		String sql1 = "EXEC SP_REGISTRA_CAB_FORECAST ?, ?, ?";

		try {

			PreparedStatement ps1 = conector.prepareStatement(sql1);
			ps1.setString(1, forecastCab.getCoServ());
			ps1.setString(2, forecastCab.getCoNave());
			ps1.setString(3, forecastCab.getFgProp());

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				coFcst = rs.getString(1);
			}

			// System.out.println(coFcst);

			String sql2 = "EXEC SP_REGISTRA_DET_FORECAST ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?";

			PreparedStatement ps2 = conector.prepareStatement(sql2);

			int rows = 0;

			for (ForecastDetDTO detalle : forecastCab.getDetalle()) {

				ps2.setString(1, coFcst);
				ps2.setString(2, detalle.getLinea());
				ps2.setString(3, detalle.getPod());
				ps2.setInt(4, detalle.getSize());
				ps2.setString(5, detalle.getType());
				ps2.setString(6, detalle.getCnd());
				ps2.setString(7, detalle.getNbrCont());
				ps2.setInt(8, detalle.getVgm());
				ps2.setString(9, detalle.getImo());
				ps2.setString(10, detalle.getUn());
				ps2.setString(11, detalle.getTemperature());

				rows = rows + ps2.executeUpdate();

			}

			Log.mensaje = rows + " REGISTROS INSERTADOS";
			Log.registraInfo();

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		} catch (NullPointerException e1) {

			Log.mensaje = e1.getMessage();
			Log.exception = e1.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

		}

		conector = null;

		return coFcst;

	}

	public void modificar(ForecastCab forecast, String token) {
		// TODO Auto-generated method stub

	}

	public void eliminar(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		String sql = "EXEC SP_ELIMINA_FORECAST ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			int rowsAffected = ps.executeUpdate();
			
			Log.mensaje = rowsAffected + " REGISTRO(S) ACTUALIZADOS(S)";
			Log.registraInfo();

			conector = null;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			
		}

	}

	
	
	
	public List<ForecastWSA1Partner> obtieneForecastWSA1Partner(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA1Partner> data = new ArrayList<ForecastWSA1Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA1_PARTNER ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA1Partner forecast = new ForecastWSA1Partner();

				forecast.setCoFcst(rs.getString("CO_FCST"));
				forecast.setNro(rs.getInt("NRO"));
				forecast.setPod(rs.getString("POD"));
				forecast.setTipo(rs.getString("TIPO"));
				forecast.setEstado(rs.getString("ESTADO"));
				forecast.setCantidad(rs.getInt("CANTIDAD"));
				forecast.setPesoKg(rs.getInt("PESO"));

				data.add(forecast);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}

	}
	
	

	public List<ForecastWSA2Partner> obtieneForecastWSA2Partner(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA2Partner> data = new ArrayList<ForecastWSA2Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA2_PARTNER ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA2Partner forecast = new ForecastWSA2Partner();

				forecast.setCoFcst(rs.getString("CO_FCST"));
				forecast.setNro(rs.getInt("NRO"));
				forecast.setPod(rs.getString("POD"));
				forecast.setTipo(rs.getString("TIPO"));
				forecast.setEstado(rs.getString("ESTADO"));
				forecast.setCantidad(rs.getInt("CANTIDAD"));
				forecast.setPesoKg(rs.getInt("PESO"));

				data.add(forecast);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}

	}
	

	
	
	public List<ForecastWSA3Partner> obtieneForecastWSA3Partner(String coFcst, String token) {
		
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA3Partner> data = new ArrayList<ForecastWSA3Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA3_PARTNER ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA3Partner forecast = new ForecastWSA3Partner();

				forecast.setCoFcst(rs.getString("CO_FCST"));
				forecast.setNro(rs.getInt("NRO"));
				forecast.setPod(rs.getString("POD"));
				forecast.setTipo(rs.getString("TIPO"));
				forecast.setEstado(rs.getString("ESTADO"));
				forecast.setCantidad(rs.getInt("CANTIDAD"));
				forecast.setPesoKg(rs.getInt("PESO"));

				data.add(forecast);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
		
	}
	
	
	
	public List<ForecastWSA4Partner> obtieneForecastWSA4Partner(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA4Partner> data = new ArrayList<ForecastWSA4Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA4Partner forecast = new ForecastWSA4Partner();

				forecast.setCoFcst(rs.getString("CO_FCST"));
				forecast.setNro(rs.getInt("NRO"));
				forecast.setPod(rs.getString("POD"));
				forecast.setTipo(rs.getString("TIPO"));
				forecast.setEstado(rs.getString("ESTADO"));
				forecast.setCantidad(rs.getInt("CANTIDAD"));
				forecast.setPesoKg(rs.getInt("PESO"));

				data.add(forecast);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}

	}
	
	
	
	
	public List<ForecastPWS2Partner> obtieneForecastPWS2Partner(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<ForecastPWS2Partner> data = new ArrayList<ForecastPWS2Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_PWS2_PARTNER ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastPWS2Partner forecast = new ForecastPWS2Partner();

				forecast.setCoFcst(rs.getString("CO_FCST"));
				forecast.setNro(rs.getInt("NRO"));
				forecast.setPod(rs.getString("POD"));
				forecast.setTipo(rs.getString("TIPO"));
				forecast.setEstado(rs.getString("ESTADO"));
				forecast.setCantidad(rs.getInt("CANTIDAD"));
				forecast.setPesoKg(rs.getInt("PESO"));

				data.add(forecast);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}

	}
	
	
	
	
	
	public List<ForecastWSA1PartnerCargo> obtieneForecastWSA1PartnerCargo(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA1PartnerCargo> data = new ArrayList<ForecastWSA1PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA1_PARTNER_CARGO ?";
		
		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA1PartnerCargo cargo = new ForecastWSA1PartnerCargo();

				cargo.setCoFcst(rs.getString("CO_FCST"));
				cargo.setTipo(rs.getString("TIPO"));
				cargo.setDescripcion(rs.getString("DES_CARGO"));

				data.add(cargo);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}
	
	
	
	
	public List<ForecastWSA2PartnerCargo> obtieneForecastWSA2PartnerCargo(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA2PartnerCargo> data = new ArrayList<ForecastWSA2PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA2_PARTNER_CARGO ?";
		
		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA2PartnerCargo cargo = new ForecastWSA2PartnerCargo();

				cargo.setCoFcst(rs.getString("CO_FCST"));
				cargo.setTipo(rs.getString("TIPO"));
				cargo.setDescripcion(rs.getString("DES_CARGO"));

				data.add(cargo);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}
	
	
	
	public List<ForecastWSA3PartnerCargo> obtieneForecastWSA3PartnerCargo(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA3PartnerCargo> data = new ArrayList<ForecastWSA3PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA3_PARTNER_CARGO ?";
		
		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA3PartnerCargo cargo = new ForecastWSA3PartnerCargo();

				cargo.setCoFcst(rs.getString("CO_FCST"));
				cargo.setTipo(rs.getString("TIPO"));
				cargo.setDescripcion(rs.getString("DES_CARGO"));

				data.add(cargo);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}
	
	
	
	
	public List<ForecastWSA4PartnerCargo> obtieneForecastWSA4PartnerCargo(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastWSA4PartnerCargo> data = new ArrayList<ForecastWSA4PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER_CARGO ?";
		
		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastWSA4PartnerCargo cargo = new ForecastWSA4PartnerCargo();

				cargo.setCoFcst(rs.getString("CO_FCST"));
				cargo.setTipo(rs.getString("TIPO"));
				cargo.setDescripcion(rs.getString("DES_CARGO"));

				data.add(cargo);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}
	
	
	
	
	public List<ForecastPWS2PartnerCargo> obtieneForecastPWS2PartnerCargo(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);

		List<ForecastPWS2PartnerCargo> data = new ArrayList<ForecastPWS2PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER_CARGO ?";
		
		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ForecastPWS2PartnerCargo cargo = new ForecastPWS2PartnerCargo();

				cargo.setCoFcst(rs.getString("CO_FCST"));
				cargo.setTipo(rs.getString("TIPO"));
				cargo.setDescripcion(rs.getString("DES_CARGO"));

				data.add(cargo);

			}

			conector = null;

			return data;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}

	
	
	
	
	
	public List<Resultado> generaForecastLocal(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<Resultado> resultados = new ArrayList<Resultado>();

		String sql = "EXEC SP_GENERA_FORECAST_LOCAL ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Resultado resultado = new Resultado();

				resultado.setCoFcst(rs.getString("CO_FCST"));
				resultado.setCoLinea(rs.getString("CO_LINE"));
				resultado.setNoLinea(rs.getString("NO_LINE"));
				resultado.setPod(rs.getString("CO_POD"));
				resultado.setSize(rs.getString("TA_CONT"));
				resultado.setTipo(rs.getString("TIPO"));
				resultado.setEstado(rs.getString("FG_ESTA"));
				resultado.setCantidad(rs.getInt("CANT"));
				resultado.setPesoKg(rs.getInt("PESO"));

				resultados.add(resultado);

			}

			conector = null;

			return resultados;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}

	}
	
	public ForecastCab getForecastCab(String coFcst, String token) {
		
		Connection conector = Conector.conectores.get(token);
		
		ForecastCab forecastCab = new ForecastCab();
		
		String sql = "EXEC SP_OBTIENE_CAB_FORECAST ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				forecastCab.setCoForecast(rs.getString("CO_FCST"));
				forecastCab.setCoServ(rs.getString("CO_SERV"));
				forecastCab.setCoNave(rs.getString("CO_NAVE"));
				forecastCab.setAlNave(rs.getString("AL_NAVE"));
				forecastCab.setNoNave(rs.getString("NO_NAVE"));
				forecastCab.setNoServ(rs.getString("NO_SERV"));
				forecastCab.setFgProp(rs.getString("FG_PROP"));

			}

			conector = null;

			return forecastCab;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
		
	}

	public List<Consolidado> obtieneConsolidadoForecast(String coFcst, String token) {

		Connection conector = Conector.conectores.get(token);

		List<Consolidado> consolidados = new ArrayList<Consolidado>();

		String sql = "EXEC SP_GENERA_FORECAST_CONSOLIDADO ?";

		try {

			PreparedStatement ps = conector.prepareStatement(sql);

			ps.setString(1, coFcst);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Consolidado consolidado = new Consolidado();

				consolidado.setCoFcst(rs.getString("CO_FCST"));
				consolidado.setCoLinea(rs.getString("CO_LINE"));
				consolidado.setNoLinea(rs.getString("NO_LINE"));
				consolidado.setPod(rs.getString("CO_POD"));
				consolidado.setSize(rs.getString("TA_CONT"));
				consolidado.setTipo(rs.getString("TIPO"));
				consolidado.setEstado(rs.getString("FG_ESTA"));
				consolidado.setImo(rs.getString("IMO"));
				consolidado.setUn(rs.getString("UN"));
				consolidado.setCantidad(rs.getInt("CANT"));
				consolidado.setPesoKg(rs.getInt("PESO"));
				consolidado.setTipoConsolidado(rs.getString("TI_CONSOLIDADO"));

				consolidados.add(consolidado);

			}

			conector = null;

			return consolidados;

		} catch (SQLException e) {

			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = e.getErrorCode();
			Log.estado = e.getSQLState();
			Log.nombreClase = this.getClass().getName();
			Log.registraError();

			conector = null;
			return null;
		}
	}

}
