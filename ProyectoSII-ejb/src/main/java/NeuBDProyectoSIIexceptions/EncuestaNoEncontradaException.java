package NeuBDProyectoSIIexceptions;

public class EncuestaNoEncontradaException extends NeuBDExceptions{
	public EncuestaNoEncontradaException() {
		this("Encuesta no encontrado en BD");
	}
	
	public EncuestaNoEncontradaException(String message){
		super(message);
	}
}
