package com.wollcorp.dao;

import java.util.List;

import com.wollcorp.beans.Puerto;

public interface IPuertoDao {
	
	public List<Puerto> listarPuertos();
	
	public Puerto obtenerPuerto(String coPuer, String token);
	
	public void registrarPuerto(Puerto puerto, String token);
	
	public void actualizarPuerto(Puerto puerto, String token);
	
	public void eliminarPuerto(String coPuer, String token);
}
