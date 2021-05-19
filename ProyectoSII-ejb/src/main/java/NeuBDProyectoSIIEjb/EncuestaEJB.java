package NeuBDProyectoSIIEjb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSIIexceptions.EncuestaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;


@Stateless
public class EncuestaEJB implements GestionEncuesta{

	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	@Override
	public void eliminarEncuesta(Encuesta e) throws EncuestaNoEncontradaException {
		// TODO Auto-generated method stub
		Encuesta encuesta = em.find(Encuesta.class, e.getFecha_de_envio());
		
		if (encuesta == null) {
			throw new EncuestaNoEncontradaException();
		}
		
		em.remove(em.merge(encuesta));
	}

	@Override
	public Encuesta visualizarEncuesta(Encuesta e) throws EncuestaNoEncontradaException {
		// TODO Auto-generated method stub
		Encuesta encuesta = em.find(Encuesta.class, e.getFecha_de_envio());
		
		if (encuesta == null) {
			throw new EncuestaNoEncontradaException();
		}
		
		return encuesta;
	}

	@Override
	public void modificarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException {
		// TODO Auto-generated method stub
		Encuesta encuesta1 = em.find(Encuesta.class, encuesta.getFecha_de_envio());
		
		if (encuesta1 == null) {
			throw new EncuestaNoEncontradaException();
		}
		
		em.merge(encuesta);
		
	}

	@Override
	public List<Encuesta> listaEncuestas() throws EncuestaNoEncontradaException {
		
		return em.createNamedQuery("Encuesta.todos",Encuesta.class).getResultList();
	}

	@Override
	public void ImportarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException {
		// TODO Auto-generated method stub
		em.merge(encuesta);
	}

}
