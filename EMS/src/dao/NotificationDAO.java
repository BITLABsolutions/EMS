/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Destination;
import vo.Maintain;
import vo.Notification;
import vo.Sensor;

/**
 *
 * @author chamin
 */
public class NotificationDAO {

    private final Connection myCon;

    public NotificationDAO(Connection myCon) {
        this.myCon = myCon;
    }

    public void createNotification(Notification notification) throws SQLException {
        PreparedStatement myStat = null;
        try {
            myStat = myCon.prepareStatement("insert into notification (sensor_id  ,genarated_date ,message ,priority  ) values (?,?,?,?)");
            myStat.setString(1, notification.getSensorId());
            myStat.setDate(2, (Date) notification.getGenaratedDate());
            myStat.setString(3, notification.getMessage());
            myStat.setString(4, notification.getPriority());
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }
    }

    public void deleteNotificationByID(int NotificationId) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myCon.prepareStatement("delete from notification where notification_id  = ?");

            //set param
            myStmt.setInt(1, NotificationId);

            //execute statement
            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }

    public List<Notification> getAllNotifications() throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<Notification> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select * from notification");
            myResult = myStat.executeQuery();
            while (myResult.next()) {
                Notification temp = convertRowToObject(myResult);
                result_list.add(temp);
            }

        } finally {
            close(myStat);
        }
        return result_list;
    }

    public List<Notification> getNotificationBySensorId(String SensorId) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<Notification> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select * from notification  where  sensor_id =? ");
            myStat.setString(1, SensorId);
            myResult = myStat.executeQuery();
            while (myResult.next()) {
                Notification temp = convertRowToObject(myResult);
                result_list.add(temp);
            }

        } finally {
            close(myStat);
        }
        return result_list;
    }

    public List<Notification> getNotificationBygenaratedDate(Date GenaratedDate) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<Notification> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select * from notification  where  genarated_date =? ");
            myStat.setDate(1, GenaratedDate);
            myResult = myStat.executeQuery();
            while (myResult.next()) {
                Notification temp = convertRowToObject(myResult);
                result_list.add(temp);
            }

        } finally {
            close(myStat);
        }
        return result_list;
    }

    public List<Notification> getNotificationByNotificationId(int notification_id) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        List<Notification> result_list = new ArrayList<>();
        try {
            myStat = myCon.prepareStatement("select * from notification  where  notification_id =? ");
            myStat.setInt(1, notification_id);
            myResult = myStat.executeQuery();
            while (myResult.next()) {
                Notification temp = convertRowToObject(myResult);
                result_list.add(temp);
            }

        } finally {
            close(myStat);
        }
        return result_list;
    }

    public int getNotificationCount(String type) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet myResult = null;
        int count = 0;
        try {
            if (type == "admin") {
                myStat = myCon.prepareStatement("select count(*) from notification where seenByAdmin=0");

                myResult = myStat.executeQuery();
                while (myResult.next()) {
                    count = myResult.getInt(1);
                }
            }
            if (type == "maintain") {
                myStat = myCon.prepareStatement("select count(*) from notification where seenByMaintain=0");

                myResult = myStat.executeQuery();
                while (myResult.next()) {
                    count = myResult.getInt(1);
                }
            }

            if (type == "all") {
                myStat = myCon.prepareStatement("select count(*) from notification");

                myResult = myStat.executeQuery();
                while (myResult.next()) {
                    count = myResult.getInt(1);
                }
            }

        } finally {
            close(myStat);
        }
        return count;
    }

    public void changeSeenStatus(int notification_id, String user) throws SQLException {
        PreparedStatement myStat = null;
        if (user.equals("admin")) {
            try {
                myStat = myCon.prepareStatement("UPDATE `ems`.`notification` SET `seenByAdmin`=1 WHERE `notification_id`=?;");
                myStat.setInt(1, notification_id);
                myStat.executeUpdate();
            } finally {
                close(myStat);
            }
        }
        if (user.equals("maintain")) {
            try {
                myStat = myCon.prepareStatement("UPDATE `ems`.`notification` SET `seenByMaintain`=1 WHERE `notification_id`=?;");
                myStat.setInt(1, notification_id);
                myStat.executeUpdate();
            } finally {
                close(myStat);
            }
        }

    }

    public void deleteNotification(int notification_id) throws SQLException {
        PreparedStatement myStat = null;
        try {
            myStat = myCon.prepareStatement("delete from notification where notification_id=?");
            myStat.setInt(1, notification_id);
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }

    }
    
    public void deleteAllNotifications() throws SQLException {
        PreparedStatement myStat = null;
        try {
            myStat = myCon.prepareStatement("delete from notification");
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }

    }

    private Notification convertRowToObject(ResultSet result) throws SQLException {
        int notificationId = result.getInt(1);
        String sensorId = result.getString(2);
        Date genaratedDate = result.getDate(3);
        String message = result.getString(4);
        String priority = result.getString(5);
        boolean seenByAdmin = result.getBoolean(6);
        boolean seenByMaintain = result.getBoolean(7);
        boolean read = result.getBoolean(8);
        boolean broken = result.getBoolean(9);
        //Notification temp = new Notification( sensorId, genaratedDate, message, priority);
        Notification temp = new Notification(notificationId, sensorId, genaratedDate, message, priority, seenByAdmin, seenByMaintain, read, broken);
        return temp;

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
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }

}
