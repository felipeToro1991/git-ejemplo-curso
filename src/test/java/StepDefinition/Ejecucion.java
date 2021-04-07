package StepDefinition;

import Bussines.drivers.DriverContext;
import Bussines.reportes.PdfBciReports;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Ejecucion {

    @Before
    public static void config() {
        PdfBciReports.createPDF();
    }

    @After
    public static void tearDown(){
        PdfBciReports.closePDF();
        DriverContext.quitDriver();
    }

}
