/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Date;

/**
 *
 * @author Malith
 */
public class Sensor {
    private int sensor_id;
    private String serial_num;
    private Date installed_date;

    public Sensor(int sensor_id, String serial_num, Date installed_date) {
        this.sensor_id = sensor_id;
        this.serial_num = serial_num;
        this.installed_date = installed_date;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public Date getInstalled_date() {
        return installed_date;
    }

    public void setInstalled_date(Date installed_date) {
        this.installed_date = installed_date;
    }
    
    
    
}
