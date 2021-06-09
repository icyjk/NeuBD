package ProyectoSII.backing;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.MatriculaBB.Modo;

@Named(value = "alumno")
@RequestScoped
public class AlumnoBB {

	public static enum Modo{
		MODIFICAR,
		ELIMINAR,
		NOACCION
	}
	
	private Alumno alumno;
	private Modo modo;
	private List<Alumno> listaAlumnos; 
	private List<Expedientes> exp;

	private List<Alumno> alumnoFiltro;
    private List<FilterMeta> filterBy;
	@Inject
	private GestionAlumno gestionAlumno;
	
	@Inject
	GestionExpediente gestionExpediente;
	
	private AlumnoBB() {
		alumno = new Alumno();
		modo = Modo.NOACCION;
	}
	@PostConstruct
	public void init() throws NeuBDExceptions {
		listaAlumnos = gestionAlumno.listaAlumno();
    }
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        
        Integer filterInt = getint(filterText);
        
        Alumno a = (Alumno) value;
        
        return a.getId() == filterInt
        		||a.getDni() == filterText
        		|| a.getNombre()==filterText
        		|| a.getPrimer_apellido() == filterText
        		|| a.getSegundo_apellido() == filterText
        		|| a.getEmail_personal() == filterText
        		|| a.getEmail_institucional() == filterText
        		|| a.getMovil() == filterText
        		|| a.getTelefono()== filterText
        		|| a.getDireccion() == filterText
        		|| a.getLocalidad() == filterText
        		|| a.getProvincia() == filterText
        		|| a.getCp() == filterText;
    }
	
    
	private Integer getint (String string) {
    	try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			return 0;
		}
    }
	
	
	
	public GestionExpediente getGestionExpediente() {
		return gestionExpediente;
	}
	public void setGestionExpediente(GestionExpediente gestionExpediente) {
		this.gestionExpediente = gestionExpediente;
	}
	
    public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	public List<Alumno> getAlumnoFiltro() {
		return alumnoFiltro;
	}
	public void setAlumnoFiltro(List<Alumno> alumnoFiltro) {
		this.alumnoFiltro = alumnoFiltro;
	}
	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
	}

	public GestionAlumno getGestionAlumno() {
		return gestionAlumno;
	}

	public void setGestionAlumno(GestionAlumno gestionAlumno) {
		this.gestionAlumno = gestionAlumno;
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

	public String modificar(Alumno a) {
		alumno = a;
	
		setModo(Modo.MODIFICAR);
		return "edicionAlumno.xhtml";
	}


	public String ejecutarAccion() {
		try {
			switch (modo) {
			case MODIFICAR:
				alumno.setExpedientes(exp);
				gestionAlumno.modificarAlumno(alumno);
				break;
				
			case ELIMINAR:
				gestionAlumno.eliminarAlumno(alumno.getId());
				break;
			}

			return "Alumnos.xhtml";
		} catch (NeuBDExceptions e) {
			return "index.xhtml";
		}
	}
	
	
	
	
	public List<Alumno> listaAlumnos() throws NeuBDExceptions {
		return gestionAlumno.listaAlumno();
	}

	
	
	public String eliminar(int id) throws NeuBDExceptions {
        try {
            gestionAlumno.eliminarAlumno(id);
        } catch (NeuBDExceptions e) {
            return "index.xhtml";
        }
        return "index.xhtml";
    }
 
	
	
	public void onRowEdit(RowEditEvent<Alumno> event) throws NeuBDExceptions {
		alumno  = event.getObject();
		gestionAlumno.modificarAlumno(alumno);
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
