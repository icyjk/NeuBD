package NeuBDProyectoSIIEjb;
import java.util.List;

import javax.ejb.Local;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.usuarioNoEncontradoException;



@Local
public interface GestionAlumno {


	public void anyadirAlumno(Alumno alumno) throws NeuBDExceptions;
	
	public void eliminarAlumno(int id) throws NeuBDExceptions;
	
	public Alumno visualizarAlumno(int id) throws NeuBDExceptions;
	
	public void modificarAlumno(Alumno usuario) throws NeuBDExceptions;
	
	public List<Alumno> listaAlumno() throws NeuBDExceptions;
	
}
