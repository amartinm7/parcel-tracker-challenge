package es.amm.domain.adapters.validations;


import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.Optional;

public class NotNeeded implements ResolveTrackingStatus {

    @Override
    public Optional<Event> validate(Optional<Shipment> optionalShipment, Tracking tracking) {

        if (!optionalShipment.isPresent()){
            return Optional.empty();
        }
        final Shipment shipment = optionalShipment.get();
        boolean isValid = shipment.getReference().equals(tracking.getReference()) &&
                shipment.getTotalParcels().equals(tracking.getParcels()) &&
                tracking.getWeight()!=null && shipment.getTotalWeight() >= tracking.getWeight() &&
                Tracking.STATUS_TRACKING.DELIVERED.equals(tracking.getStatus());

        return isValid ? Optional.of(new Event.Builder()
                .setReference(tracking.getReference())
                .setStatus(Tracking.STATUS_TRACKING.NOT_NEEDED).build())
                : Optional.empty();
    }
}
