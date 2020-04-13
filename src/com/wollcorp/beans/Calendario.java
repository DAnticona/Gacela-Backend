package com.wollcorp.beans;

import java.util.Date;

public class Calendario {
	
	private String codigo;
	private int diaSem;
	private int dia;
	private int mes;
	private int ano;
	private Date fecha;
	private String fgFeriado;
	private int nroSemAno;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDiaSem() {
		return diaSem;
	}
	public void setDiaSem(int diaSem) {
		this.diaSem = diaSem;
	}
	public String getFgFeriado() {
		return fgFeriado;
	}
	public void setFgFeriado(String fgFeriado) {
		this.fgFeriado = fgFeriado;
	}
	public int getNroSemAno() {
		return nroSemAno;
	}
	public void setNroSemAno(int nroSemAno) {
		this.nroSemAno = nroSemAno;
	}
}
