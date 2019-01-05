package es.amm.domain.ports;

import es.amm.domain.Shipment;

import java.util.Optional;

public interface ShipmentCommandPort {
    Optional<Shipment> save (Shipment shipment);
}
