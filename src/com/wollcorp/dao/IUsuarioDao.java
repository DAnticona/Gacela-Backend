package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Usuario;

public interface IUsuarioDao {
	
	public List<Usuario> obtenerUsuarios();
	
	public Usuario obtenerUsuario(String codigo);
	
	public void registrarUsuario(String codigo, String idUsuario, String nombre);
	
	public void actualizarUsuario(Usuario usuario, String nombre);
	
	public void eliminarUsuario(Usuario usuario);
	
	public String getError();

}

