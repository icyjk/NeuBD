package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.TItulacionNoEncontradaException;
import NeuBDProyectoSIIexceptions.usuarioNoEncontradoException;

@Stateless
public class TitulacionEJB implements GestionTitulacion {
	
	
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	
	@Override
	public void eliminarTitulacion(int titulacion) throws TItulacionNoEncontradaException{
		
		Titulacion titulacionEntity = em.find(Titulacion.class, titulacion);
		
		if (titulacionEntity == null) {
			throw new TItulacionNoEncontradaException();
		}
		
		em.remove(em.merge(titulacionEntity));
		
	}
	
	
	@Override
	public Titulacion visualizartitulacion(int titulacion) throws TItulacionNoEncontradaException{
		
		Titulacion titulacionEntity = em.find(Titulacion.class, titulacion);
		
		if (titulacionEntity == null) {
			throw new TItulacionNoEncontradaException();
		}
		
		return titulacionEntity;
		
	}
	
	@Override
	public void modificarTitulacion(Titulacion titulacion) throws TItulacionNoEncontradaException{
		
		Titulacion titulacionEntity = em.find(Titulacion.class,titulacion.getCodigo());

		if (titulacionEntity == null) {
			throw new TItulacionNoEncontradaException();
		}
		
		em.merge(titulacion);
		
	}


	@Override
	public List<Titulacion> listaTitulacion() throws NeuBDExceptions {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Titulacion.todos", Titulacion.class).getResultList();
	}
	

	@Override
	public void ImportarTitulacion(Titulacion titulacion) throws NeuBDExceptions {
		em.merge(titulacion);//Si no estaba la crea y si estaba la va a actualizar
		
	}
	
	
	

}
