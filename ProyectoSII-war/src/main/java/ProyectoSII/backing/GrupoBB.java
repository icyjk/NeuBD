package ProyectoSII.backing;

import java.util.ArrayList;
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
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.GrupoNoEncontrado;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Named(value = "grupo")
@RequestScoped
public class GrupoBB {
	public static enum Modo {
		MODIFICAR, 
		ELIMINAR,
		CREAR,
		NOACCION
	};

	 private List<Grupo> listagrupos;
	 private List<Grupo> grupofiltro;
	 private List<FilterMeta> filterBy;

	private Grupo grupo;
	private Modo modo;
	@Inject
	private GestionGrupo gestionGrupo;
	@Inject 
	private GestionTitulacion gestionTitulacion;
	private int refTit;

	public GrupoBB() {
		grupo= new Grupo();
		modo=Modo.NOACCION;
	}

	
	 @PostConstruct
	    public void init() throws NeuBDExceptions {
			listagrupos = gestionGrupo.listaGrupos();
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
	        
	        Grupo e = (Grupo) value;
	        
	        return e.getId() == filterInt
	        		|| e.getCurso() ==filterInt
	        		|| Character.toString(e.getLetra())  ==filterText
	        		|| e.getTurno_mañana_tarde()==filterText
	        		|| e.isIngles()==filterBoolean
	        		|| e.getVisible() == filterBoolean
	        		|| e.getAsignar() == filterText
	        		|| e.getPlazas() == filterInt
	        		|| e.getTitulacion().getCodigo() ==filterInt;
	        		
	        		

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
	    
	 

	
	
	
	public List<Grupo> getListagrupos() {
		return listagrupos;
	}




	public void setListagrupos(List<Grupo> listagrupos) {
		this.listagrupos = listagrupos;
	}




	public List<Grupo> getGrupofiltro() {
		return grupofiltro;
	}




	public void setGrupofiltro(List<Grupo> grupofiltro) {
		this.grupofiltro = grupofiltro;
	}




	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}




	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}




	public GestionGrupo getGestionGrupo() {
		return gestionGrupo;
	}




	public void setGestionGrupo(GestionGrupo gestionGrupo) {
		this.gestionGrupo = gestionGrupo;
	}




	public GestionTitulacion getGestionTitulacion() {
		return gestionTitulacion;
	}




	public void setGestionTitulacion(GestionTitulacion gestionTitulacion) {
		this.gestionTitulacion = gestionTitulacion;
	}




	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
	}

	
	public int getRefTit() {
		return refTit;
	}

	public void setRefTit(int refTit) {
		this.refTit = refTit;
	}

	public String getAccion() {
		switch (modo) {
		case MODIFICAR:
			return "Modificar";
		case ELIMINAR:
			return "Eliminar";
		case CREAR:
			return "Crear";
		}
		return null;
	}

	public String modificar(Grupo g) {
		grupo = g;
		setModo(Modo.MODIFICAR);
		return "edicionGrupos.xhtml";
	}

	public String ejecutarAccion() {
		try {
			Titulacion tit = null;
			switch (modo) {
			case MODIFICAR:
				
				gestionGrupo.modificarGrupo(grupo);
				break;
			case ELIMINAR:
				gestionGrupo.eliminarGrupo(grupo.getId());
				break;

			case CREAR:
				Titulacion aux = gestionTitulacion.visualizartitulacion(refTit);
				grupo.setTitulacion(aux);
				gestionGrupo.crearGrupo(grupo);

			}

			return "edicionGrupos.xhtml";
		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
	}

	public String eliminar(Grupo g) throws NeuBDExceptions {
		try {
			gestionGrupo.eliminarGrupo(g.getId());

		} catch (GrupoNoEncontrado e) {
			return "index.xhtml";
		}
		return "index.xhtml";
	}

	public Grupo visualizarGrupo(Grupo g) throws NeuBDExceptions {
		Grupo gr=null;

		try {

			gr = gestionGrupo.visualizarGrupo(g.getId());

		} catch (GrupoNoEncontrado e) {
			System.out.println("Grupo no encontrado");
		}
		return gr;
	}

	public String crearGrupo() {
		setModo(Modo.CREAR);
		return "edicionGrupos.xhtml";
	}


	public void asociarGrupo(Grupo g1, Grupo g2) throws NeuBDExceptions {

		try {
			gestionGrupo.asociarGrupo(g1, g2);
		} catch (GrupoNoEncontrado e) {
			// TODO: handle exception
			System.out.println("Grupo no encontrado");
		}
	}


	public List<Grupo> listaGrupo() throws NeuBDExceptions {
		
		List<Grupo> lista = new ArrayList<Grupo>();
		
		lista=gestionGrupo.listaGrupos();
		return lista ;
	}



}




























