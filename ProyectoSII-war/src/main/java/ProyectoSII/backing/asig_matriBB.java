package ProyectoSII.backing;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSIIEjb.GestionAsigMatri;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "asig_matri")
@RequestScoped
public class asig_matriBB implements Serializable {

	public static enum Modo {
        MODIFICAR, 
        NOACCION
    };
	
    
    private Asignatura_matricula asignatura_matricula;
    private Modo modo;
    @Inject
    private GestionAsigMatri GestionAsigMatri;
    
    
	public asig_matriBB() {
		asignatura_matricula= new Asignatura_matricula();
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
	            
	        }
	        return null;
	 }
	 
	 public Asignatura_matricula getAsignatura_matricula() {
	        return asignatura_matricula;
	    }

	  public void setAsignatura_matricula(Asignatura_matricula asig) {
	        this.asignatura_matricula = asig;
	    }
	  
	  
	  

	  
	  public String modificar(Asignatura_matricula asig) {
	        asignatura_matricula = asig;
	        setModo(Modo.MODIFICAR);
	        return "edicionAsignatura_matricula.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    GestionAsigMatri.modificarAsigMatri(asignatura_matricula);
	                    break;
	             
	            }
	           
	            return "edicionAsignatura_matriculas.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	 
	  public Asignatura_matricula visualizarAsignatura_matricula(NewId_Asignatura_matricula ID) throws NeuBDExceptions {
		  Asignatura_matricula asig=null;
		  
		  try {
	           
			   asig = GestionAsigMatri.visualizarAsigMatri(ID);
	    
	        } catch (NeuBDExceptions e) {
	            System.out.println("Asignatura_matricula no encontrada");
	        }
	        return asig;
	    }
	  
	 
	  
		
		public void ImportarAsignatura_matricula(Asignatura_matricula Asignatura_matricula) throws NeuBDExceptions {
			
			  
			  try {
		           GestionAsigMatri.anyadirAsignatura_matricula(Asignatura_matricula);
		    
		        } catch (NeuBDExceptions e) {
		            System.out.println("Asignatura_matricula no encontrada");
		        }
		}
	  
	  
		
		public List<Asignatura_matricula> listaAsignatura_matricula() throws NeuBDExceptions {
			return GestionAsigMatri.listaAsigMatri();
		}
}
