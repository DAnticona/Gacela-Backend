package forTrash;

import java.util.List;

public class Linea {
	
	private String codigo;
	private String nombre;
	private int nrOrde;
	private List<POD> pods;
	// private List<>
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNrOrde() {
		return nrOrde;
	}
	public void setNrOrde(int nrOrde) {
		this.nrOrde = nrOrde;
	}
	public List<POD> getPods() {
		return pods;
	}
	public void setPods(List<POD> pods) {
		this.pods = pods;
	}

}
