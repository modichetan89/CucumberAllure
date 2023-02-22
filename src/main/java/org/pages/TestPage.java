package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.util.ElementUtil;

public class TestPage extends ElementUtil {
    private WebDriver driver;
    public ElementUtil util;

    public TestPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        util = new ElementUtil(driver);
    }

    //page locators
    //https://www.amazon.in/
    @FindBy(id="nav-cart-text-container")
    WebElement homePageCartIcon;

    //page actions
    public void navigateToCart(){
        //sleepWait(10000);
        click(homePageCartIcon);
    }

    public String cartVerification() {
        return driver.getTitle();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
