package vo;

import java.sql.Timestamp;

public class Wind {

    int speed;
    int direction; //number of degrees from North (anticlockwise)
    Timestamp time;
    String sensorID;
    int measuringFrequency;

    public Wind(int speed, int direction, Timestamp time, String sensorID, int measuringFrequency) {
        this.speed = speed;
        this.direction = direction;
        this.time = time;
        this.sensorID = sensorID;
        this.measuringFrequency = measuringFrequency;
    }

    public Wind(int aInt, int aInt0, Timestamp timestamp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
