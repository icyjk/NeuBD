package NeuBDProyectoSIIexceptions;

public class AsignaturaNoEncontradaException extends NeuBDExceptions{
	
	public AsignaturaNoEncontradaException() {
		this("Asignatura no encontrada en BD");
	}

	public AsignaturaNoEncontradaException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
}
