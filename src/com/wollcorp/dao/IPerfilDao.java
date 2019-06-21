package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Perfil;

public interface IPerfilDao {
		   
	public List<Perfil> obtenerPerfiles(String coPerf);
	
	public List<Perfil> obtenerPerfilesXUsuario(String coUsua);
		   
	public void registrarPerfil(Perfil perfil);
		   
	public void modificarPerfil(Perfil perfil);
		   
	public void eliminarPerfil(String coPerf);
}
