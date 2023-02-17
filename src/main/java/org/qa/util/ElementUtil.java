package org.qa.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ElementUtil {
    public static WebDriver driver;

    public ElementUtil() {

    }

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public static void click(WebElement element) {
        element.click();
    }

    public void insertText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public static String getPopupMessage() {
        String message = null;
        try {
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
            alert.accept();
        } catch (Exception e) {
            message = null;
        }
        return message;
    }

    public static String cancelPopupMessageBox() {
        String message = null;
        try {
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
            alert.dismiss();
        } catch (Exception e) {
            message = null;
        }
        return message;
    }

    public static String getTooltipText(WebElement element) {
        String tooltip = element.getAttribute("title");
        return tooltip;
    }

    public static void selectRadioButtonByValue(List<WebElement> elementList, String value) {
        for (WebElement element : elementList) {
            if (element.getAttribute("value").equalsIgnoreCase(value)) {
                element.click();
            }
        }

    }

    public static void selectCheckboxes(List<WebElement> elementList, String value) {
        List<String> list = new ArrayList<>(Arrays.asList(value.split(",")));
        for (String check : list) {
            for (WebElement chk : elementList) {
                if (chk.getAttribute("value").equalsIgnoreCase(check)) {
                    chk.click();
                }
            }
        }
    }

    public static void selectDropdownByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public static void selectDropdownByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void selectDropdownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectSearchDropDown(WebElement element, String value) {
        element.click();
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
    }

    public static void uploadFile(WebElement uploadElement, String path) {
        uploadElement.sendKeys(path);
    }

    public static String switchToChildWindowAndReturnParentName() {
        //it will return the parent window name as string
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();

        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                //driver.close();
            }
        }
        return parent;
        //switch to parent window
        //driver.switchTo().window(parent);
    }

    public static void switchToParentWindow(String parentWindowName){
        driver.switchTo().window(parentWindowName);
    }

    public static void navigationMethods(){
        driver.navigate().to("https://url");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    public static void deleteCookies(){
        driver.manage().deleteAllCookies();
    }

    public static void implicitWait(int time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void sleepWait(int timeMs){
        try{
            Thread.sleep(timeMs);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public static void explicitWaitElementToBeClickable(int time, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void actionsSelectMultipleElements(WebElement element1, WebElement element2){
        Actions builder = new Actions(driver);
        builder.keyDown(Keys.CONTROL).click(element1).click(element2).keyUp(Keys.CONTROL).build().perform();
    }

    public static void actionsDragAndDropElements(WebElement sourceElement, WebElement targetElement){
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, targetElement).perform();
    }

    public static void selectIFrameUsingIndex(int index){
        driver.switchTo().frame(index);
    }

    public static void selectIFrameUsingName(String name){
        driver.switchTo().frame(name);
    }

    public static void selectIFrameUsingWebElement(WebElement element){
        driver.switchTo().frame(element);
    }

    public static boolean getAttributeValue(WebElement element, String attribute){
        String attributeValue = element.getAttribute(attribute);
        boolean result = false;
        if(attributeValue.contains("ng-dirty"))
            result = true;
        else if(attributeValue.contains("ng-pristine"))
            result = false;
        return result;
    }

    public static void jsExecutor(String script, WebElement element){
        //script
        //arguments[0].click();
        //arguments[0].scrollIntoView(true);
        //window.scrollBy(0,600);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }







}
