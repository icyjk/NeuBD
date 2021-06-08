package NeuBDProyectoSIITests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
import org.openqa.selenium.interactions.Actions;

import es.uma.informatica.sii.anotaciones.Requisitos;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PruebaGrupoIT {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	private static final String UNIDAD_PERSISTENCIA = "ProyectoPU";
	private static final String CONTEXT_ROOT = "http://localhost:8080/ProyectoSII-war/";
	@Before
	public void setUp() {

		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSISTENCIA);
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	  
	

	//Para los tets hemos tenido que usar una estructura de un for junto con un try catch ya que algunas veces saltaba la excepcion StaleElementReferenceException 
    //y otras veces no. Hemos visto aqui https://www.softwaretestingmaterial.com/stale-element-reference-exception-selenium-webdriver/
    //que se soluciona asi.
	
	@Test
	@Requisitos({"RF-09"})
	public void pruebaBuscarGrupo() {


		for(int i=0; i<=2;i++){
			try{

				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 903));
				driver.findElement(By.id("formIndex:botonGrupo")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).sendKeys("2");
				driver.findElement(By.id("Grupos:TablaGrupos:0:id")).click();
				assertThat(driver.findElement(By.id("Grupos:TablaGrupos:0:id")).getText(), is("2"));
				break;
			}
			catch(Exception e){

			}
		}  
		
	
	}	
	
	
	@Test
	@Requisitos({"RF-09"})
	public void pruebaBuscarMalGrupo() {


		for(int i=0; i<=2;i++){
			try{

				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 905));
				driver.findElement(By.id("formIndex:botonGrupo")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).sendKeys("minecraftstadium");
				driver.findElement(By.cssSelector("td")).click();
				assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ningun grupo con esos filtros"));
				break;
			}
			catch(Exception e){

			}
		}  


	}
	
	
	@Test
	@Requisitos({"RF-09"})
	public void pruebaEliminarGrupo() {


		for(int i=0; i<=2;i++){
			try{
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 906));
				driver.findElement(By.id("formIndex:botonGrupo")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:0:BotonEliminar")).click();
				driver.findElement(By.id("formIndex:botonGrupo")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).sendKeys("2");
				driver.findElement(By.cssSelector("td")).click();
				assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ningun grupo con esos filtros"));
				break;
			}
			catch(Exception e){

			}
		} 	

	}
	
	
	
	
	@Test
	@Requisitos({"RF-09"})
	public void pruebaCrearGrupo() {



		for(int i=0; i<=2;i++){
			try{
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 912));
				driver.findElement(By.id("formIndex:botonGrupo")).click();
				driver.findElement(By.id("Grupos:Boton_CrearG")).click();
				driver.findElement(By.id("grupo:curso")).click();
				driver.findElement(By.id("grupo:curso")).sendKeys("225");
				driver.findElement(By.id("grupo:letra")).click();
				driver.findElement(By.id("grupo:letra")).sendKeys("A");
				driver.findElement(By.id("grupo")).click();
				driver.findElement(By.id("grupo:turno")).click();
				driver.findElement(By.id("grupo:turno")).sendKeys("mañana");
				driver.findElement(By.id("grupo")).click();
				driver.findElement(By.id("grupo:ingles")).click();
				driver.findElement(By.id("grupo:asignar")).click();
				driver.findElement(By.id("grupo:asignar")).sendKeys("false");
				driver.findElement(By.id("grupo")).click();
				driver.findElement(By.id("grupo:plazas")).click();
				driver.findElement(By.id("grupo:plazas")).sendKeys("400");
				driver.findElement(By.id("grupo:titulacion")).click();
				driver.findElement(By.id("grupo:titulacion")).sendKeys("66");
				driver.findElement(By.cssSelector("html")).click();
				driver.findElement(By.name("grupo:j_idt27")).click();
				driver.findElement(By.name("grupo:j_idt28")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).click();
				driver.findElement(By.id("Grupos:TablaGrupos:globalFilter")).sendKeys("225");
				driver.findElement(By.id("Grupos:TablaGrupos:0:curso")).click();
				assertThat(driver.findElement(By.id("Grupos:TablaGrupos:0:curso")).getText(), is("225"));
				break;
			}

			catch(Exception e){

			}
		} 	
		 
		 
		 
		 
		
	
	
	 }	  
	  
}
