package project.node;

import project.sensor.Measurement;

import java.util.ArrayList;

public class TokenJava {
    private static TokenJava instance;
    private ArrayList<Measurement> averageMeasures;

    private TokenJava() {
        averageMeasures = new ArrayList<Measurement>();
    }

    // Singleton
    public synchronized static TokenJava getInstance(){
        if(instance==null)
            instance = new TokenJava();
        return instance;
    }

    public synchronized int size() {
        return this.averageMeasures.size();
    }

    public synchronized ArrayList<Measurement> getAverageMeasures() {
        return this.averageMeasures;
    }

    public synchronized void clear() {
        this.averageMeasures = new ArrayList<Measurement>();
    }

    public synchronized void setAverageMeasures(ArrayList<Measurement> averageMeasures) {
        this.averageMeasures = averageMeasures;
    }

    public synchronized void addAverageMeasures(Measurement measurement) {
        if(this.averageMeasures == null)
            this.averageMeasures = new ArrayList<Measurement>();
        this.averageMeasures.add(measurement);
    }

    public synchronized void sleeping() throws InterruptedException {
        wait();
    }

    public synchronized void awakening() {
        notify();
    }
}
