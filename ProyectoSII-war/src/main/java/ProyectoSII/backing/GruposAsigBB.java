package ProyectoSII.backing;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionGrupoPorAsignatura;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.AsignaturasBB.Modo;

@Named(value = "gruposAsig")
@RequestScoped
public class GruposAsigBB {
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
    
    
    private Modo modo;
    private Grupos_por_asignatura gpa;
    private List<Grupos_por_asignatura> lista;
    private List<Grupos_por_asignatura> listaFiltrado;
	public GruposAsigBB(){
		gpa= new Grupos_por_asignatura();
		modo=Modo.NOACCION;
	}
    public Modo getModo() {
		return modo;
	}
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public Grupos_por_asignatura getGpa() {
		return gpa;
	}
	public void setGpa(Grupos_por_asignatura gpa) {
		this.gpa = gpa;
	}
	public List<Grupos_por_asignatura> getLista() {
		return lista;
	}
	public void setLista(List<Grupos_por_asignatura> lista) {
		this.lista = lista;
	}
	public List<Grupos_por_asignatura> getListaFiltrado() {
		return listaFiltrado;
	}
	public void setListaFiltrado(List<Grupos_por_asignatura> listaFiltrado) {
		this.listaFiltrado = listaFiltrado;
	}
	public String getAccion() throws NeuBDExceptions {
        switch (modo) {
            case MODIFICAR:
            	gestionGrupoAsig.modificarGruposPorAsignatura(gpa);
                return "Modificar";
            case ELIMINAR:
                return "Eliminar";

        }
        return null;
	}
	public String eliminar(Grupos_por_asignatura g) throws NeuBDExceptions {
        try {
            gestionGrupoAsig.eliminarGruposPorAsignatura(g);
    
        } catch (NeuBDExceptions e) {
            return "index.xhtml";
        }
        return null;
    }
	 public String modificar(Grupos_por_asignatura g) {
	        gpa = g;
	        setModo(Modo.MODIFICAR);
	        return "edicionTitulacion.xhtml";
	    }
	
	public List<Grupos_por_asignatura> listaGrupoPorAsignatura() throws NeuBDExceptions {
		lista = gestionGrupoAsig.listaGruposPorAsignatura();
		return gestionGrupoAsig.listaGruposPorAsignatura();
	}
    
    
    
}
