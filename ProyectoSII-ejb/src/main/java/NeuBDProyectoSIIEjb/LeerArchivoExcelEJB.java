package NeuBDProyectoSIIEjb;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class LeerArchivoExcelEJB {
	public static void  main(String args[])  {  
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	    try {  
	        // Admite Excel 2003 y 2007 al mismo tiempo  
	        // La ubicación del archivo de Excel
	        File excelFile = new File("D://aa.xlsx"); // Crear objeto de archivo  
	        FileInputStream is = new FileInputStream(excelFile); // Flujo de archivos  
	        Workbook workbook = WorkbookFactory.create(is); // De esta forma se puede procesar Excel 2003/2007/2010  
	        int sheetCount = workbook.getNumberOfSheets();  // Número de hojas  
	        // Atraviesa cada hoja  
	        for (int s = 0; s < sheetCount; s++) {  
	            Sheet sheet = workbook.getSheetAt(s);  
	            int rowCount = sheet.getPhysicalNumberOfRows(); // Obtén el número total de filas  
	            // Iterar por cada línea  
	            for (int r = 0; r < rowCount; r++) {  
	                Row row = sheet.getRow(r);  
	                int cellCount = row.getPhysicalNumberOfCells(); // Obtener el número total de columnas  
	                // Atraviesa cada celda  
	                
	             
	                for (int c = 0; c < cellCount; c++) {  
	                    Cell cell = row.getCell(c);  
	                    CellType cellType = cell.getCellType();  
	                    String cellValue = null;
	                    // Antes de leer el contenido de la celda, configure el contenido de todas las celdas para que sea de tipo cadena
	                   //buscar otra forma con la nueva version  cell.setCellType(Cell.CELL_TYPE_STRING);
	                    // Leer los datos de la celda según el tipo de cadena
	                    cellValue = cell.getStringCellValue();
	                    // Aquí puede realizar una operación secundaria para transformar el valor en cada celda
	                    System.out.print(cellValue + "  "); 
	                }  
	                System.out.println(); 
	            }  
	        }  
	  
	    }  
	    catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	  
	}
}
