package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.TItulacionNoEncontradaException;

@Stateless
public class AsignaturaEJB implements GestionAsignatura {
	@PersistenceContext(unitName = "ProyectoSII")
	private EntityManager em;

	@Override
	public List<Asignatura> listaAsignatura() throws AsignaturaNoEncontradaException {
		return em.createNamedQuery("Asignatura.todos", Asignatura.class).getResultList();
	}

	@Override
	public Asignatura buscarAsignatura(int referencia) throws AsignaturaNoEncontradaException {
		Asignatura a = em.find(Asignatura.class, referencia);
		if(a == null)
			throw new AsignaturaNoEncontradaException();
		return a;
	}

	@Override
	public void modificarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
		buscarAsignatura(asignatura.getReferencia());
		em.merge(asignatura);
		
	}

	@Override
	public void eliminaAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
		buscarAsignatura(asignatura.getReferencia());
		em.remove(em.merge(asignatura));
		
	}

	@Override
	public List<Asignatura> buscarAsignaturaPorTitulacion(int codigoTitulacion) throws AsignaturaNoEncontradaException {
		Titulacion tit = em.find(Titulacion.class, codigoTitulacion);
		if(tit == null)
			throw new AsignaturaNoEncontradaException();
		
		return tit.getAsignaturas();
	}

	@Override
	public void ImportarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
		em.merge(asignatura);//Si no estaba la crea y si estaba la va a actualizar
		
	}

}
