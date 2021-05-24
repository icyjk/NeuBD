package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.MatriculaBB.Modo;

@Named(value = "alumno")
@RequestScoped
public class AlumnoBB {

	public static enum Modo{
		MODIFICAR,
		ELIMINAR,
		NOACCION
	}
	
	private Alumno alumno;
	private Modo modo;
	
	@Inject
	private GestionAlumno gestionAlumno;
	
	private AlumnoBB() {
		alumno = new Alumno();
		modo = Modo.NOACCION;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
	}

	public GestionAlumno getGestionAlumno() {
		return gestionAlumno;
	}

	public void setGestionAlumno(GestionAlumno gestionAlumno) {
		this.gestionAlumno = gestionAlumno;
	}
	
	public String getAccion() {
		switch (modo) {
		case MODIFICAR:
			return "Modificar";
		case ELIMINAR:
			return "Eliminar";

		}
		return null;
	}

	public String modificar(Alumno a) {
		alumno = a;
		setModo(Modo.MODIFICAR);
		return "edicionAlumno.xhtml";
	}


	public String ejecutarAccion() {
		try {
			switch (modo) {
			case MODIFICAR:
				gestionAlumno.modificarAlumno(alumno);
				break;
			case ELIMINAR:
				gestionAlumno.eliminarAlumno(alumno.getID());
				break;
			}

			return "edicionAlumno.xhtml";
		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
	}
	
	
	public Alumno visualizarAlumno(Alumno al) throws NeuBDExceptions{
		Alumno a=null;

		try {

			a = gestionAlumno.visualizarAlumno(al.getID());

		} catch (NeuBDExceptions e) {
			System.out.println("Alumno no encontrado");
		}
		return a;

	}
	
	public List<Alumno> listaAlumnos() throws NeuBDExceptions {
		return gestionAlumno.listaAlumno();
	}

	public void anyadirAlumno(Alumno a) throws NeuBDExceptions {

		try {
			gestionAlumno.anyadirAlumno(a);

		} catch (NeuBDExceptions e) {
			System.out.println("Alumno no encontrado");
		}
	}
	

	
}
