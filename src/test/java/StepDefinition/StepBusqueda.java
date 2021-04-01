package StepDefinition;


import Bussines.constants.Navegador;
import Bussines.drivers.DriverContext;
import Bussines.drivers.DriverManager;
import Bussines.xml.LeerPasos;
import Page.InicioPO;
import cucumber.api.java.ca.I;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;
import java.util.List;

public class StepBusqueda {

    LeerPasos xml = new LeerPasos();
    WebDriver driver = DriverContext.getDriver();
    InicioPO inicioPO;


    @Given("el usuario ingresa a la pagina de PCFactory")
    public void el_usuario_ingresa_a_la_pagina_de_PCFactory() {
        List<String> url = xml.getxmlSucursalVirtual("Url", "PCFactory");
        DriverContext.setUp(Navegador.Chrome, url.get(1));
    }



    @And("^el usuario realiza la busqueda del producto \"([^\"]*)\"$")
    public void el_usuario_realiza_la_busqueda_del_producto(String producto) throws InterruptedException {
        inicioPO = new InicioPO(driver);
        inicioPO.insertarProducto(producto);
        inicioPO.clickBtnBuscar();

    }

    @And("^el usuario selecciona el producto \"([^\"]*)\"$")
    public void el_usuario_selecciona_el_producto(String arg1) {

    }

    @Then("^Se valida que el usuario haya seleccionado el producto \"([^\"]*)\"$")
    public void se_valida_que_el_usuario_haya_seleccionado_el_producto(String arg1) {
    }



}
