package ProyectoSII.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionGrupoPorAsignatura;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "gruposAsig")
@ViewScoped
public class GruposAsigBB implements Serializable{
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
    GestionGrupoPorAsignatura gestionGrupoAsig;
    @Inject
    GestionGrupo gestionGrupo;
    @Inject
    GestionAsignatura gestionAsignatura;
    
    private List<Grupos_por_asignatura> listaGrupoPorAsignatura;
    private List<Grupos_por_asignatura> grupoAsifFiltro;
    private List<FilterMeta> filterBy;
    private Modo modo;
    private Grupos_por_asignatura gpa;
    private Grupo gr;
    private Asignatura a;
	
    @PostConstruct
    public void init() throws NeuBDExceptions {
        listaGrupoPorAsignatura = gestionGrupoAsig.listaGruposPorAsignatura();
    }
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        Boolean filterBoolean = getBoolean(filterText);
        
        Grupos_por_asignatura g = (Grupos_por_asignatura) value;
        
        return g.getGrupo().toString().toLowerCase().contains(filterText) 
        		|| g.getAsignatura().getNombre().toLowerCase().contains(filterText)
        		|| g.getCurso_academico().toLowerCase().contains(filterText)
        		|| g.getOferta() == filterBoolean;
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
  	
    public String getAccion() throws NeuBDExceptions {
        switch (modo) {
            case MODIFICAR:
                return "Modificar";
            case ELIMINAR:
                return "Eliminar";
        }
        return null;
	}
    
  
	public Grupos_por_asignatura getGpa() {
		return gpa;
	}
	public void setGpa(Grupos_por_asignatura gpa) {
		this.gpa = gpa;
	}
	
	
	public Grupo getGr() {
		return gr;
	}
	public void setGr(Grupo gr) {
		this.gr = gr;
	}
	public Asignatura getA() {
		return a;
	}
	public void setA(Asignatura a) {
		this.a = a;
	}
	
	public List<Grupos_por_asignatura> getListaGrupoPorAsignatura() {
		return listaGrupoPorAsignatura;
	}
	public void setListaGrupoPorAsignatura(List<Grupos_por_asignatura> listaGrupoPorAsignatura) {
		this.listaGrupoPorAsignatura = listaGrupoPorAsignatura;
	}
	
	public List<Grupos_por_asignatura> getGrupoAsifFiltro() {
		return grupoAsifFiltro;
	}
	public void setGrupoAsifFiltro(List<Grupos_por_asignatura> grupoAsifFiltro) {
		this.grupoAsifFiltro = grupoAsifFiltro;
	}
	
	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public String modificar(Grupos_por_asignatura g) throws NeuBDExceptions {
	        gpa = g;
	        a = gestionAsignatura.buscarAsignatura(g.getAsignatura().getReferencia());
	        gr = gestionGrupo.visualizarGrupo(g.getGrupo().getId());
	        setModo(Modo.MODIFICAR);
	        return "edicionGrupoAsignatura.xhtml";
	    }
	 public String ejecutarAccion() {
	        try {
	            switch (modo) {
	                case MODIFICAR:
	                    gpa.setAsignatura(a);gpa.setGrupo(gr);
	                	gestionGrupoAsig.modificarGruposPorAsignatura(gpa);
	                    break;
	            }   
	            return "grupoAsig.xhtml";
	        } catch (NeuBDExceptions e) {
	            return "index.xhtml";
	        }
	    }
	
	public String eliminar(Grupos_por_asignatura g) throws NeuBDExceptions {
        try {
            gestionGrupoAsig.eliminarGruposPorAsignatura(g);
    
        } catch (NeuBDExceptions e) {
            return "index.xhtml";
        }
        return null;
    }
	
	
	public List<Grupos_por_asignatura> listaGrupoPorAsignatura() throws NeuBDExceptions {
		return gestionGrupoAsig.listaGruposPorAsignatura();
	}
    
    
    
}
