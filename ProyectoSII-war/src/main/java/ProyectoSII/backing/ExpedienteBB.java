package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSIIEjb.ExpedienteEJB;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "expediente")
@RequestScoped
public class ExpedienteBB {
	

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
    
   
    private Expedientes expediente;
    private Modo modo;
    @Inject
    private GestionExpediente gestionExpediente;
    
    
	public ExpedienteBB() {
		expediente= new Expedientes();
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
	 
	 public Expedientes getExpediente() {
	        return expediente;
	    }

	  public void setExpediente(Expedientes ex) {
	        this.expediente = ex;
	    }
	  
	  
	  

	  
	  public String modificar(Expedientes ex) {
	        expediente = ex;
	        setModo(Modo.MODIFICAR);
	        return "edicionExpediente.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionExpediente.modificarExpediente(expediente);
	                    break;
	                case ELIMINAR:
	                    gestionExpediente.eliminarExpediente(expediente);
	                    break;
	            }
	           
	            return "edicionExpedientes.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	  public String eliminar(Expedientes ex) throws NeuBDExceptions {
	        try {
	            gestionExpediente.eliminarExpediente(ex);
	    
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	        return null;
	    }
	 
	  public Expedientes buscarExpediente(int referencia) throws NeuBDExceptions {
		  Expedientes ex=null;
		  
		  try {
	           
			   ex = gestionExpediente.visualizarExpediente(ex);
	    
	        } catch (NeuBDExceptions e) {
	            System.out.println("Expediente no encontrada");
	        }
	        return ex;
	    }
	  
		public void ImportarExpediente(Expedientes ex) throws NeuBDExceptions {
			
			  
			  try {
		           gestionExpediente.importarExpediente(ex);
		        } catch (NeuBDExceptions e) {
		            System.out.println("Expediente no encontrada");
		        }
		}
	  
	  
		
		public List<Expedientes> listaExpediente() throws NeuBDExceptions {
			return gestionExpediente.listaExpedientes();
		}
	
}



