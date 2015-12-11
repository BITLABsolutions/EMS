/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Location;

/**
 *
 * @author prabath s
 */
public class LocationDAO {

    Connection myCon;

    public LocationDAO(Connection myCon) {
        this.myCon = myCon;
    }

    public List<Location> getAllLocations() throws SQLException {
        PreparedStatement myStat = null;
        List<Location> result_list = new ArrayList<>();
        ResultSet result = null;
        try{
            String query="Select * from location";
            myStat=myCon.prepareStatement(query);
            result=myStat.executeQuery();
            while(result.next()){
                Location temp=convertRowToALocation(result);
                result_list.add(temp);
            }
        }
        finally{
            close(myStat, result);
        }
        return result_list;
    }
    
    public Location getLocationOfASensor(int sensor_id) throws SQLException{
        PreparedStatement myStat = null;
        ResultSet result = null;
        Location location=null;
        try{
            String query="Select * from location where sensor_id=?";
            myStat=myCon.prepareStatement(query);
            myStat.setInt(1, sensor_id);
            result=myStat.executeQuery();
            if(result.next()){
                location =convertRowToALocation(result);
            }
        }
        finally{
            close(myStat, result);
        }
        return location;
    }
    
    public List<Location> getSensorsInALocation(float longitude,float latitude) throws SQLException{
        PreparedStatement myStat = null;
        List<Location> result_list = new ArrayList<>();
        ResultSet result = null;
        BigDecimal longitude1=BigDecimal.valueOf(longitude);
        BigDecimal latitude1=BigDecimal.valueOf(latitude);
        try{
            String query="Select * from location where longitude=? and latitude=?";
            myStat=myCon.prepareStatement(query);
            myStat.setBigDecimal(1, longitude1);
            myStat.setBigDecimal(2, latitude1);
            result=myStat.executeQuery();
            while(result.next()){
                Location temp=convertRowToALocation(result);
                result_list.add(temp);
            }
        }
        finally{
            close(myStat, result);
        }
        return result_list;
    }
    
    public String getNearestJunction(int sensor_id) throws SQLException{
        PreparedStatement myStat = null;
        ResultSet result = null;
        String nearest_junction=null;
        try{
            String query="Select nearest_junction from location where sensor_id=?";
            myStat=myCon.prepareStatement(query);
            myStat.setInt(1, sensor_id);
            result=myStat.executeQuery();
            if(result.next()){
                nearest_junction=result.getString(1);
            }
        }
        finally{
            close(myStat, result);
        }
        return nearest_junction;
    }

    public Location convertRowToALocation(ResultSet result) throws SQLException {
        int sensor_id=result.getInt(1);
        String street=result.getString(2);
        String nearest_junction=result.getString(3);
        float longitude=result.getBigDecimal(4).floatValue();
        float lattitude=result.getBigDecimal(5).floatValue();
        return new Location(sensor_id, street, nearest_junction, longitude, lattitude);
        
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
