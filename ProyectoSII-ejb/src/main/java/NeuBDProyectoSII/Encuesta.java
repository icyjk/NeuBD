package NeuBDProyectoSII;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "Encuesta.todos", query= "select e from Encuesta e")
@Entity
public class Encuesta {
	
	@Id 
	private Date Fecha_de_envio;
	@ManyToOne
	private Expedientes expedientes;
	@ManyToMany
	private List<Grupos_por_asignatura> grupos_por_asignatura;
	

	public Encuesta() {
		
	}
	public Encuesta(Date fecha_de_envio, Expedientes expedientes, List<Grupos_por_asignatura> grupos_por_asignatura) {
		super();
		Fecha_de_envio = fecha_de_envio;
		this.expedientes = expedientes;
		this.grupos_por_asignatura = grupos_por_asignatura;
	}

	public Expedientes getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(Expedientes expedientes) {
		this.expedientes = expedientes;
	}

	

	public List<Grupos_por_asignatura> getGrupos_por_asignatura() {
		return grupos_por_asignatura;
	}

	public void setGrupos_por_asignatura(List<Grupos_por_asignatura> grupos_por_asignatura) {
		this.grupos_por_asignatura = grupos_por_asignatura;
	}

	public Date getFecha_de_envio() {
		return Fecha_de_envio;
	}

	public void setFecha_de_envio(Date fecha_de_envio) {
		Fecha_de_envio = fecha_de_envio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Fecha_de_envio == null) ? 0 : Fecha_de_envio.hashCode());
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
		Encuesta other = (Encuesta) obj;
		if (Fecha_de_envio == null) {
			if (other.Fecha_de_envio != null)
				return false;
		} else if (!Fecha_de_envio.equals(other.Fecha_de_envio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Encuesta [Fecha_de_envio=" + Fecha_de_envio + ", expedientes=" + expedientes
				+ ", grupos_por_asignatura=" + grupos_por_asignatura + "]";
	}
	
	
}
