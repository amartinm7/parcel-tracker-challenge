package es.amm.application;

import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import es.amm.domain.ports.ShipmentCommandPort;
import es.amm.intrastructure.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService{

    private ShipmentCommandPort shipmentCommandPort;

    public ShipmentServiceImpl(@Autowired ShipmentCommandPort shipmentCommandPort){
        this.shipmentCommandPort = shipmentCommandPort;
    }

    @Override
    public Optional<Shipment> save(Shipment shipment) {
        return shipmentCommandPort.save(shipment);
    }

    @Override
    public Optional<Event> pushTracking(Tracking tracking) {
        return shipmentCommandPort.pushTracking(tracking);
    }
}
