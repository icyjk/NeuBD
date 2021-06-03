package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.NewId_GrupoPorAsignatura_grupo_asignatura;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
import NeuBDProyectoSIIexceptions.GrupoPorAsignaturaYaExistenteException;
import NeuBDProyectoSIIexceptions.GruposPorAsignaturaNoEncontradoException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Stateless
public class Grupos_por_asignatura_Ejb implements GestionGrupoPorAsignatura{
	@PersistenceContext(unitName = "ProyectoSII")
	private EntityManager em;

	@Override
	public List<Grupos_por_asignatura> listaGruposPorAsignatura() throws NeuBDExceptions {
		return em.createNamedQuery("GruposPorAsignatura.todos", Grupos_por_asignatura.class).getResultList();
	}

	@Override
	public List<Grupos_por_asignatura>  buscarGruposPorAsignaturaViaAsignatura(int referenciaAsignatura)
			throws NeuBDExceptions, AsignaturaNoEncontradaException {
		Asignatura a = em.find(Asignatura.class, referenciaAsignatura);
		if(a==null)
			throw new AsignaturaNoEncontradaException();
		return a.getGruposPorAsignatura();
	}
	
	
	@Override
	public void modificarGruposPorAsignatura(Grupos_por_asignatura grupos) throws NeuBDExceptions, GruposPorAsignaturaNoEncontradoException {
		
		Grupos_por_asignatura g = em.find(Grupos_por_asignatura.class, new NewId_GrupoPorAsignatura_grupo_asignatura
				(grupos.getGrupo().getId(), grupos.getAsignatura().getReferencia(), grupos.getCurso_academico()));
		if(g == null) {
			throw new GruposPorAsignaturaNoEncontradoException();
		}
		em.merge(grupos);
		
	}
	
	@Override
	public void eliminarGruposPorAsignatura(Grupos_por_asignatura grupo) throws NeuBDExceptions, GruposPorAsignaturaNoEncontradoException {
		buscarGrupoPorAsignaturaViaCursoAcademic(grupo.getCurso_academico());
		em.remove(em.merge(grupo));
	}

	@Override
	public List<Grupos_por_asignatura> buscarGrupoPorAsignaturaViaCursoAcademic(String ca)
			throws NeuBDExceptions {
		
		List<Grupos_por_asignatura> grupos = em.createQuery("Select g from Grupos_por_asignatura g where g.Curso_academico = :curacad")
				.setParameter("curacad", ca).getResultList();
		
		if(grupos.isEmpty())
			throw new GruposPorAsignaturaNoEncontradoException();
		
		return grupos;
	}

	@Override
	public Grupos_por_asignatura buscarGrupoPorAsignatura(NewId_GrupoPorAsignatura_grupo_asignatura id)
			throws NeuBDExceptions {
		Grupos_por_asignatura grupos = em.find(Grupos_por_asignatura.class, id);
		if(grupos == null)
			throw new GruposPorAsignaturaNoEncontradoException("No existe ese grupo con la id que me has dado");
		return grupos;
	}

	@Override
	public void crearGrupoPorAsignatura(Grupos_por_asignatura grupo) throws NeuBDExceptions, GrupoPorAsignaturaYaExistenteException {
		Grupos_por_asignatura g = buscarGrupoPorAsignatura(new NewId_GrupoPorAsignatura_grupo_asignatura
				(grupo.getGrupo().getId(), grupo.getAsignatura().getReferencia(), grupo.getCurso_academico()));
		
		if(g != null)
			throw new GrupoPorAsignaturaYaExistenteException("Ese grupo por asignatura ya esta creado");
		em.persist(grupo);
	}

}
