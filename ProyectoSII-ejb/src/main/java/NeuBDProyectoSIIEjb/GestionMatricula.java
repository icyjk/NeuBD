package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.NewId_Matricula_expediente;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionMatricula {

	public void anyadirMatricula(Matricula m) throws NeuBDExceptions;
	
	public void eliminarMatricula(Matricula m) throws NeuBDExceptions;
	
	public Matricula visualizarMatricula(Matricula exp) throws NeuBDExceptions;
	
	public void modificarMatricula(Matricula m) throws NeuBDExceptions;
	
	public List<Matricula> listaMatricula() throws NeuBDExceptions;
	
}
