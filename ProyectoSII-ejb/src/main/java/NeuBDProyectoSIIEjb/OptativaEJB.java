package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Stateless
public class OptativaEJB implements GestionOptativa {
	@PersistenceContext(unitName = "ProyectoSII")
	private EntityManager em;

	@Override
	public List<Optativa> listaOptativa() throws NeuBDExceptions {
		return em.createNamedQuery("Optativa.todos", Optativa.class).getResultList();
	}

	@Override
	public Optativa buscarOptativa(int referencia) throws NeuBDExceptions {
		Optativa o = em.find(Optativa.class, referencia);
		if(o == null)
			throw new AsignaturaNoEncontradaException();
		return o;
	}

	@Override
	public void modificarOptativa(Optativa optativa) throws NeuBDExceptions {
		buscarOptativa(optativa.getReferencia());
		em.merge(optativa);
		
	}

	@Override
	public void eliminaOptativa(Optativa optativa) throws NeuBDExceptions {
		buscarOptativa(optativa.getReferencia());
		em.remove(em.merge(optativa));
		
	}

	@Override
	public void insertarOptativa(Optativa optativa, int referenciaAsignatura) throws NeuBDExceptions {
		Asignatura asignatura = em.find(Asignatura.class, referenciaAsignatura);
		if(asignatura == null)
			throw new AsignaturaNoEncontradaException();
		em.merge(optativa);

	}

}
