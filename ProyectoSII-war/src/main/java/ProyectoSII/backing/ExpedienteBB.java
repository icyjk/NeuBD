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
import NeuBDProyectoSII.Grupos_por_asignatura;
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
    
    private List<Expedientes> listaexpedientes;
    private List<Expedientes> expedientesFiltro;
    private List<FilterMeta> filterBy;
    private Expedientes expediente;
    private Modo modo;
    @Inject
    private GestionExpediente gestionExpediente;
    
    
	public ExpedienteBB() {
		expediente= new Expedientes();
		modo=Modo.NOACCION;
	}

	@PostConstruct
    public void init() throws NeuBDExceptions {
		listaexpedientes = gestionExpediente.listaExpedientes();
    }
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        Boolean filterBoolean = getBoolean(filterText);
        Long filterlong = getlong(filterText);
        Double filterdouble = getdouble(filterText);
        
        Expedientes e = (Expedientes) value;
        
        return e.getNum_expediente() == filterlong
        		|| e.isActivo() == filterBoolean
        		|| e.getNota_media_provisional() == filterdouble
        		|| e.getCreditos_superado() == filterdouble
        		|| e.getCredito_fb() == filterdouble
        		|| e.getCredito_ob() == filterdouble
        		|| e.getCredito_op() == filterdouble
        		|| e.getCredito_cf() == filterdouble
        		|| e.getCredito_pe() == filterdouble
        		|| e.getCredito_tf() == filterdouble; 

    }
    
    private Boolean getBoolean(String string) {
        try {
            return Boolean.getBoolean(string);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private long getlong(String string) {
        try {
            return Long.parseLong(string);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    private Double getdouble (String string) {
        try {
            return Double.parseDouble(string);
        }
        catch (Exception e) {
            return 0.0;
        }
    }
    
	public List<Expedientes> getlistaexpedientes() {
		return listaexpedientes;
	}
	public void setlistaexpedientes(List<Expedientes> listaexpedientes) {
		this.listaexpedientes = listaexpedientes;
	}
	
	public List<Expedientes> getexpedientesFiltro() {
		return expedientesFiltro;
	}
	public void setexpedientesFiltro(List<Expedientes> expedientesFiltro) {
		this.expedientesFiltro = expedientesFiltro;
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
	  
	  /*
	  public String eliminar(Expedientes ex) throws NeuBDExceptions {
	        try {
	            gestionExpediente.eliminarExpediente(ex);
	    
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	        return null;
	    }
	  */
		
		public List<Expedientes> listaExpediente() throws NeuBDExceptions {
			return gestionExpediente.listaExpedientes();
		}
	
}




