package apphooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.qa.factory.DriverFactory;
import org.qa.util.ConfigReader;

import java.util.Properties;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0, value = "@skip1")
    public void skip_scenario(Scenario scenario){
        System.out.println("SKIPPED SCENARIO IS " + scenario.getName());
        Assume.assumeTrue(false);
    }

    @Before(order = 1)
    public void getProperty(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order =2)
    public void launchBrowser(){
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
        // DriverFactory.getDriver().get("https://www.amazon.in");
        // driverFactory.getDriver().get("https://www.amazon.in");
        // driver.get("https://www.amazon.in");
    }

    @After(order =1)
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            //take screenshot
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }

    @After(order = 0)
    public void quitBrowser(){
        driver.quit();
    }
}
