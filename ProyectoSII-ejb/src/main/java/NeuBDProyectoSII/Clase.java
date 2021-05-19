package NeuBDProyectoSII;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@NamedQuery(name = "Clase.todos", query= "select c from Clase c")
@Entity
@IdClass(NewId_Clase_grupo.class)
public class Clase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @ManyToOne private Grupo grupo;
	@Id private String dia;
	@Temporal(TemporalType.DATE)
	@Id private Date hora_inicio;
	
	@Temporal(TemporalType.DATE)
	private Date hora_fin;
	@ManyToOne
	private Asignatura asignaturas;
	
	
	public Asignatura getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(Asignatura asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Date getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Date getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(Date hora_fin) {
		this.hora_fin = hora_fin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
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
		Clase other = (Clase) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (hora_inicio == null) {
			if (other.hora_inicio != null)
				return false;
		} else if (!hora_inicio.equals(other.hora_inicio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Clase [grupo=" + grupo + ", dia=" + dia + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin
				+ ", asignaturas=" + asignaturas + "]";
	}
	
	
}
