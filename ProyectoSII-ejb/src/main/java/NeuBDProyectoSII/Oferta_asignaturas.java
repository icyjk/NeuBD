package NeuBDProyectoSII;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Oferta_asignaturas {
	@Id 
	private String id;
	private String titulacion;
	private String ofertada;
	private int codigo;
	private String referencia;
	private String asignatura;
	private int curso;
	private String credito_teoria;
	private String credito_practica;
	private String credito_totales;
	private int duracion;
	private int plazas;
	private String otro_idioma;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	public String getOfertada() {
		return ofertada;
	}
	public void setOfertada(String ofertada) {
		this.ofertada = ofertada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	public String getCredito_teoria() {
		return credito_teoria;
	}
	public void setCredito_teoria(String credito_teoria) {
		this.credito_teoria = credito_teoria;
	}
	public String getCredito_practica() {
		return credito_practica;
	}
	public void setCredito_practica(String credito_practica) {
		this.credito_practica = credito_practica;
	}
	public String getCredito_totales() {
		return credito_totales;
	}
	public void setCredito_totales(String credito_totales) {
		this.credito_totales = credito_totales;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	public String getOtro_idioma() {
		return otro_idioma;
	}
	public void setOtro_idioma(String otro_idioma) {
		this.otro_idioma = otro_idioma;
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
		Oferta_asignaturas other = (Oferta_asignaturas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "oferta_asignaturas [id=" + id + ", titulacion=" + titulacion + ", ofertada=" + ofertada + ", codigo="
				+ codigo + ", referencia=" + referencia + ", asignatura=" + asignatura + ", curso=" + curso
				+ ", credito_teoria=" + credito_teoria + ", credito_practica=" + credito_practica + ", credito_totales="
				+ credito_totales + ", duracion=" + duracion + ", plazas=" + plazas + ", otro_idioma=" + otro_idioma
				+ "]";
	}
	
}