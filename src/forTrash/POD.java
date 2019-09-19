package forTrash;

import java.util.List;

public class POD {
	
	private String nombre;
	private List<SizeType>  tipos;
	private int teus;
	private int pesoKg;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<SizeType> getTipos() {
		return tipos;
	}
	public void setTipos(List<SizeType> tipos) {
		this.tipos = tipos;
	}
	public int getTeus() {
		return teus;
	}
	public void setTeus(int teus) {
		this.teus = teus;
	}
	public int getPesoKg() {
		return pesoKg;
	}
	public void setPesoKg(int pesoKg) {
		this.pesoKg = pesoKg;
	}

}
