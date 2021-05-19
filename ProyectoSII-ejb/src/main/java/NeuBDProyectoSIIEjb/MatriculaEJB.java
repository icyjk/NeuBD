package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.NewId_Matricula_expediente;
import NeuBDProyectoSIIexceptions.MatriculaNoEncontradaException;
import NeuBDProyectoSIIexceptions.MatriculaSinDatosParaCrearException;

@Stateless
public class MatriculaEJB implements GestionMatricula{
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	
	@Override
	public void eliminarMatricula(Matricula m) throws MatriculaNoEncontradaException {
		
		NewId_Matricula_expediente id= new NewId_Matricula_expediente(m.getExpedientes().getNum_expediente(), m.getCurso_academico());
		Matricula matricula = em.find(Matricula.class, id);
		
		if(matricula==null) {
			throw new MatriculaNoEncontradaException();
		}
		
		em.remove(em.merge(matricula));
	}

	@Override
	public Matricula visualizarMatricula(Matricula m) throws MatriculaNoEncontradaException {
		Matricula matricula= em.find(Matricula.class,new NewId_Matricula_expediente(m.getExpedientes().getNum_expediente(), m.getCurso_academico()));
		
		if (matricula == null) {
			throw new MatriculaNoEncontradaException();
		}
		
		return matricula;
	}

	@Override
	public void modificarMatricula(Matricula m) throws MatriculaNoEncontradaException {
		// TODO Auto-generated method stub
		Matricula matricula= em.find(Matricula.class, new NewId_Matricula_expediente(m.getExpedientes().getNum_expediente(), m.getCurso_academico()));
		
		if (matricula == null) {
			throw new MatriculaNoEncontradaException();
		}
		
		em.merge(m);
		
	}

	@Override
	public List<Matricula> listaMatricula() throws MatriculaNoEncontradaException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Matricula.todos",Matricula.class).getResultList();
	}

	@Override
	public void anyadirMatricula(Matricula m) throws MatriculaSinDatosParaCrearException {
		// TODO Auto-generated method stub
		if (m == null) {
			throw new MatriculaSinDatosParaCrearException();
		}
		
		em.merge(m);
		
	}

}
