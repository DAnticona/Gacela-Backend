package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Servicio;

public interface IServicioDao {
	
	public List<?> listar(String token);
	   
	public void registrar(Servicio servicio, String token);

	public void modificar(Servicio servicio, String token);
	   
	public void eliminar(Servicio servicio, String token);
	
}
