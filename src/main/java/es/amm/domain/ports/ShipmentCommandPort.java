package es.amm.domain.ports;

import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.Optional;

public interface ShipmentCommandPort {
    Optional<Shipment> save (Shipment shipment);
    Optional<Event> pushTracking(Tracking tracking);
}
