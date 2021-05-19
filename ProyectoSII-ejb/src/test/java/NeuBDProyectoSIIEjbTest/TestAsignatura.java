package NeuBDProyectoSIIEjbTest;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestAsignatura {
	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Asignatura_EJB = "java:global/classes/AsignaturaEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAsignatura gestionAsignatura;
	
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
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF-04"})
	@Test
	public void testBuscarAsignaturas() {
		try {
			//AUX
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			gestionAsignatura.ImportarAsignatura(a); //Tendria que estar dentro de la BD
			Asignatura aux = gestionAsignatura.buscarAsignatura(a.getReferencia());
			assertNotEquals(null, aux);
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testBuscarAsignaturaMal() {
		try {
			//AUX
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			gestionAsignatura.buscarAsignatura(a.getReferencia());
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testListaAsignatura() {
		try {
			int numAsignaturas = gestionAsignatura.listaAsignatura().size();
			//AUX
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
					Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
							,titulacionInf , null, null, null);
					gestionAsignatura.ImportarAsignatura(a); //Tendria que estar dentro de la BD
			int numActualizado = gestionAsignatura.listaAsignatura().size();
			assertEquals(numAsignaturas+1, numActualizado);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testbuscarAsignaturaPorTitulacion() {

		try {
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			gestionAsignatura.ImportarAsignatura(a);
			gestionAsignatura.buscarAsignaturaPorTitulacion(titulacionInf.getCodigo());
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testbuscarAsignaturaPorTitulacionAsignaturaNoExiste() {

		try {
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			List<Asignatura> lista = gestionAsignatura.buscarAsignaturaPorTitulacion(titulacionInf.getCodigo());
			boolean p = lista.contains(a);
			assertEquals(false, p);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testbuscarAsignaturaPorTitulacionTitulacioNoExiste() {

		try {
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(0,"Prueba", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(100, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			gestionAsignatura.buscarAsignaturaPorTitulacion(titulacionInf.getCodigo());
			fail("Deberia lanzar excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testModificarAsignatura() {
		try {
			Asignatura a = gestionAsignatura.buscarAsignatura(666); //Esta asgignatura esta metida en la BBDD y tiene de creditos totales 6
			a.setCreditos(5);
			gestionAsignatura.modificarAsignatura(a);
			Asignatura actu = gestionAsignatura.buscarAsignatura(666);
			assertEquals(5, actu.getCreditos());
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testModificarAsignaturaMal() {
		try {
			Asignatura a = gestionAsignatura.buscarAsignatura(0); //Esta asgignatura no existe ya que no puede tener de primary key un 0
			a.setCreditos(5);
			gestionAsignatura.modificarAsignatura(a);
			Asignatura actu = gestionAsignatura.buscarAsignatura(666);
			fail("Deberia lanzar una excepcion");
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testEliminaAsignatura() {
		try{
			int num = gestionAsignatura.listaAsignatura().size();
			Asignatura a = gestionAsignatura.buscarAsignatura(666); //Esta asgignatura esta metida en la BBDD
			gestionAsignatura.eliminaAsignatura(a);
			int actu = gestionAsignatura.listaAsignatura().size();
			assertEquals(num-1, actu);
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04"})
	@Test
	public void testEliminaAsignaturaMal() {
		try{
			int num = gestionAsignatura.listaAsignatura().size();
			Asignatura a = gestionAsignatura.buscarAsignatura(0); //Esta asgignatura no esta metida en la BBDD pq no puede tener pk = 0
			gestionAsignatura.eliminaAsignatura(a);
			int actu = gestionAsignatura.listaAsignatura().size();
			fail("Deberia lanzar una excepcion");
			
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04,RF-02"})
	@Test
	public void testImportarAsignatura() {
		try {
			int num = gestionAsignatura.listaAsignatura().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(101, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);
			gestionAsignatura.ImportarAsignatura(a);
			int actu = gestionAsignatura.listaAsignatura().size();
			assertEquals(num+1, actu);
		}catch(NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-04,RF-02"})
	@Test
	public void testImportarAsignaturaModificada() {
		try {
			int num = gestionAsignatura.listaAsignatura().size();
			
					//AUX QUE YA EXISTE EN BASE DATOS
					//centro
					Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
					
					List<Centro> listacentros = new ArrayList<Centro>();
					listacentros.add(centroETSI);
					//Titulacion
					Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
			
			Asignatura a = new Asignatura(666, 0, 0, 0, 0, false, "prueba", 1, "caracter", 0, "PrimerCuatri", "Español"
					,titulacionInf , null, null, null);//Esta PK ya existe
			gestionAsignatura.ImportarAsignatura(a);
			int actu = gestionAsignatura.listaAsignatura().size();
			assertEquals(num, actu);
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
