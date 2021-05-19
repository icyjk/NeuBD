package NeuBDProyectoSIIexceptions;

public class MatriculaSinDatosParaCrearException extends NeuBDExceptions{
	public MatriculaSinDatosParaCrearException() {
		this("Imposible crear una matricula sin datos");
	}

	public MatriculaSinDatosParaCrearException(String string) {
		
		super(string);
	}
}
