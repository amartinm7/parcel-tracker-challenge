package es.amm.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShipmentTest {

    private final Logger logger = LoggerFactory.getLogger(TrackingTest.class);

    @Test
    public void createTrackingOK() throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Shipment shipment = objectMapper.readValue(this.getClass().getResourceAsStream("./shipment.json"), Shipment.class);
        logger.info(objectMapper.writeValueAsString(shipment));
        Assert.assertNotNull(shipment);
    }
}
