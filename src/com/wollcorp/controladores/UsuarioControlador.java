package com.wollcorp.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.wollcorp.beans.Perfil;
import com.wollcorp.beans.Persona;
import com.wollcorp.beans.Usuario;
import com.wollcorp.dao.PerfilDao;
import com.wollcorp.dao.PersonaDao;
import com.wollcorp.dao.UsuarioDao;
import com.wollcorp.dto.UsuarioDto;
import com.wollcorp.globales.Token;

public class UsuarioControlador {

	Properties config = new Properties();
	InputStream configInput = null;

	public UsuarioDto obtenerUsuarioPorUsername(String token, Usuario usuario) throws Exception {

		UsuarioDto usuarioDto = null;

		if (Token.tokenValido(token)) {

			usuarioDto = new UsuarioDto();

			usuario = (new UsuarioDao()).obtenerUsuarioPorUsername(usuario, token);

			Perfil perfil = (new PerfilDao()).obtenerPerfilXUsuario(usuario.getNoUsua(), token);

			usuario.setPerfil(perfil);
			usuarioDto.setUsuario(usuario);

		}

		return usuarioDto;

	}

	public void actualizarUsuario(String token, Usuario usuario) throws Exception {

		if (Token.tokenValido(token)) {

			// Persona persona = (new UsuarioDao()).obtenerUsuarioPorUsername(usuario, token);

			Persona persona = new Persona();
			
			persona.setCoPers(usuario.getCoPers());
			persona.setTiDocu(usuario.getTiDocu());
			persona.setNuDocu(usuario.getNuDocu());
			persona.setNoPers(usuario.getNoPers());
			persona.setApPate(usuario.getApPate());
			persona.setApMate(usuario.getApMate());
			persona.setSexo(usuario.getSexo());
			persona.setEmail(usuario.getEmail());
			persona.setFeNaci(usuario.getFeNaci());
			persona.setRutaImagen(usuario.getRutaImagen());

			(new PersonaDao()).actualizarPersona(persona, token);

		}

	}

	public void updateImagen(String token, Usuario usuario, File file) throws SQLException, IOException {

		usuario = (new UsuarioDao().obtenerUsuarioPorUsername(usuario, token));

		String rutaFile = "C:\\opt\\assets\\images\\";

		String noImagenOld = usuario.getNoImagen();

		if (noImagenOld != null) {

			File imagenOld = new File(rutaFile + noImagenOld);

			imagenOld.delete();

		}

		String noImagen = usuario.getNoUsua() + Instant.now().toEpochMilli() + ".png";

		BufferedImage imagen = ImageIO.read(file);

		File outputfile = new File(rutaFile + noImagen);

		ImageIO.write(imagen, "png", outputfile);

		getConfig();

		String ruta = config.getProperty("images") + "/" + noImagen;

		(new PersonaDao()).actualizarImagen(ruta, noImagen, token, usuario.getNoUsua());

	}

	private void getConfig() throws IOException {

		configInput = new FileInputStream("c:\\opt\\assets\\config\\config.properties");
		config.load(configInput);

	}

}
