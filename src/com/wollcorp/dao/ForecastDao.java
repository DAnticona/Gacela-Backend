package com.wollcorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.ForecastDet;
import com.wollcorp.beans.Nave;
import com.wollcorp.beans.Servicio;
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
import com.wollcorp.conexion.ConexionSQLServer;

public class ForecastDao {
	
	public ForecastCab obtenerForecastCab(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		ForecastCab forecastCab = new ForecastCab();

		String sql = "EXEC SP_OBTIENE_CAB_FORECAST ?";

		PreparedStatement ps = conector.prepareStatement(sql);

		ps.setString(1, coFcst);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			Servicio servicio = new Servicio();
			servicio.setCoServ(rs.getString("CO_SERV"));
			servicio.setNoServ(rs.getString("NO_SERV"));
			
			Nave nave = new Nave();
			nave.setCoNave(rs.getString("CO_NAVE"));
			nave.setNoNave(rs.getString("NO_NAVE"));
			nave.setAlNave(rs.getString("AL_NAVE"));
			
			forecastCab.setCoForecast(rs.getString("CO_FCST"));
			forecastCab.setFgProp(rs.getString("FG_PROP"));
			forecastCab.setNave(nave);
			forecastCab.setServicio(servicio);

		}

		conector = null;

		return forecastCab;

	}

	public String registrar(ForecastCab forecastCab, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String coFcst = null;

		String sql1 = "EXEC SP_REGISTRA_CAB_FORECAST ?, ?, ?";

		PreparedStatement ps1 = conector.prepareStatement(sql1);
		ps1.setString(1, forecastCab.getServicio().getCoServ());
		ps1.setString(2, forecastCab.getNave().getCoNave());
		ps1.setString(3, forecastCab.getFgProp());

		ResultSet rs = ps1.executeQuery();

		while (rs.next()) {
			coFcst = rs.getString(1);
		}

		String sql2 = "EXEC SP_REGISTRA_DET_FORECAST ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?";

		PreparedStatement ps2 = conector.prepareStatement(sql2);

		int rows = 0;

		for (ForecastDet detalle : forecastCab.getDetalle()) {

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

		conector = null;

		return coFcst;

	}

	public void eliminar(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		String sql = "EXEC SP_ELIMINA_FORECAST ?";

		PreparedStatement ps = conector.prepareStatement(sql);

		ps.setString(1, coFcst);

		ps.executeUpdate();

		conector = null;

	}

	public List<ForecastWSA1Partner> obtieneForecastWSA1Partner(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA1Partner> data = new ArrayList<ForecastWSA1Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA1_PARTNER ?";

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

	}

	public List<ForecastWSA2Partner> obtieneForecastWSA2Partner(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA2Partner> data = new ArrayList<ForecastWSA2Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA2_PARTNER ?";

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

	}

	public List<ForecastWSA3Partner> obtieneForecastWSA3Partner(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA3Partner> data = new ArrayList<ForecastWSA3Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA3_PARTNER ?";

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

	}

	public List<ForecastWSA4Partner> obtieneForecastWSA4Partner(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA4Partner> data = new ArrayList<ForecastWSA4Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER ?";

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

	}

	public List<ForecastPWS2Partner> obtieneForecastPWS2Partner(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastPWS2Partner> data = new ArrayList<ForecastPWS2Partner>();

		String sql = "EXEC SP_GENERA_FORECAST_PWS2_PARTNER ?";

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

	}

	public List<ForecastWSA1PartnerCargo> obtieneForecastWSA1PartnerCargo(String coFcst, String token)
			throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA1PartnerCargo> data = new ArrayList<ForecastWSA1PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA1_PARTNER_CARGO ?";

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

	}

	public List<ForecastWSA2PartnerCargo> obtieneForecastWSA2PartnerCargo(String coFcst, String token)
			throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA2PartnerCargo> data = new ArrayList<ForecastWSA2PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA2_PARTNER_CARGO ?";

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

	}

	public List<ForecastWSA3PartnerCargo> obtieneForecastWSA3PartnerCargo(String coFcst, String token)
			throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA3PartnerCargo> data = new ArrayList<ForecastWSA3PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA3_PARTNER_CARGO ?";

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

	}

	public List<ForecastWSA4PartnerCargo> obtieneForecastWSA4PartnerCargo(String coFcst, String token)
			throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastWSA4PartnerCargo> data = new ArrayList<ForecastWSA4PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER_CARGO ?";

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

	}

	public List<ForecastPWS2PartnerCargo> obtieneForecastPWS2PartnerCargo(String coFcst, String token)
			throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<ForecastPWS2PartnerCargo> data = new ArrayList<ForecastPWS2PartnerCargo>();

		String sql = "EXEC SP_GENERA_FORECAST_WSA4_PARTNER_CARGO ?";

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

	}

	public List<Resultado> generaForecastLocal(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<Resultado> resultados = new ArrayList<Resultado>();

		String sql = "EXEC SP_GENERA_FORECAST_LOCAL ?";

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

	}

	public List<Consolidado> obtieneConsolidadoForecast(String coFcst, String token) throws SQLException {

		Connection conector = ConexionSQLServer.conectores.get(token);

		List<Consolidado> consolidados = new ArrayList<Consolidado>();

		String sql = "EXEC SP_GENERA_FORECAST_CONSOLIDADO ?";

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
	}

}
