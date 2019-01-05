package es.amm.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import es.amm.domain.Shipment;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/tracking.feature",
        plugin = {"pretty", "html:target/cucumber"})
public class TrackingCucumberIntegrationTest {
    // RUN THIS CLASS TO DO THE BDD TESTS

    private static final Logger logger = LoggerFactory.getLogger(TrackingCucumberIntegrationTest.class);

    Shipment shipment;

    @Before
    public void setup() {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            shipment = objectMapper.readValue(this.getClass().getResourceAsStream("./shipment.json"), Shipment.class);
            logger.info(objectMapper.writeValueAsString(shipment));
        }catch (IOException ex){
            logger.error(ex.toString());
        }
    }
}