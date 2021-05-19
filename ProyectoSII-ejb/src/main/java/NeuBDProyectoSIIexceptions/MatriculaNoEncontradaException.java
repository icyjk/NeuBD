package NeuBDProyectoSIIexceptions;

public class MatriculaNoEncontradaException extends NeuBDExceptions{
	public MatriculaNoEncontradaException() {
		this("Matricula no encontrado en BD");
	}
	
	public MatriculaNoEncontradaException(String message){
		super(message);
	}
}
