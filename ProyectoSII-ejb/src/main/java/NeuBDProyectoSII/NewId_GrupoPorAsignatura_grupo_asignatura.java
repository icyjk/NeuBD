package NeuBDProyectoSII;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


public class NewId_GrupoPorAsignatura_grupo_asignatura implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int grupo;
		private int asignatura;
		private int Curso_academico;
		
		
		public NewId_GrupoPorAsignatura_grupo_asignatura() {
		}
		public NewId_GrupoPorAsignatura_grupo_asignatura(int grupo, int asignatura, int curso_academico) {
			super();
			this.grupo = grupo;
			this.asignatura = asignatura;
			Curso_academico = curso_academico;
		}
		public int getGrupo() {
			return grupo;
		}
		public void setGrupo(int grupo) {
			this.grupo = grupo;
		}
		public int getAsignatura() {
			return asignatura;
		}
		public void setAsignatura(int asignatura) {
			this.asignatura = asignatura;
		}
		public int getCurso_academico() {
			return Curso_academico;
		}
		public void setCurso_academico(int curso_academico) {
			Curso_academico = curso_academico;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Curso_academico;
			result = prime * result + asignatura;
			result = prime * result + grupo;
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
			NewId_GrupoPorAsignatura_grupo_asignatura other = (NewId_GrupoPorAsignatura_grupo_asignatura) obj;
			if (Curso_academico != other.Curso_academico)
				return false;
			if (asignatura != other.asignatura)
				return false;
			if (grupo != other.grupo)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "NewId_GrupoPorAsignatura_grupo_asignatura [grupo=" + grupo + ", asignatura=" + asignatura
					+ ", Curso_academico=" + Curso_academico + "]";
		}
		
		
}
