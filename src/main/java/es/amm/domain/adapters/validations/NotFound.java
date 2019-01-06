package es.amm.domain.adapters.validations;


import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.Optional;

public class NotFound implements ResolveTrackingStatus {

    @Override
    public Optional<Event> validate(Optional<Shipment> optionalShipment, Tracking tracking) {

        final Event event = new Event.Builder()
                .setReference(tracking.getReference())
                .setStatus(Tracking.STATUS_TRACKING.NOT_FOUND).build();

        if (!optionalShipment.isPresent()){
            return Optional.of(event);
        }

        final Shipment shipment = optionalShipment.get();
        boolean isValid = !shipment.getReference().equals(tracking.getReference());

        return isValid ? Optional.of(event)
                : Optional.empty();
    }
}
