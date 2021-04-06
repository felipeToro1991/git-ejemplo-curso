package Page;

import Bussines.drivers.DriverContext;
import Bussines.selenium.AccionesWeb;
import StepDefinition.StepBusqueda;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class InicioPO {

    public InicioPO() {
        PageFactory.initElements(DriverContext.getDriver(), this);
    }
    @FindBy(xpath = "//*[@id='search']")
    public WebElement inputTextBusqueda;
    @FindBy(xpath = "//*[@id='search_btn']")
    public WebElement btnBusqueda;
    @FindBy(xpath = "//*[@id='seccion-banner-bajo']/a/img")
    public WebElement imgBanner2;

    public void insertarProducto(String producto){
        inputTextBusqueda.sendKeys(producto);
    }
    public void clickBtnBuscar(){
        btnBusqueda.click();
    }
    public void clickBannerDos(){
        imgBanner2.click();
    }


}
