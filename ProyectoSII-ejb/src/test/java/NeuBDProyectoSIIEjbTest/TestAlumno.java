package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
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
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSIIEjb.GestionAlumno;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestAlumno {

	
	private static final String AlumnoEJB = "java:global/classes/AlumnoEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
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
		gestionAlumno = (GestionAlumno) ctx.lookup(AlumnoEJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-05"})
	@Test
	public void testEliminarAlumno() {
		try {
			
			List<Alumno> listaAlumnos = gestionAlumno.listaAlumno();
			
			int tamañoinicial = listaAlumnos.size();
			
			Alumno Alumnoaeliminar = listaAlumnos.get(0);
			
			gestionAlumno.eliminarAlumno(Alumnoaeliminar.getId());
			
			listaAlumnos = gestionAlumno.listaAlumno();
			
			assertEquals(tamañoinicial-1, listaAlumnos.size());
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
		
		
	@Requisitos({"RF-05"})
	@Test
	public void testEliminarAlumnoMAL() {
		try {
				
		
				
			gestionAlumno.eliminarAlumno(0);
				
		
			
			fail("Deberia lanzar excepcion");
				
				
			} catch (NeuBDExceptions e) {
				e.printStackTrace();
			}
					
		
		
		
	}
	
	@Requisitos({"RF-05"})
	@Test
	public void testVisualizarAlumno() {
	try {
			
			Alumno alumno = gestionAlumno.listaAlumno().get(0);
			int ID = alumno.getId();
			
		
			assertTrue(alumno.equals(gestionAlumno.visualizarAlumno(ID)));	
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
		
	}
	
	@Test
	public void testVisualizarAlumnoMAL() {
		try {
			
			Alumno alumno = gestionAlumno.listaAlumno().get(0);
			int ID = alumno.getId();
			
			gestionAlumno.visualizarAlumno(0);
		
			fail("Deberia lanzar excepcion");
			
			
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Requisitos({"RF-05"})
	@Test
	public void testModificarAlumno() {
		try {
			
			Alumno a = gestionAlumno.listaAlumno().get(0);
			a.setNombre("Ramon");
			
			gestionAlumno.modificarAlumno(a);
			
			
			assertEquals("Ramon",gestionAlumno.visualizarAlumno(a.getId()).getNombre() );
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-05"})
	@Test
	public void testModificarAlumnoMAL() {
		try {
			
			
			Alumno alumno = new Alumno("EEEE","Grs","Moa","ola@pa.com","o@uma.es","8437","callsa n25","ma","aga","10",null," ", null);
			//alumno.setID("12131123");
			gestionAlumno.modificarAlumno(alumno);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	
	
	////// ESTOS 2 TEMRINAR MAL
	
	@Requisitos({"RF-05"})
	@Test
	public void testListaAlumno() {
		
		try {
			
			List<Alumno> listaAlumnos = gestionAlumno.listaAlumno();
			int tam = listaAlumnos.size();
			Alumno alumno = new Alumno();
			gestionAlumno.anyadirAlumno(alumno);
			int tamNuevo=gestionAlumno.listaAlumno().size();
			
			assertEquals(tamNuevo-1,tam);

			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
		
	}
	
	
	
	
	@Requisitos({"RF-05,RF-01,RF-02"})
	@Test
	public void testAnyadirAlumno() {
		try {
			
			List<Alumno> listaAlumnos = gestionAlumno.listaAlumno();
			
			int tamañoinicial = listaAlumnos.size();
			
			Alumno alumnoAanyadir = new Alumno();
			
			gestionAlumno.anyadirAlumno(alumnoAanyadir);
			
			
			
			int tamañoNuevo=gestionAlumno.listaAlumno().size();
			
			assertEquals(tamañoinicial+1, tamañoNuevo);
			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
	
	
	@Requisitos({"RF-05,RF-01,RF-02"})
	@Test
	public void testAnyadirAlumnoMAL() {
		try {
			
	
		
			
			gestionAlumno.anyadirAlumno(null);
			
			
			
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
