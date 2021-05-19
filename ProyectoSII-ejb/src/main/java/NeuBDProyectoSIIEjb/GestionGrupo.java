package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;


@Local
public interface GestionGrupo {
	
	public void eliminarGrupo(int id) throws NeuBDExceptions;
	
	public Grupo visualizarGrupo(int id) throws NeuBDExceptions;
	
	public void modificarGrupo(Grupo grupo) throws NeuBDExceptions;
	
	public List<Grupo> listaGrupos() throws NeuBDExceptions;
	
	public void crearGrupo(Grupo grupo) throws NeuBDExceptions;
	
	public void asociarGrupo(Grupo grupo,Grupo grupo1) throws NeuBDExceptions;
	
}
			
