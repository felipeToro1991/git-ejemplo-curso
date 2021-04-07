package StepDefinition;


import Bussines.bd.ConsultasMysql;
import Bussines.constants.Navegador;
import Bussines.drivers.DriverContext;
import Bussines.excel.PcfactoryExcel;
import Bussines.reportes.EstadoPrueba;
import Bussines.reportes.PdfBciReports;
import Bussines.xml.LeerPasos;
import Page.InicioPO;
import Page.Producto;
import Page.ResultadoBusqueda;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class StepBusqueda {

    LeerPasos xml = new LeerPasos();
    WebDriver driver = DriverContext.getDriver();
    InicioPO inicioPO;


    @Given("el usuario ingresa a la pagina de PCFactory")
    public void el_usuario_ingresa_a_la_pagina_de_PCFactory() {
        List<String> url = xml.getxmlSucursalVirtual("Url", "PCFactory");
        DriverContext.setUp(Navegador.Chrome, url.get(1));
        String urlWeb = DriverContext.getDriver().getCurrentUrl();
        if(urlWeb.equals(url)){
            PdfBciReports.addWebReportImage("Levantamiento de navegador", "levantamiento de navegador en la pagina :"+url, EstadoPrueba.PASSED, false);
        }else{
            PdfBciReports.addWebReportImage("Levantamiento de navegador", "levantamiento de navegador en la pagina no fue correcto se cargo la URL:"+urlWeb, EstadoPrueba.FAILED, true);
        }


    }



    @And("^el usuario realiza la busqueda del producto \"([^\"]*)\"$")
    public void el_usuario_realiza_la_busqueda_del_producto(String producto){
        inicioPO = new InicioPO();
        inicioPO.insertarProducto(producto);
        PdfBciReports.addWebReportImage("Pagina principal con ingreso del producto", "Ingreso del producto", EstadoPrueba.PASSED, false);
        inicioPO.clickBtnBuscar();

    }

    @And("^el usuario selecciona el producto \"([^\"]*)\"$")
    public void el_usuario_selecciona_el_producto(String arg1) {
        ResultadoBusqueda result = new ResultadoBusqueda();
        PdfBciReports.addWebReportImage("Pagina de resultado", "Resultado de la busqueda del producto: "+arg1, EstadoPrueba.PASSED, false);
        result.SeleccionarProducto(arg1);
    }

    @Then("^Se valida que el usuario haya seleccionado el producto \"([^\"]*)\"$")
    public void se_valida_que_el_usuario_haya_seleccionado_el_producto(String arg1) {
        PcfactoryExcel excel = new PcfactoryExcel();
        ConsultasMysql sql = new ConsultasMysql();
        Producto prod = new Producto();
        PdfBciReports.addWebReportImage("Pagina del producto", "Extraccion de informacion del producto", EstadoPrueba.PASSED, false);
        prod.mostrarTexto();
        String id = prod.extraerId();
        List<String> datos = prod.extraerTextos();
        String[] datosExcel = new String[datos.size()];
        List<String> datos2 = sql.consultaProductos(id);

        Assert.assertTrue("Las listas no son iguales",datos.equals(datos2));

        for(int i = 0; i < datos.size();i++){
            PdfBciReports.addTextValidate("Validacion textos del producto", datos.get(i), datos2.get(i), false);
        }





        int i = 0;
        for(String info: datos){
            datosExcel[i] = info;
            i++;
        }

        excel.excelPcfactory(datosExcel);






    }



}
