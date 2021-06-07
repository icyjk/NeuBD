package NeuBDProyectoSII;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Asignatura.todos", query= "select a from Asignatura a")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Asignatura implements Serializable{
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
	private String unidad_temporal;
	private String idioma_imparticion;
	@ManyToOne
	private Titulacion titulacion;
	@OneToMany (mappedBy = "asignatura")
	private List<Asignatura_matricula> asignaturaMatricula;
	@OneToMany(mappedBy = "asignaturas",fetch = FetchType.EAGER)
	private List<Clase> clases;
	@OneToMany(mappedBy = "asignatura")
	private List<Grupos_por_asignatura> gruposPorAsignatura;

	
	public Asignatura() {
	}
	public Asignatura(int referencia, int codigo, int creditos_practica, int creditos_teoria, int creditos,
			boolean ofertada, String nombre, int curso, String caracter, int duracion, String unidad_temporal,
			String idioma_imparticion, Titulacion titulacion, List<Asignatura_matricula> asignaturaMatricula,
			List<Clase> clases, List<Grupos_por_asignatura> gruposPorAsignatura) {
		super();
		this.referencia = referencia;
		this.codigo = codigo;
		this.creditos_practica = creditos_practica;
		this.creditos_teoria = creditos_teoria;
		this.creditos = creditos;
		this.ofertada = ofertada;
		this.nombre = nombre;
		this.curso = curso;
		this.caracter = caracter;
		this.duracion = duracion;
		this.unidad_temporal = unidad_temporal;
		this.idioma_imparticion = idioma_imparticion;
		this.titulacion = titulacion;
		this.asignaturaMatricula = asignaturaMatricula;
		this.clases = clases;
		this.gruposPorAsignatura = gruposPorAsignatura;
	}
	public List<Grupos_por_asignatura> getGruposPorAsignatura() {
		return gruposPorAsignatura;
	}
	public void setGruposPorAsignatura(List<Grupos_por_asignatura> gruposPorAsignatura) {
		this.gruposPorAsignatura = gruposPorAsignatura;
	}
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
	public String getUnidad_temporal() {
		return unidad_temporal;
	}
	public void setUnidad_temporal(String unidad_temporal) {
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
				+ duracion + ", unidad_temporal=" + unidad_temporal + ", idioma_imparticion=" + idioma_imparticion + "]";
	}
	

	
	
	
	
}
