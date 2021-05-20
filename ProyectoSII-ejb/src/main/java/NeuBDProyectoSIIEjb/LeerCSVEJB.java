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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import NeuBDProyectoSII.Alumno;
import NeuBDProyectoSII.Asignatura;
import NeuBDProyectoSII.Centro;
import NeuBDProyectoSII.Encuesta;
import NeuBDProyectoSII.Expedientes;
import NeuBDProyectoSII.Matricula;
import NeuBDProyectoSII.Optativa;
import NeuBDProyectoSII.Titulacion;
import NeuBDProyectoSIIexceptions.AlumnoSInDatosParaCrearException;
import NeuBDProyectoSIIexceptions.NeuBDExceptions;



@Stateless
public class LeerCSVEJB implements GestionLeerCSV{
	@PersistenceContext(name="ProyectoSII")
	private EntityManager em;
	@Override
	public void insertarAlumnoCSV(Titulacion titu,String route)throws AlumnoSInDatosParaCrearException, ParseException{
        try { 
        	Reader reader = Files.newBufferedReader(Paths.get(route));
        	CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        	//CSVParser parser=new CSVParser(reader, CSVFormat.DEFAULT);
        	boolean activo=true;String curso ="20/21";
            for (CSVRecord csvRecord : csvParser) {
            	if(csvRecord.getRecordNumber()==1) {
            		//Estado matricula
            		curso= csvRecord.get(1);
            		
            	}
            	if(csvRecord.getRecordNumber()==3) {
            		//Estado matricula
            		String strActivo = csvRecord.get(1);
            		if(strActivo.equalsIgnoreCase("Activa")) {
            			activo=true;
            		}else {
            			activo=false;
            		}
            		
            	}
            	if((csvRecord.getRecordNumber() >=5 ) && (csvRecord.get(0).length() >= 1)) {
                // Accediendo a los valores por el indice de la columna
            		//System.out.println("\n\n\n\n\n\nNuevo alumno");
	                String DNI = csvRecord.get(0);
	                String nombre = csvRecord.get(1);
	                String priApellido = csvRecord.get(2);
	                String secApellido = csvRecord.get(3);
	                String numExp = csvRecord.get(4);
	                int numArchivo = Integer.parseInt(csvRecord.get(5));
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
	                double credSuperados = Double.parseDouble(csvRecord.get(18));
	                int CREDITOS_FB = Integer.parseInt(csvRecord.get(19));
	                int CREDITOS_OB = Integer.parseInt(csvRecord.get(20));
	                double CREDITOS_OP = Double.parseDouble(csvRecord.get(21));
	                int CREDITOS_CF = Integer.parseInt(csvRecord.get(22));
	                int CREDITOS_PE = Integer.parseInt(csvRecord.get(23));
	                int CREDITOS_TF = Integer.parseInt(csvRecord.get(24));
	                //System.out.println("pruwba");
	                List<Expedientes> lista=new ArrayList<Expedientes>();
	                Alumno alum=new Alumno(DNI,nombre,priApellido,secApellido,emPersonal, emInstitucional, movil, telefono, direccion, localidad, lista, cp, provincia);
	                
	                Alumno a = em.merge(alum); //Por si el alumno pasado ya existia en la BD
	                //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +a);
	                Expedientes e= new Expedientes(true, notaMedia, credSuperados, CREDITOS_FB, CREDITOS_OB, CREDITOS_OP, CREDITOS_CF, CREDITOS_PE, CREDITOS_TF, titu, a,null);
	                em.merge(e);
	                lista = a.getExpedientes();
	                lista.add(e);
	                alum.setExpedientes(lista); 
	                Alumno al = em.merge(alum);
	                
	                String strActivo;
	                if(activo) {
	                	strActivo= "activo";
	                }{
	                	strActivo="inactivo";
	                }
	                Expedientes expe = al.getExpedientes().get(al.getExpedientes().size()-1);
	                Matricula m = new Matricula(expe, curso, strActivo, numArchivo, turnoPref, cambiarAFecha(fechaMatricula), "", gruposAsig, null);
	                System.out.println(m);
	                em.merge(m);
	                List<Matricula> list = e.getMatricula();
	                list.add(m);
	                e.setMatricula(list);
	                em.merge(e);	
	                
            	}
            }
            csvParser.close();
            reader.close();
        }
        catch (IOException e) {  
	        e.printStackTrace();  
	    } 
    }
	
	@Override
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
	@Override
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
	@Override
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
	@Override
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dateInString = fecha;
        Date fechaR;
        fechaR = formatter.parse(dateInString);
        return fechaR;
    }

	
}
