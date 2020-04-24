package com.wollcorp.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.wollcorp.TEMP.TipoDocumentoDTO;
import com.wollcorp.beans.Persona;
import com.wollcorp.beans.TipoDocumento;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.PersonaDaoImpl;
import com.wollcorp.dao.TipoDocumentoDaoImpl;
import com.wollcorp.dao.UsuarioDaoImpl;
import com.wollcorp.dto.usuarioDTO.UsuarioDTO;
import com.wollcorp.globales.Log;
import com.wollcorp.globales.Token;
import com.wollcorp.restServices.responses.UsuarioRes;

public class UsuarioControlador {
	
	Properties config = new Properties();
	InputStream configInput = null;

	public UsuarioRes getUsuario(String token, String noUsua) {

		UsuarioRes usuarioRes = new UsuarioRes();

		// MiPerfilDTO miPerfilDTO = new MiPerfilDTO();

		Usuario usuario;
		List<TipoDocumento> tiposDocumento;

		UsuarioDTO usuarioDTO;
		// PerfilDTO perfilDTO;

		List<TipoDocumentoDTO> tiposDocumentoDTO;

		if (Token.tokenValido(token)) {

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

			for (TipoDocumento td : tiposDocumento) {

				TipoDocumentoDTO tdDTO = new TipoDocumentoDTO();

				tdDTO.setCoTiDocu(td.getCoTiDocu());
				tdDTO.setNoTiDocu(td.getNoTiDocu());
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

	public void updateUsuario(String token, UsuarioDTO usuarioDTO) {

		if (Token.tokenValido(token)) {

			Persona persona = obtieneUsuario(usuarioDTO.getNoUsua(), token);

			persona.setTiDocu(usuarioDTO.getTiDocu());
			persona.setNuDocu(usuarioDTO.getNuDocu());
			persona.setNoPers(usuarioDTO.getNoPers());
			persona.setApPate(usuarioDTO.getApPate());
			persona.setApMate(usuarioDTO.getApMate());
			persona.setSexo(usuarioDTO.getSexo());
			persona.setEmail(usuarioDTO.getEmail());
			persona.setFeNaci(usuarioDTO.getFeNaci());
			persona.setRutaImagen(usuarioDTO.getRutaImagen());

			(new PersonaDaoImpl()).actualizarPersona(persona, token);

		}

	}

	public void updateImagen(String token, String noUsua, File file) {

		try {
			
			Usuario usuario = obtieneUsuario(noUsua, token);
			
			String rutaFile = "C:\\opt\\assets\\images\\";
			
			String noImagenOld = usuario.getNoImagen();
			
			if(noImagenOld != null) {
				
				File imagenOld = new File(rutaFile + noImagenOld);
				
				imagenOld.delete();
				
			}
			
			
			String noImagen = noUsua + Instant.now().toEpochMilli() + ".png";
			

			BufferedImage imagen = ImageIO.read(file);

			File outputfile = new File(rutaFile + noImagen);

			ImageIO.write(imagen, "png", outputfile);
			
			getConfig();
			
			
			String ruta = config.getProperty("images") + "/" + noImagen;
			
			(new PersonaDaoImpl()).actualizarImagen(ruta, noImagen, token, noUsua);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private Usuario obtieneUsuario(String noUsua, String token) {

		return (new UsuarioDaoImpl().obtenerUsuario(noUsua, token));

	}

	private List<TipoDocumento> obtieneTiposDocumento(String token) {

		return (new TipoDocumentoDaoImpl().listarTiposDocumento(token));

	}
	
	private void getConfig() {

		try {
			
			configInput = new FileInputStream("c:\\opt\\assets\\config\\config.properties");
			config.load(configInput);
			
		} catch (Exception e) {
			
			Log.mensaje = e.getMessage();
			Log.exception = e.toString();
			Log.codigo = 0;
			Log.estado = null;
			Log.nombreClase = this.getClass().getName();
			Log.registraError();
			
		}

	}

}
