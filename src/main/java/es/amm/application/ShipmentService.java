package es.amm.application;

import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.Optional;

public interface ShipmentService {

    Optional<Shipment> save (Shipment shipment);

    Optional<Event> pushTracking(Tracking tracking);

}
