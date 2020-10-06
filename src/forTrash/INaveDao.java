package forTrash;

import java.util.List;

// import com.wollcorp.beans.NaveTemp;

public interface INaveDao {
	
	public List<NaveTemp> listar(String token);
	   
	public void registrar(NaveTemp menu, String token);

	public void modificar(NaveTemp menu, String token);
	   
	public void eliminar(NaveTemp menu, String token);
	
}
