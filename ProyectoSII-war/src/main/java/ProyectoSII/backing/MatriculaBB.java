package ProyectoSII.backing;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.TitulacionBB.Modo;


@Named(value = "matricula")
@RequestScoped
public class MatriculaBB {

	public static enum Modo{
		MODIFICAR,
		ELIMINAR,
		NOACCION
	}

	private Matricula matricula;
	private Modo modo;
	private List<Matricula> listamatricula;
    private List<Matricula> matriculaFiltro;
    private List<FilterMeta> filterBy;
	

	@Inject
	private GestionMatricula gestionMatricula;
	private List<Matricula> listaMatriculas;

	public MatriculaBB() {
		matricula = new Matricula();
		modo = Modo.NOACCION;
	}
	
	@PostConstruct
	public void init() throws NeuBDExceptions {
		listaMatriculas = gestionMatricula.listaMatricula();
    }
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        
        Integer filterInt = getint(filterText);
        
        Matricula m = (Matricula) value;
        
        return //m.getmatricula()
        		 m.getCurso_academico() == filterText
        		|| m.getEstado() == filterText
        		|| m.getNum_archivo() == filterInt
        		|| m.getTurno_preferente() == filterText
        		//|| m.getFecha_matricula() == filterDate 
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
    
  
	

	public List<Matricula> getListamatricula() {
		return listamatricula;
	}

	public void setListamatricula(List<Matricula> listamatricula) {
		this.listamatricula = listamatricula;
	}

	public List<Matricula> getmatriculaFiltro() {
		return matriculaFiltro;
	}

	public void setmatriculaFiltro(List<Matricula> matriculaFiltro) {
		this.matriculaFiltro = matriculaFiltro;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public GestionMatricula getGestionMatricula() {
		return gestionMatricula;
	}

	public void setGestionMatricula(GestionMatricula gestionMatricula) {
		this.gestionMatricula = gestionMatricula;
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

	public String modificar(Matricula m) {
		matricula = m;
		setModo(Modo.MODIFICAR);
		return "edicionMatricula.xhtml";
	}


	public String ejecutarAccion() {
		try {
			switch (modo) {
			case MODIFICAR:
				gestionMatricula.modificarMatricula(matricula);
				break;
			case ELIMINAR:
				gestionMatricula.eliminarMatricula(matricula);
				break;
			}

			return "edicionMatricula.xhtml";
		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
	}

	public String eliminarMatricula(Matricula m) throws NeuBDExceptions{
		try {
			gestionMatricula.eliminarMatricula(m);

		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
		return null;

	}

	public Matricula visualizarMatricula(Matricula mat) throws NeuBDExceptions{
		Matricula m=null;

		try {

			m = gestionMatricula.visualizarMatricula(mat);

		} catch (NeuBDExceptions e) {
			System.out.println("Matricula no encontrada");
		}
		return m;

	}


	public List<Matricula> listaMatricula() throws NeuBDExceptions {
		return gestionMatricula.listaMatricula();
	}

	public void anyadirMatricula(Matricula m) throws NeuBDExceptions {

		try {
			gestionMatricula.anyadirMatricula(m);

		} catch (NeuBDExceptions e) {
			System.out.println("Matricula no encontrada");
		}
	}



}
