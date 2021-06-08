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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class PruebaLeerCSVIT {
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
  

  @Test
  public void pruebaInsertarTiulaciones(){
	  driver.get("http://localhost:8080/ProyectoSII-war/");
	    driver.manage().window().setSize(new Dimension(767, 699));
	    //Me meto en insertar Titulaciones
	    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
	    driver.findElement(By.id("formLeerCSV:selectorImportacion")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("formLeerCSV:selectorImportacion"));
	      dropdown.findElement(By.xpath("//option[. = 'Titulacion']")).click();
	    }
	    driver.findElement(By.xpath("//select[@id=\'formLeerCSV:selectorImportacion\']/option[2]")).click();
	    //driver.findElement(By.id("formLeerCSV:file")).click();
	    
	   
	    File titulacion = new File("Titulacion.csv");
	    
	    driver.findElement(By.id("formLeerCSV:file")).sendKeys(titulacion.getAbsolutePath());
	    driver.findElement(By.id("formLeerCSV:botonInsertar")).click(); 
	    
	    //Comprobar si se han introducido las titulaciones
	    driver.findElement(By.id("formIndex:botonTitulaciones")).click();
	    //Segunda ya que la primera es la que se importa de la bd de prueba
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:1:SalidaCodigo")).getText(), is("1041"));
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:1:SalidaNombre")).getText(), is("Grado en Ingeniería Informática"));
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:1:SalidaCreditos")).getText(), is("240"));
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:5:SalidaCodigo")).getText(), is("1073"));
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:5:SalidaNombre")).getText(), is("Doble Grado en Ingeniería Informatica y Matemáticas"));
	    assertThat(driver.findElement(By.id("Titulaciones:TablaTitulacion:5:SalidaCreditos")).getText(), is("372"));
	    driver.findElement(By.id("Titulaciones:BotonInicio")).click();
  }
  @Test
  public void importarAsignaturaIT() {
    driver.get("http://localhost:8080/ProyectoSII-war/");
    driver.manage().window().setSize(new Dimension(767, 708));
    
    //Necesitamos importar las titulaciones primero para poder referenciarlas con las asignaturas de pruebas que tenemos
    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
    driver.findElement(By.id("formLeerCSV:selectorImportacion")).click();
    {
      WebElement dropdown = driver.findElement(By.id("formLeerCSV:selectorImportacion"));
      dropdown.findElement(By.xpath("//option[. = 'Titulacion']")).click();
    }
    driver.findElement(By.xpath("//select[@id=\'formLeerCSV:selectorImportacion\']/option[2]")).click();
    
    File titulacion = new File("Titulacion.csv");
    driver.findElement(By.id("formLeerCSV:file")).sendKeys(titulacion.getAbsolutePath());
    driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
    driver.findElement(By.id("formLeerCSV:selectorImportacion")).click();
    {
      WebElement dropdown = driver.findElement(By.id("formLeerCSV:selectorImportacion"));
      dropdown.findElement(By.xpath("//option[. = 'Asignatura']")).click();
    }
    driver.findElement(By.xpath("//option[@value=\'as\']")).click();
    File asignaturas = new File("Oferta-asignaturas.csv");
    driver.findElement(By.id("formLeerCSV:file")).sendKeys(asignaturas.getAbsolutePath());
    driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
    
    //Aprovhecharemos que hemos importado todas las asignaturas, para también probar el importar esas asignaturas optativas
    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
    driver.findElement(By.id("formLeerCSV:selectorImportacion")).click();
    {
      WebElement dropdown = driver.findElement(By.id("formLeerCSV:selectorImportacion"));
      dropdown.findElement(By.xpath("//option[. = 'Optativa']")).click();
    }
    driver.findElement(By.xpath("//option[@value=\'op\']")).click();
    File optativas = new File("OptativaPrueba.csv");
    driver.findElement(By.id("formLeerCSV:file")).sendKeys(optativas.getAbsolutePath());
    driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
    
    //Comprobar que se han insertado
    //La primera deberia de ser Calculo para la computación
    driver.findElement(By.id("formIndex:botonAsignaturas")).click();
    //Probamos el segundo ya que el primero de la fila es decir el 0 es el que importa la bd de prueba
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:AReferencia")).getText(), is("50658"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:ACodigo")).getText(), is("101"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:Anombre")).getText(), is("Cálculo para la Computación"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:ACreditos")).getText(), is("0"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:ACPractica")).getText(), is("6"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:ACTeoria")).getText(), is("1"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:ADuracion")).getText(), is("60"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:Aunidad_temporal")).getText(), is("6"));
    assertThat(driver.findElement(By.id("Asignaturas:TablaAsignaturas:1:Aidioma_imparticion")).getText(), is("-"));
    //Ahora comprobaremos si se ha insertado esa asignatura optativa que queriamos dentro del apratado Optativas de asignatura 
    driver.findElement(By.id("Asignaturas:botonOptativasA")).click();
    //Igual que pasa en la prueba de asignaturas pasa aqui
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Oreferencia")).getText(), is("51040"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Ocodigo")).getText(), is("865"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Onombre")).getText(), is("Programación de Robots"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Ocreditos")).getText(), is("0"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:OCpractica")).getText(), is("6"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:OCteoria")).getText(), is("3"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Oduracion")).getText(), is("60"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Ounidad_temporal")).getText(), is("6"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Oidioma_de_imparticion")).getText(), is("35"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Oplazas")).getText(), is("999"));
    assertThat(driver.findElement(By.id("Optativas:TablaOptativas:1:Omencion")).getText(), is("Informática"));
  }
  @Test
  public void pruebaAlumno() {
    driver.get("http://localhost:8080/ProyectoSII-war/");
    driver.manage().window().setSize(new Dimension(1015, 726));
    
    //Necesitamos importar las titulaciones primero para poder referenciarlas con las asignaturas de pruebas que tenemos
    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
    driver.findElement(By.id("formLeerCSV:selectorImportacion")).click();
    {
      WebElement dropdown = driver.findElement(By.id("formLeerCSV:selectorImportacion"));
      dropdown.findElement(By.xpath("//option[. = 'Titulacion']")).click();
    }
    driver.findElement(By.xpath("//select[@id=\'formLeerCSV:selectorImportacion\']/option[2]")).click();
    
    File titulacion = new File("Titulacion.csv");
    driver.findElement(By.id("formLeerCSV:file")).sendKeys(titulacion.getAbsolutePath());
    driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
    
    //Creamos dos grupos, que serán los grupos a los que los alumnos se van a matricular 1A y 2A de la titulacion que se acaba de insertar
    driver.findElement(By.id("formIndex:botonGrupo")).click();
    driver.findElement(By.id("Grupos:Boton_CrearG")).click();
    driver.findElement(By.id("grupo:curso")).sendKeys("1");
    driver.findElement(By.id("grupo:letra")).sendKeys("A");
    driver.findElement(By.id("grupo:turno")).sendKeys("Mañana");
    driver.findElement(By.id("grupo:plazas")).sendKeys("90");
    driver.findElement(By.id("grupo:titulacion")).sendKeys("1041");
    driver.findElement(By.name("grupo:j_idt27")).click();
    driver.findElement(By.name("grupo:j_idt28")).click();
    driver.findElement(By.id("Grupos:Boton_IndiceG")).click();
    driver.findElement(By.id("formIndex:botonGrupo")).click();
    driver.findElement(By.id("Grupos:Boton_CrearG")).click();
    driver.findElement(By.id("grupo:curso")).click();
    driver.findElement(By.id("grupo:curso")).sendKeys("2");
    driver.findElement(By.id("grupo:letra")).sendKeys("A");
    driver.findElement(By.id("grupo:turno")).sendKeys("Mañana");
    driver.findElement(By.id("grupo:plazas")).sendKeys("90");
    driver.findElement(By.id("grupo:titulacion")).sendKeys("1041");
    driver.findElement(By.name("grupo:j_idt27")).click();
    driver.findElement(By.name("grupo:j_idt28")).click();
    driver.findElement(By.id("Grupos:Boton_IndiceG")).click();
    
    //Ya creado los grupoos, es hora de importar estos alumnos
    
    driver.findElement(By.id("formIndex:botonLeerCSV")).click();
    
    
    File alumnos = new File("Alumnos1Fila.csv");
    driver.findElement(By.id("formLeerCSV:file")).sendKeys(alumnos.getAbsolutePath());
    driver.findElement(By.id("formLeerCSV:botonInsertar")).click();
    driver.findElement(By.id("formIndex:botonAlumnos")).click();
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(1)")).getText(), is("7"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(2)")).getText(), is("Carmelita"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(3)")).getText(), is("Enríquez"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(4)")).getText(), is("Navarro"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(5)")).getText(), is("CarmelitaEnriquezNavarro@gustr.com"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(6)")).getText(), is("06104200001@uma.es"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(7)")).getText(), is("795 115 697"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(8)")).getText(), is("795 115 697"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(9)")).getText(), is("Ventanilla de Beas 72"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(10)")).getText(), is("Ourol"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(11)")).getText(), is("MÁLAGA"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(12)")).getText(), is("27865"));
    driver.findElement(By.name("alumno:j_idt37")).click();
    driver.findElement(By.id("formIndex:botonMatriucla")).click();
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:expedientes")).getText(), is("[num_expediente=8]"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:cursoacademico")).getText(), is("2020/2021"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:estado")).getText(), is("inactivo"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:numeroarchivo")).getText(), is("306000001"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:turnopreferete")).getText(), is("Mañana"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:fechamatricula")).getText(), is("2020-09-22 15:06:00.0"));
    assertThat(driver.findElement(By.id("matricula:dataTableMatricula:1:listadoasignatura")).getText(), is("101-,102-,103-,104-,105-,201-"));
    
    
    driver.findElement(By.name("j_idt17:j_idt18")).click();
    
    driver.findElement(By.id("formIndex:botonExpedientes")).click();
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaNumexpediente")).getText(), is("8"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaActivo")).getText(), is("true"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaNotaMedia")).getText(), is("6.32"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditosS")).getText(), is("150.0"));
    assertThat(driver.findElement(By.cssSelector(".ui-datatable-odd > td:nth-child(5)")).getText(), is("60"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditosob")).getText(), is("78"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditosop")).getText(), is("12.0"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditoscf")).getText(), is("0"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditospe")).getText(), is("0"));
    assertThat(driver.findElement(By.id("Expedientes:TablaExpedientes:1:SalidaCreditostf")).getText(), is("0"));
    driver.findElement(By.id("Expedientes:BotonInicio")).click();
  }
  
}
