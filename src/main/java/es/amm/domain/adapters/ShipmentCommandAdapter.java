package es.amm.domain.adapters;


import es.amm.domain.Shipment;
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

    private boolean isShipmentStored(String reference){
        return repositoryPort.exists(reference);
    }

    private boolean storeShipment(Shipment shipment){ return repositoryPort.save(shipment); }


    @Override
    public Optional<Shipment> save (Shipment shipment){
        if (isShipmentStored(shipment.getReference())){
            throw new IllegalArgumentException(String.format("The Shipment reference %s already exists in the system.", shipment.getReference()));
        }
        return (storeShipment(shipment)) ? Optional.of(shipment) : Optional.empty();
    }

}
