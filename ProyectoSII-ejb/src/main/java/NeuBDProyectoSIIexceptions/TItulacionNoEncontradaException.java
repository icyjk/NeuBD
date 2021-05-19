package NeuBDProyectoSIIexceptions;

public class TItulacionNoEncontradaException extends NeuBDExceptions{
	public TItulacionNoEncontradaException() {
		this("Titulacion no encontrado en BD");
	}
	
	public TItulacionNoEncontradaException(String message){
		super(message);
	}
}
