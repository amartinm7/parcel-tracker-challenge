package es.amm.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Parcel.Builder.class)
public class Parcel {

    private final Long width;
    private final Long lenght;
    private final Long weight;
    private final Long height;

    private Parcel(Long width, Long lenght, Long weight, Long height) {
        this.width = width;
        this.lenght = lenght;
        this.weight = weight;
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public Long getLenght() {
        return lenght;
    }

    public Long getWeight() {
        return weight;
    }

    public Long getHeight() {
        return height;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder{

        private Long width;
        private Long lenght;
        private Long weight;
        private Long height;

        public Builder(){
            super();
        }

        public Builder setWidth(Long width) {
            this.width = width;
            return this;
        }

        public Builder setLenght(Long lenght) {
            this.lenght = lenght;
            return this;
        }

        public Builder setWeight(Long weight) {
            this.weight = weight;
            return this;
        }

        public Builder setHeight(Long height) {
            this.height = height;
            return this;
        }

        public Parcel build(){
            if (this.width == null) {
                throw new IllegalArgumentException("width is required");
            }
            if (this.lenght == null) {
                throw new IllegalArgumentException("lenght is required");
            }
            if (this.width == null) {
                throw new IllegalArgumentException("weight is required");
            }
            if (this.width == null) {
                throw new IllegalArgumentException("height is required");
            }
            return new Parcel(this.width, this.lenght, this.weight, this.height);
        }
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Parcel{");
        sb.append("width=").append(width);
        sb.append(", lenght=").append(lenght);
        sb.append(", weight=").append(weight);
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
}