/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Location;
import vo.Sensor;

/**
 *
 * @author prabath s
 */
public class SensorDAO {
    
    Connection myCon;
    public SensorDAO(Connection myCon){
        this.myCon=myCon;
    }
    
    public void insertSensor(Sensor sensor) throws SQLException{
        PreparedStatement myStat=null;
        try{
            myStat=myCon.prepareStatement("Insert into sensor (serial_no, installed_date, measure_types, sensor_id) values(?,?,?,?)");
            myStat.setString(1, sensor.getSerial_num());
            myStat.setDate(2, new java.sql.Date(sensor.getInstalled_date().getTime()));
            myStat.setString(3, sensor.getMeasure_type());
            myStat.setString(4, sensor.getSensor_id());
            
            // execute the statement
            myStat.executeUpdate();
        }
        finally{
            close(myStat);
        }
    }
    public List<Sensor> getAllSensors() throws SQLException{
        PreparedStatement myStat=null;
        ResultSet result=null;
        List<Sensor> result_list=new ArrayList<>();
        try{
            String query="Select * from sensor";
            myStat=myCon.prepareStatement(query);
            result=myStat.executeQuery();
            while(result.next()){
                Sensor temp=convertRowToASensor(result);
                result_list.add(temp);
            }
        }
        finally{
            close(myStat, result);
        }
        return result_list;
    }
    
    public List<Sensor> searchSensor(String keyWord,String searchPara) throws SQLException{
        List<Sensor> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String query;
        try {
            switch (searchPara) {
          
                case "Serial number":
                    keyWord = "%" + keyWord + "%";
                    query = "select * from sensor where serial_no like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                case "Sensor ID":
                    keyWord = "%" + keyWord + "%";
                    query = "select * from sensor where sensor_id like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                               
                case "Property messured":
                    keyWord = "%" + keyWord + "%";
                    query = "select * from sensor where measure_types like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                case "Location":
                   //query = "select * from sensor where sensor_id in(select sensor_id from location where street like ?)";
                   String[] arr=keyWord.split(":");
                   if(arr.length==2){
                       query = "select * from sensor where sensor_id in(select sensor_id from location where street like ? or nearest_junction like ?)";
                       myStmt = myCon.prepareStatement(query);
                       String keyWord1="%"+arr[0]+"%";
                       String keyWord2="%"+arr[1]+"%";
                       myStmt.setString(1, keyWord1);
                       myStmt.setString(2, keyWord2);
                   }
                   else if(keyWord.equals(":"+arr[0]) && arr.length==1){
                       keyWord = "%" + arr[0] + "%";
                       query = "select * from sensor where sensor_id in(select sensor_id from location where nearest_junction like ?)";
                       myStmt = myCon.prepareStatement(query);
                       myStmt.setString(1, keyWord);
                   }
                   else if(keyWord.equals(arr[0]+":") && arr.length==1){
                       keyWord="%"+arr[0]+"%";
                       query = "select * from sensor where sensor_id in(select sensor_id from location where street like ?)";
                       myStmt = myCon.prepareStatement(query);
                       myStmt.setString(1, keyWord);
                   }
                   
                    
                    
            }

            // execute statement
            myRs = myStmt.executeQuery();

            //load persons to a Employee List
            while (myRs.next()) {
                Sensor tempSensor = convertRowToASensor(myRs);
                list.add(tempSensor);
            }
            return list;
        } finally {
            close(myStmt, myRs);
        }
        
    }
     public Sensor getSensor(String street, String junction, String measureType) throws SQLException{
         
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String query;
       
          
                

        query = "select * from sensor where measure_types = ? and  sensor_id in (select sensor_id from location where street = ? and nearest_junction = ?)";
        myStmt = myCon.prepareStatement(query);
        myStmt.setString(1, measureType);
        myStmt.setString(2, street);
        myStmt.setString(3, junction);




        // execute statement
        myRs = myStmt.executeQuery();
        while ( myRs.next()){
       
        Sensor tempSensor = convertRowToASensor(myRs);    
        return tempSensor;}
        return null;
        
    }
     
    private Sensor convertRowToASensor(ResultSet result) throws SQLException{
        String sensor_id=result.getString(1);
        String serial_no=result.getString(2);
        Date installed_date=result.getTimestamp(3);
        String measure_types = result.getString(4);
        return new Sensor(sensor_id, serial_no, installed_date,measure_types);
        
    }
    
    public void deleteSensor(String sensor_id) throws SQLException{
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myCon.prepareStatement("delete from sensor where sensor_id = ?");

            //set param
            myStmt.setString(1, sensor_id);

            //execute statement
            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }
    
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
            myRs.close();
        }
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) {
        try {
            close(null, myStmt, myRs);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }
}
