package es.amm.domain.adapters;


import es.amm.domain.adapters.validations.ResolveStatusTracking;
import es.amm.intrastructure.Event;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.domain.ports.ShipmentCommandPort;
import es.amm.intrastructure.repository.RepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentCommandAdapter implements ShipmentCommandPort {

    private RepositoryPort<Shipment,String> repositoryPort;

    public ShipmentCommandAdapter(@Autowired RepositoryPort<Shipment,String> repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    private boolean storeShipment(Shipment shipment){ return repositoryPort.save(shipment); }
    private boolean existsShipment(String reference){ return repositoryPort.exists(reference); }

    @Override
    public Optional<Shipment> save (Shipment shipment){
        // if the shipment exists return empty, to finally return a BAD_REQUEST
        if (shipment != null && existsShipment(shipment.getReference())){
            return Optional.empty();
        }
        return (storeShipment(shipment)) ? Optional.of(shipment) : Optional.empty();
    }

    @Override
    public Optional<Event> pushTracking(Tracking tracking){

        final Optional<Shipment> optionalShipment = repositoryPort.find(tracking.getReference());

        final Optional<Event> event = ResolveStatusTracking.getValidations().stream()
                .map(resolveTrackingStatus -> resolveTrackingStatus.validate(optionalShipment,tracking))
                .filter(optionalEvent -> optionalEvent.isPresent())
                .findFirst().get();

        return event;
    }

}
