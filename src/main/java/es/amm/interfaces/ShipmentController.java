package es.amm.interfaces;

import es.amm.application.ShipmentService;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.CustomResponse;
import es.amm.intrastructure.HttpParams;
import es.amm.intrastructure.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ResponseMessage> register(@RequestBody Shipment shipment) {
        logger.info("asking for register shipment {}", shipment);
        return shipmentService.save(shipment)
                .map(_shipment -> CustomResponse.getOKMessage(_shipment))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @PutMapping(value = HttpParams.URI_PUSH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> push(
            @RequestBody Tracking tracking) {
        logger.info("asking for tracking {}", tracking);
        return shipmentService.addTracking(tracking)
                .map(_tracking -> CustomResponse.getOKMessage(_tracking))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

}
