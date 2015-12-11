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
import vo.Maintain;

/**
 *
 * @author prabath s
 */
public class MaintainsDAO {
    private final Connection myCon;

    public MaintainsDAO(Connection myCon) {
        this.myCon = myCon;
    }

    public void insertMaintanaceRecord(Maintain maintain) throws SQLException {
        PreparedStatement myStat = null;
        try {
            myStat = myCon.prepareStatement("insert into maintains (emp_id,sensor_id,rep_date,rep_time,rep_detail) values (?,?,?,?,?)");
            setParams(maintain, myStat);
            myStat.executeUpdate();
        } finally {
            close(myStat);
        }
    }

    public List<Maintain> getMaintenanceRecordsOfEmployee(int employee_id) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet result = null;
        List<Maintain> result_list = new ArrayList<>();
        try {
            String query = "Select * from maintain where maintain.employee_id=?";
            myStat = myCon.prepareStatement(query);
            myStat.setInt(1, employee_id);
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                result_list.add(temp);
            }
            return result_list;

        } finally {
            close(myStat, result);
        }

    }

    public Maintain getParticularMaintainRecord(int emp_id, int sensor_id, java.util.Date date, java.sql.Time time) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet result = null;
        Maintain maintain = null;
        try {
            String query = "Select * from maintain where rep_date=? and rep_time=? and emp_id=? and sensor_id=?";
            myStat = myCon.prepareStatement(query);
            myStat.setDate(1, new java.sql.Date(date.getTime()));
            myStat.setTime(2, time);
            myStat.setInt(3, emp_id);
            myStat.setInt(4, sensor_id);
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                maintain = temp;
            }
            return maintain;
        } finally {
            close(myStat, result);
        }
    }

    public Maintain getParticularMaintainRecord(int sensor_id, java.util.Date date, java.sql.Time time) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet result = null;
        Maintain maintain = null;
        try {
            String query = "Select * from maintain where rep_date=? and rep_time=? and sensor_id=?";
            myStat = myCon.prepareStatement(query);
            myStat.setDate(1, new java.sql.Date(date.getTime()));
            myStat.setTime(2, time);
            myStat.setInt(3, sensor_id);
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                maintain = temp;
            }
            return maintain;
        } finally {
            close(myStat, result);
        }
    }

    public List<Maintain> getMaintenanceRecordsInADay(java.util.Date date) throws SQLException {
        PreparedStatement myStat = null;
        ResultSet result = null;
        List<Maintain> result_list = new ArrayList<>();
        try {
            String query = "Select * from maintain where rep_date=?";
            myStat = myCon.prepareStatement(query);
            myStat.setDate(1, new java.sql.Date(date.getTime()));
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                result_list.add(temp);
            }
            return result_list;
        } finally {
            close(myStat, result);
        }
    }

    public List<Maintain> getMaintaienaceRecordsOfASensor(int sensor_id) throws SQLException {

        PreparedStatement myStat = null;
        ResultSet result = null;
        List<Maintain> result_list = new ArrayList<>();
        try {
            String query = "Select * from maintain where sensor_id=?";
            myStat = myCon.prepareStatement(query);
            myStat.setInt(1, sensor_id);
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                result_list.add(temp);
            }
            return result_list;
        } finally {
            close(myStat, result);
        }
    }

    public List<Maintain> getAllMaintainRecords() throws SQLException {
        PreparedStatement myStat = null;
        ResultSet result = null;
        List<Maintain> result_list = new ArrayList<>();
        try {
            String query = "Select * from maintain";
            myStat = myCon.prepareStatement(query);
            result = myStat.executeQuery();
            while (result.next()) {
                Maintain temp = convertRowToObject(result);
                result_list.add(temp);
            }
            return result_list;
        } finally {
            close(myStat, result);
        }
    }

    public PreparedStatement setParams(Maintain maintain, PreparedStatement myStat) throws SQLException {
        myStat.setInt(1, maintain.getEmployee_id());
        myStat.setInt(2, maintain.getSensor_id());
        myStat.setDate(3, maintain.get_sql_date());
        myStat.setTime(4, maintain.getRep_time());
        myStat.setString(5, maintain.getRepDetails());
        return myStat;
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

    private Maintain convertRowToObject(ResultSet result) throws SQLException {
        int emp_id = result.getInt(1);
        int sensor_id = result.getInt(2);
        java.util.Date rep_date = result.getTimestamp(3);
        java.sql.Time rep_time = result.getTime(4);
        String rep_detail = result.getString(5);
        Maintain temp = new Maintain(emp_id, sensor_id, rep_date, rep_time, rep_detail);
        return temp;

    }
}
