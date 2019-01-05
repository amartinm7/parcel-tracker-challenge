package es.amm.domain;


public class Parcel {

    private Long width;
    private Long lenght;
    private Long weight;
    private Long height;

    public Parcel() {
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getLenght() {
        return lenght;
    }

    public void setLenght(Long lenght) {
        this.lenght = lenght;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
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