package ProyectoSII.backing;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIEjb.GestionOptativa;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "optativas")
@RequestScoped
public class OptativasBB {

	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
	
    
    private List<Optativa> listaoptativas;
    private List<Optativa> OptativaFiltro;
    private List<FilterMeta> filterBy;
    private Optativa optativa;
    private Modo modo;
    @Inject
    private GestionOptativa gestionOptativa;
    
    
    @PostConstruct
    public void init() throws NeuBDExceptions {
		listaoptativas = gestionOptativa.listaOptativa();
    }
    
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
      
        Integer filterInt = getint(filterText);
        
        Optativa e = (Optativa) value;
        
        return e.getReferencia() == filterInt
        		|| e.getCodigo() == filterInt
        		|| e.getNombre() == filterText
        		|| e.getCreditos()== filterInt
        		|| e.getCreditos_practica() == filterInt
        		|| e.getCreditos_teoria() == filterInt
        		|| e.getCurso() == filterInt
        		|| e.getCaracter() == filterText
        		|| e.getDuracion() == filterInt
        		|| e.getUnidad_temporal() == filterText
        		|| e.getIdioma_imparticion()==filterText
        		|| e.getMencion()==filterText
        		||e.getTitulacion().getNombre()==filterText
        		||e.getPlazas()==filterInt;

    }
    
   

	public List<Optativa> getListaoptativas() {
		return listaoptativas;
	}


	public void setListaoptativas(List<Optativa> listaoptativas) {
		this.listaoptativas = listaoptativas;
	}


	public List<Optativa> getOptativaFiltro() {
		return OptativaFiltro;
	}


	public void setOptativaFiltro(List<Optativa> optativaFiltro) {
		OptativaFiltro = optativaFiltro;
	}


	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}


	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}


	public GestionOptativa getGestionOptativa() {
		return gestionOptativa;
	}


	public void setGestionOptativa(GestionOptativa gestionOptativa) {
		this.gestionOptativa = gestionOptativa;
	}


	
    private int getint(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    
   
    
	public OptativasBB() {
		optativa= new Optativa();
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
	 
	 public Optativa getOptativa() {
	        return optativa;
	    }

	  public void setOptativa(Optativa opt) {
	        this.optativa = opt;
	    }
	  
	  
	  

	  
	  public String modificar(Optativa opt) {
	        optativa = opt;
	        setModo(Modo.MODIFICAR);
	        return "edicionOptativa.xhtml";
	    }
	 
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	                    gestionOptativa.modificarOptativa(optativa);
	                    break;	  
	                   
	                 
	            }
	           
	            return "edicionOpativa.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	  
	  
	  public String eliminar(Optativa opt) throws NeuBDExceptions {
	        try {
	            gestionOptativa.eliminaOptativa(opt);	    
	        } catch (AsignaturaNoEncontradaException e) {
	            return "index.xhtml";
	        }
	        return "index.xhtml";
	    }
	 
	  
	  
	
	  
		
		public List<Optativa> listaOptativa() throws NeuBDExceptions {
			return gestionOptativa.listaOptativa();
		}
	
}