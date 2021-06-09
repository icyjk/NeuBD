package NeuBDProyectoSIITests;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
// Generated by Selenium IDE
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import es.uma.informatica.sii.anotaciones.Requisitos;

public class PruebaMatriculaIT {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	private static final String UNIDAD_PERSISTENCIA = "ProyectoPU";
	private static final String CONTEXT_ROOT = "http://localhost:8080/ProyectoSII-war/";

	@Before
	public void setUp() {
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSISTENCIA);
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		driver.get("http://localhost:8080/ProyectoSII-war/");
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Requisitos({"RF-07"})
	@Test
	public void pruebaBuscarMatriculaTest() {

		for(int i=0;i<=2;i++) {

			try {
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(967, 804));
				driver.findElement(By.id("formIndex:botonMatriucla")).click();
				driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).click();
				driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).sendKeys("3");
				driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).sendKeys(Keys.ENTER);
				driver.findElement(By.id("matricula:dataTableMatricula:0:expedientes")).click();
				assertThat(driver.findElement(By.id("matricula:dataTableMatricula:0:expedientes")).getText(), is("3"));
				break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Requisitos({"RF-07"})
	@Test
	
	  public void pruebaBuscarMatriculaMalTest() {
	    
		for(int i=0;i<=2;i++) {
			try {
				
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
			    driver.manage().window().setSize(new Dimension(967, 809));
			    driver.findElement(By.id("formIndex:botonMatriucla")).click();
			    driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).click();
			    driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).sendKeys("ramon");
			    driver.findElement(By.id("matricula:dataTableMatricula:globalFilter")).sendKeys(Keys.ENTER);
			    driver.findElement(By.cssSelector("td")).click();
			    assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ninguna matricula con esos filtros"));
				break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	  }
	
	
	@Requisitos({"RF-07"})
	@Test
	  public void pruebaEliminarMatriculaTest() {
	    
		for(int i=0;i<=2;i++) {
			try {
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
			    driver.manage().window().setSize(new Dimension(967, 811));
			    driver.findElement(By.id("formIndex:botonMatriucla")).click();
			    driver.findElement(By.id("matricula:dataTableMatricula:0:BotonEliminar")).click();
			    driver.findElement(By.cssSelector("td")).click();
			    assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ninguna matricula con esos filtros"));
			    break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	  }
	
	@Requisitos({"RF-07"})
	@Test
	@Ignore
	public void pruebaModificarMatriculaTest() {

		for(int i=0;i<=2;i++) {
			try {

				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(967, 816));
				driver.findElement(By.id("formIndex:botonLeerCSV")).click();
				File alumno = new File("Alumnos1Fila.csv");
				driver.findElement(By.id("formLeerCSV:file")).sendKeys(alumno.getAbsolutePath());
				driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
				driver.findElement(By.id("formIndex:botonMatriucla")).click();
				
				driver.findElement(By.id("matricula:dataTableMatricula:CEstado:filter")).sendKeys("inactivo");
				 driver.findElement(By.id("matricula:dataTableMatricula:CEstado:filter")).sendKeys(Keys.ENTER);
				driver.findElement(By.cssSelector(".ui-icon-pencil")).click();
				driver.findElement(By.id("matricula:dataTableMatricula:0:estadoInput")).click();
				driver.findElement(By.id("matricula:dataTableMatricula:0:estadoInput")).sendKeys("activo");
				driver.findElement(By.cssSelector(".ui-icon-check")).click();
				driver.findElement(By.id("matricula:dataTableMatricula:0:estado")).click();
				assertThat(driver.findElement(By.id("matricula:dataTableMatricula:0:estado")).getText(), is("activo"));
				break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}


	}
	
	

}









