package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Alumno.todos", query= "select a from Alumno a")
@Entity

public class Alumno {

	

	@Id @GeneratedValue
	private int id;
	private String dni,nombre,primer_apellido,segundo_apellido,email_personal,email_institucional,movil,telefono,direccion,localidad,provincia,cp;
	@OneToMany(mappedBy = "alumno", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private List<Expedientes> expedientes;

	
	
	
	
	public Alumno() {
		
	}
	
	public Alumno(String dni, String nombre, String primer_apellido, String segundo_apellido,
			String email_personal, String email_institucional, String movil, String telefono, String direccion,
			String localidad, List<Expedientes> expedientes, String cp, String provincia) {
		
	
		this.dni = dni;
		this.nombre = nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.email_personal = email_personal;
		this.email_institucional = email_institucional;
		this.movil = movil;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.cp = cp;
		this.expedientes = expedientes;
	}

	public int getId() {
		return id;
	}

	public String getEmail_institucional() {
		return email_institucional;
	}

	public void setEmail_institucional(String email_institucional) {
		this.email_institucional = email_institucional;
	}

	public void setID(int id) {
		id = id;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getEmail_personal() {
		return email_personal;
	}

	public void setEmail_personal(String email_personal) {
		this.email_personal = email_personal;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public List<Expedientes> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<Expedientes> expedientes) {
		this.expedientes = expedientes;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + id;
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
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [ID=" + id + ", dni=" + dni + ", nombre=" + nombre + ", primer_apellido=" + primer_apellido
				+ ", segundo_apellido=" + segundo_apellido + ", email_personal=" + email_personal
				+ ", email_institucional=" + email_institucional + ", movil=" + movil + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", cp=" + cp
				+ ", expedientes=" + expedientes + "]";
	}



	
	
	
	
	
	
	
}
