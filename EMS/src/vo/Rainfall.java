package vo;

import java.sql.Timestamp;

public class Rainfall {

    int height;
    Timestamp time;
    String sensorID;
    int measuringFrequency;

    public Rainfall(int height, Timestamp time, String sensorID, int measuringFrequency) {
        this.height = height;
        this.time = time;
        this.sensorID = sensorID;
        this.measuringFrequency = measuringFrequency;
    }

    public int getHeight() {
        return height;
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

    public void setHeight(int height) {
        this.height = height;
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
