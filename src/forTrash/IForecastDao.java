package forTrash;

import java.util.List;

import com.wollcorp.beans.ForecastCab;
import com.wollcorp.beans.forecast.Resultado;

public interface IForecastDao {
	
	public List<ForecastCab> listar(String token);
	
	public List<Resultado> obtieneForecast(String codigo, String token);
	   
	public String registrar(ForecastCab forecast, String token);

	public void modificar(ForecastCab forecast, String token);
	   
	public void eliminar(ForecastCab forecast, String token);

}
