package project.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasurementWrapper {
    private String id;
    private String type;
    private double value;
    private long timestamp;

    public MeasurementWrapper() {}

    public MeasurementWrapper(String id, String type, double value, long timestamp) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
