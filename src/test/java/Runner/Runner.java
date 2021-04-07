package Runner;


import Bussines.drivers.DriverContext;
import Bussines.reportes.PdfBciReports;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@flujo-feliz2"},
				 glue = {"StepDefinition"},
				 features = "./src/test/java/Feature/",
				 plugin = {"pretty", "json:target/ReportJson/LocalChromecucumber.json"})
public class Runner extends AbstractTestNGCucumberTests {


}