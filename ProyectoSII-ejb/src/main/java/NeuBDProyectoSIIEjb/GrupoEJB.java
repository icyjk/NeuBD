package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIexceptions.GrupoNoEncontrado;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.NoTodasLasClasesSonCoincidentes;

@Stateless
public class GrupoEJB implements GestionGrupo {
	
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	
	@Override
	public void eliminarGrupo(int id) throws GrupoNoEncontrado{
		
		Grupo grupoEntity = em.find(Grupo.class, id);
		
		if (grupoEntity == null) {
			throw new GrupoNoEncontrado();
		}
		
		em.remove(em.merge(grupoEntity));
		
	}
	
	@Override
	public List<Grupo> listaGrupos() throws GrupoNoEncontrado {
		return em.createNamedQuery("Grupo.todos", Grupo.class).getResultList();
	}

	
	@Override
	public Grupo visualizarGrupo(int id) throws GrupoNoEncontrado{

		Grupo grupoEntity = em.find(Grupo.class, id);
		
		if (grupoEntity == null) {
			throw new GrupoNoEncontrado();
		}
		
		return grupoEntity;
	}
	
	@Override
	public void modificarGrupo(Grupo grupo) throws GrupoNoEncontrado { //EL grupo debe pasarse ya modificado
		Grupo grupoEntity = em.find(Grupo.class, grupo.getId());
		
		if (grupoEntity == null) {
			throw new GrupoNoEncontrado();
		}
		
		em.merge(grupo); //Manda a la base de datos el grupo modificado, y lo mezcla con el grupo que habia
		
	}

	@Override
    public void crearGrupo(Grupo grupo) throws NeuBDExceptions {

        List<Grupo> lista = listaGrupos();

        int i=0;
        while(i<lista.size()) {
            Grupo g = lista.get(i);

            if(grupo.getCurso()==g.getCurso() && grupo.getLetra()==g.getLetra() && grupo.getTitulacion().getCodigo()==g.getTitulacion().getCodigo()) {
                throw new GrupoNoEncontrado();
            }

            i++;
            }


        em.persist(grupo);

    }

	@Override
	public void asociarGrupo(Grupo grupo,Grupo grupo1) throws NoTodasLasClasesSonCoincidentes {
		// TODO Auto-generated method stub
		if(!grupo.getClases().equals(grupo1.getClases())) {
			throw new NoTodasLasClasesSonCoincidentes();
		}
		
		grupo.setGrupos(grupo1);
		
		em.merge(grupo);
	}
	
}


