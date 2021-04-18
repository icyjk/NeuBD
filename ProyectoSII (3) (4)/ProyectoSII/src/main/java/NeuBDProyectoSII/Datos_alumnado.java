package NeuBDProyectoSII;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Datos_alumnado {
	@Id 
	private String dni;
	private String nombre;
	private String p_apellido;
	private String s_apellido;
	private int num_expediente;
	private String email_institucional;
	private String email_personal;
	private int telefono;
	private int movil;
	private String direccion_not;
	private String localidad_not;
	private String provincia_not;
	private String cp;
	private String fecha_matricula;
	private String turno_preferente;
	private String grupos_asignados;
	private String nota_media;
	private String creditos_superados;
	private String creditos_FB;
	private String creditos_OB;
	private String creditos_OP;
	private String creditos_CF;
	private String creditos_PE;
	private String creditos_TF;
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
	public String getP_apellido() {
		return p_apellido;
	}
	public void setP_apellido(String p_apellido) {
		this.p_apellido = p_apellido;
	}
	public String getS_apellido() {
		return s_apellido;
	}
	public void setS_apellido(String s_apellido) {
		this.s_apellido = s_apellido;
	}
	public int getNum_expediente() {
		return num_expediente;
	}
	public void setNum_expediente(int num_expediente) {
		this.num_expediente = num_expediente;
	}
	public String getEmail_institucional() {
		return email_institucional;
	}
	public void setEmail_institucional(String email_institucional) {
		this.email_institucional = email_institucional;
	}
	public String getEmail_personal() {
		return email_personal;
	}
	public void setEmail_personal(String email_personal) {
		this.email_personal = email_personal;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getMovil() {
		return movil;
	}
	public void setMovil(int movil) {
		this.movil = movil;
	}
	public String getDireccion_not() {
		return direccion_not;
	}
	public void setDireccion_not(String direccion_not) {
		this.direccion_not = direccion_not;
	}
	public String getLocalidad_not() {
		return localidad_not;
	}
	public void setLocalidad_not(String localidad_not) {
		this.localidad_not = localidad_not;
	}
	public String getProvincia_not() {
		return provincia_not;
	}
	public void setProvincia_not(String provincia_not) {
		this.provincia_not = provincia_not;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getFecha_matricula() {
		return fecha_matricula;
	}
	public void setFecha_matricula(String fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}
	public String getTurno_preferente() {
		return turno_preferente;
	}
	public void setTurno_preferente(String turno_preferente) {
		this.turno_preferente = turno_preferente;
	}
	public String getGrupos_asignados() {
		return grupos_asignados;
	}
	public void setGrupos_asignados(String grupos_asignados) {
		this.grupos_asignados = grupos_asignados;
	}
	public String getNota_media() {
		return nota_media;
	}
	public void setNota_media(String nota_media) {
		this.nota_media = nota_media;
	}
	public String getCreditos_superados() {
		return creditos_superados;
	}
	public void setCreditos_superados(String creditos_superados) {
		this.creditos_superados = creditos_superados;
	}
	public String getCreditos_FB() {
		return creditos_FB;
	}
	public void setCreditos_FB(String creditos_FB) {
		this.creditos_FB = creditos_FB;
	}
	public String getCreditos_OB() {
		return creditos_OB;
	}
	public void setCreditos_OB(String creditos_OB) {
		this.creditos_OB = creditos_OB;
	}
	public String getCreditos_OP() {
		return creditos_OP;
	}
	public void setCreditos_OP(String creditos_OP) {
		this.creditos_OP = creditos_OP;
	}
	public String getCreditos_CF() {
		return creditos_CF;
	}
	public void setCreditos_CF(String creditos_CF) {
		this.creditos_CF = creditos_CF;
	}
	public String getCreditos_PE() {
		return creditos_PE;
	}
	public void setCreditos_PE(String creditos_PE) {
		this.creditos_PE = creditos_PE;
	}
	public String getCreditos_TF() {
		return creditos_TF;
	}
	public void setCreditos_TF(String creditos_TF) {
		this.creditos_TF = creditos_TF;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Datos_alumnado other = (Datos_alumnado) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Datos_alumnado [dni=" + dni + ", nombre=" + nombre + ", p_apellido=" + p_apellido + ", s_apellido="
				+ s_apellido + ", num_expediente=" + num_expediente + ", email_institucional=" + email_institucional
				+ ", email_personal=" + email_personal + ", telefono=" + telefono + ", movil=" + movil
				+ ", direccion_not=" + direccion_not + ", localidad_not=" + localidad_not + ", provincia_not="
				+ provincia_not + ", cp=" + cp + ", fecha_matricula=" + fecha_matricula + ", turno_preferente="
				+ turno_preferente + ", grupos_asignados=" + grupos_asignados + ", nota_media=" + nota_media
				+ ", creditos_superados=" + creditos_superados + ", creditos_FB=" + creditos_FB + ", creditos_OB="
				+ creditos_OB + ", creditos_OP=" + creditos_OP + ", creditos_CF=" + creditos_CF + ", creditos_PE="
				+ creditos_PE + ", creditos_TF=" + creditos_TF + "]";
	}
	
}
