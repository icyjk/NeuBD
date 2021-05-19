package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestTitulacion {
	
	private static final String Titulacion_EJB = "java:global/classes/TitulacionEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
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
		gestionTitulacion = (GestionTitulacion) ctx.lookup(Titulacion_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);

	}
	@Requisitos({"RF-06"})
	@Test
	public void testEliminarTitulacion() {
		try {
			
			List<Titulacion> titulaciones = gestionTitulacion.listaTitulacion();
			
			int tamañoinicial = titulaciones.size();
			
			Titulacion titulacionaeliminar = titulaciones.get(0);
			
			gestionTitulacion.eliminarTitulacion(titulacionaeliminar.getCodigo());
			
			titulaciones = gestionTitulacion.listaTitulacion();
			
			assertEquals(tamañoinicial-1, titulaciones.size());
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	
	@Requisitos({"RF-06"})
	@Test
	public void testEliminarTitulacionMal() {
		try{
		
			gestionTitulacion.eliminarTitulacion(0);
			fail("Deberia lanzar una excepcion");
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-06"})
	@Test
	public void testBuscarTitulacion() {
		try {
			
			List<Titulacion> titulaciones = gestionTitulacion.listaTitulacion();
			
			Titulacion t = titulaciones.get(0);
			
			Titulacion aux = gestionTitulacion.visualizartitulacion(t.getCodigo());
			assertNotEquals(null, aux);
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-06"})
	@Test
	public void testBuscarTitulacionMal() {
		try {
			
			//centro
			Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
			
			List<Centro> listacentros = new ArrayList<Centro>();
			listacentros.add(centroETSI);
			
			//Titulacion
			Titulacion titulacionInf = new Titulacion(0,"Informatica", 360,listacentros, null, null, null);
			
			gestionTitulacion.visualizartitulacion(titulacionInf.getCodigo());
			
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-06"})
	@Test
	public void testListaTitulacion() {
		try {
			int numtitulaciones = gestionTitulacion.listaTitulacion().size();
			//centro
			Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
			
			List<Centro> listacentros = new ArrayList<Centro>();
			listacentros.add(centroETSI);
			
			//Titulacion
			Titulacion titulacionInf = new Titulacion(45,"Informaatica", 360,listacentros, null, null, null);
			
			gestionTitulacion.ImportarTitulacion(titulacionInf); //Tendria que estar dentro de la BD
			int numActualizado = gestionTitulacion.listaTitulacion().size();
			assertEquals(numtitulaciones+1, numActualizado);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-06"})
	@Test
	public void testModificarTitulacion() {
		try {
			
			Titulacion t = gestionTitulacion.listaTitulacion().get(0);
			int ID=t.getCodigo();
			t.setNombre("maincra");
			
			gestionTitulacion.modificarTitulacion(t);
			
			
			assertEquals("maincra",gestionTitulacion.visualizartitulacion(ID).getNombre());
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-06"})
	@Test
	public void testModificarTitulacionMAL() {
		try {
			
			
			List<Centro> listacentros = new ArrayList<Centro>();
			
			Titulacion titulacionInf = new Titulacion(66,"Informaatica", 360,listacentros, null, null, null);
			titulacionInf.setCodigo(12131123);
			gestionTitulacion.modificarTitulacion(titulacionInf);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-06,RF-01,RF-02"})
	@Test
	public void testImportarTitulacion() {
		try {
			int num = gestionTitulacion.listaTitulacion().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
			List<Centro> listacentros = new ArrayList<Centro>();
			
			Titulacion titulacionInf = new Titulacion(45,"Informaatica", 360,listacentros, null, null, null);
			
			gestionTitulacion.ImportarTitulacion(titulacionInf);
			
			int numNuevo = gestionTitulacion.listaTitulacion().size();
			
		
			assertEquals(num+1, numNuevo);
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-06,RF-01,RF-02"})
	@Test
	public void testImportarTitulacionModificada() {
		try {
			int num = gestionTitulacion.listaTitulacion().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					
			List<Titulacion> t = gestionTitulacion.listaTitulacion();
			Titulacion titulacion = t.get(0);
			titulacion.setCreditos(69);
			gestionTitulacion.ImportarTitulacion(titulacion);
			
			int num2 = gestionTitulacion.listaTitulacion().size();
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
