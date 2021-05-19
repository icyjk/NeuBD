package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionExpediente {
	
	public void eliminarExpediente(Expedientes ep) throws NeuBDExceptions;
	
	public Expedientes visualizarExpediente(Expedientes ep) throws NeuBDExceptions;
	
	public void modificarExpediente(Expedientes expediente) throws NeuBDExceptions;
		
	public List<Expedientes> listaExpedientes() throws NeuBDExceptions;
	
	public void importarExpediente(Expedientes exp) throws NeuBDExceptions;
	

}
