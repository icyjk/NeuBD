package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "centro")
@RequestScoped
public class CentroBB {

	public static enum Modo {
        MODIFICAR, 
        CREAR,
        NOACCION
    };
	
    
    private Centro centro;
    private Modo modo;
    @Inject
    private GestionCentro gestionCentro;
    
    
	public CentroBB() {
		centro= new Centro();
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
	            case CREAR:
	            	return "Crear";
	        }
	        return null;
	 }
	 
	 public Centro getCentro() {
	        return centro;
	    }

	  public void setCentro(Centro Centro) {
	        this.centro = Centro;
	    }
	  
	  
	  

	  
	  public String modificar(Centro enc) {
	        centro = enc;
	        setModo(Modo.MODIFICAR);
	        return "edicionCentro.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionCentro.modificarCentro(centro);
	                    break;
	                
	                   
	                case CREAR:
	                	gestionCentro.CrearCentro(centro);
	                
	            }
	           
	            return "edicionCentro.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	
	 
	  public Centro buscarCentro(Centro c) throws NeuBDExceptions {
		  Centro enc=null;
		  
		  try {
	           
			   enc = gestionCentro.buscarCentro(c.getId());
	    
	        } catch (NeuBDExceptions e) {
	            System.out.println("Centro no encontrada");
	        }
	        return enc;
	    }

	  
	  
	  public String crearCentro() {
	        setModo(Modo.CREAR);
	        return "edicionCentros.xhtml";
	    }
		
	  
		
		public List<Centro> listaCentro() throws NeuBDExceptions {
			return gestionCentro.buscarTodosCentros();
		}
	
}
