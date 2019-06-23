package com.wollcorp.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.wollcorp.beans.Usuario;

public class UsuariosMock {
	
	List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public UsuariosMock() {
		
		Usuario usuario1 = new Usuario();
		
		usuario1.setCoUsua("999999");
		usuario1.setNoUsua("SUPERVISOR");
		
		usuarios.add(usuario1);
		
		for(int i = 0; i<10; i++) {
			Usuario usuario = new Usuario();
			
			usuario.setCoUsua("00000" + i);
			usuario.setNoUsua("USUARIO" + i);
			
			usuarios.add(usuario);
		}
		
	}
	
	public List<Usuario> getUser(String noUsua) {
		
		List<Usuario> usuarioEncontrado = new ArrayList<Usuario>();
		
		usuarioEncontrado = usuarios.stream().filter(usuario -> usuario.getNoUsua().equals(noUsua)).collect(Collectors.toList());
		
		return usuarioEncontrado;
	}

}
