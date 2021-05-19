package NeuBDProyectoSIIexceptions;

public class GruposPorAsignaturaNoEncontradoException extends NeuBDExceptions {
	public GruposPorAsignaturaNoEncontradoException() {
		this("Grupo por asignatura no encontrado en BD");
	}
	
	public GruposPorAsignaturaNoEncontradoException(String message){
		super(message);
	}
}
