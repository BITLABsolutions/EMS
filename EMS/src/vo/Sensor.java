/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Arrays;
import java.util.Date;



/**
 *
 * @author Malith
 */
public class Sensor {
    private String sensor_id;
    private String serial_no;
    private Date installed_date;
    private String measure_type;

    public Sensor(String sensor_id, String serial_no, Date installed_date, String measure_type) {
        this.sensor_id = sensor_id;
        this.serial_no = serial_no;
        this.installed_date = installed_date;
        this.measure_type = measure_type;
    }

    public String getMeasure_type() {
        return measure_type;
    }

    public void setMeasure_type(String measure_type) {
        this.measure_type = measure_type;
    }

    
    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSerial_num() {
        return serial_no;
    }

    public void setSerial_num(String serial_no) {
        this.serial_no = serial_no;
    }

    public Date getInstalled_date() {
        return installed_date;
    }

    public void setInstalled_date(Date installed_date) {
        this.installed_date = installed_date;
    }
    
     public String getSummary(){
        String temp = "<html><body>";
        
        String name = "";
        name+= "<h1><font color=\"#00BFFF\">"+"Sensor "+sensor_id+"</font>&#160";    
         
        temp="<TABLE ALIGN=CENTER WIDTH=\"90%\" BORDER=0 CELLSPACING=1 CELLPADDING=1><caption>"+name+"</caption>";   
                      
        if(serial_no != null & !"".equals(serial_no)){temp+=           "<tr><td><b>Serial number</b></td><td>"+serial_no+"</td></tr>";}
        if(installed_date != null ){temp+=                               "<tr><td><b>Installed date</b></td><td>"+installed_date+"</td></tr>";}
        
       // temp+= "</table></body></html>";
        return temp;
    }

}
