package NeuBDProyectoSII;
import java.io.Serializable;


public class NewId_Matricula_expediente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long expedientes;
	private String curso_academico;
	
	public NewId_Matricula_expediente() {
		
	}
	
	public NewId_Matricula_expediente(long expedientes, String curso_academico) {
		super();
		this.expedientes = expedientes;
		this.curso_academico = curso_academico;
	}
	
	
	public long getExpedientes() {
		return expedientes;
	}
	public void setExpedientes(long expedientes) {
		this.expedientes = expedientes;
	}
	public String getCurso_academico() {
		return curso_academico;
	}
	public void setCurso_academico(String curso_academico) {
		this.curso_academico = curso_academico;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso_academico == null) ? 0 : curso_academico.hashCode());
		result = prime * result + (int) (expedientes ^ (expedientes >>> 32));
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
		NewId_Matricula_expediente other = (NewId_Matricula_expediente) obj;
		if (curso_academico == null) {
			if (other.curso_academico != null)
				return false;
		} else if (!curso_academico.equals(other.curso_academico))
			return false;
		if (expedientes != other.expedientes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewId_Matricula_expediente [expedientes=" + expedientes + ", curso_academico=" + curso_academico + "]";
	}
	
	
}
