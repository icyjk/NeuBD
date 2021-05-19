package NeuBDProyectoSIIexceptions;

public class usuarioNoEncontradoException extends NeuBDExceptions{

	
	public usuarioNoEncontradoException() {
		this("Usario no encontrado en BD");
	}
	
	public usuarioNoEncontradoException(String message){
		super(message);
	}
}
