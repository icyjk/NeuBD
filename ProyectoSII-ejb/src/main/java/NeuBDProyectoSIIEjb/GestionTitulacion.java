package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;


@Local
public interface GestionTitulacion {
	
public void eliminarTitulacion(int titulacion) throws NeuBDExceptions;

public Titulacion visualizartitulacion(int titulacion) throws NeuBDExceptions;
	
public void modificarTitulacion(Titulacion titulacion) throws NeuBDExceptions;

public List<Titulacion> listaTitulacion()throws NeuBDExceptions;

public void ImportarTitulacion(Titulacion titulacion) throws NeuBDExceptions;
}
