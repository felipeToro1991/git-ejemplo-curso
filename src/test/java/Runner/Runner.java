package Runner;


import cucumber.api.CucumberOptions;


import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@flujo-feliz"},
				 glue = {"StepDefinition"},
				 features = "./src/test/java/Feature/",
				 plugin = {"pretty", "json:target/ReportJson/LocalChromecucumber.json"})
public class Runner{


	@BeforeMethod
	public static void config() {

	}

	@AfterMethod
	public static void tearDown(){

	}
}