package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionAsignatura {
	public List<Asignatura> listaAsignatura()throws NeuBDExceptions;
	public Asignatura buscarAsignatura(int referencia) throws NeuBDExceptions;
	public List<Asignatura> buscarAsignaturaPorTitulacion(int codigoTitulacion) throws NeuBDExceptions;
	public void modificarAsignatura(Asignatura asignatura) throws NeuBDExceptions;
	public void eliminaAsignatura(Asignatura asignatura) throws NeuBDExceptions;
	public void ImportarAsignatura(Asignatura asignatura) throws NeuBDExceptions;
}
