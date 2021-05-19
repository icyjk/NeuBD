package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.ExpedienteNoEncontradoException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.usuarioNoEncontradoException;

@Stateless
public class ExpedienteEJB implements GestionExpediente{
	
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	@Override
	public void eliminarExpediente(Expedientes ep) throws ExpedienteNoEncontradoException{
		
		Expedientes expedienteEntity = em.find(Expedientes.class,ep.getNum_expediente());
		
		if (expedienteEntity == null) {
			throw new ExpedienteNoEncontradoException();
		}
		
		em.remove(em.merge(ep));
		
	}
	
	@Override
	public Expedientes visualizarExpediente(Expedientes ep) throws ExpedienteNoEncontradoException{

		Expedientes expedienteEntity = em.find(Expedientes.class,ep.getNum_expediente());
		
		if (expedienteEntity == null) {
			throw new ExpedienteNoEncontradoException();
		}
		
		return expedienteEntity;
	}
	
	@Override
	public void modificarExpediente(Expedientes expediente) throws ExpedienteNoEncontradoException{
		
		Expedientes exp = em.find(Expedientes.class, expediente.getNum_expediente());
		
		if (exp == null) {
			throw new ExpedienteNoEncontradoException();
		}
		
		em.merge(expediente);
	}
	
	
	@Override
	public List<Expedientes> listaExpedientes() throws ExpedienteNoEncontradoException {
		return em.createNamedQuery("Expedientes.todos", Expedientes.class).getResultList();
	}

	@Override
	public void importarExpediente(Expedientes exp) throws ExpedienteNoEncontradoException {
		// TODO Auto-generated method stub
		em.merge(exp);//Si no estaba la crea y si estaba la va a actualizar
		
	}
	


}
