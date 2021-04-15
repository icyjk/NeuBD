package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@IdClass(NewId_GrupoPorAsignatura_grupo_asignatura.class)
public class Grupos_por_asignatura {

	@Id @ManyToOne private Grupo grupo;
	@Id @ManyToOne private Asignatura asignatura;
	@Id private int Curso_academico;
	
	
	private String oferta;
	@ManyToMany(mappedBy = "grupos_por_asignatura")
	private List<Encuesta> encuestas;
	
	
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
	public int getCurso_academico() {
		return Curso_academico;
	}
	public void setCurso_academico(int curso_academico) {
		Curso_academico = curso_academico;
	}
	public String getOferta() {
		return oferta;
	}
	public void setOferta(String oferta) {
		this.oferta = oferta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Curso_academico;
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
		if (Curso_academico != other.Curso_academico)
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
