package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionOptativa {
	public List<Optativa> listaOptativa()throws NeuBDExceptions;
	public Optativa buscarOptativa(int referencia) throws NeuBDExceptions;
	public void modificarOptativa(Optativa optativa) throws NeuBDExceptions;
	public void eliminaOptativa(Optativa optativa) throws NeuBDExceptions;
	public void insertarOptativa(Optativa optativa, int referenciaAsignatura) throws NeuBDExceptions;
}
