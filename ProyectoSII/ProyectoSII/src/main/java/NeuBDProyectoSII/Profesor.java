package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
public class Profesor extends Usuario{

	private String departamento,despacho;
	@ManyToMany(mappedBy = "profesores")
	private List<Asignatura> asignaturas;
	
	

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((despacho == null) ? 0 : despacho.hashCode());
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
		Profesor other = (Profesor) obj;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (despacho == null) {
			if (other.despacho != null)
				return false;
		} else if (!despacho.equals(other.despacho))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profesor [departamento=" + departamento + ", despacho=" + despacho + ", asignaturas=" + asignaturas
				+ "]";
	}

	
}
