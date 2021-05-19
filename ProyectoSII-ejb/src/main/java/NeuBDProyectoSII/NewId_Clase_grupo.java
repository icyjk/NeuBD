package NeuBDProyectoSII;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


public class NewId_Clase_grupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int grupo;
	private String dia;
	private Date hora_inicio;
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Date getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + grupo;
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
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
		NewId_Clase_grupo other = (NewId_Clase_grupo) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (grupo != other.grupo)
			return false;
		if (hora_inicio == null) {
			if (other.hora_inicio != null)
				return false;
		} else if (!hora_inicio.equals(other.hora_inicio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewId_Clase_grupo [grupo=" + grupo + ", dia=" + dia + ", hora_inicio=" + hora_inicio + "]";
	}
	
	
}
