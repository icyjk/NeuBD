package NeuBDProyectoSIIEjb;


import java.util.List;


import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

public interface GestionEncuesta {
	
	public void eliminarEncuesta(Encuesta encuesta) throws NeuBDExceptions;
	
	public Encuesta visualizarEncuesta(Encuesta e) throws NeuBDExceptions;
	
	public void modificarEncuesta(Encuesta encuesta) throws NeuBDExceptions;
	
	public List<Encuesta> listaEncuestas() throws NeuBDExceptions;
	
	public void ImportarEncuesta(Encuesta encuesta) throws NeuBDExceptions; 
}
