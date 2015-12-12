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
    private String serial_num;
    private Date installed_date;
    private String measure_types;

    public Sensor(String sensor_id, String serial_num, Date installed_date, String measure_types) {
        this.sensor_id = sensor_id;
        this.serial_num = serial_num;
        this.installed_date = installed_date;
        this.measure_types = measure_types;
    }

    public String getMeasure_types() {
        return measure_types;
    }

    public void setMeasure_types(String[] measure_types) {
        this.measure_types = convert_arr_to_string(measure_types);
    }

    
    
    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
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
    
     public String getSummary(){
        String temp = "<html><body>";
        
        String name = "";
        name+= "<h1><font color=\"#00BFFF\">"+"Sensor "+sensor_id+"</font>&#160";    
         
        temp="<TABLE ALIGN=CENTER WIDTH=\"90%\" BORDER=0 CELLSPACING=1 CELLPADDING=1><caption>"+name+"</caption>";   
                      
        if(serial_num != null & !"".equals(serial_num)){temp+=           "<tr><td><b>Serial number</b></td><td>"+serial_num+"</td></tr>";}
        if(installed_date != null ){temp+=                               "<tr><td><b>Installed date</b></td><td>"+installed_date+"</td></tr>";}
        
       // temp+= "</table></body></html>";
        return temp;
    }

    public String[] convert_string_to_arr(String str){
        String[] result = null;
            result = str.split("");
        return result;
    }
    
    public String convert_arr_to_string(String[] arr){
        String result = null;
            result = Arrays.toString(arr);
        return result;
    }
}
