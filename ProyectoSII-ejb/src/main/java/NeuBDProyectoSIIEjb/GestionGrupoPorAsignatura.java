package NeuBDProyectoSIIEjb;

import java.util.List;

import javax.ejb.Local;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.NewId_GrupoPorAsignatura_grupo_asignatura;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;

@Local
public interface GestionGrupoPorAsignatura {
	public List<Grupos_por_asignatura> listaGruposPorAsignatura()throws NeuBDExceptions;
	public List<Grupos_por_asignatura> buscarGruposPorAsignaturaViaAsignatura(int referenciaAsignatura) throws NeuBDExceptions;
	public List<Grupos_por_asignatura> buscarGrupoPorAsignaturaViaCursoAcademic(int Curso_academico) throws NeuBDExceptions;
	public Grupos_por_asignatura buscarGrupoPorAsignatura(NewId_GrupoPorAsignatura_grupo_asignatura id) throws NeuBDExceptions;
	public void modificarGruposPorAsignatura(Grupos_por_asignatura grupo) throws NeuBDExceptions;
	public void eliminarGruposPorAsignatura(Grupos_por_asignatura grupo) throws NeuBDExceptions;
	public void crearGrupoPorAsignatura(Grupos_por_asignatura grupo) throws NeuBDExceptions;
}
