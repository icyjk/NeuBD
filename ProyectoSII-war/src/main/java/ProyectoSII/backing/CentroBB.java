package ProyectoSII.backing;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Expedientes;
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
	
    private List<Centro> listacentros;
    private List<Centro> centrosFiltro;
    private List<FilterMeta> filterBy;
    private Centro centro;
    private Modo modo;
    @Inject
    private GestionCentro gestionCentro;
    
    
	public CentroBB() {
		centro= new Centro();
		modo=Modo.NOACCION;
	}
	
	@PostConstruct
    public void init() throws NeuBDExceptions {
		listacentros = gestionCentro.buscarTodosCentros();
    }
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        Integer filterInteger = getInteger(filterText);
        
        Centro c = (Centro) value;
        
        return c.getId() == filterInteger
        	   || c.getNombre().toString().toLowerCase().contains(filterText)
        	   || c.getDireccion().toString().toLowerCase().contains(filterText)
        	   || c.getTlf_conserjeria().toString().toLowerCase().contains(filterText);

    }	
    
    private Integer getInteger(String string) {
        try {
            return Integer.getInteger(string);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    
	
	 public List<Centro> getListacentros() {
		return listacentros;
	}

	public void setListacentros(List<Centro> listacentros) {
		this.listacentros = listacentros;
	}

	public List<Centro> getCentrosFiltro() {
		return centrosFiltro;
	}

	public void setCentrosFiltro(List<Centro> centrosFiltro) {
		this.centrosFiltro = centrosFiltro;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
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
	           
	            return "index.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	
	 
	  public Centro buscarCentro(int id) throws NeuBDExceptions {
		  Centro enc=null;
		  
		  try {
	           
			   enc = gestionCentro.buscarCentro(id);
	    
	        } catch (NeuBDExceptions e) {
	            System.out.println("Centro no encontrada");
	        }
	        return enc;
	    }

	  
	  
	  public String crearCentro() {
	        setModo(Modo.CREAR);
	        return "edicionCentro.xhtml";
	    }
	  
	  public void eliminarCentro(Centro c) throws NeuBDExceptions {
		  gestionCentro.eliminarCentro(c);
	        
	    }
	  

		
		public List<Centro> listaCentro() throws NeuBDExceptions {
			return gestionCentro.buscarTodosCentros();
		}
	
}
