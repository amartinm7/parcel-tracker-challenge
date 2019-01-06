package es.amm.interfaces;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/tracking.feature",
        plugin = {"pretty", "html:target/cucumber"})
public class TrackingCucumberIntegrationTest {
    // RUN THIS CLASS TO DO THE BDD TESTS
}