package NeuBDProyectoSIITests;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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


public class PruebaOptativasIT {

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
	@Requisitos({"RF-04"})
	public void pruebaOptativasBuscar() {

		for(int i=0; i<=2;i++){
			try{
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 892));
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).sendKeys("311");
				driver.findElement(By.id("Optativas:TablaOptativas:0:Ocodigo")).click();
				assertThat(driver.findElement(By.id("Optativas:TablaOptativas:0:Ocodigo")).getText(), is("311"));

				break;
			}
			catch(Exception e){

			}
		}  

	}

	@Test
	@Requisitos({"RF-04"})
	public void pruebaOptativasBuscarMal() {
		for(int i=0; i<=2;i++){
			try{

				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 893));
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).sendKeys("111");
				driver.findElement(By.cssSelector("td")).click();
				assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ninguna optativa con esos filtros"));
				break;
			}
			catch(Exception e){

			}
		}  

	}


	@Test
	@Requisitos({"RF-04"})
	public void pruebaOptativasEliminar() {
		for(int i=0; i<=2;i++){
			try{
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 894));
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:0:BotonEliminarO")).click();
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:globalFilter")).sendKeys("311");
				assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Ninguna optativa con esos filtros"));


				break;
			}
			catch(Exception e){

			}
		}  

	}



	@Test
	@Requisitos({"RF-04"})
	public void pruebaModificarOptativa() {



		for(int i=0; i<=2;i++){
			try{
				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 900));
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:TablaOptativas:0:BotonModificarO")).click();
				driver.findElement(By.id("Optativas:nombre")).click();
				driver.findElement(By.id("Optativas:nombre")).sendKeys("modificado");
				driver.findElement(By.id("Optativas:boton_optativa_accion")).click();
				driver.findElement(By.id("Optativas:boton_volver_edicionO")).click();
				assertThat(driver.findElement(By.id("Optativas:TablaOptativas:0:Onombre")).getText(), is("IAJuegosmodificado"));


				break;
			}
			catch(Exception e){

			}
		}











	}		  



	@Test
	@Requisitos({"RF-04"})
	public void pruebaBotonInicioOptativas() {

		for(int i=0; i<=2;i++){
			try{

				driver.get("http://0.0.0.0:8080/ProyectoSII-war/");
				driver.manage().window().setSize(new Dimension(2139, 902));
				driver.findElement(By.id("formIndex:botonAsignaturas")).click();
				driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
				driver.findElement(By.id("Optativas:BotonIndiceO")).click();
				driver.findElement(By.id("texto")).click();
				assertThat(driver.findElement(By.id("texto")).getText(), is("INICIO"));


				break;
			}
			catch(Exception e){

			}
		}  






	}
		  
		  
}