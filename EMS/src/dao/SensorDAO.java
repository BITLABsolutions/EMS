/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            myStat=myCon.prepareStatement("Insert into sensor (sensor_id,serial_num,installed_date) values(?,?,?)");
            myStat.setInt(1, sensor.getSensor_id());
            myStat.setString(2, sensor.getSerial_num());
            myStat.setDate(3, new java.sql.Date(sensor.getInstalled_date().getTime()));
        }
        finally{}
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
    
    public Sensor getSensorBySerialNumber(String serial_num) throws SQLException{
        PreparedStatement myStat=null;
        ResultSet result=null;
        List<Sensor> result_list=new ArrayList<>();
        Sensor sensor=null;
        try{
            String query="Select * from sensor where serial_num=?";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, serial_num);
            result=myStat.executeQuery();
            while(result.next()){
                Sensor temp=convertRowToASensor(result);
                sensor=temp;
            }
        }
        finally{
            close(myStat, result);
        }
        return sensor;
    }
    
    private Sensor convertRowToASensor(ResultSet result) throws SQLException{
        int sensor_id=result.getInt(1);
        String serial_num=result.getString(2);
        Date installed_date=result.getTimestamp(3);
        return new Sensor(sensor_id, serial_num, installed_date);
        
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
