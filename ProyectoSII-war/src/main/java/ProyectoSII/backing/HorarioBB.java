package ProyectoSII.backing;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Clase;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionClase;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import ProyectoSII.backing.CentroBB.Modo;

@Named(value = "horario")
@RequestScoped
public class HorarioBB {

	public static enum Modo {
        Completo, 
        PorAsignatura,
        PorGrupo
    };
	
	
	
	
	@Inject
	private GestionClase gestionClase;

	
	private Asignatura asignatura;
	private Grupo grupo;
	private Modo modo;
	

	public HorarioBB() {
		super();
	}
	
	

	public Modo getModo() {
		return modo;
	}






	public void setModo(Modo modo) {
		this.modo = modo;
	}

	
	 public String getAccion() {
	        switch (modo) {
	            case Completo:
	                return "Completo";
	            case PorAsignatura:
	            	return "Asignatura";
	            case PorGrupo:
	            	return "Grupo";
	            	
	        }
	        return null;
	 }

	 
	 public List<Clase> ejecutarAccion() throws NeuBDExceptions { //HERE EL PROBLEMA

		 
		 List<Clase> lista =new ArrayList<Clase>();
		 
		 if(modo==Modo.Completo) {
			 lista = verHorarioPorCompleto();
		 }
		 
		 
		 if(modo==Modo.PorAsignatura) {
			 lista = verHorarioPorAsignatura();
		 }
		 
		 if(modo==Modo.PorGrupo) {
			 lista = verHorarioPorGrupo();
		 }
		 
		 
		 
		 return lista;
		 
		 
	    }




	
	
	
	
	
	
	public String verHorarioCompleto() throws NeuBDExceptions {
		setModo(Modo.Completo);
	
		return "Horario.xhtml";
	
	}
	
	public String verHorarioAsignatura(Asignatura asig) throws NeuBDExceptions {
		asignatura=asig;
		setModo(Modo.PorAsignatura);
	
		return "Horario.xhtml";
	
	}
	
	public String verHorarioGrupo(Grupo grupo) throws NeuBDExceptions {
		this.grupo=grupo;
		setModo(Modo.PorGrupo);
	
		return "Horario.xhtml";
	
	}
	
	
	
	
	
	

	public List<Clase> verHorarioPorCompleto() throws NeuBDExceptions {

		List<Clase> lista = gestionClase.verHorarioCompleto();


		return lista ;
	}




	public List<Clase> verHorarioPorAsignatura() throws NeuBDExceptions {

		List<Clase> lista = gestionClase.verHorarioporAsignatura(asignatura.getReferencia()); //dangeorus


		return lista;

	}

	public List<Clase> verHorarioPorGrupo() throws NeuBDExceptions {

		List<Clase> lista = gestionClase.verHorarioporGrupo(grupo.getId());


		return lista;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
