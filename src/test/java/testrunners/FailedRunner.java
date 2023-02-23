package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        glue = {"stepDefinitions", "apphooks"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-results/cucumber-failed-html-reports",
                "json:target/cucumber-results/cucumber-failed.json",
                "junit:target/cucumber-results/cucumber.xml",
                "junit:target/cucumber-failed.xml"
        },
        monochrome = true,
        dryRun = false

)

public class FailedRunner {
}
