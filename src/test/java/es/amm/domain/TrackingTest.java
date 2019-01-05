package es.amm.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingTest {

    private final Logger logger = LoggerFactory.getLogger(TrackingTest.class);

    @Test
    public void createTrackingOK() throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Tracking tracking = objectMapper.readValue(this.getClass().getResourceAsStream("./tracking.json"), Tracking.class);
        logger.info(objectMapper.writeValueAsString(tracking));
    }
}
