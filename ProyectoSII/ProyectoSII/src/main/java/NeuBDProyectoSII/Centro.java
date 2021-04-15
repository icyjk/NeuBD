package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Centro {
	
	@Id @GeneratedValue
	private String id;
	private String nombre;
	private String direccion;
	private String tlf_conserjeria;
	@ManyToMany
	@JoinTable(name = "jnd_centro_titulacion" , joinColumns = @JoinColumn(name = "centro_fk"), inverseJoinColumns = @JoinColumn(name = "titulacion_fk"))
	private List<Titulacion> titulaciones;
	
	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}
	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTlf_conserjeria() {
		return tlf_conserjeria;
	}
	public void setTlf_conserjeria(String tlf_conserjeria) {
		this.tlf_conserjeria = tlf_conserjeria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Centro other = (Centro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", tlf_conserjeria="
				+ tlf_conserjeria + ", titulaciones=" + titulaciones + "]";
	}
	
	

}
