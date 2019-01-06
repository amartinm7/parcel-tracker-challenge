package es.amm.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Shipment {

    private String reference;
    private HashSet<Parcel> parcels;
    private Tracking tracking;

    public Shipment(String reference,
                    Collection<Parcel> parcels,
                    Tracking tracking ){
        this.reference = reference;
        this.parcels = new LinkedHashSet<>(parcels);
        this.tracking = tracking;
    }

    public Shipment() {
        super();
    }

    public Long getTotalWeight(){
        return parcels.stream().map(parcel -> parcel.getWeight()).count();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public HashSet<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(HashSet<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Shipment{");
        sb.append("reference='").append(reference).append('\'');
        sb.append(", parcels=").append(parcels);
        sb.append(", tracking=").append(tracking);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment)) return false;

        Shipment shipment = (Shipment) o;

        return getReference().equals(shipment.getReference());
    }

    @Override
    public int hashCode() {
        return getReference().hashCode();
    }
}
