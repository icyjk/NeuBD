package ProyectoSII.backing;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionGrupoPorAsignatura;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "matricula")
@ViewScoped
public class MatriculaBB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static enum Modo {
        MODIFICAR, 
        ELIMINAR,
        NOACCION
    };
    
    @Inject
    GestionMatricula gestionMatricula;
    @Inject
    GestionExpediente gestionExpediente;
 
    
    private List<Matricula> listaMatricula;
    private List<Matricula> matriculaFiltro;
    private List<FilterMeta> filterBy;
    private Modo modo;
    private Matricula matri;
    
	
    @PostConstruct
    public void init() throws NeuBDExceptions {
        listaMatricula = gestionMatricula.listaMatricula();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
    	String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
    	if (LangUtils.isValueBlank(filterText)) {
    		return true;
    	}

    	Integer filterInt = getint(filterText);

    	Matricula m = (Matricula) value;

    	return 
    			m.getCurso_academico() == filterText
    			|| m.getEstado() == filterText
    			||  m.getNum_archivo() == filterInt
    			||m.getTurno_preferente() == filterText 
    			|| m.getNuevo_ingreso() == filterText
    			|| m.getListado_asignaturas() == filterText;

    }


        private Integer getint (String string) {
            try {
                return Integer.parseInt(string);
            } catch (Exception e) {
                return 0;
            }
        }

  
    
    public Modo getModo() {
  		return modo;
  	}
  	public void setModo(Modo modo) {
  		this.modo = modo;
  	}
  	
    
  
	

	public GestionMatricula getGestionMatricula() {
		return gestionMatricula;
	}
	public void setGestionMatricula(GestionMatricula gestionMatricula) {
		this.gestionMatricula = gestionMatricula;
	}
	public List<Matricula> getListaMatricula() {
		return listaMatricula;
	}
	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}
	public List<Matricula> getMatriculaFiltro() {
		return matriculaFiltro;
	}
	public void setMatriculaFiltro(List<Matricula> matriculaFiltro) {
		this.matriculaFiltro = matriculaFiltro;
	}
	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}
	public Matricula getmatri() {
		return matri;
	}
	public void setmatri(Matricula matri) {
		this.matri = matri;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAccion() throws NeuBDExceptions {
        switch (modo) {
            case MODIFICAR:
                return "Modificar";
            case ELIMINAR:
                return "Eliminar";
        }
        return null;
	}
    
	
	public String modificar(Matricula m) throws NeuBDExceptions {
	        matri = m;
	        setModo(Modo.MODIFICAR);
	        return "edicionMatricula.xhtml";
	    }
	 public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    
	               	 	gestionMatricula.modificarMatricula(matri);
	                    break;
	                case ELIMINAR:
	    				gestionMatricula.eliminarMatricula(matri);
	    				break;
	            }   
	            return "Matricula.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	
	public String eliminar(Matricula m) throws NeuBDExceptions {
        try {
            gestionMatricula.eliminarMatricula(m);
    
        } catch (NeuBDExceptions e) {
            return "index.xhtml";
        }
        return null;
    }
	
	
	public List<Matricula> listaMatricula() throws NeuBDExceptions {
		
		return gestionMatricula.listaMatricula();
	}
	public void onRowEdit(RowEditEvent<Matricula> event) throws NeuBDExceptions {
		matri  = event.getObject();
		gestionMatricula.modificarMatricula(matri);
    }

    public void onRowCancel(RowEditEvent<Matricula> event) {
        
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