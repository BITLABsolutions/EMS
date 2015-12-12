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
            myStat=myCon.prepareStatement("Insert into sensor (sensor_id, serial_no, installed_date, measure_types) values(?,?,?,?)");
            myStat.setString(1, sensor.getSensor_id());
            myStat.setString(2, sensor.getSerial_num());
            myStat.setDate(3, new java.sql.Date(sensor.getInstalled_date().getTime()));
            myStat.setString(4, sensor.getMeasure_types());
            
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
    
    public List<Sensor> getSensorBySerialNumber(String keyWord,String searchPara) throws SQLException{
        List<Sensor> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String query;
        try {
            keyWord = "%" + keyWord + "%";
            switch (searchPara) {
          
                case "serialNumber":
                    query = "select * from sensor where serial_no like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                case "sensor_id":
                    query = "select * from employee where sensor_id like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                               
                case "property":
                    query = "select * from employee where measure_types like ?";
                    myStmt = myCon.prepareStatement(query);
                    myStmt.setString(1, keyWord);
                    break;
                default:
                   query = "select * from employee where emp_id like ? or first_name like ? or last_name like ? or nic like ? or username like ? or street like ? or town like ? or phone like ?";
                    myStmt = myCon.prepareStatement(query);
                    //set parameters
                    for (int i = 1; i < 9; i++) {
                        myStmt.setString(i, keyWord);
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
