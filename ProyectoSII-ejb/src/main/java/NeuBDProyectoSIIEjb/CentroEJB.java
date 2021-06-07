package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSIIexceptions.CentroNoExistente;
import NeuBDProyectoSIIexceptions.CentroYaExistenteException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Stateless
public class CentroEJB implements GestionCentro {
	@PersistenceContext(unitName = "ProyectoSII")
	private EntityManager em;
	
	@Override
	public void CrearCentro(Centro centro) throws NeuBDExceptions {
		// TODO Auto-generated method stub
		Centro aux = em.find(Centro.class, centro.getId());
		if(aux != null)
			throw new CentroYaExistenteException();
		em.persist(centro);
	}

	@Override
	public void modificarCentro(Centro centro) throws NeuBDExceptions {
		buscarCentro(centro.getId());
		em.merge(centro);
		
	}

	@Override
	public Centro buscarCentro(int id) throws NeuBDExceptions {
		Centro c = em.find(Centro.class, id);
		if(c == null)
			throw new CentroNoExistente();
		
		return c;
	}

	@Override
	public List<Centro> buscarTodosCentros() throws NeuBDExceptions {
		return em.createNamedQuery("Centro.todos", Centro.class).getResultList();
	}

	@Override
	public void eliminarCentro(Centro centro) throws NeuBDExceptions {
		
		Centro aux = em.find(Centro.class, centro.getId());
		if(aux == null)
			throw new CentroYaExistenteException();
		em.remove(centro);
	}

}
