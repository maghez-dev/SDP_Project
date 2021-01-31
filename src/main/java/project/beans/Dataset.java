package project.beans;

import java.util.ArrayList;

// Bean used to pass measurement to client
public class Dataset {
    private ArrayList<Double> values;
    private ArrayList<Long> timestamps;

    public Dataset() {
        this.values = new ArrayList<>();
        this.timestamps = new ArrayList<>();
    }

    public void addStat(double value, long timestamp) {
        values.add(value);
        timestamps.add(timestamp);
    }

    public int size() {
        return values.size();
    }

    public double getValue(int index){
        return values.get(index);
    }

    public ArrayList<Double> getValues() {
        return this.values;
    }

    public long getTimestamp(int index){
        return timestamps.get(index);
    }

    public ArrayList<Long> getTimestamps() {
        return this.timestamps;
    }
}
