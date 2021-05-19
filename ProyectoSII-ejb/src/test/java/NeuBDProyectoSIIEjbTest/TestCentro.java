package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;

import static org.junit.Assert.fail;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestCentro {
	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Centro_EJB = "java:global/classes/CentroEJB";
	private static final String Titulacion_EJB = "java:global/classes/TitulacionEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	

	private GestionCentro gestionCentro;
	private GestionTitulacion gestionTitulacion;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionCentro = (GestionCentro) ctx.lookup(Centro_EJB);
		gestionTitulacion= (GestionTitulacion) ctx.lookup(Titulacion_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-18"})
	@Test
	public void testCrearCentro() {
		try{
			int numInicio = gestionCentro.buscarTodosCentros().size();
			
			
			Centro c = new Centro("Prueba", "direccion", "6", null);
			gestionCentro.CrearCentro(c);
			int numActu = gestionCentro.buscarTodosCentros().size();
			assertEquals(numInicio+1, numActu);
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-18"})
	@Test
	public void testmodificarCentro() {
		try{
			Centro c = gestionCentro.buscarTodosCentros().get(0);
			String nombreAntiguo = c.getNombre();
			c.setNombre("Prueba");
			gestionCentro.modificarCentro(c);
			assertNotEquals(nombreAntiguo, "Prueba");
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-18"})
	@Test
	public void testmodificarCentroMal() {
		try{
			Centro c = new Centro("Prueba", "direccion", "6", null);
			gestionCentro.modificarCentro(c);
			fail("Deberia de lanzarse excepcion");
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-18"})
	@Test
	public void testbuscarCentro() {
		try{
			Centro c = gestionCentro.buscarTodosCentros().get(0);
			gestionCentro.buscarCentro(c.getId());
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-18"})
	@Test
	public void testbuscarCentroMal() {
		try{
			gestionCentro.buscarCentro(0);//No existe
			fail("Deberia salir una excepcion");
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-18"})
	@Test
	public void testbuscarTodosCentrosl() {
		try{
			int num = gestionCentro.buscarTodosCentros().size();
			assertEquals(1, num);
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
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
