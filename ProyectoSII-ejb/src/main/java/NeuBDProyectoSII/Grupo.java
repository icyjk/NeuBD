package NeuBDProyectoSII;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Grupo.todos", query= "select g from Grupo g")
@Entity
public class Grupo implements Serializable{
	@Id @GeneratedValue
	private int id;
	private int curso;
	private char letra;
	private String turno_mañana_tarde;
	private boolean ingles;
	private boolean visible;
	private String asignar;
	private int plazas;
	@ManyToOne
	private Titulacion titulacion;
	@OneToMany(mappedBy = "grupo", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Grupos_por_asignatura> grupo_por_asignatura;
	@ManyToOne(optional = true)
	private Grupo grupos;
	@OneToMany (mappedBy = "grupo",fetch = FetchType.EAGER)
	private List<Clase> clases;
	@OneToMany (mappedBy="grupo")
	private List<Asignatura_matricula> asigMatriculas;
	
	
	public Grupo() {
		super();
	}
	
	
	
	public Grupo(int curso, char letra, String turno_mañana_tarde, boolean ingles, boolean visible, String asignar,
			int plazas, Titulacion titulacion, List<Grupos_por_asignatura> grupo_por_asignatura, Grupo grupos,
			List<Clase> clases, List<Asignatura_matricula> asigMatriculas) {
		super();
		this.curso = curso;
		this.letra = letra;
		this.turno_mañana_tarde = turno_mañana_tarde;
		this.ingles = ingles;
		this.visible = visible;
		this.asignar = asignar;
		this.plazas = plazas;
		this.titulacion = titulacion;
		this.grupo_por_asignatura = grupo_por_asignatura;
		this.grupos = grupos;
		this.clases = clases;
		this.asigMatriculas = asigMatriculas;
	}



	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public List<Asignatura_matricula> getAsigMatriculas() {
		return asigMatriculas;
	}

	public void setAsigMatriculas(List<Asignatura_matricula> asigMatriculas) {
		this.asigMatriculas = asigMatriculas;
	}

	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
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
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
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
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return curso + "º" + letra ;
	}
	
	
}
