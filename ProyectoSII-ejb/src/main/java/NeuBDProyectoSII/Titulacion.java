package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Titulacion.todos", query= "select t from Titulacion t")
@Entity
public class Titulacion {

	@Id 
	private int codigo;
	private String nombre;
	private int creditos;
	@ManyToMany(mappedBy = "titulaciones",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private List<Centro> centros;
	@OneToMany(mappedBy = "titulaciones",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private List<Expedientes> expedientes;
	@OneToMany(mappedBy = "titulacion",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private List<Asignatura> asignaturas;
	@OneToMany(mappedBy = "titulacion",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private List<Grupo> grupos;
	@OneToMany(mappedBy = "titulacion",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private List<Optativa> optativa;
	
	
	
	
	
	public Titulacion() {
		super();
	}
	
	
	public Titulacion(int codigo, String nombre, int creditos, List<Centro> centros, List<Expedientes> expedientes,
			List<Asignatura> asignaturas, List<Grupo> grupos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.centros = centros;
		this.expedientes = expedientes;
		this.asignaturas = asignaturas;
		this.grupos = grupos;
	}

	
	public List<Optativa> getOptativa() {
		return optativa;
	}


	public void setOptativa(List<Optativa>optativa) {
		this.optativa = optativa;
	}
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
