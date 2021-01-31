package project.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Database {

    @XmlElement(name="nodeList")
    private ArrayList<MeasurementWrapper> statList;
    private static Database instance;

    private Database() {
        statList = new ArrayList<>();
    }

    // Singleton
    public synchronized static Database getInstance(){
        if(instance==null)
            instance = new Database();
        return instance;
    }

    public synchronized ArrayList<MeasurementWrapper> getStatList() {
        return new ArrayList<>(statList);
    }

    public synchronized void add(MeasurementWrapper stat) {
        statList.add(stat);
    }

    //////////////////////////////////////////// UTILITY METHODS //////////////////////////////////////////////////////

    public Dataset getLastStats(int num) {
        return getLastStats(num, getStatList());
    }

    public Dataset getLastStats(int num, ArrayList<MeasurementWrapper> statListCpy) {
        List<MeasurementWrapper> lastStats = statListCpy.subList(Math.max(statListCpy.size() - num, 0), statListCpy.size());
        ArrayList<MeasurementWrapper> result1 = new ArrayList<>(lastStats);
        Dataset result2 = new Dataset();
        for(int i=0; i<result1.size(); i++) {
            result2.addStat(result1.get(i).getValue(), result1.get(i).getTimestamp());
        }
        return result2;
    }

    public Double getDeviation(int num) {
        ArrayList<MeasurementWrapper> statListCpy = getStatList();
        if(statListCpy.size() <= 0 || num == 0) return 0.0;
        ArrayList<Double> table = getLastStats(num, statListCpy).getValues();
        double mean = getAverage(num);
        double temp = 0;

        for (int i = 0; i < table.size(); i++)
        {
            double val = table.get(i);
            double squrDiffToMean = Math.pow(val - mean, 2);
            temp += squrDiffToMean;
        }
        double meanOfDiffs = (double) temp / (double) (table.size());
        return Math.sqrt(meanOfDiffs);
    }

    public Double getAverage(int num) {
        ArrayList<MeasurementWrapper> statListCpy = getStatList();
        if(statListCpy.size() <= 0 || num == 0) return 0.0;
        Dataset lastMeasures = getLastStats(num, statListCpy);
        ArrayList<Double> values = lastMeasures.getValues();
        double average = 0;
        for(Double d: values) {
            average += d;
        }
        return average/values.size();
    }
}
