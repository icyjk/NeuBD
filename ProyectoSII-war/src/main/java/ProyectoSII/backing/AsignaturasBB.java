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
	
    
    private List<Asignatura> listaasignaturas;
    private List<Asignatura> asignaturafiltro;
    private List<FilterMeta> filterBy;
    private Asignatura asignatura;
    private Modo modo;
    @Inject
    private GestionAsignatura gestionAsignatura;
    
    
    @PostConstruct
    public void init() throws NeuBDExceptions {
		listaasignaturas = gestionAsignatura.listaAsignatura();
    }
    
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        Boolean filterBoolean = getBoolean(filterText);
        Long filterlong = getlong(filterText);
        Double filterdouble = getdouble(filterText);
        Integer filterInt = getint(filterText);
        
        Asignatura e = (Asignatura) value;
        
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
        		|| e.getIdioma_imparticion()==filterText;

    }
    
 


	public List<Asignatura> getListaasignaturas() {
		return listaasignaturas;
	}


	public void setListaasignaturas(List<Asignatura> listaasignaturas) {
		this.listaasignaturas = listaasignaturas;
	}


	public List<Asignatura> getAsignaturafiltro() {
		return asignaturafiltro;
	}


	public void setAsignaturafiltro(List<Asignatura> asignaturafiltro) {
		this.asignaturafiltro = asignaturafiltro;
	}


	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}


	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}


	public GestionAsignatura getGestionAsignatura() {
		return gestionAsignatura;
	}


	public void setGestionAsignatura(GestionAsignatura gestionAsignatura) {
		this.gestionAsignatura = gestionAsignatura;
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
    
    private int getint(String string) {
        try {
            return Integer.parseInt(string);
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
