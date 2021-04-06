package Page;

import Bussines.drivers.DriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    String nombre;
    String marca;
    String precioNormal;
    String precioOferta;
    String precioOfertEfectivo;
    String idProducto;

    public Producto() {
        PageFactory.initElements(DriverContext.getDriver(),this);
    }
    @FindBy(xpath = "(//span [@itemprop='name'])[2]")
    public WebElement labelNombre;
    @FindBy(xpath = "(//span [@itemprop='brand'])[2]")
    public WebElement labelMarca;
    @FindBy(xpath = "//div [@class='ficha_precio_normal'] /h2")
    public WebElement labelPrecioNormal;
    @FindBy(xpath = "//div [@class='ficha_precio_efectivo'] /h2")
    public WebElement labelPrecioOferta;
    @FindBy(xpath = "//li [@id='tab_ficha_tecnica']")
    public WebElement pestFichaTecnica;
    @FindBy(xpath = "(//ul [@class='resp-tabs-list hor_1'] /li)[1]")
    public WebElement pestDescripcion;
    @FindBy(xpath = "//*[@id='id_ficha_producto']/div[2]/div[2]/div/div[3]/div[1]/p/span")
    public WebElement labelIdProducto;

    public void mostrarTexto() {
        System.out.println(nombre=labelNombre.getText());
        System.out.println(marca=labelMarca.getText());
        System.out.println(precioNormal=labelPrecioNormal.getText());
        System.out.println(precioOferta=labelPrecioOferta.getText());
        System.out.println(idProducto=labelIdProducto.getText());
    }
    public List<String> extraerTextos(){
        ArrayList<String> datos = new ArrayList();
            datos.add(idProducto);
            datos.add(nombre);
            datos.add(marca);
            datos.add(precioNormal);
            datos.add(precioOferta);
            datos.add(extraerUrl());
        return datos;
    }

    public String extraerUrl(){
        String url = DriverContext.getDriver().getCurrentUrl();
        return url;
    }

    public String extraerId(){
        return idProducto;
    }


}
