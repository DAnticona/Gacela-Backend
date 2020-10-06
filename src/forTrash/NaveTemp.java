package forTrash;

import java.time.LocalDateTime;

import com.wollcorp.beans.Servicio;

public class NaveTemp {
	private String coNave;
	private String noNave;
	private String alNave;
	private String fgActi;
	private Servicio servicio;
	private String usCreaNave;
	private LocalDateTime feCreaNave;
	private String usModiNave;
	private LocalDateTime feModiNave;
	private Linea linea;
	private String fgPropLinea;
	
	public String getCoNave() {
		return coNave;
	}
	public void setCoNave(String coNave) {
		this.coNave = coNave;
	}
	public String getNoNave() {
		return noNave;
	}
	public void setNoNave(String noNave) {
		this.noNave = noNave;
	}
	public String getAlNave() {
		return alNave;
	}
	public void setAlNave(String alNave) {
		this.alNave = alNave;
	}
	public String getFgActi() {
		return fgActi;
	}
	public void setFgActi(String fgActi) {
		this.fgActi = fgActi;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public String getUsCreaNave() {
		return usCreaNave;
	}
	public void setUsCreaNave(String usCreaNave) {
		this.usCreaNave = usCreaNave;
	}
	public LocalDateTime getFeCreaNave() {
		return feCreaNave;
	}
	public void setFeCreaNave(LocalDateTime feCreaNave) {
		this.feCreaNave = feCreaNave;
	}
	public String getUsModiNave() {
		return usModiNave;
	}
	public void setUsModiNave(String usModiNave) {
		this.usModiNave = usModiNave;
	}
	public LocalDateTime getFeModiNave() {
		return feModiNave;
	}
	public void setFeModiNave(LocalDateTime feModiNave) {
		this.feModiNave = feModiNave;
	}
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public String getFgPropLinea() {
		return fgPropLinea;
	}
	public void setFgPropLinea(String fgPropLinea) {
		this.fgPropLinea = fgPropLinea;
	}
	
}
