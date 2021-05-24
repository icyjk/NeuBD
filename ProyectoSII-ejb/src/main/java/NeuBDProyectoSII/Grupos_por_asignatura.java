package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "GruposPorAsignatura.todos", query= "select g from Grupos_por_asignatura g")
@Entity
@IdClass(NewId_GrupoPorAsignatura_grupo_asignatura.class)
public class Grupos_por_asignatura {

	@Id @ManyToOne private Grupo grupo;
	@Id @ManyToOne private Asignatura asignatura;
	@Id private String Curso_academico;
	private boolean oferta;
	@ManyToMany(mappedBy = "grupos_por_asignatura", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Encuesta> encuestas;
	
	
	
	public Grupos_por_asignatura() {
	}
	public Grupos_por_asignatura(Grupo grupo, Asignatura asignatura, String curso_academico, boolean oferta,
			List<Encuesta> encuestas) {
		super();
		this.grupo = grupo;
		this.asignatura = asignatura;
		Curso_academico = curso_academico;
		this.oferta = oferta;
		this.encuestas = encuestas;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public String getCurso_academico() {
		return Curso_academico;
	}
	public void setCurso_academico(String curso_academico) {
		Curso_academico = curso_academico;
	}
	public boolean getOferta() {
		return oferta;
	}
	public void setOferta(boolean oferta) {
		this.oferta = oferta;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Curso_academico == null) ? 0 : Curso_academico.hashCode());
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
		Grupos_por_asignatura other = (Grupos_por_asignatura) obj;
		if (Curso_academico == null) {
			if (other.Curso_academico != null)
				return false;
		} else if (!Curso_academico.equals(other.Curso_academico))
			return false;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grupos_por_asignatura [grupo=" + grupo + ", asignatura=" + asignatura + ", Curso_academico="
				+ Curso_academico + ", oferta=" + oferta + ", encuestas=" + encuestas + "]";
	}
	
	
	
	
}
