package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grupo {
	@Id @GeneratedValue
	private String id;
	private String curso;
	private char letra;
	private String turno_mañana_tarde;
	private boolean ingles;
	private String visible;
	private String asignar;
	private int plazas;
	@ManyToOne
	private Titulacion titulacion;
	@OneToMany(mappedBy = "grupo")
	private List<Grupos_por_asignatura> grupo_por_asignatura;
	@ManyToOne(optional = true)
	private Grupo grupos;
	
	
	public String getTurno_mañana_tarde() {
		return turno_mañana_tarde;
	}
	public void setTurno_mañana_tarde(String turno_mañana_tarde) {
		this.turno_mañana_tarde = turno_mañana_tarde;
	}
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	public List<Grupos_por_asignatura> getGrupo_por_asignatura() {
		return grupo_por_asignatura;
	}
	public void setGrupo_por_asignatura(List<Grupos_por_asignatura> grupo_por_asignatura) {
		this.grupo_por_asignatura = grupo_por_asignatura;
	}
	
	public Grupo getGrupos() {
		return grupos;
	}
	public void setGrupos(Grupo grupos) {
		this.grupos = grupos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public boolean isIngles() {
		return ingles;
	}
	public void setIngles(boolean ingles) {
		this.ingles = ingles;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getAsignar() {
		return asignar;
	}
	public void setAsignar(String asignar) {
		this.asignar = asignar;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turno_mañana_tarde="
				+ turno_mañana_tarde + ", ingles=" + ingles + ", visible=" + visible + ", asignar=" + asignar
				+ ", plazas=" + plazas + ", titulacion=" + titulacion + ", grupo_por_asignatura=" + grupo_por_asignatura
				+ ", grupos=" + grupos + "]";
	}
	
	
}
