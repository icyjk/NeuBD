package NeuBDProyectoSII;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "Optativa.todos", query= "select o from Optativa o")
@Entity
public class Optativa extends Asignatura {
	
	private int plazas;
	private String mencion;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Titulacion titulacion;
	
	public Optativa() {
	}
	public Optativa(int plazas, String mencion,Titulacion titulacion) {
		super();
		this.plazas = plazas;
		this.mencion = mencion;
		this.titulacion = titulacion;
	}
	
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	public String getMencion() {
		return mencion;
	}
	public void setMencion(String mencion) {
		this.mencion = mencion;
	}
	@Override
	public String toString() {
		return "Optativa [plazas=" + plazas + ", mencion=" + mencion + ", titulacion=" + titulacion + "]";
	}
	
	
}
