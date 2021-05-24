package ProyectoSII.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSIIEjb.GestionEncuesta;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.EncuestaNoEncontradaException;
import NeuBDProyectoSIIexceptions.GrupoNoEncontrado;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.EncuestaBB.Modo;

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
			switch (modo) {
			case MODIFICAR:

				gestionGrupo.modificarGrupo(grupo);
				break;
			case ELIMINAR:
				gestionGrupo.eliminarGrupo(grupo.getId());
				break;

			case CREAR:
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
		return gestionGrupo.listaGrupos();
	}



}




























