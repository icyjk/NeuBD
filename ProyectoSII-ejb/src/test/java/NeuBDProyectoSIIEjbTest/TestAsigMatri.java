package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;
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
import org.junit.Test;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Asignatura_matricula;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.NewId_Asignatura_matricula;
import NeuBDProyectoSII.NewId_Matricula_expediente;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsigMatri;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionMatricula;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestAsigMatri {


	private static final String AsigMatriEJB = "java:global/classes/AsigMatriEJB";
	private static final String AsignaturaEJB = "java:global/classes/AsignaturaEJB";
	private static final String MatriculaEJB = "java:global/classes/MatriculaEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAsigMatri gestionAsigMatri;
	private GestionAsignatura gestionAsignatura;
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
		gestionAsigMatri = (GestionAsigMatri) ctx.lookup(AsigMatriEJB);
		gestionAsignatura = (GestionAsignatura) ctx.lookup(AsignaturaEJB);
		gestionMatricula = (GestionMatricula) ctx.lookup(MatriculaEJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Requisitos({"RF-17"})
	@Test
	public void testVisualizarAsigMatri() {
		try {
			
			Asignatura_matricula asigMa = gestionAsigMatri.listaAsigMatri().get(0);
			NewId_Matricula_expediente IdMatriExp = new NewId_Matricula_expediente(asigMa.getMatricula().getExpedientes().getNum_expediente(),asigMa.getMatricula().getCurso_academico());
			
			
			NewId_Asignatura_matricula idAsigMatri = new NewId_Asignatura_matricula(asigMa.getAsigantura().getReferencia(),IdMatriExp);
			Asignatura_matricula asigMa2 = gestionAsigMatri.visualizarAsigMatri(idAsigMatri);
			NewId_Asignatura_matricula idAsigMatri2 = new NewId_Asignatura_matricula(asigMa2.getAsigantura().getReferencia(),IdMatriExp);
			
			
		
			assertTrue(idAsigMatri.equals(idAsigMatri2));	
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
	@Requisitos({"RF-17"})
	@Test
	public void testVisualizarAsigMatriMAL() {
		try {
			
			
			NewId_Matricula_expediente IdMatriExp = new NewId_Matricula_expediente(1234,"20/21");
			NewId_Asignatura_matricula ID  = new NewId_Asignatura_matricula(12,IdMatriExp);
			
			
			gestionAsigMatri.visualizarAsigMatri(ID);
		
			fail("Deberia lanzar excepcion");
			
			
		} catch (NeuBDExceptions e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Requisitos({"RF-17"})
	@Test
	public void testModificarAsigMatri() {
	
		try {
			
			Asignatura_matricula asigMa = gestionAsigMatri.listaAsigMatri().get(0);
			NewId_Matricula_expediente IdMatriExp = new NewId_Matricula_expediente(asigMa.getMatricula().getExpedientes().getNum_expediente(),asigMa.getMatricula().getCurso_academico());
			NewId_Asignatura_matricula ID  = new NewId_Asignatura_matricula(asigMa.getAsigantura().getReferencia(),IdMatriExp);
			asigMa.setAsignacionManual(true);
			
			
			gestionAsigMatri.modificarAsigMatri(asigMa);
			
			
			assertEquals(true,gestionAsigMatri.visualizarAsigMatri(ID).isAsignacionManual() );
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Requisitos({"RF-17"})
	@Test
	public void testModificarAsigMatriMAL() {
	
		try {
			
			Centro centroETSI = new Centro("ola","Caozo","675",null);
			
			List<Centro> listacentros = new ArrayList<Centro>();
			listacentros.add(centroETSI);
			Titulacion titulacionInf = new Titulacion(45,"Inica", 310,listacentros, null, null, null);
			Grupo grupoAinf = new Grupo(1,'B',"TARDE",true, true, "", 50 , titulacionInf, null, null,null,null);
			Asignatura calculo = new Asignatura(616, 1201, 1, 3, 6, false, "MAINCRAo", 1, "ter", 0, "SegundoCuatri", "Español"
					,titulacionInf , null, null, null);
			Expedientes expediente = new Expedientes(true, 3,1, 6, 3, 0, 5, 0, 0, null, null, null);
			Matricula matricula = new Matricula(expediente, "20/21", null,5, null, null, null, null, null);
			
			
			Asignatura_matricula asig_ma= new Asignatura_matricula(calculo,matricula,grupoAinf,false,false);
			
			gestionAsigMatri.modificarAsigMatri(asig_ma);
			
			
			fail("Deberia lanzar excepcion");
		}catch(NeuBDExceptions e) {
			
			e.printStackTrace();
		}
	}
	
	
	@Requisitos({"RF-17,RF-01,RF-02"})
	@Test
	public void testAnyadirAsignatura_matricula() {
		try {
			
			List<Asignatura_matricula> listaAsigMatri = gestionAsigMatri.listaAsigMatri();
			
			int tamañoinicial = listaAsigMatri.size();

			Asignatura a = gestionAsignatura.listaAsignatura().get(1); // la opcional
			
			Matricula m = gestionMatricula.listaMatricula().get(0);
					
			Asignatura_matricula asig_anyadir = new Asignatura_matricula(a, m, null, true, false);
			
			gestionAsigMatri.anyadirAsignatura_matricula(asig_anyadir);
			
			
			
			int tamañoNuevo=gestionAsigMatri.listaAsigMatri().size();
			
			assertEquals(tamañoinicial+1, tamañoNuevo);
			
		
			
			
		} catch (NeuBDExceptions e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
	@Requisitos({"RF-17,RF-01,RF-02"})
	@Test
	public void testAnyadirAsignatura_matriculaMAL() {
	try {
			
	
		
			
			gestionAsigMatri.anyadirAsignatura_matricula(null);
			
			
			
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
