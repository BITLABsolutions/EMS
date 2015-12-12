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
