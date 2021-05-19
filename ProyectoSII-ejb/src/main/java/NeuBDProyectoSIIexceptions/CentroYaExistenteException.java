package NeuBDProyectoSIIexceptions;

public class CentroYaExistenteException  extends NeuBDExceptions{
	public CentroYaExistenteException() {
		this("Centro ya existente en la BD");
	}
	
	public CentroYaExistenteException(String message){
		super(message);
	}
}
