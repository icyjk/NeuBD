package NeuBDProyectoSIIexceptions;

public class GrupoNoEncontrado extends NeuBDExceptions{
	public GrupoNoEncontrado() {
		this("Grupo no encontrado en BD");
	}
	
	public GrupoNoEncontrado(String message){
		super(message);
	}
}
