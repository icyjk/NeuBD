package NeuBDProyectoSIITests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Titulacion;

public class BaseDatos {
	
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		//System.out.println("ESTOY INICIALIZANDO LA BASE DE DATOS");
		
		em.getTransaction().begin();
		
		//centro
		Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
		Centro centroBuscar = new Centro("Buscador","Buscador","635004675",null);
		
		
		List<Centro> listacentros = new ArrayList<Centro>();
		listacentros.add(centroETSI);
		
		em.persist(centroETSI);
		em.persist(centroBuscar);
		//Titulacion
		Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
		
		em.persist(titulacionInf);
		
		//Asignatura
		Asignatura calculo = new Asignatura(666, 101, 3, 3, 6, false, "Calculo", 1, "caracter", 0, "PrimerCuatri", "Español"
				,titulacionInf , null, null, null);
		em.persist(calculo);
		
		//Optativa
		Optativa iaJuegosOpta = new Optativa(30, "Informatica",null);
		
		iaJuegosOpta.setCodigo(311);
		iaJuegosOpta.setNombre("IAJuegos");
		iaJuegosOpta.setReferencia(69);
		em.persist(iaJuegosOpta);
		//Grupo
		Grupo grupoAinf = new Grupo(1,'A',"Mañana",true, true, "", 50 , titulacionInf, null, null,null,null);
		
		em.persist(grupoAinf);

		Grupos_por_asignatura calculoA = new Grupos_por_asignatura(grupoAinf, calculo, "20/21", true, null);
		em.persist(calculoA);
		
		
		
		
		//Expediente 
		Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, titulacionInf, null, null);
		List<Expedientes> listaExpedientes = new ArrayList<Expedientes>();
		em.persist(expediente);
		listaExpedientes.add(em.merge(expediente));
		titulacionInf.setExpedientes(listaExpedientes);
		
		
		//Matricula
		
		Matricula matricula = new Matricula(expediente, "20/21", null, 0, null, null, null, null, null);
		
		em.persist(matricula); 
		
		//AsigMatri
		Asignatura_matricula asigmatri = new Asignatura_matricula(calculo, matricula, null, false, false);
		em.persist(asigmatri);
		
		List<Asignatura_matricula> listaAsigMatri = new ArrayList<Asignatura_matricula>();
		listaAsigMatri.add(asigmatri);	
		calculo.setAsignaturaMatricula(listaAsigMatri );
		em.merge(calculo);
		matricula.setAsignatura_matricula(listaAsigMatri);
		em.merge(matricula);
		grupoAinf.setAsigMatriculas(listaAsigMatri);
		em.merge(grupoAinf);
		
		
		//Encuesta
		Date fecha2 = new Date(116,5,4);
		Encuesta encuesta = new Encuesta(fecha2, null, null);
		em.persist(encuesta);
		//Alumno
		Alumno alumno = new Alumno("Gustavo","Gracias","Molina","ola@prueba.com","ola@uma.es","75848437","calle falsa n25","malaga","malaga","29010",listaExpedientes," "," ");
		//Alumno alumno2 = new Alumno ();
		
		em.persist(alumno);
		expediente.setAlumnos(alumno);
		em.merge(expediente);
		//em.persist(alumno2);
		//em.persist(alumno2);
		em.getTransaction().commit();
		
		//System.out.println("LA BASE DE DATOS HA SIDO INICIALIZADA");
		//Titulacion aux = em.find(Titulacion.class,66 );
		//System.out.println(aux);
		
		em.close();
		emf.close();
	}
}