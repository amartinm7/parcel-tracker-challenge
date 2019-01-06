package es.amm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Shipment {

    private String reference;
    private HashSet<Parcel> parcels;

    public Shipment(String reference,
                    Collection<Parcel> parcels){
        this.reference = reference;
        this.parcels = new LinkedHashSet<>(parcels);
    }

    public Shipment() {
        super();
    }

    @JsonIgnore
    public Long getTotalWeight(){
        return parcels.stream().map(parcel -> parcel.getWeight()).count();
    }

    @JsonIgnore
    public Long getTotalParcels(){
        return Long.valueOf(parcels.size());
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Shipment{");
        sb.append("reference='").append(reference).append('\'');
        sb.append(", parcels=").append(parcels);
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
