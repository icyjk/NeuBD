package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "asignaturas")
@RequestScoped
public class AsignaturasBB {

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
	
    
    private Asignatura asignatura;
    private Modo modo;
    @Inject
    private GestionAsignatura gestionAsignatura;
    
    
	public AsignaturasBB() {
		asignatura= new Asignatura();
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

	        }
	        return null;
	 }
	 
	 public Asignatura getAsignatura() {
	        return asignatura;
	    }

	  public void setAsignatura(Asignatura asig) {
	        this.asignatura = asig;
	    }
	  
	  
	  

	  
	  public String modificar(Asignatura asig) {
	        asignatura = asig;
	        setModo(Modo.MODIFICAR);
	        return "edicionAsignatura.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionAsignatura.modificarAsignatura(asignatura);
	                    break;
	                case ELIMINAR:
	                    gestionAsignatura.eliminaAsignatura(asignatura);
	                    break;
	            }
	           
	            return "edicionAsignaturas.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	  public String eliminar(Asignatura asig) throws NeuBDExceptions {
	        try {
	            gestionAsignatura.eliminaAsignatura(asig);
	    
	        } catch (AsignaturaNoEncontradaException e) {
	            return "index.xhtml";
	        }
	        return null;
	    }
	 
	  public Asignatura buscarAsignatura(int referencia) throws NeuBDExceptions {
		  Asignatura asig=null;
		  
		  try {
	           
			   asig = gestionAsignatura.buscarAsignatura(referencia);
	    
	        } catch (AsignaturaNoEncontradaException e) {
	            System.out.println("Asignatura no encontrada");
	        }
	        return asig;
	    }
	  
	  public List<Asignatura> buscarAsignaturaPorTitulacion(int referencia) throws NeuBDExceptions {
		  List<Asignatura> listAsig=null; 
		  
		  try {
	           listAsig = gestionAsignatura.buscarAsignaturaPorTitulacion(referencia);
	    
	        } catch (AsignaturaNoEncontradaException e) {
	            System.out.println("Asignatura no encontrada");
	        }
	        return listAsig;
	    }
	  
		
		public void ImportarAsignatura(Asignatura asignatura) throws NeuBDExceptions {
			
			  
			  try {
		           gestionAsignatura.ImportarAsignatura(asignatura);
		    
		        } catch (AsignaturaNoEncontradaException e) {
		            System.out.println("Asignatura no encontrada");
		        }
		}
	  
	  
		
		public List<Asignatura> listaAsignatura() throws NeuBDExceptions {
			return gestionAsignatura.listaAsignatura();
		}
	
}
