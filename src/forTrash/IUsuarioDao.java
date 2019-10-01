package forTrash;

import java.util.List;

import com.wollcorp.beans.Usuario;

public interface IUsuarioDao {
	
	public List<Usuario> listarUsuarios();
	
	public Usuario obtenerUsuario(String noUsua, String token);
	
	public void registrarUsuario(Usuario usuario, String token);
	
	public void actualizarUsuario(Usuario usuario, String token);
	
	public void eliminarUsuario(String noUsua, String token);

}

