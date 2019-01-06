package es.amm.interfaces;

import es.amm.application.ShipmentService;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;
import es.amm.intrastructure.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HttpParams.URI_API)
public class ShipmentController {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    private ShipmentService shipmentService;

    public ShipmentController(@Autowired ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping(value = HttpParams.URI_REGISTER,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> register(@RequestBody Shipment shipment) {
        logger.info("asking for register a shipment {}", shipment);
        return shipmentService.save(shipment)
                .map(_shipment -> new ResponseEntity(_shipment, HttpStatus.OK ))
                .orElse(new ResponseEntity(shipment, HttpStatus.BAD_REQUEST));
    }

    @PutMapping(value = HttpParams.URI_PUSH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> push(
            @RequestBody Tracking tracking) {
        logger.info("asking for a tracking {}", tracking);
        return shipmentService.pushTracking(tracking)
                .map(event -> new ResponseEntity(event, HttpStatus.OK ))
                .orElse( new ResponseEntity( new Event.Builder()
                        .setStatus(Tracking.STATUS_TRACKING.NOT_FOUND)
                        .setReference(tracking.getReference()).build(),
                        HttpStatus.BAD_REQUEST));
    }

}
