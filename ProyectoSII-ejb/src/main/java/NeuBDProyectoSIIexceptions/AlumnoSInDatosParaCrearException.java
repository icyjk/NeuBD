package NeuBDProyectoSIIexceptions;

public class AlumnoSInDatosParaCrearException extends NeuBDExceptions {

	public AlumnoSInDatosParaCrearException() {
		this("Imposible crear un alumno sin datos");
	}

	public AlumnoSInDatosParaCrearException(String string) {
		
		super(string);
	}
}
