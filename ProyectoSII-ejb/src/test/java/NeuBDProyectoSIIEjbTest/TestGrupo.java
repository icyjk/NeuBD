package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;


public class TestGrupo {

	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Grupo_EJB = "java:global/classes/GrupoEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionGrupo gestionGrupo;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionGrupo = (GestionGrupo) ctx.lookup(Grupo_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-09"})
	@Test
	public void testEliminarGrupo() {
		try {
			
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			
			int tamañoinicial = grupos.size();
			
			Grupo grupoaeliminar = grupos.get(0);
			
			gestionGrupo.eliminarGrupo(grupoaeliminar.getId());
			
			grupos = gestionGrupo.listaGrupos();
			
			assertEquals(tamañoinicial-1, grupos.size());
			
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	
	@Requisitos({"RF-09"})
	@Test
	public void testEliminarGrupoMal() {
		try{
		
			gestionGrupo.eliminarGrupo(0);
			fail("Deberia lanzar una excepcion");
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-09"})
	@Test
	public void testBuscarGrupo() {
		try {
			
			List<Grupo> lista = gestionGrupo.listaGrupos();
			
			Grupo g = lista.get(0);
			
			Grupo aux = gestionGrupo.visualizarGrupo(g.getId());
			
			assertNotEquals(null, aux);
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-09"})
	@Test
	public void testBuscarGrupoMal() {
		try {
			
			
			Grupo aux = gestionGrupo.visualizarGrupo(0);
			
			assertNotEquals(null, aux);
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Requisitos({"RF-09"})
	@Test
	public void testModificarGrupo() {
		try {
			
			Grupo g = gestionGrupo.listaGrupos().get(0);
			int curso = g.getPlazas();
			g.setPlazas(1000);
			
			
			gestionGrupo.modificarGrupo(g);
			
			
			assertNotEquals(curso,gestionGrupo.visualizarGrupo(g.getId()).getPlazas());
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-09"})
	@Test
	public void testModificarGrupoMal() {
		try {
			
			Grupo grupoA = new Grupo(1,'A',"Mañana",true, true, "", 50 , null, null, null,null,null);
			grupoA.setId(0);
			
			gestionGrupo.modificarGrupo(grupoA);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-09"})
	@Test
	public void testListaGrupoyCrear() {
		try {
			int numgrupos = gestionGrupo.listaGrupos().size();
			
			Grupo grupoA = new Grupo(4,'A',"Mañana",true, true, "", 50 , null, null, null,null,null);
			
			
			gestionGrupo.crearGrupo(grupoA); //Tendria que estar dentro de la BD
			
			int numActualizado = gestionGrupo.listaGrupos().size();
			assertEquals(numgrupos+1, numActualizado);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-09"})
	@Test
	public void testCrearGrupoMal() {
		
		try {
			int numgrupos = gestionGrupo.listaGrupos().size();
			
			Grupo grupoAinf = new Grupo(1,'A',"Mañana",true, true, "", 50 , null, null, null,null,null);
			
			gestionGrupo.crearGrupo(grupoAinf); //Tendria que estar dentro de la BD
			
			int numActualizado = gestionGrupo.listaGrupos().size();
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Requisitos({"RF-09"})
	@Test
	public void testAsociarGrupo(){
		
		try {
			Grupo grupoAinf = new Grupo(4,'A',"Mañana",true, true, "", 50 , null, null, null,null,null);
			gestionGrupo.crearGrupo(grupoAinf);
			
			
			List<Grupo> lista = gestionGrupo.listaGrupos();
			Grupo grupo1 = lista.get(0);
			Grupo grupo2 = lista.get(1);
			
			gestionGrupo.asociarGrupo(grupo1, grupo2);
			
			assertTrue(grupo2.equals(grupo1.getGrupos()));
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Requisitos({"RF-09"})
	@Test
	public void testAsociarGrupoMal(){
		
		try {
			Grupo grupoAinf = new Grupo(4,'A',"Mañana",true, true, "", 50 , null, null, null,null,null);
			
			List<Grupo> lista = gestionGrupo.listaGrupos();
			Grupo grupo1 = lista.get(0);
			
			gestionGrupo.asociarGrupo(grupo1, grupoAinf);
			
			fail("Deberia lanzar excepcion");
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
