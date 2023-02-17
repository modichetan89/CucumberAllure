package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pages.TestPage;
import org.qa.factory.DriverFactory;

public class TestSteps {
    private TestPage testPage = new TestPage(DriverFactory.getDriver());

    @Given("user is on home page {string}")
    public void user_is_on_home_page(String expectedTitle){
        DriverFactory.getDriver().get("https://www.amazon.in");
        String actualTitle = testPage.getPageTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @When("user click on cart icon")
    public void user_click_on_cart_icon(){
        testPage.navigateToCart();
    }

    @Then("user navigated to cart {string}")
    public void user_navigated_to_cart(String expectedTitle){
        String actualTitle = testPage.cartVerification();
        Assert.assertTrue(expectedTitle.equalsIgnoreCase(actualTitle));
    }

}
