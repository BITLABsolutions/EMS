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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Destination;
import vo.Location;
import vo.Maintain;

/**
 *
 * @author Chamin
 */
public class DestinationDAO {
    private final Connection myCon;
    
    
    public DestinationDAO(Connection myCon) {
        this.myCon = myCon;
    }
    
    public Connection GetMyCon(){
        return myCon;
    }
    
    public void insertNotificationDestination(Destination destination) throws SQLException {
        PreparedStatement myStat = null;
        try {
            myStat = myCon.prepareStatement("insert into destination (notification_id ,destiny) values (?,?)");
            myStat.setInt(1, destination.getNotificationId());
            myStat.setString(2, destination.getDestiny());
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }
    }
    public List<Integer> getNotificationsInADestiny(String Destiny) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<Integer> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select notification_id from destination  where  destiny =? ");
            myStat.setString(1, Destiny);
            myResult= myStat.executeQuery();
            while(myResult.next()){
                int temp = myResult.getInt(1);
                result_list.add(temp);
            }
            
        } finally {
            close(myStat);
        }
        return result_list;
    }
    
    public List<String> getDestiniesOfANotification(int  NotificationId) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<String> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select destiny from destination  where  notification_id = ? ");
            myStat.setInt(1,NotificationId);
            myResult= myStat.executeQuery();
            while(myResult.next()){
                String temp = myResult.getString(1);
                result_list.add(temp);
            }
            
        } finally {
            close(myStat);
        }
        return result_list;
    }
     public void deleteByDestiny(String Destiny) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myCon.prepareStatement("delete from destination where destiny = ?");

            //set param
            myStmt.setString(1, Destiny);

            //execute statement
            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }
     
      public void deleteByNotification(int NotificationId) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myCon.prepareStatement("delete from destination where notification_id = ?");

            //set param
            myStmt.setInt(1, NotificationId);

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
            Logger.getLogger(DestinationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }
    
}
