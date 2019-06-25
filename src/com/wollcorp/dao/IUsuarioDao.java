package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Usuario;

public interface IUsuarioDao {
	
	public List<Usuario> listarUsuarios();
	
	public Usuario obtenerUsuario(String noUsua);
	
	public void registrarUsuario(String codigo, String idUsuario, String nombre);
	
	public void actualizarUsuario(Usuario usuario);
	
	public void eliminarUsuario(Usuario usuario);

}

