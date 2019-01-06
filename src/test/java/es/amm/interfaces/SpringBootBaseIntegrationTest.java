package es.amm.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.amm.intrastructure.Event;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.HttpParams;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringBootBaseIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootBaseIntegrationTest.class);

    private final String SERVER_URL = "http://localhost";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    private String registerEndPoint() {
        return String.format("%s:%d%s%s",SERVER_URL,port,HttpParams.URI_API,HttpParams.URI_REGISTER);
    }

    private String pushEndPoint() {
        return String.format("%s:%d%s%s",SERVER_URL,port,HttpParams.URI_API,HttpParams.URI_PUSH);
    }

    public String getShipmentAsString() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Shipment shipment = objectMapper.readValue(this.getClass().getResourceAsStream("./shipment.json"), Shipment.class);
        final String shipmentAsString = objectMapper.writeValueAsString(shipment);
        logger.info(objectMapper.writeValueAsString(shipment));
        return shipmentAsString;
    }

    public Shipment postShipment()throws Exception {
        // given shipment
        final String jsonShipment = getShipmentAsString();
        final HttpEntity<String> request = RestTemplateHelper.getHttpEntity (jsonShipment);
        final URI uri = new URL(registerEndPoint()).toURI();
        return this.restTemplate.postForObject(uri, request, Shipment.class);
    }

    public Event putTracking(Tracking tracking) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonTracking = objectMapper.writeValueAsString(tracking);
        final HttpEntity<String> request = RestTemplateHelper.getHttpEntity (jsonTracking);
        final URI uri = new URL(pushEndPoint()).toURI();
        final ResponseEntity<Event> response = restTemplate.exchange(uri, HttpMethod.PUT, request, Event.class);
        return response.getBody();
    }

    void clean() {
        restTemplate.delete(registerEndPoint());
        restTemplate.delete(pushEndPoint());
    }
}