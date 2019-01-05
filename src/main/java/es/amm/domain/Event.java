package es.amm.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

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
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("reference='").append(reference).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
