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
    
    public List<String> getAllSreets() throws SQLException {
        PreparedStatement myStat = null;
        List<String> result_list = new ArrayList<>();
        ResultSet result = null;
        try{
            String query="Select DISTINCT street from location";
            myStat=myCon.prepareStatement(query);
            result=myStat.executeQuery();
            while(result.next()){               
                result_list.add(result.getString(1));
            }
        }
        finally{
            close(myStat, result);
        }
        return result_list;
    }
    
    
    public void insertLocationOfSensor(Location location) throws SQLException{
        PreparedStatement myStat = null;
        try {
            
            myStat = myCon.prepareStatement("insert into location (sensor_id,street,nearest_junction,longitude,latitude) values (?,?,?,?,?)");
            setParams(location, myStat);
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }
    }
    
    public PreparedStatement setParams(Location location, PreparedStatement myStat) throws SQLException {
        myStat.setString(1, location.getSensor_id());
        myStat.setString(2, location.getStreet());
        myStat.setString(3, location.getNearest_junction());
        myStat.setDouble(4, location.getLongitude());
        myStat.setDouble(5, location.getLattitude());
        return myStat;
    }
    public Location getLocationOfASensor(String sensor_id) throws SQLException{
        PreparedStatement myStat = null;
        ResultSet result = null;
        Location location=null;
        try{
            String query="Select * from location where sensor_id=?";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, sensor_id);
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
    
    public List<Location> getSensorsInALocation(double longitude,double latitude) throws SQLException{
        PreparedStatement myStat = null;
        List<Location> result_list = new ArrayList<>();
        ResultSet result = null;
        
        try{
            String query="Select * from location where longitude=? and latitude=?";
            myStat=myCon.prepareStatement(query);
            myStat.setDouble(1, longitude);
            myStat.setDouble(2, latitude);
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
    
    
    public String getNearestJunction(String sensor_id) throws SQLException{
        PreparedStatement myStat = null;
        ResultSet result = null;
        String nearest_junction=null;
        try{
            String query="Select nearest_junction from location where sensor_id=?";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, sensor_id);
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
    public Location getLocation(String street, String junction){
        PreparedStatement myStat = null;
        ResultSet result = null;
        
        try{
            String query="Select * from location where street=? and nearest_junction = ?";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, street);
            myStat.setString(2, junction);
            result=myStat.executeQuery();
            if(result.next()){
                return convertRowToALocation(result);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        finally{
            close(myStat, result);
        }
        return null;
    }
    
    public int getBrokenSensorCount(String street, String junction){
        PreparedStatement myStat = null;
        ResultSet result = null;
        
        try{
            String query="Select count(sensor_id) from location where street=? and nearest_junction = ? and functional = 0";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, street);
            myStat.setString(2, junction);
            result=myStat.executeQuery();
            if(result.next()){
                return Integer.parseInt(result.toString());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        finally{
            close(myStat, result);
        }
        return 0;
    }
    
    public int getWorkingSensorCount(String street, String junction){
        PreparedStatement myStat = null;
        ResultSet result = null;
        
        try{
            String query="Select count(sensor_id) from location where street=? and nearest_junction = ? and functional = 1";
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, street);
            myStat.setString(2, junction);
            result=myStat.executeQuery();
            if(result.next()){
                return Integer.parseInt(result.toString());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        finally{
            close(myStat, result);
        }
        return 0;
    }
    
    public Location convertRowToALocation(ResultSet result) throws SQLException {
        String sensor_id=result.getString(1);
        String street=result.getString(2);
        String nearest_junction=result.getString(3);
        double longitude=result.getDouble(4);
        double lattitude=result.getDouble(5);
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

    public String[] getReleventJunctions(String street) {
        PreparedStatement myStat = null;
        
        List<String> result_list = new ArrayList<>();
        ResultSet result = null;
        try{
            String query="select nearest_junction from location where street = ?";           
            myStat=myCon.prepareStatement(query);
            myStat.setString(1, street);
            
            result=myStat.executeQuery();
            while(result.next()){               
                result_list.add(result.getString(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            close(myStat, result);
        }
         
            int size = result_list.size();
            String[] junctionArray = new String[size];
            for (int i = 0; i < size; i++) {
                junctionArray[i] = result_list.get(i);
            }
            return junctionArray;

    }

}
