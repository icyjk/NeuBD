package ProyectoSII.backing;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "Titulacion")
@RequestScoped
public class TitulacionBB {

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
	
    private List<Titulacion> listatitulaciones;
    private List<Titulacion> titulacionFiltro;
    private List<Titulacion> filterBy;
    private  Titulacion titulacion;
    private Modo modo;
    @Inject
    GestionTitulacion gestionTitulacion;
    
    
	public TitulacionBB() {
		titulacion= new Titulacion();
		modo=Modo.NOACCION;
	}
	
	@PostConstruct
    public void init() throws NeuBDExceptions {
		listatitulaciones = gestionTitulacion.listaTitulacion();
    }
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
       
        Integer filterInteger= getInteger(filterText);
        
        Titulacion t = (Titulacion) value;
        
        return t.getCodigo() == filterInteger
        		|| t.getNombre().toString().toLowerCase().contains(filterText)
        		|| t.getCreditos() == filterInteger;

    }
    
    private Integer getInteger(String string) {
        try {
            return Integer.getInteger(string);
        }
        catch (Exception e) {
            return 0;
        }
    }
	
    
    
    
	 public List<Titulacion> getListatitulaciones() {
		return listatitulaciones;
	}

	public void setListatitulaciones(List<Titulacion> listatitulaciones) {
		this.listatitulaciones = listatitulaciones;
	}

	public List<Titulacion> getTitulacionFiltro() {
		return titulacionFiltro;
	}

	public void setTitulacionFiltro(List<Titulacion> titulacionFiltro) {
		this.titulacionFiltro = titulacionFiltro;
	}

	public List<Titulacion> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<Titulacion> filterBy) {
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
	            case ELIMINAR:
	                return "Eliminar";

	        }
	        return null;
	 }
	 
	 public Titulacion getTitulacion() {
	        return titulacion;
	    }

	  public void setTitulacion(Titulacion t) {
	        this.titulacion = t;
	    }
	  
	  
	  

	  
	  public String modificar(Titulacion t) {
	        titulacion = t;
	        setModo(Modo.MODIFICAR);
	        return "edicionTitulacion.xhtml";
	    }
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionTitulacion.modificarTitulacion(titulacion);
	                    break;
	                case ELIMINAR:
	                    gestionTitulacion.eliminarTitulacion(titulacion.getCodigo());
	                    break;
	            }
	           
	            return "edicionTitulacions.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	  public String eliminar(Titulacion t) throws NeuBDExceptions {
	        try {
	            gestionTitulacion.eliminarTitulacion(t.getCodigo());
	    
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	        return null;
	    }
	 
	  public Titulacion visualizarTitulacion(Titulacion tit) throws NeuBDExceptions {
		  Titulacion t=null;
		  
		  try {
	           
			   t = gestionTitulacion.visualizartitulacion(titulacion.getCodigo());
	    
	        } catch (NeuBDExceptions e) {
	            System.out.println("Titulacion no encontrada");
	        }
	        return t;
	    }
	  
	  
	  
		
		public void ImportarTitulacion(Titulacion Titulacion) throws NeuBDExceptions {
			
			  
			  try {
		           gestionTitulacion.ImportarTitulacion(Titulacion);
		    
		        } catch (NeuBDExceptions e) {
		            System.out.println("Titulacion no encontrada");
		        }
		}
	  
	  
		
		public List<Titulacion> listaTitulacion() throws NeuBDExceptions {
			return gestionTitulacion.listaTitulacion();
		}
	
}
