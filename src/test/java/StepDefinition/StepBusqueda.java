package StepDefinition;


import Bussines.bd.ConsultasMysql;
import Bussines.constants.Navegador;
import Bussines.drivers.DriverContext;

import Bussines.excel.PcfactoryExcel;
import Bussines.xml.LeerPasos;
import Page.InicioPO;
import Page.Producto;
import Page.ResultadoBusqueda;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;


import java.util.List;

public class StepBusqueda {

    LeerPasos xml = new LeerPasos();
    InicioPO inicioPO;
    ResultadoBusqueda result;
    PcfactoryExcel excel;
    ConsultasMysql sql;
    Producto prod;
    String id;
    List<String> datos;
    List<String> datos2;
    String[] datosExcel;

    @Given("el usuario ingresa a la pagina de PCFactory")
    public void el_usuario_ingresa_a_la_pagina_de_PCFactory() {
        List<String> url = xml.getxmlSucursalVirtual("Url", "PCFactory");
        DriverContext.setUp(Navegador.Chrome, url.get(1));
    }

    @And("^el usuario realiza la busqueda del producto \"([^\"]*)\"$")
    public void el_usuario_realiza_la_busqueda_del_producto(String producto) {
        inicioPO = new InicioPO();
        inicioPO.insertarProducto(producto);
        inicioPO.clickBtnBuscar();

    }

    @And("^el usuario selecciona el producto \"([^\"]*)\"$")
    public void el_usuario_selecciona_el_producto(String producto) {
        result = new ResultadoBusqueda();
        result.SeleccionarProducto(producto);
    }

    @Then("^Se valida que el usuario haya seleccionado el producto \"([^\"]*)\"$")
    public void se_valida_que_el_usuario_haya_seleccionado_el_producto(String arg1) {
        excel = new PcfactoryExcel();
        sql = new ConsultasMysql();
        prod = new Producto();
        prod.mostrarTexto();
        id = prod.extraerId();
        datos = prod.extraerTextos();
        datosExcel = new String[datos.size()];
        datos2 = sql.consultaProductos(id);

        Assert.assertEquals("Las listas no son iguales", datos, datos2);

        int i = 0;
        for (String info : datos) {
            datosExcel[i] = info;
            i++;
        }
        excel.excelPcfactory(datosExcel);
        DriverContext.getDriver().quit();
    }


}
