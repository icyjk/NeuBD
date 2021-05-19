package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
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

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIEjb.GestionLeerCSV;
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
	private static final String Expedientes_EJB = "java:global/classes/ExpedientesEJB";
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
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	@Ignore
	public void TestLeerCSVAlumno(){	
		try {
			
			Titulacion titu = gestionTitulacion.listaTitulacion().get(0);
			String route="/home/alumno/Escritorio/AlumnoPrueba.csv";
			
			int tam=gestionAlumno.listaAlumno().size();
			
			gestionLeerCSV.insertarAlumnoCSV(titu, route);
			
			int tam2=gestionAlumno.listaAlumno().size();
			
			assertEquals(tam+1, tam2);
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	@Ignore
	public void TestLeerCSVTitulacion() {
		try {
			Centro cen = gestionCentro.buscarTodosCentros().get(0);
			String route="/home/alumno/Escritorio/TitulacionPrueba.csv";
			
			
			int tam=gestionTitulacion.listaTitulacion().size();
			
			gestionLeerCSV.insertarTitulacionCSV(cen, route);
			
			int tam2=gestionTitulacion.listaTitulacion().size();
			
			assertEquals(tam+1, tam2);
			
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	@Ignore
	public void TestLeerCSVAsignatura() {
		try {
			String route="/home/alumno/Escritorio/AsignaturaPrueba.csv";
			
			int tam=gestionAsignatura.listaAsignatura().size();
			
			gestionLeerCSV.insertarAsignaturaCSV(route);
			
			int tam2=gestionAsignatura.listaAsignatura().size();
			
			assertEquals(tam+1, tam2);
			
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-01,RF-02"})
	@Test
	@Ignore
	public void TestLeerCSVOptativa() {
		try {
			
			Titulacion titu = gestionTitulacion.listaTitulacion().get(0);
			String route="/home/alumno/Escritorio/OptativaPrueba.csv";
			int tam=gestionOptativa.listaOptativa().size();
			gestionLeerCSV.insertarOptativaCSV(route,titu);
			int tam2=gestionOptativa.listaOptativa().size();
		
			assertEquals(tam+1, tam2);
		
		
		}catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	@Requisitos({"RF-01,RF-02"})
	@Test
	@Ignore
	public void TestLeerCSVEncuesta() {
		try {
			Expedientes exp=gestionExpediente.listaExpedientes().get(0);
			String route="/home/alumno/Escritorio/EncuestaPrueba.csv";
			int tam=gestionExpediente.listaExpedientes().size();
			
			gestionExpediente.importarExpediente(exp);
			
			int tam2=gestionExpediente.listaExpedientes().size();
			
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
