package testrunners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions", "apphooks"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failedrerun.txt",
                "html:target/cucumber", 
                  "json:target/cucumber.json"  
        },
        tags = "not @Skip",
        monochrome = true,
        dryRun = false

)
/**
 * First way to skip the test cases which are tagged with @Skip is feature file. Mention tags in cucumber options
 * Second way is to mention in maven goals as - clean test -DCucumber.options="--tags '@Regression and not @Skip'" allure:serve
 * Second way of overriding cucumberOptions from maven is not working
 * Third way is to create before hook with parameter passed as tag name which want to skip
 * To verify we commented tag from Runner file, also we passed value in hook as @Skip tag
 */

public class TestRunner {
}
