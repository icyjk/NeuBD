package ProyectoSII.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
				tit = gestionTitulacion.visualizartitulacion(refTit);
				grupo.setTitulacion(tit);
				gestionGrupo.modificarGrupo(grupo);
				break;
			case ELIMINAR:
				gestionGrupo.eliminarGrupo(grupo.getId());
				break;

			case CREAR:
				tit = gestionTitulacion.visualizartitulacion(refTit);
				grupo.setTitulacion(tit);
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
		return null;
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




























