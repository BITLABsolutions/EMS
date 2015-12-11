/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.math.BigDecimal;

/**
 *
 * @author prabath s
 */
public class Location {
    private int sensor_id;
    private String street;
    private String nearest_junction;
    private float longitude;
    private float lattitude;

    
    public Location(int sensor_id, String street, String nearest_junction, float longitude, float lattitude) {
        this.sensor_id = sensor_id;
        this.street = street;
        this.nearest_junction = nearest_junction;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }
    
    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNearest_junction() {
        return nearest_junction;
    }

    public void setNearest_junction(String nearest_junction) {
        this.nearest_junction = nearest_junction;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    
    public BigDecimal getBGValue(float x){
        return BigDecimal.valueOf(x);
    }
    
    
}
