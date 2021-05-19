package NeuBDProyectoSIIexceptions;

public class GrupoPorAsignaturaYaExistenteException extends NeuBDExceptions{
	public GrupoPorAsignaturaYaExistenteException() {
		this("Grupo por asigantura  no encontrado en BD");
	}
	
	public GrupoPorAsignaturaYaExistenteException(String message){
		super(message);
	}
}
