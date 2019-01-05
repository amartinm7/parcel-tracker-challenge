package es.amm.domain.adapters;

import es.amm.domain.Shipment;
import es.amm.domain.ports.ShipmentQueryPort;
import es.amm.intrastructure.repository.RepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ShipmentQueryAdapter implements ShipmentQueryPort {

    private RepositoryPort<Shipment,String> repositoryPort;

    public ShipmentQueryAdapter(@Autowired RepositoryPort<Shipment,String> repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<Shipment> get (String reference){
        return repositoryPort.find(reference);
    }

    private boolean isShipmentStored (String reference) {
        return repositoryPort.exists(reference);
    }
}
