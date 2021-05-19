package NeuBDProyectoSIIexceptions;

public class AsignaturaPorMatriculaNoEncontradaException extends NeuBDExceptions {

	public AsignaturaPorMatriculaNoEncontradaException() {
		this("Asignatura por matricula no encontrado en BD");
	}
	
	public AsignaturaPorMatriculaNoEncontradaException(String message){
		super(message);
	}
}
