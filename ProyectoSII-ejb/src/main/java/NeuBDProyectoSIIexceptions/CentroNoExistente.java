package NeuBDProyectoSIIexceptions;

public class CentroNoExistente extends NeuBDExceptions{
	public CentroNoExistente() {
		this("Centro no encontrado en BD");
	}
	
	public CentroNoExistente(String message){
		super(message);
	}
}
