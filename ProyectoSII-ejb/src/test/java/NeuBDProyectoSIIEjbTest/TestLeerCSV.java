package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionEncuesta;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIEjb.GestionLeerCSV;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIEjb.GestionOptativa;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestLeerCSV {
	private static final String LeerCSV_EJB = "java:global/classes/LeerCSVEJB";
	private static final String Centro_EJB = "java:global/classes/CentroEJB";
	private static final String Titulacion_EJB = "java:global/classes/TitulacionEJB";
	private static final String Alumno_EJB = "java:global/classes/AlumnoEJB";
	private static final String Asignatura_EJB = "java:global/classes/AsignaturaEJB";
	private static final String Optativa_EJB = "java:global/classes/OptativaEJB";
	private static final String Expedientes_EJB = "java:global/classes/ExpedienteEJB";
	private static final String Matricula_EJB = "java:global/classes/MatriculaEJB";
	private static final String Encuesta_EJB = "java:global/classes/EncuestaEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionLeerCSV gestionLeerCSV;
	private GestionCentro gestionCentro;
	private GestionTitulacion gestionTitulacion;
	private GestionAlumno gestionAlumno;
	private GestionAsignatura gestionAsignatura;
	private GestionOptativa gestionOptativa;
	private GestionExpediente gestionExpediente;
	private GestionMatricula gestionMatricula;
	private GestionEncuesta gestionEncuesta;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionLeerCSV = (GestionLeerCSV) ctx.lookup(LeerCSV_EJB);
		gestionCentro = (GestionCentro) ctx.lookup(Centro_EJB);
		gestionTitulacion = (GestionTitulacion) ctx.lookup(Titulacion_EJB);
		gestionAlumno = (GestionAlumno) ctx.lookup(Alumno_EJB);
		gestionAsignatura = (GestionAsignatura) ctx.lookup(Asignatura_EJB);
		gestionOptativa = (GestionOptativa) ctx.lookup(Optativa_EJB);
		gestionExpediente = (GestionExpediente) ctx.lookup(Expedientes_EJB);
		gestionMatricula = (GestionMatricula) ctx.lookup(Matricula_EJB);
		gestionEncuesta = (GestionEncuesta) ctx.lookup(Encuesta_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	public void TestLeerCSVAlumno() {	
		try {
			
			String route="/home/alumno/Escritorio/AlumnoPrueba.csv";
			
			int tam=gestionAlumno.listaAlumno().size();
			int tamExpedientes = gestionExpediente.listaExpedientes().size();
			int tamMatriculas = gestionMatricula.listaMatricula().size();
			try {
				gestionLeerCSV.insertarAlumnoCSV(route);
			}catch(ParseException e) {
				fail("No deberia lanzarse excepcion");
			}
			List<Expedientes> lista = gestionAlumno.listaAlumno().get(5).getExpedientes();
			int numeroExpedientes = lista.size();
			assertNotEquals(0, numeroExpedientes);
			List<Matricula>list= gestionExpediente.listaExpedientes().get(2).getMatricula();
			int numeroMatriculas = list.size();
			int tam2=gestionAlumno.listaAlumno().size();
			int tamExpedientes2 = gestionExpediente.listaExpedientes().size();
			int tamMatriculas2 = gestionMatricula.listaMatricula().size();
			System.out.println("NUMERO DE MATRICULAS TOTALES"+tamMatriculas2);
			assertNotEquals(tam+1, tam2);
			assertNotEquals(tamExpedientes+1, tamExpedientes2);
			assertNotEquals(tamMatriculas+1, tamMatriculas2);
			assertNotEquals(tam, tam2);
			assertNotEquals(tamExpedientes, tamExpedientes2);
			assertNotEquals(tamMatriculas, tamMatriculas2);
			assertNotEquals(0, numeroMatriculas);
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	public void TestLeerCSVTitulacion() {
		try {
			Centro cen = gestionCentro.buscarTodosCentros().get(0);
			String route="/home/alumno/Escritorio/Titulacion.csv";
			
			
			int tam=gestionTitulacion.listaTitulacion().size();
			
			gestionLeerCSV.insertarTitulacionCSV(cen, route);
			
			int tam2=gestionTitulacion.listaTitulacion().size();
			
			assertNotEquals(tam+1, tam2);
			assertNotEquals(tam,tam2);
			
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	public void TestLeerCSVAsignatura() {
		try {
			String route="/home/alumno/Escritorio/Oferta-asignaturas.csv";
			String route2="/home/alumno/Escritorio/Titulacion.csv";
			Centro cen = gestionCentro.buscarTodosCentros().get(0);
			
			int tam=gestionAsignatura.listaAsignatura().size();
			gestionLeerCSV.insertarTitulacionCSV(cen, route2);//Necesito que esten insertadas las titulaciones para hacer la referencia a ellas
			gestionLeerCSV.insertarAsignaturaCSV(route);
			
			int tam2=gestionAsignatura.listaAsignatura().size();
			
			assertNotEquals(tam+1, tam2);
			assertNotEquals(tam, tam2);
			
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	public void TestLeerCSVOptativa() {
		try {
			
			Titulacion titu = gestionTitulacion.listaTitulacion().get(0);
			Centro cen = gestionCentro.buscarTodosCentros().get(0);
			String route="/home/alumno/Escritorio/OptativaPrueba.csv";
			String route2="/home/alumno/Escritorio/Oferta-asignaturas.csv";
			String route3="/home/alumno/Escritorio/Titulacion.csv";
			int tam=gestionOptativa.listaOptativa().size();
			gestionLeerCSV.insertarTitulacionCSV(cen, route3);//Necesito que esten insertadas las titulaciones para hacer la referencia a ellas
			gestionLeerCSV.insertarAsignaturaCSV(route2);
			gestionLeerCSV.insertarOptativaCSV(route,titu);
			int tam2=gestionOptativa.listaOptativa().size();
		
			assertEquals(tam+1, tam2);
		
		
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	@Requisitos({"RF-01,RF-02"})
	@Test
	public void TestLeerCSVEncuesta() {
		try {
			String route="/home/alumno/Escritorio/EncuestaPrueba.csv";
			int tam=gestionEncuesta.listaEncuestas().size();
			
			gestionLeerCSV.insertarEncuestaCSV(route);
			
			int tam2=gestionEncuesta.listaEncuestas().size();
			
			assertEquals(tam+1, tam2);
			
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	
	
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}
