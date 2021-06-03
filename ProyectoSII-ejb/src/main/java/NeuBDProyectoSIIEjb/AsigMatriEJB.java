package NeuBDProyectoSIIEjb;

import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSII.NewId_Matricula_expediente;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.AsignaturaNoEncontradaException;
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
			if(!grupos.get(i).getGrupo_por_asignatura().isEmpty()) {
				if(grupos.get(i).getTitulacion().equals(titAlum) && grupos.get(i).getGrupo_por_asignatura().get(0).getCurso_academico()==asig_matri.getMatricula().getCurso_academico() ) {
					int j=0;
					while(j<grupos.get(i).getGrupo_por_asignatura().size()) {
						if(asig_matri.getAsigantura().equals(grupos.get(i).getGrupo_por_asignatura().get(0).getAsignatura())) {
							asig_matri.setGrupo(grupos.get(i));
						}
					j++;
					}
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
					
					Grupos_por_asignatura g = new Grupos_por_asignatura(grupos.get(i), asig_matri.getAsigantura(), 
							asig_matri.getMatricula().getCurso_academico(), true, null);
					
					em.merge(g);
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




	@Override
	public void crearAsigsMatris() throws NeuBDExceptions {
		List<Matricula> matriculas = em.createNamedQuery("Matricula.todos",Matricula.class).getResultList();
		int size = matriculas.size();int cnt = 0;
		while(cnt <size) {
			if(matriculas.get(cnt).getAsignatura_matricula().isEmpty()) {
				crearMatri(matriculas.get(cnt));
			}

			cnt++;
		}
	}
	
    private void crearMatri(Matricula m) throws AsignaturaNoEncontradaException {
		
		String listado = m.getListado_asignaturas();
		Scanner scan = new Scanner(listado);
		//System.out.println(listado);

		scan.useDelimiter(",");
		
		while(scan.hasNext()) {
			String aux = scan.next();
			
			String codigo = aux.substring(0,3);
			Asignatura a = buscarAsignatura(codigo);
			try {
				anyadirAsignatura_matricula(new Asignatura_matricula(a, m, null, false, false));
			} catch (NeuBDExceptions e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		scan.close();
		
	}
    private Asignatura buscarAsignatura(String codigo) throws AsignaturaNoEncontradaException {
		List<Asignatura> lista = em.createNamedQuery("Asignatura.todos", Asignatura.class).getResultList();
		int cont = 0;boolean encontrado= false;Asignatura resultado = null;

		while(!encontrado && cont<lista.size()) {
			Asignatura a = lista.get(cont);
			if(a.getCodigo()==Integer.parseInt(codigo)) {
				resultado = a;
				encontrado=true;
			}
			cont++;
		}
		if(!encontrado) {
			throw new AsignaturaNoEncontradaException();
		}
		return resultado;
	}

}
