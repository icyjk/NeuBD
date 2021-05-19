package NeuBDProyectoSIIexceptions;

public class ExpedienteNoEncontradoException extends NeuBDExceptions{
	public ExpedienteNoEncontradoException() {
		this("Expediente no encontrado en BD");
	}
	
	public ExpedienteNoEncontradoException(String message){
		super(message);
	}
}
