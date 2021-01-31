package project.node;

import project.sensor.Buffer;
import project.sensor.Measurement;

import java.util.ArrayList;

// Implements Buffer interface adding a sliding window filter
public class NodeBuffer implements Buffer {
    private static int SLIDING_WINDOW_SIZE = 12;
    private static int HALF_WINDOW = SLIDING_WINDOW_SIZE/2;
    private ArrayList<Measurement> slidingWindow;
    private ArrayList<Measurement> data;
    private static NodeBuffer instance;
    private int index;

    private NodeBuffer() {
        data = new ArrayList<Measurement>();
        slidingWindow = new ArrayList<Measurement>();
        index = 0;
    }

    // Singleton
    public synchronized static NodeBuffer getInstance(){
        if(instance==null)
            instance = new NodeBuffer();
        return instance;
    }

    // Compute and average measure based on the size of the sliding window
    private synchronized Measurement slidingWindowAverage(){
        String id = "id";
        String type = "type";
        double value = 0;
        long timestamp = 0;
        Measurement localAverage = new Measurement(id, type, value, timestamp);

        for(int i=0; i < SLIDING_WINDOW_SIZE; i++) {
            Measurement tmp = slidingWindow.get(i);
            value += tmp.getValue();
            timestamp += tmp.getTimestamp();
            if (i==SLIDING_WINDOW_SIZE/2) {
                localAverage.setId(tmp.getId());
                localAverage.setType(tmp.getType());
            }
        }
        slidingWindow.subList(0, HALF_WINDOW).clear();

        localAverage.setValue(value/SLIDING_WINDOW_SIZE);
        localAverage.setTimestamp(timestamp/SLIDING_WINDOW_SIZE);

        return localAverage;
    }

    public synchronized Measurement localAverage() {
        String id = "id";
        String type = "type";
        double value = 0;
        long timestamp = 0;
        Measurement globalAverage = new Measurement(id, type, value, timestamp);

        if(data.size() == 0)
            return globalAverage;

        for(int i=0; i < data.size(); i++) {
            Measurement tmp = data.get(i);
            value += tmp.getValue();
            timestamp += tmp.getTimestamp();
            if (i == data.size()/2) {
                globalAverage.setId(tmp.getId());
                globalAverage.setType(tmp.getType());
            }
        }
        globalAverage.setValue(value/data.size());
        globalAverage.setTimestamp(timestamp/data.size());

        return globalAverage;
    }

    @Override
    public synchronized void addMeasurement(Measurement m) {
        slidingWindow.add(m);
        if(slidingWindow.size() >= SLIDING_WINDOW_SIZE){
            Measurement average = slidingWindowAverage();
            data.add(average);
        }
    }

    public synchronized int size(){
        return data.size();
    }

    public synchronized ArrayList<Measurement> getData(){
        return this.data;
    }
}
