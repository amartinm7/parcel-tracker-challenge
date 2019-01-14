package es.amm.domain.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.domain.ports.ShipmentCommandPort;
import es.amm.domain.repository.RepositoryProviderAdapter;
import es.amm.intrastructure.Event;
import es.amm.intrastructure.repository.RepositoryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentCommandAdapterTest {

    @Autowired
    private ShipmentCommandPort shipmentCommandport;

    private Shipment shipment;
    private Tracking trackingA;
    private Tracking trackingB;
    private Tracking trackingC;
    private Tracking trackingD;
    private Tracking trackingE;
    private Tracking trackingF;
    private Tracking trackingG;
    private Tracking trackingH;

    @Before
    public void setup() throws Exception{
        final RepositoryPort<Shipment,String> repositoryPort = new RepositoryProviderAdapter();
        shipmentCommandport = new ShipmentCommandAdapter(repositoryPort);
        shipment = getShipment();
        trackingA = getTracking("./trackingA.json");
        trackingB = getTracking("./trackingB.json");
        trackingC = getTracking("./trackingC.json");
        trackingD = getTracking("./trackingD.json");
        trackingE = getTracking("./trackingE.json");
        trackingF = getTracking("./trackingF.json");
        trackingG = getTracking("./trackingG.json");
        trackingH = getTracking("./trackingH.json");
    }

    private Shipment getShipment() throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Shipment shipment = objectMapper.readValue(this.getClass().getResourceAsStream("./shipment.json"), Shipment.class);
        return shipment;
    }

    private Tracking getTracking(String fileName) throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Tracking tracking = objectMapper.readValue(this.getClass().getResourceAsStream(fileName), Tracking.class);
        return tracking;
    }

    @Test
    public void whenSaveAShipmentGetsTheShipment(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipment =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipment.isPresent(),"the shipment is not saved properly");
    }

    @Test
    public void whenSaveSameShipmentTwoTimesGetsNull(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Shipment> optionalShipmentSecond =  shipmentCommandport.save(shipment);
        // then
        assertFalse(optionalShipmentSecond.isPresent(),"you can't save two times the same shipment");
    }

    @Test
    public void whenSendEmptyWeightTrackingGetsIncomplete(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEvent =  shipmentCommandport.pushTracking(trackingA);
        // then
        assertTrue(optionalEvent.isPresent(), "there's not any event to return");
        assertTrue(optionalEvent.get().getStatus().equals(Tracking.STATUS_TRACKING.INCOMPLETE), "the event status doesn't match");
    }

    @Test
    public void whenSendButWrongStatusTrackingGetsIncomplete(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEventB =  shipmentCommandport.pushTracking(trackingB);
        // then
        assertTrue(optionalEventB.isPresent(), "there's not any event to return");
        assertTrue(optionalEventB.get().getStatus().equals(Tracking.STATUS_TRACKING.INCOMPLETE), "the event status doesn't match");

        // when
        final Optional<Event> optionalEventC =  shipmentCommandport.pushTracking(trackingC);
        // then
        assertTrue(optionalEventC.isPresent(), "there's not any event to return");
        assertTrue(optionalEventC.get().getStatus().equals(Tracking.STATUS_TRACKING.INCOMPLETE), "the event status doesn't match");

        // when
        final Optional<Event> optionalEventD =  shipmentCommandport.pushTracking(trackingD);
        // then
        assertTrue(optionalEventD.isPresent(), "there's not any event to return");
        assertTrue(optionalEventD.get().getStatus().equals(Tracking.STATUS_TRACKING.INCOMPLETE), "the event status doesn't match");
    }


    @Test
    public void whenSendLessWeightAndDeliveredGetsNotNeeded(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEvent =  shipmentCommandport.pushTracking(trackingE);
        // then
        assertTrue(optionalEvent.isPresent(), "there's not any event to return");
        assertTrue(optionalEvent.get().getStatus().equals(Tracking.STATUS_TRACKING.NOT_NEEDED), "the event status doesn't match");
    }

    @Test
    public void whenSendHigherWeightAndDeliveredGetsConcilliationRequest(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEvent =  shipmentCommandport.pushTracking(trackingF);
        // then
        assertTrue(optionalEvent.isPresent(), "there's not any event to return");
        assertTrue(optionalEvent.get().getStatus().equals(Tracking.STATUS_TRACKING.CONCILLIATION_REQUEST), "the event status doesn't match");
    }

    @Test
    public void whenSendTrackingReferenceNotExistsGetsNotFound(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEvent =  shipmentCommandport.pushTracking(trackingG);
        // then
        assertTrue(optionalEvent.isPresent(), "there's not any event to return");
        assertTrue(optionalEvent.get().getStatus().equals(Tracking.STATUS_TRACKING.NOT_FOUND), "the event status doesn't match");
    }

    @Test
    public void whenSendEmptyParcelGetsIncomplete(){
        // given a shipment
        // when
        final Optional<Shipment> optionalShipmentFirst =  shipmentCommandport.save(shipment);
        // then
        assertTrue(optionalShipmentFirst.isPresent(),"the shipment is not saved properly");

        // when
        final Optional<Event> optionalEvent =  shipmentCommandport.pushTracking(trackingH);
        // then
        assertTrue(optionalEvent.isPresent(), "there's not any event to return");
        assertTrue(optionalEvent.get().getStatus().equals(Tracking.STATUS_TRACKING.INCOMPLETE), "the event status doesn't match");
    }

}
