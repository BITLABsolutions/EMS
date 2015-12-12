package vo;

import java.sql.Timestamp;

public class Temperature {

    int tempValue;
    Timestamp time;
    String sensorID;
    int measuringFrequency;

    public Temperature(int tempValue, Timestamp time, String sensorID, int measuringFrequency) {
        this.tempValue = tempValue;
        this.time = time;
        this.sensorID = sensorID;
        this.measuringFrequency = measuringFrequency;
    }

    public int getTempValue() {
        return tempValue;
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

    public void setTempValue(int tempValue) {
        this.tempValue = tempValue;
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
