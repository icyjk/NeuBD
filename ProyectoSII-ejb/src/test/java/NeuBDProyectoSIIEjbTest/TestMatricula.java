package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestMatricula {
	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Matricula_EJB = "java:global/classes/MatriculaEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionMatricula gestionMatricula;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionMatricula = (GestionMatricula) ctx.lookup(Matricula_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-07"})
	@Test
	public void testEliminarMatricula() {
		try {
			
			List<Matricula> matriculas = gestionMatricula.listaMatricula();
			
			int tamañoinicial = matriculas.size();
			
			Matricula matriculae = matriculas.get(0);
			
			gestionMatricula.eliminarMatricula(matriculae);
			
			matriculas = gestionMatricula.listaMatricula();
			
			assertEquals(tamañoinicial-1, matriculas.size());
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	@Requisitos({"RF-07"})
	@Test
	public void testEliminarMatriculaMAL() {
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			Matricula m = new Matricula(expediente, 0, null, 0, null, null, null, null, null);
			gestionMatricula.eliminarMatricula(m);
			
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-07"})
	@Test 
	public void testVisualizarMatricula() {
		try {
				
			Matricula matricula = gestionMatricula.listaMatricula().get(0);
							
			
			assertTrue(matricula.equals(gestionMatricula.visualizarMatricula(matricula)));	
				
				
			} catch (NeuBDExceptions e) {
				fail("No debería lanzarse excepción");
			}
			
			
		}
	
	
	@Requisitos({"RF-07"})
	@Test
	public void testVisualizarMatriculaMAL() {
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			Matricula matricula = new Matricula(expediente, 0, null, 0, null, null, null, null, null);
			matricula.setCurso_academico(267);
			
			gestionMatricula.visualizarMatricula(matricula);
		
			fail("Deberia lanzar excepcion");
			
			
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
		
		
	}
	@Requisitos({"RF-07"})
	@Test
	public void testModificarMatricula() {
		try {
			
			Matricula m = gestionMatricula.listaMatricula().get(0);
			
			m.setEstado("Ramon");
			
			gestionMatricula.modificarMatricula(m);
			
			
			
			assertEquals("Ramon",gestionMatricula.visualizarMatricula(m).getEstado());
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-07"})
	@Test
	public void testModificarMatriculaMAL() {
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			Matricula m = new Matricula(expediente, 0, null, 0, null, null, null, null, null);
			m.setEstado("12131123");
			gestionMatricula.modificarMatricula(m);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-07"})
	@Test
	public void testListaMatricula() {
		
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			List<Matricula> listaMatricula = gestionMatricula.listaMatricula();
			int tam = listaMatricula.size();
			Matricula m = new Matricula(expediente, 0, null, 0, null, null, null, null, null);
			gestionMatricula.anyadirMatricula(m);
			int tamNuevo=gestionMatricula.listaMatricula().size();
			
			assertEquals(tamNuevo-1,tam);

			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
		
	}
	
	@Requisitos({"RF-07,RF-01,RF-02"})
	@Test
	public void testAnyadirMatricula() {
		try {
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			List<Matricula> listaMatricula = gestionMatricula.listaMatricula();
			
			int tamañoinicial = listaMatricula.size();
			
			Matricula matricula = new Matricula(expediente, 0, null, 0, null, null, null, null, null); 
			
			gestionMatricula.anyadirMatricula(matricula);
			
			
			
			int tamañoNuevo=gestionMatricula.listaMatricula().size();
			
			assertEquals(tamañoinicial+1, tamañoNuevo);
			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	@Requisitos({"RF-07,RF-01,RF-02"})
	@Test
	public void testAnyadirMatriculaMAL() {
		try {
			
			
			gestionMatricula.anyadirMatricula(null);
			
			
			
			fail("No debe saltar");
			
		
			
			
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
		
	}
	
	
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}
