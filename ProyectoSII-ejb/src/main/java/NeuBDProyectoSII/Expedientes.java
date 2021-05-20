package NeuBDProyectoSII;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "Expedientes.todos", query= "select e from Expedientes e")
@Entity
public class Expedientes {
	
	@Id @GeneratedValue
	private long num_expediente;
	private boolean activo;
	private double nota_media_provisional;
	private double creditos_superado;
	private int credito_fb;
	private int credito_ob;
	private double credito_op;
	private int credito_cf;
	private int credito_pe;
	private int credito_tf;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private Titulacion titulaciones;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private Alumno alumno;
	@OneToMany(mappedBy = "expedientes")
	private List<Encuesta> encuestas;
	@OneToMany(mappedBy = "expedientes",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE} )
	private List<Matricula> matricula;
	
	
	
	
	public Expedientes() {
		
	}
	
	public Expedientes(boolean activo, double nota_media_provisional, double creditos_superado,
			int credito_fb, int credito_ob, double credito_op, int credito_cf, int credito_pe, int credito_tf,
			Titulacion titulaciones, Alumno alumno, List<Encuesta> encuestas) {
		super();
		this.activo = activo;
		this.nota_media_provisional = nota_media_provisional;
		this.creditos_superado = creditos_superado;
		this.credito_fb = credito_fb;
		this.credito_ob = credito_ob;
		this.credito_op = credito_op;
		this.credito_cf = credito_cf;
		this.credito_pe = credito_pe;
		this.credito_tf = credito_tf;
		this.titulaciones = titulaciones;
		this.alumno = alumno;
		this.encuestas = encuestas;
		matricula = new ArrayList<Matricula>();
	}
	public Titulacion getTitulaciones() {
		return titulaciones;
	}
	public void setTitulaciones(Titulacion titulaciones) {
		this.titulaciones = titulaciones;
	}
	public Alumno getAlumnos() {
		return alumno;
	}
	public void setAlumnos(Alumno alumnos) {
		this.alumno = alumnos;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	public long getNum_expediente() {
		return num_expediente;
	}
	public void setNum_expediente(long num_expediente) {
		this.num_expediente = num_expediente;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public double getNota_media_provisional() {
		return nota_media_provisional;
	}
	public void setNota_media_provisional(double nota_media_provisional) {
		this.nota_media_provisional = nota_media_provisional;
	}
	public double getCreditos_superado() {
		return creditos_superado;
	}
	public void setCreditos_superado(double creditos_superado) {
		this.creditos_superado = creditos_superado;
	}
	public int getCredito_fb() {
		return credito_fb;
	}
	public void setCredito_fb(int credito_fb) {
		this.credito_fb = credito_fb;
	}
	public int getCredito_ob() {
		return credito_ob;
	}
	public void setCredito_ob(int credito_ob) {
		this.credito_ob = credito_ob;
	}
	public double getCredito_op() {
		return credito_op;
	}
	public void setCredito_op(double credito_op) {
		this.credito_op = credito_op;
	}
	public int getCredito_cf() {
		return credito_cf;
	}
	public void setCredito_cf(int credito_cf) {
		this.credito_cf = credito_cf;
	}
	public int getCredito_pe() {
		return credito_pe;
	}
	public void setCredito_pe(int credito_pe) {
		this.credito_pe = credito_pe;
	}
	public int getCredito_tf() {
		return credito_tf;
	}
	public void setCredito_tf(int credito_tf) {
		this.credito_tf = credito_tf;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Matricula> getMatricula() {
		return matricula;
	}

	public void setMatricula(List<Matricula> matricula) {
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (num_expediente ^ (num_expediente >>> 32));
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
		Expedientes other = (Expedientes) obj;
		if (num_expediente != other.num_expediente)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Expedientes [num_expediente=" + num_expediente +"]";
	}

	
	
}
