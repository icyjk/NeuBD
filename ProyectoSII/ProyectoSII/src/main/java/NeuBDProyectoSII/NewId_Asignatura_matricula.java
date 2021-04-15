package NeuBDProyectoSII;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


public class NewId_Asignatura_matricula implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private int asignatura;
	@ManyToOne
	private NewId_Matricula_expediente matricula;
	public int getasignatura() {
		return asignatura;
	}
	public void setasignatura(int asignatura) {
		this.asignatura = asignatura;
	}
	public NewId_Matricula_expediente getMatricula() {
		return matricula;
	}
	public void setMatricula(NewId_Matricula_expediente matricula) {
		this.matricula = matricula;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + asignatura;
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
		NewId_Asignatura_matricula other = (NewId_Asignatura_matricula) obj;
		if (asignatura != other.asignatura)
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
		return "NewId_Asignatura_matricula [asignatura=" + asignatura + ", matricula=" + matricula + "]";
	}
	
	
}
