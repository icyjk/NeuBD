package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.TitulacionBB.Modo;


@Named(value = "matricula")
@RequestScoped
public class MatriculaBB {

	public static enum Modo{
		MODIFICAR,
		ELIMINAR,
		NOACCION
	}

	private Matricula matricula;
	private Modo modo;

	@Inject
	private GestionMatricula gestionMatricula;

	public MatriculaBB() {
		matricula = new Matricula();
		modo = Modo.NOACCION;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public GestionMatricula getGestionMatricula() {
		return gestionMatricula;
	}

	public void setGestionMatricula(GestionMatricula gestionMatricula) {
		this.gestionMatricula = gestionMatricula;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
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

	public String modificar(Matricula m) {
		matricula = m;
		setModo(Modo.MODIFICAR);
		return "edicionMatricula.xhtml";
	}


	public String ejecutarAccion() {
		try {
			switch (modo) {
			case MODIFICAR:
				gestionMatricula.modificarMatricula(matricula);
				break;
			case ELIMINAR:
				gestionMatricula.eliminarMatricula(matricula);
				break;
			}

			return "edicionMatricula.xhtml";
		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
	}

	public String eliminarMatricula(Matricula m) throws NeuBDExceptions{
		try {
			gestionMatricula.eliminarMatricula(m);

		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
		return null;

	}

	public Matricula visualizarMatricula(Matricula mat) throws NeuBDExceptions{
		Matricula m=null;

		try {

			m = gestionMatricula.visualizarMatricula(mat);

		} catch (NeuBDExceptions e) {
			System.out.println("Matricula no encontrada");
		}
		return m;

	}


	public List<Matricula> listaAlumnos() throws NeuBDExceptions {
		return gestionMatricula.listaMatricula();
	}

	public void anyadirMatricula(Matricula m) throws NeuBDExceptions {

		try {
			gestionMatricula.anyadirMatricula(m);

		} catch (NeuBDExceptions e) {
			System.out.println("Matricula no encontrada");
		}
	}



}
