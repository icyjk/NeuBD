package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionExpediente;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestExpediente {
	
	private static final String Expediente_EJB = "java:global/classes/ExpedienteEJB";
	private static final String Alumno_EJB = "java:global/classes/AlumnoEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionExpediente gestionExpediente;
	private GestionAlumno gestionAlumno;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionExpediente = (GestionExpediente) ctx.lookup(Expediente_EJB);
		gestionAlumno = (GestionAlumno) ctx.lookup(Alumno_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);

	}
	
	@Requisitos({"RF-08"})
	@Test
	public void testEliminarExpediente() {
		try {
		
			
			List<Expedientes> expedientes = gestionExpediente.listaExpedientes();
			
			int tamañoinicial = expedientes.size();
			
			Expedientes expedienteaeliminar = expedientes.get(0);
			
			gestionExpediente.eliminarExpediente(expedienteaeliminar);
			
			int expedientees = gestionExpediente.listaExpedientes().size();
			
			assertEquals(tamañoinicial-1, expedientees);
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	@Requisitos({"RF-08"})
	@Test
	public void testEliminarExpedienteMal() {
		try{
		
			Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			expediente.setNum_expediente(0);
			
			gestionExpediente.eliminarExpediente(expediente);
			fail("Deberia lanzar una excepcion");
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-08"})
	@Test
	public void testBuscarExpediente() {
		try {
			
			List<Expedientes> expedientes = gestionExpediente.listaExpedientes();
			
			Expedientes e = expedientes.get(0);
			
			Expedientes aux = gestionExpediente.visualizarExpediente(e);
			assertTrue(e.equals(aux));
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-08"})
	@Test
	public void testBuscarExpedienteMal() {
		try {
			
		Expedientes expediente = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
		expediente.setNum_expediente(0);
			
		gestionExpediente.visualizarExpediente(expediente);
			
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-08"})
	@Test
	public void testModificarExpediente() {
		try {
			
			Expedientes e = gestionExpediente.listaExpedientes().get(0);
			
			e.setCreditos_superado(100);
			
			gestionExpediente.modificarExpediente(e);
			
			
			assertEquals(100,gestionExpediente.visualizarExpediente(e).getCreditos_superado());
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-08"})
	@Test
	public void testModificarExpedienteMAL() {
		try {
			
			
			Expedientes e = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			e.setCreditos_superado(345);
			
			gestionExpediente.modificarExpediente(e);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-08"})
	@Test
	public void testListaExpediente() {
		try {
			
			int numexpedientes = gestionExpediente.listaExpedientes().size();
			
			Expedientes e = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			gestionExpediente.importarExpediente(e);
			
			
			int numActualizado = gestionExpediente.listaExpedientes().size();
			assertEquals(numexpedientes+1, numActualizado);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-08,RF-01,RF-02"})
	@Test
	public void testImportarExpediente() {
		try {
			int num = gestionExpediente.listaExpedientes().size();
			
			
			Expedientes e = new Expedientes(true, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null);
			
			gestionExpediente.importarExpediente(e);
			
			int numNuevo = gestionExpediente.listaExpedientes().size();
			
		
			assertEquals(num+1, numNuevo);
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-08,RF-01,RF-02"})
	@Test
	public void testImportarExpedienteModificada() {
		try {
			List<Expedientes> expedientes = gestionExpediente.listaExpedientes();
			
			int num = expedientes.size();
					
			Expedientes exp = expedientes.get(0);
			
			exp.setCreditos_superado(453);
			
			gestionExpediente.importarExpediente(exp);
			
			int num2 = gestionExpediente.listaExpedientes().size();
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
