package NeuBDProyectoSIIEjb;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;
import NeuBDProyectoSIIexceptions.TItulacionNoEncontradaException;



@Stateless
public class LeerCSVEJB implements GestionLeerCSV{
	
	public void insertarAlumnoCSV(Titulacion titu,String route)throws AlumnoSInDatosParaCrearException{
        try { 
        	Reader reader = Files.newBufferedReader(Paths.get(route));
        	 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                     
        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
        	 
            for (CSVRecord csvRecord : csvParser) {
            	if(csvRecord.getRecordNumber() !=1) {
                // Accediendo a los valores por el indice de la columna
	                String DNI = csvRecord.get(0);
	                String nombre = csvRecord.get(1);
	                String priApellido = csvRecord.get(2);
	                String secApellido = csvRecord.get(3);
	                String numExp = csvRecord.get(4);
	                String numArchivo = csvRecord.get(5);
	                String emInstitucional = csvRecord.get(6);
	                String emPersonal = csvRecord.get(7);
	                String telefono = csvRecord.get(8);
	                String movil = csvRecord.get(9);
	                String direccion = csvRecord.get(10);
	                String localidad = csvRecord.get(11);
	                String provincia = csvRecord.get(12);
	                String cp = csvRecord.get(13);
	                String fechaMatricula = csvRecord.get(14);
	                String turnoPref = csvRecord.get(15);
	                String gruposAsig = csvRecord.get(16);
	                double notaMedia = Double.parseDouble(csvRecord.get(17));
	                int credSuperados = Integer.parseInt(csvRecord.get(18));
	                int CREDITOS_FB = Integer.parseInt(csvRecord.get(19));
	                int CREDITOS_OB = Integer.parseInt(csvRecord.get(20));
	                int CREDITOS_OP = Integer.parseInt(csvRecord.get(21));
	                int CREDITOS_CF = Integer.parseInt(csvRecord.get(22));
	                int CREDITOS_PE = Integer.parseInt(csvRecord.get(23));
	                int CREDITOS_TF = Integer.parseInt(csvRecord.get(24));

	                Alumno alum=new Alumno(DNI,nombre,priApellido,secApellido,emPersonal, emInstitucional, movil, telefono, direccion, localidad, null, cp, provincia);
	                Expedientes e= new Expedientes(true, notaMedia, credSuperados, CREDITOS_FB, CREDITOS_OB, CREDITOS_OP, CREDITOS_CF, CREDITOS_PE, CREDITOS_TF, titu, alum, null);
	                AlumnoEJB ej=new AlumnoEJB();
	                List<Expedientes> lista=new ArrayList<Expedientes>();
	                lista.add(e);
	                alum.setExpedientes(lista);
	                ej.anyadirAlumno(alum);
	                
	                
	                /// TIENES QUE AÑADIR LA MATRICULA Y EL EXPEDIENTE
	                //// Y CON gruposAsig TIENES QUE LLAMAR A ASIG MATRI
	                //// LA COSA ES QE TE PASA UN -303, -305 O ALGO ASI Y TIENES QUE HACER
	                /// USE DELIMITER Y Y CON ESA ASIGNATURA BUSCARLA EN LA BASE DE DATOS Y AÑADIR ESA ASIG MATRI

//                System.out.println("Record No - " + csvRecord.getRecordNumber());
//                System.out.println("---------------");
//                System.out.println("DNI : " + DNI);
//                System.out.println("Nombre : " + nombre);
//                System.out.println("priApellido : " + priApellido);
//                System.out.println("secApellido : " + secApellido);
//                System.out.println("---------------\n\n");
	                
               
            	}
            }
        }
        catch (IOException e) {  
	        e.printStackTrace();  
	    } 
    }
	
	public void insertarTitulacionCSV(Centro cen, String route)throws NeuBDExceptions{
		 try { 
	        	Reader reader = Files.newBufferedReader(Paths.get(route));
	        	 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
	                     
	        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
	        	 
	            for (CSVRecord csvRecord : csvParser) {
	            	if(csvRecord.getRecordNumber() !=1) {
	                // Accediendo a los valores por el indice de la columna
		                int codigo = Integer.parseInt(csvRecord.get(0));
		                String nombre = csvRecord.get(1);
		                int creditos = Integer.parseInt(csvRecord.get(2));
		                
		                List<Centro> lista=new ArrayList<Centro>();
		                lista.add(cen);
		                
		                Titulacion titu=new Titulacion(codigo,nombre, creditos, lista, null, null, null);
		                TitulacionEJB ejb= new TitulacionEJB();
		                ejb.ImportarTitulacion(titu);
	            	}
	            }
		 }catch (IOException e) {  
		        e.printStackTrace();
		 } 
	            
	}
	
