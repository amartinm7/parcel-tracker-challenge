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
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tracking)) return false;

        Tracking tracking = (Tracking) o;

        if (getStatus() != tracking.getStatus()) return false;
        if (!getParcels().equals(tracking.getParcels())) return false;
        if (!getWeight().equals(tracking.getWeight())) return false;
        return getReference().equals(tracking.getReference());
    }

    @Override
    public int hashCode() {
        int result = getStatus().hashCode();
        result = 31 * result + getParcels().hashCode();
        result = 31 * result + getWeight().hashCode();
        result = 31 * result + getReference().hashCode();
        return result;
    }
}
