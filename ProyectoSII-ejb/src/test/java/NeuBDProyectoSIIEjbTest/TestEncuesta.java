package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
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

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionEncuesta;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestEncuesta {
	private static final Logger LOG = Logger.getLogger(TestAlumno.class.getCanonicalName());

	private static final String EncuestaEJB = "java:global/classes/EncuestaEJB";
	private static final String ExpedienteEJB = "java:global/classes/ExpedienteEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionEncuesta gestionEncuesta;
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
		gestionEncuesta = (GestionEncuesta) ctx.lookup(EncuestaEJB);
		gestionExpediente = (GestionExpediente) ctx.lookup(ExpedienteEJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-14"})
	@Test 
	public void testEliminarEncuesta() {
		try {
			
			List<Encuesta> listaEncuesta = gestionEncuesta.listaEncuestas();
			
			int tamañoinicial = listaEncuesta.size();
			
			Encuesta Encuestaaeliminar = listaEncuesta.get(0);
			
			gestionEncuesta.eliminarEncuesta(Encuestaaeliminar);
			
			listaEncuesta = gestionEncuesta.listaEncuestas();
			
			assertEquals(tamañoinicial-1, listaEncuesta.size());
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	@Requisitos({"RF-14"})
	@Test
	public void testEliminarEncuestaMAL() {
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			Encuesta encuesta = new Encuesta(new Date(11,1,22), expediente, null);
			gestionEncuesta.eliminarEncuesta(encuesta);
			
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-14"})
	@Test 
	public void testVisualizarEncuesta() {
		try {
				
			Encuesta e = gestionEncuesta.listaEncuestas().get(0);
							
			
			assertTrue(e.equals(gestionEncuesta.visualizarEncuesta(e)));	
				
				
			} catch (NeuBDExceptions e) {
				fail("No debería lanzarse excepción");
			}
			
			
		}
	
	
	@Requisitos({"RF-14"})
	@Test
	public void testVisualizarEncuestaMAL() {
		try {
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			Encuesta encuesta = new Encuesta(new Date(11,1,22), expediente, null);
			
			gestionEncuesta.visualizarEncuesta(encuesta);
		
			fail("Deberia lanzar excepcion");
			
			
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
		
		
	}
	@Requisitos({"RF-14"})
	@Test
	public void testModificarEncuesta() {
		try {
			
			
			Encuesta e = gestionEncuesta.listaEncuestas().get(0);
			Expedientes expAntiguo = e.getExpedientes();
			
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 4, 0, 0, 0, 0, null, null, null);
			e.setExpedientes(expediente);
			
			gestionEncuesta.modificarEncuesta(e);
			Expedientes expNuevo = gestionEncuesta.visualizarEncuesta(e).getExpedientes();
			
			
			assertNotEquals(expAntiguo,expNuevo);
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-14"})
	@Test
	public void testModificarEncuestaMAL() {
		try {
			
			Encuesta e = gestionEncuesta.listaEncuestas().get(0);
			Date date = new Date (1,1,1); 
			Encuesta encuestamal = new Encuesta(date, new Expedientes(true, 0, 0, 0, 4, 0, 0, 0, 0, null, null, null), null);

			gestionEncuesta.modificarEncuesta(encuestamal);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-14"})
	@Test
	public void testListaEncuesta() {
		
		try {
			

			List<Encuesta> listaEncuesta = gestionEncuesta.listaEncuestas();
			int tam = listaEncuesta.size();
			Date date = new Date(22,4,21);
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			Encuesta e = new Encuesta(date, expediente, null);
			gestionEncuesta.ImportarEncuesta(e);
			int tamNuevo=gestionEncuesta.listaEncuestas().size();
			
			assertEquals(tamNuevo-1,tam);

			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	@Requisitos({"RF-14"})
	@Test
	public void testImportarEncuesta() {
		try {
			int num = gestionEncuesta.listaEncuestas().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//ce
			Date fecha2 = new Date(11,52,3);
			Encuesta encuesta = new Encuesta(fecha2, gestionExpediente.listaExpedientes().get(0), null);
			gestionEncuesta.ImportarEncuesta(encuesta);
			int actual = gestionEncuesta.listaEncuestas().size();
			
			assertEquals(num+1, actual);
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-14"})
	@Test
	public void testImportarEncuestaModificada() {
		try {
			int num = gestionEncuesta.listaEncuestas().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
			List<Encuesta> lista = gestionEncuesta.listaEncuestas();
			Encuesta encuesta = lista.get(0);
			encuesta.setGrupos_por_asignatura(null);
			gestionEncuesta.ImportarEncuesta(encuesta);
			
			int num2 = gestionEncuesta.listaEncuestas().size();
			assertEquals(num, num2);
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
