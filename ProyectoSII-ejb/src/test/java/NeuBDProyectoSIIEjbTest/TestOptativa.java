package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.fail;

import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionOptativa;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestOptativa {
	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Asignatura_EJB = "java:global/classes/AsignaturaEJB";
	private static final String Titulacion_EJB = "java:global/classes/TitulacionEJB";
	private static final String Centro_EJB = "java:global/classes/CentroEJB";
	private static final String Optativa_EJB = "java:global/classes/OptativaEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAsignatura gestionAsignatura;
	private GestionTitulacion gestionTitulacion;
	private GestionCentro gestionCentro;
	private GestionOptativa gestionOptativa;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionAsignatura = (GestionAsignatura) ctx.lookup(Asignatura_EJB);
		gestionTitulacion = (GestionTitulacion) ctx.lookup(Titulacion_EJB);
		gestionCentro = (GestionCentro) ctx.lookup(Centro_EJB);
		gestionOptativa = (GestionOptativa) ctx.lookup(Optativa_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF-04"})
	@Test
	public void testListaOptativa() {
		try {
			int numAntiguo = gestionOptativa.listaOptativa().size();
			Optativa opt = new Optativa(10, "Informatica",null);
			opt.setReferencia(10);opt.setNombre("Prueba");opt.setCodigo(102);
			Titulacion t = gestionTitulacion.listaTitulacion().get(0);
			opt.setTitulacion(t);
			gestionOptativa.insertarOptativa(opt, opt.getReferencia());
			int numNuevo = gestionOptativa.listaOptativa().size();
			assertEquals(numAntiguo+1, numNuevo);
			assertEquals(1, numAntiguo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testBuscarOptativa() {
		try {
			gestionOptativa.buscarOptativa(69);//ESTA DENTRO DE LA BD
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testBuscarOptativaNoEsUnaOptativa() {
		try {
			gestionOptativa.buscarOptativa(666);//ESTA DENTRO DE LA BD
			fail("Deberia de lanzar una excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testBuscarOptativaMal() {
		try {
			gestionOptativa.buscarOptativa(0);// NO ESTA DENTRO DE LA BD
			fail("Deberia de lanzar una excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testModificarOptativa() {
		try {
			Optativa opt = gestionOptativa.listaOptativa().get(0);
			int plazasAntiguas = opt.getPlazas();
			opt.setPlazas(150);
			gestionOptativa.modificarOptativa(opt);
			Optativa aux = gestionOptativa.buscarOptativa(opt.getReferencia());
			int plazasNuevas = aux.getPlazas();
			assertNotEquals(plazasAntiguas, plazasNuevas);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testModificarOptativaMal() {
		try {
			Optativa opt = gestionOptativa.buscarOptativa(99999);// No existe
			int plazasAntiguas = opt.getPlazas();
			opt.setPlazas(150);
			gestionOptativa.modificarOptativa(opt);
			Optativa aux = gestionOptativa.buscarOptativa(opt.getReferencia());
			int plazasNuevas = aux.getPlazas();
			fail("Deberia de lanzar una excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testEliminaOptativa() {
		try {
			int numAntiguo = gestionOptativa.listaOptativa().size();
			Optativa opt = gestionOptativa.listaOptativa().get(0);
			gestionOptativa.eliminaOptativa(opt);
			int numNuevo = gestionOptativa.listaOptativa().size();
			assertEquals(numAntiguo-1, numNuevo);
			assertEquals(1, numAntiguo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testEliminaOptativaMal() {
		try {
			int numAntiguo = gestionOptativa.listaOptativa().size();
			Optativa opt = gestionOptativa.buscarOptativa(1111111); //No existe
			gestionOptativa.eliminaOptativa(opt);
			int numNuevo = gestionOptativa.listaOptativa().size();
			assertEquals(numAntiguo, numNuevo);
			assertEquals(1, numAntiguo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04,RF-02"})
	@Test
	public void testinsertarOptativa() {
		try {
			int numAntiguo = gestionOptativa.listaOptativa().size();
			Optativa opt = new Optativa(10, "Informatica",null);
			opt.setReferencia(10);opt.setNombre("Prueba");opt.setCodigo(102);
			Titulacion t = gestionTitulacion.listaTitulacion().get(0);
			opt.setTitulacion(t);
			gestionOptativa.insertarOptativa(opt, opt.getReferencia());
			int numNuevo = gestionOptativa.listaOptativa().size();
			assertEquals(numAntiguo+1, numNuevo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04,RF-02"})
	@Test
	public void testinsertarOptativaAUnaAsignaturaExistente() {
		try {
			int numAntiguo = gestionOptativa.listaOptativa().size();
			Optativa opt = new Optativa(10, "Informatica",null);
			opt.setReferencia(666);opt.setNombre("Prueba");opt.setCodigo(102);
			Titulacion t = gestionTitulacion.listaTitulacion().get(0);
			opt.setTitulacion(t);
			gestionOptativa.insertarOptativa(opt, opt.getReferencia());
			int numNuevo = gestionOptativa.listaOptativa().size();
			assertEquals(numAntiguo+1, numNuevo);
		} catch (NeuBDExceptions e) {
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
