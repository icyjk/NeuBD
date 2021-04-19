package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Titulacion {

	@Id @GeneratedValue
	private int codigo;
	private String nombre;
	private int creditos;
	@ManyToMany(mappedBy = "titulaciones")
	private List<Centro> centros;
	@OneToMany(mappedBy = "titulaciones")
	private List<Expedientes> expedientes;
	@OneToMany(mappedBy = "titulacion")
	private List<Asignatura> asignaturas;
	@OneToMany(mappedBy = "titulacion")
	private List<Grupo> grupos;
	
	
	public List<Expedientes> getExpedientes() {
		return expedientes;
	}
	public void setExpedientes(List<Expedientes> expedientes) {
		this.expedientes = expedientes;
	}
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public List<Centro> getCentros() {
		return centros;
	}
	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Titulacion other = (Titulacion) obj;
		if (codigo != other.codigo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Titulacion [codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + ", centros=" + centros
				+ ", expedientes=" + expedientes + ", asignaturas=" + asignaturas + ", grupos=" + grupos + "]";
	}
	

	
	
}
