package NeuBDProyectoSII;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Matricula.todos", query= "select m from Matricula m")
@Entity
@IdClass(NewId_Matricula_expediente.class)
public class Matricula {
	@Id @ManyToOne
	private Expedientes expedientes;
	@Id private int curso_academico;
	private String estado;
	private int num_archivo;
	private String turno_preferente;
	private Date fecha_matricula;
	private String nuevo_ingreso;
	private List<Asignatura> listado_asignaturas;
	@OneToMany
	private List<Asignatura_matricula> asignatura_matricula;
	
	public Matricula () {
		
	}
	
	public Matricula(Expedientes expedientes, int curso_academico, String estado, int num_archivo,
			String turno_preferente, Date fecha_matricula, String nuevo_ingreso, List<Asignatura> listado_asignaturas,
			List<Asignatura_matricula> asignatura_matricula) {
		super();
		this.expedientes = expedientes;
		this.curso_academico = curso_academico;
		this.estado = estado;
		this.num_archivo = num_archivo;
		this.turno_preferente = turno_preferente;
		this.fecha_matricula = fecha_matricula;
		this.nuevo_ingreso = nuevo_ingreso;
		this.listado_asignaturas = listado_asignaturas;
		this.asignatura_matricula = asignatura_matricula;
	}
	public List<Asignatura_matricula> getAsignatura_matricula() {
		return asignatura_matricula;
	}
	public void setAsignatura_matricula(List<Asignatura_matricula> asignatura_matricula) {
		this.asignatura_matricula = asignatura_matricula;
	}
	
	public Expedientes getExpedientes() {
		return expedientes;
	}
	public void setExpedientes(Expedientes expedientes) {
		this.expedientes = expedientes;
	}
	public int getCurso_academico() {
		return curso_academico;
	}
	public void setCurso_academico(int curso_academico) {
		this.curso_academico = curso_academico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getNum_archivo() {
		return num_archivo;
	}
	public void setNum_archivo(int num_archivo) {
		this.num_archivo = num_archivo;
	}
	public String getTurno_preferente() {
		return turno_preferente;
	}
	public void setTurno_preferente(String turno_preferente) {
		this.turno_preferente = turno_preferente;
	}
	public Date getFecha_matricula() {
		return fecha_matricula;
	}
	public void setFecha_matricula(Date fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}
	public String getNuevo_ingreso() {
		return nuevo_ingreso;
	}
	public void setNuevo_ingreso(String nuevo_ingreso) {
		this.nuevo_ingreso = nuevo_ingreso;
	}
	public List<Asignatura> getListado_asignaturas() {
		return listado_asignaturas;
	}
	public void setListado_asignaturas(List<Asignatura> listado_asignaturas) {
		this.listado_asignaturas = listado_asignaturas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curso_academico;
		result = prime * result + ((expedientes == null) ? 0 : expedientes.hashCode());
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
		Matricula other = (Matricula) obj;
		if (curso_academico != other.curso_academico)
			return false;
		if (expedientes == null) {
			if (other.expedientes != null)
				return false;
		} else if (!expedientes.equals(other.expedientes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Matricula [expedientes=" + expedientes + ", curso_academico=" + curso_academico + ", estado=" + estado
				+ ", num_archivo=" + num_archivo + ", turno_preferente=" + turno_preferente + ", fecha_matricula="
				+ fecha_matricula + ", nuevo_ingreso=" + nuevo_ingreso + ", listado_asignaturas=" + listado_asignaturas
				+ ", asignatura_matricula=" + asignatura_matricula + "]";
	}
	
	
	
}
