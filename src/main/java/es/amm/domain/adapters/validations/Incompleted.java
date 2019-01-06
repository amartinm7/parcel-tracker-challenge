package es.amm.domain.adapters.validations;


import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.Optional;

public class Incompleted implements ResolveTrackingStatus {

    @Override
    public Optional<Event> validate(Optional<Shipment> optionalShipment, Tracking tracking) {

        if (!optionalShipment.isPresent()){
            return Optional.empty();
        }
        final Shipment shipment = optionalShipment.get();
        boolean isValid = shipment.getReference().equals(tracking.getReference()) &&
                !Tracking.STATUS_TRACKING.DELIVERED.equals(tracking.getStatus());

        return isValid ? Optional.of(new Event.Builder()
                .setReference(tracking.getReference())
                .setStatus(Tracking.STATUS_TRACKING.INCOMPLETE).build())
                : Optional.empty();
    }
}
