package NeuBDProyectoSIIEjbTest;

import static org.junit.Assert.assertEquals;

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
import static org.junit.Assert.fail;

import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Grupo;
import NeuBDProyectoSII.Grupos_por_asignatura;
import NeuBDProyectoSII.NewId_GrupoPorAsignatura_grupo_asignatura;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIEjb.GestionAsignatura;
import NeuBDProyectoSIIEjb.GestionCentro;
import NeuBDProyectoSIIEjb.GestionGrupo;
import NeuBDProyectoSIIEjb.GestionGrupoPorAsignatura;
import NeuBDProyectoSIIEjb.GestionTitulacion;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class TestGrupoPorAsignatura {
	private static final Logger LOG = Logger.getLogger(TestGrupo.class.getCanonicalName());

	private static final String Grupo_Por_Asignatura_EJB = "java:global/classes/Grupos_por_asignatura_Ejb";
	private static final String Grupo_EJB = "java:global/classes/GrupoEJB";
	private static final String Asignatura_EJB = "java:global/classes/AsignaturaEJB";
	private static final String Titulacion_EJB = "java:global/classes/TitulacionEJB";
	private static final String Centro_EJB = "java:global/classes/CentroEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "ProyectoTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionGrupoPorAsignatura gestionGrupoPorAsignatura;
	private GestionGrupo gestionGrupo;
	private GestionAsignatura gestionAsignatura;
	private GestionTitulacion gestionTitulacion;
	private GestionCentro gestionCentro;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionGrupoPorAsignatura = (GestionGrupoPorAsignatura) ctx.lookup(Grupo_Por_Asignatura_EJB);
		gestionGrupo = (GestionGrupo) ctx.lookup(Grupo_EJB);
		gestionAsignatura = (GestionAsignatura) ctx.lookup(Asignatura_EJB);
		gestionTitulacion = (GestionTitulacion) ctx.lookup(Titulacion_EJB);
		gestionCentro = (GestionCentro) ctx.lookup(Centro_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF-16"})
	@Test
	public void testListaGruposPorAsignatura() {
		try {
			int numAsignaturas = gestionGrupoPorAsignatura.listaGruposPorAsignatura().size();
						//AUX
						//centro
						Centro centroETSI = new Centro("ETSI","Calle ruben del pozo","639004675",null);
						
						List<Centro> listacentros = new ArrayList<Centro>();
						listacentros.add(centroETSI);
						//Titulacion
						Titulacion titulacionInf = new Titulacion(66,"Informatica", 360,listacentros, null, null, null);
						//Asignatura
						Asignatura calculo = new Asignatura(666, 101, 3, 3, 6, false, "Calculo", 1, "caracter", 0, "PrimerCuatri", "Español"
								,titulacionInf , null, null, null);
						
						//Grupo
						Grupo grupoAinf = new Grupo(1,'A',"Mañana",true, true, "", 50 , titulacionInf, null, null,null,null);
			gestionGrupoPorAsignatura.buscarGrupoPorAsignatura(new NewId_GrupoPorAsignatura_grupo_asignatura(grupoAinf.getId(), calculo.getReferencia(), "20/21"));
			int numActualizado = gestionGrupoPorAsignatura.listaGruposPorAsignatura().size();
			assertEquals(numAsignaturas+1, numActualizado);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGruposPorAsignaturaViaAsignatura(){
		try {
			gestionGrupoPorAsignatura.buscarGruposPorAsignaturaViaAsignatura(666);//Esta metido en la BD
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGruposPorAsignaturaViaAsignaturaMal(){
		try {
			gestionGrupoPorAsignatura.buscarGruposPorAsignaturaViaAsignatura(0);//No puede encontrarlo pq no existe esa asignatura en la BD
			fail("Deberia de dar una excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGrupoPorAsignaturaViaCursoAcademic() {
		try {
			gestionGrupoPorAsignatura.buscarGrupoPorAsignaturaViaCursoAcademic("20/21");//Esta metido en la BD
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGrupoPorAsignaturaViaCursoAcademicNo() {
		try {
			gestionGrupoPorAsignatura.buscarGrupoPorAsignaturaViaCursoAcademic("64/65");//No esta metido en la BD
			fail("Deberia de dar una excepcion");
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGrupoPorAsignatura() {
		try {
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			NewId_GrupoPorAsignatura_grupo_asignatura id = new NewId_GrupoPorAsignatura_grupo_asignatura(aInf.getId(), 666, "20/21");
			gestionGrupoPorAsignatura.buscarGrupoPorAsignatura(id);
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testBuscarGrupoPorAsignaturaMal() {
		try {
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			NewId_GrupoPorAsignatura_grupo_asignatura id = new NewId_GrupoPorAsignatura_grupo_asignatura(aInf.getId(), 0, "99/00");
			gestionGrupoPorAsignatura.buscarGrupoPorAsignatura(id);
			fail("Deberia de dar una excepcion");
			
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testModificarGruposPorAsignatura() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			Grupos_por_asignatura grupo = gruposAs.get(0);
			boolean valorAntiguo = grupo.getOferta();
			grupo.setOferta(false);
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			NewId_GrupoPorAsignatura_grupo_asignatura id = new NewId_GrupoPorAsignatura_grupo_asignatura(aInf.getId(), 0, "20/21");
			gestionGrupoPorAsignatura.modificarGruposPorAsignatura(grupo);
			Grupos_por_asignatura aux = gestionGrupoPorAsignatura.buscarGrupoPorAsignatura(id);
			assertEquals(valorAntiguo, aux.getOferta());
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testModificarGruposPorAsignaturaMal() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			Grupos_por_asignatura grupo = gruposAs.get(0);
			boolean valorAntiguo = grupo.getOferta();
			grupo.setOferta(false);
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			NewId_GrupoPorAsignatura_grupo_asignatura id = new NewId_GrupoPorAsignatura_grupo_asignatura(aInf.getId(), 0, "00/01");//No existe
			gestionGrupoPorAsignatura.modificarGruposPorAsignatura(grupo);
			Grupos_por_asignatura aux = gestionGrupoPorAsignatura.buscarGrupoPorAsignatura(id);
			fail("Deberia de haber saltado la excepcion");
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testEliminarGruposPorAsignatura() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numAntiguo = gruposAs.size();
			Grupos_por_asignatura grupo = gruposAs.get(0);
			gestionGrupoPorAsignatura.eliminarGruposPorAsignatura(grupo);
			gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numNuevo = gruposAs.size();
			assertEquals(numAntiguo-1, numNuevo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16"})
	@Test
	public void testEliminarGruposPorAsignaturaMal() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numAntiguo = gruposAs.size();
			Grupos_por_asignatura grupo = new Grupos_por_asignatura();
			gestionGrupoPorAsignatura.eliminarGruposPorAsignatura(grupo);
			gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numNuevo = gruposAs.size();
			assertEquals(numAntiguo, numNuevo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16,RF-01,RF-02"})
	@Test
	public void testCrearGrupoPorAsignatura() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numAntiguo = gruposAs.size();
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			List<Asignatura> listAsi = gestionAsignatura.listaAsignatura();
			Asignatura asi = listAsi.get(0);
			Grupos_por_asignatura grupo = new Grupos_por_asignatura(aInf, asi, "21/22", true, null);
			gestionGrupoPorAsignatura.crearGrupoPorAsignatura(grupo);
			gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numNuevo = gruposAs.size();
			assertEquals(numAntiguo+1, numNuevo);
		} catch (NeuBDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Requisitos({"RF-16,RF-01,RF-02"})
	@Test
	public void testCrearGrupoPorAsignaturaMal() {
		try {
			List<Grupos_por_asignatura> gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numAntiguo = gruposAs.size();
			List<Grupo> grupos = gestionGrupo.listaGrupos();
			Grupo aInf = grupos.get(0);
			List<Asignatura> listAsi = gestionAsignatura.listaAsignatura();
			Asignatura asi = listAsi.get(0);
			Grupos_por_asignatura grupo = new Grupos_por_asignatura(aInf, asi, "20/21", true, null);//Ya existe
			gestionGrupoPorAsignatura.crearGrupoPorAsignatura(grupo);
			gruposAs = gestionGrupoPorAsignatura.listaGruposPorAsignatura();
			int numNuevo = gruposAs.size();
			assertEquals(numAntiguo, numNuevo);
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
