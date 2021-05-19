package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionCentro {
	public void CrearCentro(Centro centro) throws NeuBDExceptions;
	public void modificarCentro(Centro centro) throws NeuBDExceptions;
	public Centro buscarCentro(int id) throws NeuBDExceptions;
	public List<Centro> buscarTodosCentros() throws NeuBDExceptions;
}
