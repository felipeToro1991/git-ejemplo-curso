package Bussines.selenium;

import Bussines.drivers.DriverContext;
import Bussines.xml.LeerPasos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
*
* @author 
*/
public class AccionesWeb {
	private WebDriver driver;

	/**
	 * Constructor de la clase.
	 */
	public AccionesWeb() {

	}

	public WebElement FindElemento(String nombre, String plataforma) throws InterruptedException {

		WebElement elem = null;
		driver = DriverContext.getDriver();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		nombre = nombre.replace("\"", "");
		plataforma = plataforma.replace("\"", "");
		//Leer Xpath de los archivos xml
		LeerPasos xml = new LeerPasos();
		List<String> Atributos = xml.getxmlSucursalVirtual(nombre, plataforma);
		if (Atributos == null)
			System.out.println("Objeto de nombre " + nombre + " No ha se ha encontrado");

		String TipoBy = Atributos.get(0);
		String Xpath = Atributos.get(1);


		switch (TipoBy) {
			case "Xpath":
				elem = driver.findElement(By.xpath(Xpath));
				break;
			case "Id":
				elem = driver.findElement(By.id(Xpath));
				break;
			case "LinkText":
				elem = driver.findElement(By.linkText(Xpath));
				break;
		}

		return elem;
	}

	public void Click(String Nombre, String Plataforma) throws InterruptedException {
		WebElement Elem = FindElementoWait(Nombre, Plataforma, 60);
		Elem.click();
	}

	public void AlertAccept(String Plataforma) {

	}

	public void InputText(String Value, String Nombre, String Plataforma) throws InterruptedException {
		WebElement Elem = FindElementoWait(Nombre, Plataforma, 60);
		Elem.sendKeys(Value);
	}

	public void ClearText(String Nombre, String Plataforma) throws InterruptedException {
		WebElement Elem = FindElementoWait(Nombre, Plataforma, 60);
		Elem.clear();
	}

	public String LeerElemento(String Nombre, String Plataforma) throws InterruptedException {
		// Iniciamos el objeto del webdriver.
		WebElement Elem = FindElementoWait(Nombre, Plataforma, 60);
		String Resultado = Elem.getText();
		// Imprimimos y devolvemos el resultado.
		System.out.println("Lectura: " + Resultado);
		return Resultado;
	}

	public void CompararString(String Value, String Nombre, String Plataforma) throws InterruptedException {

		String Resultado = LeerElemento(Nombre, Plataforma);
		int time = 60;
		for (int i = 0; i < time; i++) {

			if (Resultado.contains(Value))
				return;
			Thread.sleep(1000);
		}

		throw new RuntimeException(Resultado + " es distinto a: " + Value);


	}


	public WebElement FindElementoWait(String Nombre, String Plataforma, int TimeOut) throws InterruptedException {
		System.out.println(".. buscando elemento " + Nombre);
		WebElement elem = null;
		for (int i = 0; i < TimeOut; i++) {
			try {
				elem = FindElemento(Nombre, Plataforma);
			} catch (Exception e) {
				System.out.println(".. buscando elemento " + Nombre);
				Thread.sleep(1000);
			}
			if (elem != null) {
				return elem;
			}

		}
		return elem;
	}

	public boolean FindElementoWaitBoo(String Nombre, String Plataforma, int TimeOut) throws InterruptedException {
		System.out.println(".. buscando elemento " + Nombre);
		WebElement elem = null;
		for (int i = 0; i < TimeOut; i++) {
			try {
				elem = FindElemento(Nombre, Plataforma);
			} catch (Exception e) {
				System.out.println(".. buscando elemento " + Nombre);
				Thread.sleep(1000);
			}
			if (elem != null) {
				return true;
			}

		}
		return false;
	}

	public void AbrirDireccion(String Url) throws InterruptedException {
		// Solicitamos el driver de selenium ya iniciado.
		WebDriver Driver = DriverContext.getDriver();

		// Hacemos una breve pausa antes de comenzar con la accion.
		Thread.sleep(500);

		// Navegamos a la url indicada.
		Driver.get(Url);

		// Hacemos una breve pausa antes de continuar.
		Thread.sleep(500);
	}

	public void CloseDriver() {
		WebDriver Driver = DriverContext.getDriver();
		Driver.close();

	}

