package ProyectoSII.backing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        ENCUESTA,
        CLASE
    };
	@Inject 
	GestionLeerCSV gestionLeerCSV;
	@Inject
	GestionCentro gestionCentro;
	private Modo modo;
	private Part part;
	private String strModo;
	private String strTitu;
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
	
		
	public String getStrTitu() {
			return strTitu;
		}

		public void setStrTitu(String strTitu) {
			this.strTitu = strTitu;
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
		}else if(strModo.equals("cl")){
			setModo(modo.CLASE);
		}
	}

	public String ejecutarAccion() throws ParseException, IOException {
		try {
			seleccionModo();
			Path temp;String ruta; File f;
			switch (modo) {
			case ALUMNO:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();
				part.write(temp.toString());
				gestionLeerCSV.insertarAlumnoCSV(temp.toString());			
				break;
			case TITULACION:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();//Si creo el temp despues me daria el error con el part.write por eso hago esto
				part.write(temp.toString());
				Centro c = gestionCentro.buscarTodosCentros().get(0);
				gestionLeerCSV.insertarTitulacionCSV(c, temp.toString());
				part.delete();
				break;
			case ASIGNATURA:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();
				part.write(temp.toString());
				gestionLeerCSV.insertarAsignaturaCSV(temp.toString());	
				break;
			case OPTATIVA:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();
				part.write(temp.toString());
				gestionLeerCSV.insertarOptativaCSV(temp.toString(),new Titulacion(1, "prueba", 0, null, null, null, null) );			
				break;
			case ENCUESTA:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();
				part.write(temp.toString());
				gestionLeerCSV.insertarEncuestaCSV(temp.toString());			
				break;
			case CLASE:
				temp = Files.createTempFile(null, ".csv");
				ruta = temp.toString();
				f = new File(ruta);
				f.delete();
				part.write(temp.toString());
				gestionLeerCSV.insertarClasesCSV(temp.toString());			
				break;
			}
			return "index.xhtml";
		}catch(NeuBDExceptions e) {
			return "index.xhtml";
		}
		
	}
}
