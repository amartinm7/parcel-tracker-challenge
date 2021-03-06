package es.amm.intrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import es.amm.domain.Tracking;

@JsonDeserialize(builder = Event.Builder.class)
public class Event {

    private final String reference;
    private final Tracking.STATUS_TRACKING status;

    private Event(String reference, Tracking.STATUS_TRACKING status) {
        this.reference = reference;
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public Tracking.STATUS_TRACKING getStatus() {
        return status;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder{
        private String reference;
        private Tracking.STATUS_TRACKING status;

        public Builder(){
            super();
        }

        public Builder setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder setStatus(Tracking.STATUS_TRACKING status) {
            this.status = status;
            return this;
        }

        public Event build(){
            if (this.reference == null) {
                throw new IllegalArgumentException("width is required");
            }
            if (this.status == null) {
                throw new IllegalArgumentException("lenght is required");
            }
            return new Event(this.reference, this.status);
        }
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex){
            final StringBuffer sb = new StringBuffer("{");
            sb.append("\"reference\":").append(reference).append("\",");
            sb.append("\"status\":").append(status.toString()).append("\"");
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (!getReference().equals(event.getReference())) return false;
        return getStatus() == event.getStatus();
    }

    @Override
    public int hashCode() {
        int result = getReference().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
