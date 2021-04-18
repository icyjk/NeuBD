package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Asignatura {
	@Id private int referencia;
	
	private int codigo;
	private int creditos_practica;
	private int creditos_teoria;
	private int creditos;
	private boolean ofertada;
	private String nombre;
	private int curso;
	private String caracter;
	private int duracion;
	private int unidad_temporal;
	private String idioma_imparticion;
	@ManyToMany
	@JoinTable(name = "jnd_asignatura_profesor" , joinColumns = @JoinColumn(name = "asignatura_fk"), inverseJoinColumns = @JoinColumn(name = "profesor_fk"))
	private List<Profesor> profesores;
	@ManyToOne
	private Titulacion titulacion;
	@OneToMany (mappedBy = "asignatura")
	private List<Asignatura_matricula> asignaturaMatricula;
	@OneToMany(mappedBy = "asignaturas")
	private List<Clase> clases;
	
	
	
	
	
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	public List<Asignatura_matricula> getAsignaturaMatricula() {
		return asignaturaMatricula;
	}
	public void setAsignaturaMatricula(List<Asignatura_matricula> asignaturaMatricula) {
		this.asignaturaMatricula = asignaturaMatricula;
	}
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public int getReferencia() {
		return referencia;
	}
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCreditos_practica() {
		return creditos_practica;
	}
	public void setCreditos_practica(int creditos_practica) {
		this.creditos_practica = creditos_practica;
	}
	public int getCreditos_teoria() {
		return creditos_teoria;
	}
	public void setCreditos_teoria(int creditos_teoria) {
		this.creditos_teoria = creditos_teoria;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public boolean isOfertada() {
		return ofertada;
	}
	public void setOfertada(boolean ofertada) {
		this.ofertada = ofertada;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	public String getCaracter() {
		return caracter;
	}
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getUnidad_temporal() {
		return unidad_temporal;
	}
	public void setUnidad_temporal(int unidad_temporal) {
		this.unidad_temporal = unidad_temporal;
	}
	public String getIdioma_imparticion() {
		return idioma_imparticion;
	}
	public void setIdioma_imparticion(String idioma_imparticion) {
		this.idioma_imparticion = idioma_imparticion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + referencia;
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
		Asignatura other = (Asignatura) obj;
		if (referencia != other.referencia)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos_practica="
				+ creditos_practica + ", creditos_teoria=" + creditos_teoria + ", creditos=" + creditos + ", ofertada="
				+ ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", duracion="
				+ duracion + ", unidad_temporal=" + unidad_temporal + ", idioma_imparticion=" + idioma_imparticion
				+ ", profesores=" + profesores + ", titulacion=" + titulacion + ", asignaturaMatricula="
				+ asignaturaMatricula + ", clases=" + clases + "]";
	}
	

	
	
	
	
}
