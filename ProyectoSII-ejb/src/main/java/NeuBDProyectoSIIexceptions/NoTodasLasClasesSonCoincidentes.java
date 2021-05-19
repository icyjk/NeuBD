package NeuBDProyectoSIIexceptions;

public class NoTodasLasClasesSonCoincidentes extends NeuBDExceptions{
	public NoTodasLasClasesSonCoincidentes() {
		this("Las clases no son coincidentes");
	}
	
	public NoTodasLasClasesSonCoincidentes(String message){
		super(message);
	}
}
