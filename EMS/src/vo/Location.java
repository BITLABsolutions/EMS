/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;



/**
 *
 * @author prabath s
 */
public class Location {
    private String sensor_id;
    private String street;
    private String nearest_junction;
    private double longitude;
    private double lattitude;

    
    public Location(String sensor_id, String street, String nearest_junction, double longitude, double lattitude) {
        this.sensor_id = sensor_id;
        this.street = street;
        this.nearest_junction = nearest_junction;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }
    
    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    
    
    
    
    
}
