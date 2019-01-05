package es.amm.domain;

public class Tracking {

    private STATUS_TRACKING status;
    private Long parcels;
    private Long weight;
    private String reference;

    public enum STATUS_TRACKING {
        DELIVERED, WAITING_IN_HUB, CONCILLIATION_REQUEST, NOT_NEEDED, INCOMPLETE, NOT_FOUND;
    }

    public Tracking() {

    }

    public STATUS_TRACKING getStatus() {
        return status;
    }

    public void setStatus(STATUS_TRACKING status) {
        this.status = status;
    }

    public Long getParcels() {
        return parcels;
    }

    public void setParcels(Long parcels) {
        this.parcels = parcels;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tracking{");
        sb.append("status=").append(status);
        sb.append(", parcels=").append(parcels);
        sb.append(", weight=").append(weight);
        sb.append(", reference='").append(reference).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
