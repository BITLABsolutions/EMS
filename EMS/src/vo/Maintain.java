/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.sql.Time;
import java.util.Date;



/**
 *
 * @author prabath s
 */
public class Maintain {
    private int employee_id;
    private int sensor_id;
    private Date rep_date;
    private Time rep_time;
    private String rep_details;

    public Maintain(int employee_id, int sensor_id, Date rep_date, Time rep_time, String varchar) {
        this.employee_id = employee_id;
        this.sensor_id = sensor_id;
        this.rep_date = rep_date;
        this.rep_time = rep_time;
        this.rep_details = varchar;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public Date getRep_date() {
        return rep_date;
    }

    public void setRep_date(Date rep_date) {
        this.rep_date = rep_date;
    }

    public Time getRep_time() {
        return rep_time;
    }

    public void setRep_time(Time rep_time) {
        this.rep_time = rep_time;
    }

    public String getRepDetails() {
        return rep_details;
    }

    public void setRepDetails(String rep_details) {
        this.rep_details = rep_details;
    }
    public java.sql.Date get_sql_date(){
        
        return new java.sql.Date(rep_date.getTime());
    }
    
    
}
