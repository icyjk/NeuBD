package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.usuarioNoEncontradoException;

@Stateless
public class AlumnoEJB implements GestionAlumno {
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;

	@Override
	public void anyadirAlumno(Alumno alumno) throws AlumnoSInDatosParaCrearException {
		
		
		if (alumno == null) {
			throw new AlumnoSInDatosParaCrearException();
		}
		
		em.merge(alumno); //Por si el alumno pasado ya existia en la BD
		
	}
	
	
	
	
	
	@Override
	public void eliminarAlumno(int id) throws usuarioNoEncontradoException {
	
		Alumno usuario = em.find(Alumno.class, id);
		
		if (usuario == null) {
			throw new usuarioNoEncontradoException();
		}
		
		em.remove(em.merge(usuario));
		
	}

	@Override
	public Alumno visualizarAlumno(int id) throws usuarioNoEncontradoException {
		Alumno usuario = em.find(Alumno.class, id);
		
		if (usuario == null) {
			throw new usuarioNoEncontradoException();
		}
		
		return usuario;
	}

	@Override
	public void modificarAlumno(Alumno usuario) throws usuarioNoEncontradoException {
		
		Alumno usuario1 = em.find(Alumno.class, usuario.getID());
		
		if (usuario1 == null) {
			throw new usuarioNoEncontradoException();
		}
		
		em.merge(usuario); //Manda a la base de datos el grupo modificado, y lo mezcla con el grupo que habia
	}

	@Override
	public List<Alumno> listaAlumno() throws NeuBDExceptions {
		return em.createNamedQuery("Alumno.todos", Alumno.class).getResultList();
	}
	
	
	

	
}
