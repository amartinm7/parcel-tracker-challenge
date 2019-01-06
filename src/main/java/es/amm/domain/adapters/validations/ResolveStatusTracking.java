package es.amm.domain.adapters.validations;

import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.intrastructure.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@FunctionalInterface
public interface ResolveStatusTracking {

    Optional<Event> validate (final Optional<Shipment> optionalShipment, final Tracking tracking);

    static Collection<ResolveStatusTracking> getValidations (){
        Collection<ResolveStatusTracking> collection = new ArrayList<>();
        collection.add(new ConcilliationRequest());
        collection.add(new Incompleted());
        collection.add(new IncompletedByEmptyTrackingFields());
        collection.add(new NotFound());
        collection.add(new NotNeeded());
        return collection;

    }
}
