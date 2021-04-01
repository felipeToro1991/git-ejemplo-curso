package Page;

import Bussines.drivers.DriverContext;
import Bussines.selenium.AccionesWeb;
import StepDefinition.StepBusqueda;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class InicioPO extends Base{

    AccionesWeb accion = new AccionesWeb();


    public InicioPO(WebDriver driver) {
        super(driver);
    }

    public void insertarProducto(String producto) throws InterruptedException {
        accion.InputText(producto,"inputBusqueda", "PCFactory");
    }

    public void clickBtnBuscar(){
        accion.Click("btnBuscar", "PCFactory");
    }

    public void clickBannerDos(){

    }


}
