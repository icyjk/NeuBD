package ProyectoSII.backing;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsigMatri;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionLeerCSV;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;


@Named(value = "Leercsv")
@ViewScoped
public class LeerCSVBB implements Serializable{

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
	@Inject
	GestionAsigMatri gestionAsigMatri;
	@Inject
	GestionTitulacion gestionTitulacion;
	private Modo modo;
	private Part part;
	private String strModo;
	private int strTitu;
	private List<Integer> titulaciones;
	
	@PostConstruct
	public void init() throws NeuBDExceptions {
		List<Titulacion> aux = gestionTitulacion.listaTitulacion();
		 int cnt = 0;Integer i;
		 titulaciones = new ArrayList<Integer>();
		 while(cnt<aux.size()) {
			 i = (Integer) aux.get(cnt).getCodigo();
			 titulaciones.add(i);
			 cnt++;
		 }
	}

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
	
		
	public int getStrTitu() {
			return strTitu;
		}

		public void setStrTitu(int strTitu) {
			this.strTitu = strTitu;
		}
		
	public List<Integer> getTitulaciones() {
		return titulaciones;
		}

		public void setTitulaciones(List<Integer> titulaciones) {
			this.titulaciones = titulaciones;
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
				gestionAsigMatri.crearAsigsMatris();
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
				Titulacion t = gestionTitulacion.visualizartitulacion(strTitu);
				gestionLeerCSV.insertarOptativaCSV(temp.toString(),t );			
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
