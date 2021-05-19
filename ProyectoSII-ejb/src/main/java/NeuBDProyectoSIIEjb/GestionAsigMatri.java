package NeuBDProyectoSIIEjb;

import java.util.List;

import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

public interface GestionAsigMatri {

	public List<Asignatura_matricula> listaAsigMatri() throws NeuBDExceptions;
	
	public void anyadirAsignatura_matricula(Asignatura_matricula asig_matri) throws NeuBDExceptions;
	
	public Asignatura_matricula visualizarAsigMatri(NewId_Asignatura_matricula id) throws NeuBDExceptions;
	
	public void modificarAsigMatri(Asignatura_matricula AsigMatri) throws NeuBDExceptions;
}
