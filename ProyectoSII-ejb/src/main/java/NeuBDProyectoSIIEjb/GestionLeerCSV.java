package NeuBDProyectoSIIEjb;

import java.io.IOException;

import javax.ejb.Local;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionLeerCSV {
	public void insertarAlumnoCSV(Titulacion titu,String route)throws  NeuBDExceptions;
	public void insertarTitulacionCSV(Centro cen, String route)throws  NeuBDExceptions;
	public void insertarAsignaturaCSV(String route)throws  NeuBDExceptions;
	public void insertarOptativaCSV(String route,Titulacion tit)throws  NeuBDExceptions;
	public void insertarEncuestaCSV(String route, Expedientes exp)throws  NeuBDExceptions;
}
