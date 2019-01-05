package es.amm.domain.repository;

import es.amm.domain.Shipment;
import es.amm.intrastructure.repository.RepositoryPort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class RepositoryProviderAdapter implements RepositoryPort<Shipment,String> {

    private final Map<String, Shipment> store = new HashMap<>();

    @Override
    public boolean save(Shipment shipment) {
        return (store.put(shipment.getReference(),shipment) == null);
    }

    @Override
    public boolean remove(String reference) {
        return (store.remove(reference)==null);
    }

    @Override
    public Optional<Shipment> find(String reference) {
        return (store.containsKey(reference)) ? Optional.of(store.get(reference)) : Optional.empty();
    }

    @Override
    public boolean exists(String reference) {
        return store.containsKey(reference);
    }
}
