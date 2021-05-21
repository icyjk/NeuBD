package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionEncuesta;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "encuesta")
@RequestScoped
public class EncuestaBB {

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        CREAR,
        NOACCION
    };
	
    
    private Encuesta encuesta;
    private Modo modo;
    @Inject
    private GestionEncuesta gestionEncuesta;
    
    
	public EncuestaBB() {
		encuesta= new Encuesta();
		modo=Modo.NOACCION;
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
	            case CREAR:
	            	return "Crear";
	        }
	        return null;
	 }
	 
	 public Encuesta getEncuesta() {
	        return encuesta;
	    }

	  public void setEncuesta(Encuesta encuesta) {
	        this.encuesta = encuesta;
	    }
	  
	  
	  

	  
	  public String modificar(Encuesta enc) {
	        encuesta = enc;
	        setModo(Modo.MODIFICAR);
	        return "edicionEncuesta.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionEncuesta.modificarEncuesta(encuesta);
	                    break;
	                case ELIMINAR:
	                    gestionEncuesta.eliminarEncuesta(encuesta);
	                    break;
	                    
	                case CREAR:
	                	gestionEncuesta.ImportarEncuesta(encuesta);
	                
	            }
	           
	            return "edicionEncuestas.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	  public String eliminar(Encuesta enc) throws NeuBDExceptions {
	        try {
	            gestionEncuesta.eliminarEncuesta(enc);
	    
	        } catch (AsignaturaNoEncontradaException e) {
	            return "index.xhtml";
	        }
	        return null;
	    }
	 
	  public Encuesta visualizarEncuesta(Encuesta encuestaa) throws NeuBDExceptions {
		  Encuesta enc=null;
		  
		  try {
	           
			   enc = gestionEncuesta.visualizarEncuesta(encuestaa);
	    
	        } catch (AsignaturaNoEncontradaException e) {
	            System.out.println("Asignatura no encontrada");
	        }
	        return enc;
	    }

	  
	  
	  public String crearEncuesta() {
	        setModo(Modo.CREAR);
	        return "edicionEncuestas.xhtml";
	    }
		
	  
		
		public List<Encuesta> listaEncuesta() throws NeuBDExceptions {
			return gestionEncuesta.listaEncuestas();
		}
	
}

