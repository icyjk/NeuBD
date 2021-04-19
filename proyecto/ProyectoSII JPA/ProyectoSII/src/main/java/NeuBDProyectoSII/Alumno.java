package NeuBDProyectoSII;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
public class Alumno extends Usuario {

		private String email_institucional;
		@OneToMany(mappedBy = "alumno")
		private List<Expedientes> expedientes; 
		
		

		public String getEmail_institucional() {
			return email_institucional;
		}

		public void setEmail_institucional(String email_institucional) {
			this.email_institucional = email_institucional;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email_institucional == null) ? 0 : email_institucional.hashCode());
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
			if (email_institucional == null) {
				if (other.email_institucional != null)
					return false;
			} else if (!email_institucional.equals(other.email_institucional))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ALUMNO [email_institucional=" + email_institucional + "]";
		}
		
		
		
}
