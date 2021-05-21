package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSII.NewId_Matricula_expediente;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.AsignaturaPorMatriculaNoEncontradaException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.usuarioNoEncontradoException;

@Stateless
public class AsigMatriEJB implements GestionAsigMatri{
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	
	@Override
	public void anyadirAsignatura_matricula(Asignatura_matricula asig_matri) throws NeuBDExceptions {
		
		int i=0;
		
		if (asig_matri == null) {
			throw new AlumnoSInDatosParaCrearException();
		}
		
		System.out.println(asig_matri.getAsigantura()+" "+asig_matri.getMatricula());
		Titulacion titAlum= asig_matri.getMatricula().getExpedientes().getTitulaciones();
		List<Grupo> grupos = em.createNamedQuery("Grupo.todos", Grupo.class).getResultList();
		
		
		
		
		//Algoritmo de asignacion
		while(i<grupos.size()) {
			if(grupos.get(i).getTitulacion().equals(titAlum) && grupos.get(i).getGrupo_por_asignatura().get(0).getCurso_academico()==asig_matri.getMatricula().getCurso_academico() ) {
				int j=0;
				while(j<grupos.get(i).getGrupo_por_asignatura().size()) {
					if(asig_matri.getAsigantura().equals(grupos.get(i).getGrupo_por_asignatura().get(0).getAsignatura())) {
						asig_matri.setGrupo(grupos.get(i));
					}
				j++;
				}
			}
		i++;
		}
		//En el caso que no existiera
		//Tendremos que crear ese grupos
		// EN EL MOMENTO QE UNA ASIG MATRI TENGA YA UN GRUPO HABRA QUE CREAR UN GRUPO POR ASIGNATURA 
		
		if(asig_matri.getGrupo() == null) {
			i = 0;
			
			while(i<grupos.size()) {
				if(grupos.get(i).getCurso() == asig_matri.getAsigantura().getCurso()
						&& grupos.get(i).getTitulacion().equals(asig_matri.getAsigantura().getTitulacion())) {
					
					Grupos_por_asignatura_Ejb g = new Grupos_por_asignatura_Ejb();
					
					g.crearGrupoPorAsignatura(new Grupos_por_asignatura(grupos.get(i), asig_matri.getAsigantura(), 
							asig_matri.getMatricula().getCurso_academico(), true, null));
					
					asig_matri.setGrupo(grupos.get(i));
				}
				i++;
			}
			
		}
		
		em.merge(asig_matri); //Por si el alumno pasado ya existia en la BD
	}
	
	
	
	
	@Override
	public Asignatura_matricula visualizarAsigMatri(NewId_Asignatura_matricula id) throws NeuBDExceptions {
		
		Asignatura_matricula AsigMatri = em.find(Asignatura_matricula.class, id);
		
		if (AsigMatri == null) {
			throw new AsignaturaPorMatriculaNoEncontradaException();
		}
		
		return AsigMatri;
	}

	@Override
	public void modificarAsigMatri(Asignatura_matricula asigMatri) throws NeuBDExceptions {
		NewId_Matricula_expediente id2 = new NewId_Matricula_expediente(asigMatri.getMatricula().getExpedientes().getNum_expediente(), asigMatri.getMatricula().getCurso_academico());
		NewId_Asignatura_matricula id = new NewId_Asignatura_matricula(asigMatri.getAsigantura().getReferencia(), id2);
		Asignatura_matricula AsigMatri1 = em.find(Asignatura_matricula.class, id);
		
		
		if (AsigMatri1 == null) {
			throw new usuarioNoEncontradoException();
		}
		em.merge(asigMatri); //Manda a la base de datos el grupo modificado, y lo mezcla con el grupo que habia
	}




	@Override
	public List<Asignatura_matricula> listaAsigMatri() throws NeuBDExceptions {
		return em.createNamedQuery("AsigMatri.todos",Asignatura_matricula.class).getResultList();
	}

}
