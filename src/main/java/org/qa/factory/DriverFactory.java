package org.qa.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import org.qa.util.Constants;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver init_driver(String browser){
        System.out.println("Browser value is : "+browser);
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        }
        else if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        }
        else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver());
        }
        else{
            System.out.println("Please pass the correct browser value " +browser);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        return getDriver();
    }

    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }
}
