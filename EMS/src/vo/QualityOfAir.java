package vo;

import java.sql.Timestamp;

public class QualityOfAir {

    //percentages out of tenthousand(not out of hundred)

    int co2;
    int o2;
    int n2;
    int humadity;
    Timestamp time;
    String sensorID;
    int measuringFrequency;

    public QualityOfAir(int co2, int o2, int n2, int humadity, Timestamp time, String sensorID, int measuringFrequency) {
        this.co2 = co2;
        this.o2 = o2;
        this.n2 = n2;
        this.humadity = humadity;
        this.time = time;
        this.sensorID = sensorID;
        this.measuringFrequency = measuringFrequency;
    }

    public int getCo2() {
        return co2;
    }

    public int getO2() {
        return o2;
    }

    public int getN2() {
        return n2;
    }

    public int getHumadity() {
        return humadity;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getSensorID() {
        return sensorID;
    }

    public int getMeasuringFrequency() {
        return measuringFrequency;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public void setO2(int o2) {
        this.o2 = o2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public void setHumadity(int humadity) {
        this.humadity = humadity;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    public void setMeasuringFrequency(int measuringFrequency) {
        this.measuringFrequency = measuringFrequency;
    }

}
