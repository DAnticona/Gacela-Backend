package com.wollcorp.controladores;

import java.util.ArrayList;
import java.util.List;

import com.wollcorp.beans.TipoDocumento;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.TipoDocumentoDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.dto.TipoDocumentoDTO;
import com.wollcorp.dto.usuarioDTO.UsuarioDTO;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.UsuarioRes;

public class UsuariosControlador {
	
	public UsuarioRes getUsuario(String token, String noUsua) {
		
		UsuarioRes usuarioRes = new UsuarioRes();
		
		//MiPerfilDTO miPerfilDTO = new MiPerfilDTO();
		
		Usuario usuario;
		List<TipoDocumento> tiposDocumento;
		
		
		
		UsuarioDTO usuarioDTO;
		// PerfilDTO perfilDTO;
		
		List<TipoDocumentoDTO> tiposDocumentoDTO;
		
		
		if(Token.tokenValido(token)) {
			
			usuarioDTO = new UsuarioDTO();
			// perfilDTO = new PerfilDTO();
			
			usuario = obtieneUsuario(noUsua, token);
			tiposDocumento = obtieneTiposDocumento(token);
			
			usuarioDTO.setCoPers(usuario.getCoPers());
			usuarioDTO.setTiDocu(usuario.getTiDocu());
			usuarioDTO.setNuDocu(usuario.getNuDocu());
			usuarioDTO.setNoPers(usuario.getNoPers());
			usuarioDTO.setApPate(usuario.getApPate());
			usuarioDTO.setApMate(usuario.getApMate());
			usuarioDTO.setSexo(usuario.getSexo());
			usuarioDTO.setFeNaci(usuario.getFeNaci());
			usuarioDTO.setEmail(usuario.getEmail());
			usuarioDTO.setRutaImagen(usuario.getRutaImagen());
			
			usuarioDTO.setNoUsua(usuario.getNoUsua());
			usuarioDTO.setNoPerf(usuario.getPerfil().getNoPerf());
			
			/*
			 * perfilDTO.setCoPerf(usuario.getPerfil().getCoPerf());
			 * perfilDTO.setNoPerf(usuario.getPerfil().getNoPerf());
			 * perfilDTO.setMenus(null);
			 */
			
			// usuarioDTO.setPerfil(perfilDTO);
			
			
			tiposDocumentoDTO = new ArrayList<TipoDocumentoDTO>();
			
			
			  for(TipoDocumento td : tiposDocumento) {
			  
			  TipoDocumentoDTO tdDTO = new TipoDocumentoDTO();
			  
			  tdDTO.setCoTiDocu(td.getCoTiDocu()); tdDTO.setNoTiDocu(td.getNoTiDocu());
			  tdDTO.setAlTiDocu(td.getAlTiDocu());
			  
			  tiposDocumentoDTO.add(tdDTO);
			  
			  }
			 
			
			
			
			// miPerfilDTO.setUsuario(usuarioDTO);
			// miPerfilDTO.setTiposDocumento(tiposDocumentoDTO);
			  usuarioRes.setTidocs(tiposDocumentoDTO);
			
			
		} else {
			
			usuarioDTO = null;
			
		}
		
		
		usuarioRes.setUsuario(usuarioDTO);
		
		
		return usuarioRes;
		
	}
	
	
	
	private Usuario obtieneUsuario(String noUsua, String token) {
		
		return (new UsuarioDaoImpl().obtenerUsuario(noUsua, token));
		
	}
	
	
	private List<TipoDocumento> obtieneTiposDocumento(String token) {
		
		return (new TipoDocumentoDaoImpl().listarTiposDocumento(token));
		
	}

}