	public void Iframe() {
		WebDriver Driver = DriverContext.getDriver();
		Driver.switchTo().frame(1);

	}

	public void IngresarTexto(String Objeto, String Texto, Boolean Capturar, String Comentario) throws IOException, InterruptedException {
		// Hacemos una breve pausa antes de comenzar con la accion.
		Thread.sleep(500);

		// Buscamos el elemento con el objeto indicado.
		WebElement Elemento = BuscarElemento("Xpath", Objeto);
		//GenericValidations.existElementReport(Elemento,Capturar,Comentario);
		// Limpiamos el campo e ingresamos el texto indicado.
		Elemento.clear();
		Elemento.sendKeys(Texto);

		// En caso que sea requerido, realizamos una captura del campo de texto con el texto ingresado.
        /*if (Capturar == true){
            // Creamos el objeto para captura de la accion.
            Screenshots Captura = new Screenshots();

            // Realizamos una captura de la accion para registrar en el reporte.
            if(Comentario != "") {
                // Además de la captura, ingresamos un comentario personalizado.
                Captura.CapturaElementoWeb(Elemento, Comentario);
            }

            else {
                // Si no existe un comentario persoinalizado, ingresamos un comentario basico de la acción.
                Captura.CapturaElementoWeb(Elemento, "Ingresamos el texto: " + Texto + " en el campo: " + Objeto);
            }
        }*/

		// Hacemos una breve pausa antes de continuar.
		Thread.sleep(500);
	}

	public WebElement BuscarElemento(String Tipo, String Objeto) throws IOException {
		// Solicitamos el driver de selenium ya iniciado.
		WebDriver Driver = DriverContext.getDriver();

		// Creamos el objeto para obtener las rutas de los elementos del modulo de alquiler.
		ElementosWeb Elementos = new ElementosWeb();

		// Obtenemos la ruta del elemento indicado.
		String Ruta = Elementos.RutaElemento(Objeto);

		// Iniciamos la variable elemento.
		WebElement Elemento = null;

		// Iniciamos variable para guardar error en caso de ser necesario.
		String Error = null;

		// Buscamos el elemento a traves del tipo y la ruta indicada.
		try {
			switch (Tipo) {
				case "Xpath":
					Elemento = Driver.findElement(By.xpath(Ruta));
					break;
				case "PartialLinkText":
					Elemento = Driver.findElement(By.partialLinkText(Ruta));
					break;
				case "Id":
					Elemento = Driver.findElement(By.id(Ruta));
					break;
				case "Class":
					Elemento = Driver.findElement(By.className(Ruta));
					break;
				case "Css":
					Elemento = Driver.findElement(By.cssSelector(Ruta));
					break;
			}
		} catch (Exception Err) {
			// Devolvemos un error de manera simple indicando el objeto y su ruta.
			if (Objeto != Ruta)
				Error = "Error: El elemento: " + Objeto + " con la ruta: " + Ruta + " no fue encontrado!";

				// Devolvemos un error de manera simple en caso que se haya indicado solo la ruta.
			else if (Objeto == Ruta)
				Error = "Error: El elemento con la ruta: " + Ruta + " no fue encontrado!";

				// Devolvemos el error de java completo en caso de no indicar log simple.
			else
				Error = "Error: " + Err;

			// Imprimimos el error en la consola.
			System.err.println(Error);

			// Iniciamos el objeto para capturas.
			//Screenshots Captura = new Screenshots();

			// Obtenemos una captura de pantalla completa mas una descripcion del error.
			//	Captura.CapturaElementoWeb(null, "<span style='color: red;'>" + Error + "</span>");

			// Omitimos el resto de la ejecucion.
			throw new SkipException("El caso ha finalizado con error!");
		}

		// Devolvemos el elemento encontrado.
		return Elemento;
	}

	public void Click(String Objeto, Boolean Capturar, String Comentario) throws IOException, InterruptedException {

		// Buscamos el elemento con el objeto indicado.
		WebElement Elemento = BuscarElemento("Xpath", Objeto);
		//GenericValidations.existElementReport(Elemento, Capturar, Comentario);
		// Hacemos una breve pausa antes de comenzar con la accion.
		Thread.sleep(500);
		// Hacemos click en el elemento indicado.
		Elemento.click();

	}

}


