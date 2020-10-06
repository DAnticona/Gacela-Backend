package forTrash;

import java.util.List;

import com.wollcorp.dto.ErrorDto;

public class MiPerfilDTO {
	
	private UsuarioDTO usuario;
	private ErrorDto error;
	// private List<TipoDocumentoDTO> tiposDocumento;
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}
//	public List<TipoDocumentoDTO> getTiposDocumento() {
//		return tiposDocumento;
//	}
//	public void setTiposDocumento(List<TipoDocumentoDTO> tiposDocumento) {
//		this.tiposDocumento = tiposDocumento;
//	}

}
