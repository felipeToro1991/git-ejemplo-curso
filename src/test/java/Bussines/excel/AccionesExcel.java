package Bussines.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AccionesExcel {
	public boolean existeExcel(String NombreArchivo)
	{
		
	    File RutaRaiz = new File("ReportesExcel"+ "//" + NombreArchivo + ".xlsx");
        File CarpetaRaiz = (RutaRaiz);
        if (CarpetaRaiz.exists())
        	return true;
        return false;
	}
	
    public void crearNuevoExcel(String NombreArchivo, String NombreHoja, String[] Datos) {
    	// Iniciamos la creacion de un archivo excel.
    	System.out.println("Iniciamos la creacion del archivo: " + NombreArchivo);
    	
    	// Obtenemos la ruta principal de la carpeta del usuario de windows.
    	String CarpetaUsuario = System.getProperty("user.home");
    	
    	try {
	        // Definimos la ruta para ambas carpetas, raiz y la de capturas.
	        //String RutaRaiz = CarpetaUsuario + "//reportes//core.Excel";
    		//String RutaRaiz = "//reportes//core.Excel";
	        //String RutaExcel = RutaRaiz + "//" + NombreArchivo + ".xlsx";
	        
	        File RutaRaiz = new File("ReportesExcel");
	        String RutaExcel = RutaRaiz + "//" + NombreArchivo + ".xlsx";
	        
	        // Creamos la carpeta raiz si esta no existe.
	        File CarpetaRaiz = (RutaRaiz);
	        if (!CarpetaRaiz.exists())
	       	CarpetaRaiz.mkdir();
	        	        
	        // Creamos un nuevo libro con una nueva hoja con los nombres indicados.
	        XSSFWorkbook LibroExcel = new XSSFWorkbook();
	        XSSFSheet HojaExcel = LibroExcel.createSheet(NombreHoja);
	        
	        // Creamos la cabecera e iniciamos un correlativo para la columna.
	        Row FilaExcel = HojaExcel.createRow(0);
	        int NumColumna = 0;
	
	        // LLenamos la primera columna con el correlativo siguiente a la fila anterior. 
	        Cell CeldaExcel = FilaExcel.createCell(NumColumna);
	        CeldaExcel.setCellValue(0);
	        
	        // Configuramos estilo para color de fondo negro.
	        CellStyle EstiloCelda = LibroExcel.createCellStyle(); 
	        EstiloCelda.setFillForegroundColor(IndexedColors.BLACK.getIndex()); 
	        EstiloCelda.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Configuramos estilo para color de fuente.
	        Font Fuente = LibroExcel.createFont();
	        Fuente.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
	        EstiloCelda.setFont(Fuente);
	        
	        // Inicamos el llenado de cada columna en la cabecera.
	        for (String Dato : Datos) {
	        	CeldaExcel = FilaExcel.createCell(NumColumna++);
	        	CeldaExcel.setCellValue(Dato);
	        	CeldaExcel.setCellStyle(EstiloCelda);
	        }
	        
	        // Creamos un archivo de salida para guardar los cambios.
	        FileOutputStream ArchivoSalida = new FileOutputStream(RutaExcel);
	        LibroExcel.write(ArchivoSalida);
	        
	        // Cerramos el archivo de salida y el libro excel.
	        ArchivoSalida.close();
	        LibroExcel.close();
	                
	        // Finalizamos la actualizacion del archivo excel.
	    	System.out.println("Creacion del archivo: " + NombreArchivo + ", finalizado de manera exitosa!");
    	}
    
    	// En caso de algun problema con la actualizacion del excel, arrojamos un error.
        catch (IOException | EncryptedDocumentException ex) {
        	System.err.println("Hubo un error al crear el archivo: " + NombreArchivo);
        	ex.printStackTrace();
        }

    }
    
    public void agregarLineaExcel(String ArchivoExcel, String[] Datos){
    	// Iniciamos actualizacion de archivo excel.
    	System.out.println("Iniciamos actualizacion del archivo: " + ArchivoExcel + ".xlsx");
    	
    	// Obtenemos la ruta principal de la carpeta del usuario de windows.
    	String CarpetaUsuario = System.getProperty("user.home");
        
        try {
        	// Abrimos el archivo excel indicado desde la carpeta de usuario.
        	//String RutaRaiz = CarpetaUsuario + "//reportes//core.Excel//" + ArchivoExcel + ".xlsx";
        	
        	File RutaRaiz = new File("ReportesExcel/"+ArchivoExcel + ".xlsx");
        	FileInputStream ArchivoEntrada = new FileInputStream(RutaRaiz);
            
            // Abrimos el libro y nos posicionamos en la primera hoja de trabajo.
            Workbook LibroExcel = WorkbookFactory.create(ArchivoEntrada);
            Sheet HojaExcel = LibroExcel.getSheetAt(0);
 
            // Obtenemos el numero de la ultima fila con datos.
            int NumFilaActual = HojaExcel.getLastRowNum();
            int NumNuevaFila = NumFilaActual + 1;
            
            // Creamos una fila e iniciamos un correlativo para la columna.
            Row FilaExcel = HojaExcel.createRow(NumNuevaFila);
            int NumColumna = 0;
 
            // LLenamos la primera columna con el correlativo siguiente a la fila anterior. 
            Cell CeldaExcel = FilaExcel.createCell(NumColumna);
            CeldaExcel.setCellValue(NumNuevaFila);
            
            // Inicamos el llenado de cada columna en la fila.
            for (String Dato : Datos) {
            	CeldaExcel = FilaExcel.createCell(NumColumna++);
            	CeldaExcel.setCellValue(Dato);
            }
 
            // Cerramos el archivo de entrada.
            ArchivoEntrada.close();
 
            // Creamos un archivo de salida para guardar los cambios.
            FileOutputStream ArchivoSalida = new FileOutputStream(RutaRaiz);
            LibroExcel.write(ArchivoSalida);
            
            // Cerramos el libro y el archivo de salida.
            LibroExcel.close();
            ArchivoSalida.close();
            
            // Finalizamos la actualizacion del archivo excel.
        	System.out.println("Actualizacion del archivo: " + ArchivoExcel + ".xlsx, finalizada de manera exitosa!");
        } 
        
        // En caso de algun problema con la actualizacion del excel, arrojamos un error.
        catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
        	System.err.println("Hubo un error al actualizar el archivo: " + ArchivoExcel + ".xlsx");
        	ex.printStackTrace();
        }
    }

	public void crearExcel(String nombreArchivo, String[] datos, String[] datosCabecera){
		if(!existeExcel(nombreArchivo))
		{
			crearNuevoExcel(nombreArchivo, "Data", datosCabecera);
		}

		agregarLineaExcel(nombreArchivo, datos);
	}
}