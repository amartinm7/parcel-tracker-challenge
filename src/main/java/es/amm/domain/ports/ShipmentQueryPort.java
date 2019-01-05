package es.amm.domain.ports;

import es.amm.domain.Shipment;

import java.util.Optional;

public interface ShipmentQueryPort {
    Optional<Shipment> get (String reference);
}
