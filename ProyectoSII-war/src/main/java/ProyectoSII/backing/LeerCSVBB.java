package ProyectoSII.backing;

import java.io.IOException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.enterprise.context.RequestScoped;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionLeerCSV;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;


@Named(value = "Leercsv")
@RequestScoped
public class LeerCSVBB {

	public static enum Modo {
        ALUMNO,
        TITULACION,
        ASIGNATURA,
        OPTATIVA,
        ENCUESTA
    };
	@Inject 
	GestionLeerCSV gestionLeerCSV;
	@Inject
	GestionCentro gestionCentro;
	private Modo modo;
	private Part part;
	private String strModo;
	 public Modo getModo() {
	        return modo;
	    }

	    public void setModo(Modo modo) {
	        this.modo = modo;
	    }
	
	

	public String getStrModo() {
			return strModo;
		}

		public void setStrModo(String strModo) {
			this.strModo = strModo;
		}

	public Part getPart() {
			return part;
		}

		public void setPart(Part part) {
			this.part = part;
		}
		
	public void seleccionModo() {
		if(strModo.equals("al")) {//no deja utilizar switch con string en esta version de java
			setModo(modo.ALUMNO);
		}else if(strModo.equals("ti")){
			setModo(modo.TITULACION);
		}else if(strModo.equals("as")){
			setModo(modo.ASIGNATURA);
		}else if(strModo.equals("op")){
			setModo(modo.OPTATIVA);
		}else if(strModo.equals("en")){
			setModo(modo.ENCUESTA);
		}
	}

	public String ejecutarAccion() throws ParseException, IOException {
		try {
			seleccionModo();
			switch (modo) {
			case ALUMNO:
				part.write("/tmp/alumnos");
				gestionLeerCSV.insertarAlumnoCSV(new Titulacion(1, "prueba", 0, null, null, null, null), "/tmp/alumnos");			
				break;
			case TITULACION:
				part.write("/tmp/titulaciones");
				Centro c = gestionCentro.buscarTodosCentros().get(0);
				gestionLeerCSV.insertarTitulacionCSV(c, "/tmp/titulaciones");		
				break;
			case ASIGNATURA:
				part.write("/tmp/asignaturas");
				gestionLeerCSV.insertarAsignaturaCSV("/tmp/asignaturas");	
				break;
			case OPTATIVA:
				part.write("/tmp/optativas");
				gestionLeerCSV.insertarOptativaCSV("/tmp/optativas",new Titulacion(1, "prueba", 0, null, null, null, null) );			
				break;
			case ENCUESTA:
				part.write("/tmp/encuestas");
				gestionLeerCSV.insertarEncuestaCSV("/tmp/optativas");			
				break;
			}
			return "index.xhtml";
		}catch(NeuBDExceptions e) {
			return "index.xhtml";
		}
		
	}
}
