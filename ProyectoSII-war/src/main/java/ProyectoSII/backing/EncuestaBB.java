package ProyectoSII.backing;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionEncuesta;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "encuesta")
@RequestScoped
public class EncuestaBB {

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
	
    private List<Encuesta> listaEncuesta;
    private List<Encuesta> encuestaFiltro;
    private List<FilterMeta> filterBy;
    private Encuesta encuesta;
    private Modo modo;
    @Inject
    private GestionEncuesta gestionEncuesta;

    
    
	public EncuestaBB() {
		encuesta= new Encuesta();
		modo=Modo.NOACCION;
	}
	
	@PostConstruct
    public void init() throws NeuBDExceptions {
		listaEncuesta = gestionEncuesta.listaEncuestas();
    }
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
       
        Long filterlong = getlong(filterText);
        Encuesta e = (Encuesta) value;
        
        return e.getFecha_de_envio().toString().toLowerCase().contains(filterText)
        		|| e.getExpedientes().getNum_expediente()== filterlong;
        		

    }
    
	private long getlong(String string) {
        try {
            return Long.parseLong(string);
        }
        catch (Exception e) {
            return 0;
        }
    }
	
	
	
	 public List<Encuesta> getListaEncuesta() {
		return listaEncuesta;
	}

	public void setListaEncuesta(List<Encuesta> listaEncuesta) {
		this.listaEncuesta = listaEncuesta;
	}

	public List<Encuesta> getEncuestaFiltro() {
		return encuestaFiltro;
	}

	public void setEncuestaFiltro(List<Encuesta> encuestaFiltro) {
		this.encuestaFiltro = encuestaFiltro;
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
	            case ELIMINAR:
	                return "Eliminar";

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

		
		public List<Encuesta> listaEncuesta() throws NeuBDExceptions {
			return gestionEncuesta.listaEncuestas();
		}
		
	
}

