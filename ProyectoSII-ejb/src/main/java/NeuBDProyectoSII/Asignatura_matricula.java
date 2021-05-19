package NeuBDProyectoSII;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@IdClass(NewId_Asignatura_matricula.class)
@NamedQuery(name = "AsigMatri.todos", query= "select a from Asignatura_matricula a")
public class Asignatura_matricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @ManyToOne private Asignatura asignatura;
	@Id @ManyToOne private Matricula matricula;
	@ManyToOne(optional = true)
	private Grupo grupo;
	private boolean idioma,asignacionManual;
	
	
	
	public Asignatura_matricula() {
	}
	public Asignatura_matricula(Asignatura asignatura, Matricula matricula, Grupo grupo, boolean idioma,
			boolean asignacionManual) {
		super();
		this.asignatura = asignatura;
		this.matricula = matricula;
		this.grupo = grupo;
		this.idioma = idioma;
		this.asignacionManual = asignacionManual;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public boolean isIdioma() {
		return idioma;
	}
	public void setIdioma(boolean idioma) {
		this.idioma = idioma;
	}
	public boolean isAsignacionManual() {
		return asignacionManual;
	}
	public void setAsignacionManual(boolean asignacionManual) {
		this.asignacionManual = asignacionManual;
	}
	public Asignatura getAsigantura() {
		return asignatura;
	}
	public void setAsigantura(Asignatura asigantura) {
		this.asignatura = asigantura;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Asignatura_matricula other = (Asignatura_matricula) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Asignatura_matricula [asignatura=" + asignatura + ", matricula=" + matricula + ", grupo=" + grupo
				+ ", idioma=" + idioma + ", asignacionManual=" + asignacionManual + "]";
	}
	
	
	
}
