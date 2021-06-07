package NeuBDProyectoSIIEjb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Clase;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.NewId_Clase_grupo;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.GrupoNoEncontrado;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
@Stateless
public class ClaseEJB implements GestionClase{

	@PersistenceContext(unitName = "ProyectoSII")
	private EntityManager em;

	
	@Override
	public List<Clase> verHorarioporGrupo(int id) throws NeuBDExceptions {
		// TODO Auto-generated method stub
		Grupo grupo = em.find(Grupo.class, id);
		
		if(grupo==null) {
			throw new GrupoNoEncontrado();
		}
	
		return grupo.getClases();
	}

	@Override
	public List<Clase> verHorarioporAsignatura(int referencia) throws NeuBDExceptions {
		// TODO Auto-generated method stub
		Asignatura asignatura = em.find(Asignatura.class, referencia);
		
		if(asignatura == null) {
			throw new AsignaturaNoEncontradaException();
		}
		
		return asignatura.getClases();
	}

	@Override
	public List<Clase> verHorarioCompleto() throws NeuBDExceptions {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Clase.todos", Clase.class).getResultList();
	}

	@Override
	public Clase verClase(NewId_Clase_grupo id) throws NeuBDExceptions {
		// TODO Auto-generated method stub
		Clase clase = em.find(Clase.class, id);
		
		if (clase == null) {
			throw new GrupoNoEncontrado();
		}
		
		return clase;
	}
	
}
