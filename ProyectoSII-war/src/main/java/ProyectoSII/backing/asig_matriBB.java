package ProyectoSII.backing;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSIIEjb.GestionAsigMatri;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionGrupoPorAsignatura;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "asig_matri")
@ViewScoped
public class asig_matriBB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Modo {
        MODIFICAR, 
        NOACCION
    };
	
    @Inject
    GestionAsigMatri gestionAsigMatri;
    @Inject
    GestionAsignatura gestionAsignatura;
    @Inject
    GestionMatricula gestionMatricula;
    @Inject
    GestionGrupo gestionGrupo;
    
    private List<Asignatura_matricula> listaAsigMatri;
    private List<Asignatura_matricula> asigMatriFiltro;
    private List<FilterMeta> filterBy;
    private Modo modo;
    private Asignatura_matricula asignatura_matricula;
    private Asignatura a;
    private Grupo gr;
    private Matricula ma;
    
    @PostConstruct
    public void init() throws NeuBDExceptions {
        listaAsigMatri = gestionAsigMatri.listaAsigMatri();
    }
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        Boolean filterBoolean = getBoolean(filterText);
        
        Asignatura_matricula g = (Asignatura_matricula) value;
        
        return g.getGrupo().toString().toLowerCase().contains(filterText) 
        		|| g.getAsignatura().getNombre().toLowerCase().contains(filterText)
        		|| g.getMatricula().getCurso_academico().toLowerCase().contains(filterText)
        		|| g.isIdioma() == filterBoolean
        		|| g.isAsignacionManual() == filterBoolean;
    }
    
    private Boolean getBoolean(String string) {
        try {
            return Boolean.getBoolean(string);
        }
        catch (Exception e) {
            return null;
        }
    }
	
	 public Modo getModo() {
	        return modo;
	 }
	 
	 public void setModo(Modo modo) {
	        this.modo = modo;
	 }
	  
	 public String getAccion() throws NeuBDExceptions{
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
	 
	 public Asignatura getA() {
		return a;
	}

	public void setA(Asignatura a) {
		this.a = a;
	}

	public Grupo getGr() {
		return gr;
	}

	public void setGr(Grupo gr) {
		this.gr = gr;
	}

	public Matricula getMa() {
		return ma;
	}

	public void setMa(Matricula ma) {
		this.ma = ma;
	}
	
	

	public List<Asignatura_matricula> getListaAsigMatri() {
		return listaAsigMatri;
	}

	public void setListaAsigMatri(List<Asignatura_matricula> listaAsigMatri) {
		this.listaAsigMatri = listaAsigMatri;
	}

	public List<Asignatura_matricula> getasigMatriFiltro() {
		return asigMatriFiltro;
	}

	public void setasigMatriFiltro(List<Asignatura_matricula> asigMatriFiltro) {
		this.asigMatriFiltro = asigMatriFiltro;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public String modificar(Asignatura_matricula asig) throws NeuBDExceptions{
		asignatura_matricula = asig;
		a=gestionAsignatura.buscarAsignatura(asig.getAsignatura().getReferencia());
		gr = gestionGrupo.visualizarGrupo(asig.getGrupo().getId());
		ma = gestionMatricula.visualizarMatricula(asig.getMatricula());
	    setModo(Modo.MODIFICAR);
	    return "edicionAsignatura_matriculas.xhtml";
	}
	  
	  
	  public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                	asignatura_matricula.setAsigantura(a);
	                	asignatura_matricula.setGrupo(gr);
	                	asignatura_matricula.setMatricula(ma);
	                    gestionAsigMatri.modificarAsigMatri(asignatura_matricula);
	                    break;
	             
	            }
	           
	            return "Asignatura_matricula.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }

		public List<Asignatura_matricula> listaAsignatura_matricula() throws NeuBDExceptions {
			return gestionAsigMatri.listaAsigMatri();
			
		}
		
		public void onRowEdit(RowEditEvent<Asignatura_matricula> event) throws NeuBDExceptions {
			asignatura_matricula  = event.getObject();
			gestionAsigMatri.modificarAsigMatri(asignatura_matricula);;
	    }
		
		public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();

	        if (newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	    }
}