	public void insertarAsignaturaCSV(String route)throws NeuBDExceptions{
		
		 try { 
	        	Reader reader = Files.newBufferedReader(Paths.get(route));
	        	 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
	                     
	        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
	        	boolean ofertadaB;
	            for (CSVRecord csvRecord : csvParser) {
	            	if(csvRecord.getRecordNumber() !=1) {
	                // Accediendo a los valores por el indice de la columna
		                int titulacion = Integer.parseInt(csvRecord.get(0));
		                String ofertada = csvRecord.get(1);
		                if(ofertada=="Sí") {
		                	ofertadaB=true;
		                }else {
		                	ofertadaB=false;
		                }
		                int codigo = Integer.parseInt(csvRecord.get(2));
		                int referencia = Integer.parseInt(csvRecord.get(3));
		                String nombre = csvRecord.get(4);
		                int creTeo = Integer.parseInt(csvRecord.get(5));
		                int crePrac = Integer.parseInt(csvRecord.get(6));
		                int creditos = Integer.parseInt(csvRecord.get(7));
		                String unidadTemporal = csvRecord.get(8);
		                String plazas = csvRecord.get(9);
		                String otroIdioma = csvRecord.get(10);
		                TitulacionEJB ejb1 =new TitulacionEJB();
		                Titulacion titu= ejb1.visualizartitulacion(titulacion);
		                Asignatura asig=new Asignatura(referencia, codigo, crePrac, creTeo, creditos, ofertadaB, nombre, codigo/100, "", 60, unidadTemporal, otroIdioma, titu, null, null, null);
		                
		                List<Asignatura> lista=new ArrayList<Asignatura>();
		                lista.add(asig);
		               
		                AsignaturaEJB ejb = new AsignaturaEJB();
		                
		                ejb.ImportarAsignatura(asig);
	            	}
	            }
		 }catch (IOException e) {  
		        e.printStackTrace();
		 } 
	            
	}
	
	public void insertarOptativaCSV(String route,Titulacion tit)throws NeuBDExceptions{
		try { 
        	Reader reader = Files.newBufferedReader(Paths.get(route));
        	 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                     
        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
        	List<Optativa> lista = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
            	if(csvRecord.getRecordNumber() !=1) {
                // Accediendo a los valores por el indice de la columna
	                int referencia = Integer.parseInt(csvRecord.get(0));
	                int plazas = Integer.parseInt(csvRecord.get(1));
	                String mencion = csvRecord.get(2);
	                
	                Optativa op=new Optativa(plazas, mencion,tit);
	                lista.add(op);
	                OptativaEJB ejb=new OptativaEJB();
	                ejb.insertarOptativa(op, referencia);
	               
            	}
            	 tit.setOptativa(lista);
            	 TitulacionEJB ejb2 = new TitulacionEJB();
            	 ejb2.modificarTitulacion(tit);
            }
	 }catch (IOException e) {  
	        e.printStackTrace();
	 } 
	}
	public void insertarEncuestaCSV(String route, Expedientes exp) throws NeuBDExceptions{
		try { 
        	Reader reader = Files.newBufferedReader(Paths.get(route));
        	 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                     
        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
        	
            for (CSVRecord csvRecord : csvParser) {
            	if(csvRecord.getRecordNumber() !=1) {
            		String fecha = csvRecord.get(0);
            		Date fechax=cambiarAFecha(fecha);
            		Encuesta enc=new Encuesta(fechax, exp, null);
            	}
            }
           
            
    	} catch (Exception e) {  
    	        e.printStackTrace();
    	}
	}
	public Date cambiarAFecha(String fecha) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = fecha;
        Date fechaR;
        fechaR = formatter.parse(dateInString);
        return fechaR;
    }
		
	
}
