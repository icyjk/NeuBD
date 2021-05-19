package NeuBDProyectoSIIEjb;

import java.util.List;

import NeuBDProyectoSII.Clase;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.NewId_Clase_grupo;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

public interface GestionClase {
	
	public List<Clase> verHorarioporGrupo(String id) throws NeuBDExceptions;
	
	public List<Clase> verHorarioporAsignatura(int referencia) throws NeuBDExceptions;
	
	public List<Clase> verHorarioCompleto() throws NeuBDExceptions;
	
	public Clase verClase(NewId_Clase_grupo id) throws NeuBDExceptions; 


	
}
