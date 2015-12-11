/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Employee;

/**
 *
 * @author Malith
 */
public class EmployeeDAO {
    private final Connection myConn;

    public EmployeeDAO(Connection myConn) throws IOException, SQLException {
        this.myConn = myConn;
    }

    public Connection getMyConn() {
        return myConn;
    }

    /**
     * get all person to a List
     *
     * @return 
     */
    public List<Employee> getAllEmployee() {

        List<Employee> list = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from employee");

            // load each person object to the Employee List
            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                list.add(tempEmployee);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(myStmt, myRs);
        }
        return list;
    }

    /**
     * search persons for given input parameters like last name, tel number, id,
     * AC number
     *
     * @param keyWord
     * @param searchPara - should be one of these --> emp_id, first_name, last_name, nic, username, street, town, phone
     * @return 
     * @throws java.lang.Exception 
     */
    public List<Employee> searchEmployee(String keyWord, String searchPara) throws Exception {

        List<Employee> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String query;
        try {
            keyWord += "%";
            switch (searchPara) {
          
                //if "keyWord" in any field in the record, return them
                case "All":
                    query = "select * from employee where emp_id like ? or first_name like ? or last_name like ? or nic like ? or username like ? or street like ? or town like ? or phone like ?";
                    myStmt = myConn.prepareStatement(query);
                    //set parameters
                    for (int i = 1; i < 9; i++) {
                        myStmt.setString(i, keyWord);
                    }
                    break;
                    
                default:
                    query = "select * from employee where ? like ?";
                    myStmt = myConn.prepareStatement(query);
                    myStmt.setString(1, searchPara);
                    myStmt.setString(2, keyWord);

            }

            // execute statement
            myRs = myStmt.executeQuery();

            //load persons to a Employee List
            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                list.add(tempEmployee);
            }
            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    /**
     * add a new person
     *
     * @param employee
     * @throws java.sql.SQLException
     */
    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            //prepare statement
            myStmt = myConn.prepareStatement("INSERT INTO employee (emp_id, first_name, last_name, nic, username, password, access_level,street,town, phone, sex)values (?,?,?,?,?,?,?,?,?,?,?)");

            //set params
           myStmt = setParams(myStmt, employee);

            // execute the statement
            myStmt.executeUpdate();
            
        } finally {
            close(myStmt);
        }
    }

    /**
     * update person
     *
     * @param employee
     * @param previousNIC
     * @throws java.sql.SQLException
     */
    public void updateEmployee(Employee employee, int previousNIC) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            //prepare the statement
            myStmt = myConn.prepareStatement("update employee set emp_id = ?, first_name = ?, last_name = ?, nic = ?, username = ?, password = ?, access_level = ?, street = ?, town = ?, phone = ?, sex = ? where nic = ? ");

            // set params
            myStmt = setParams(myStmt, employee);
            myStmt.setInt(12, previousNIC);
            //execute statement
            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }
    
    

    /**
     * delete person
     *
     * @param emp_id
     * @throws SQLException
     */
    public void deleteEmployee(int emp_id) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myConn.prepareStatement("delete from employee where emp_id = ?");

            //set param
            myStmt.setInt(1, emp_id);

            //execute statement
            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }

    /**
     * close connections
     *
     * @param myConn
     * @param myStmt
     * @param myRs
     * @throws SQLException
     */
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

    /**
     * convert a row from DB in to a Employee
     *
     * @param myRs
     * @return
     * @throws SQLException
     */
    private Employee convertRowToEmployee(ResultSet myRs) throws SQLException {

        int emp_id = myRs.getInt(1);
        String first_name = myRs.getString(2);
        String last_name = myRs.getString(3);
        String nic = myRs.getString(4);
        String username = myRs.getString(5);
        String password = myRs.getString(6);
        String access_level = myRs.getString(7);
        String street = myRs.getString(8);
        String town = myRs.getString(9);
        String phone = myRs.getString(10);
        String sex = myRs.getString(11);
        

        Employee tempEmployee = new Employee(emp_id,  first_name,  last_name,  nic,  username,  password,  access_level,  street,  town,  phone,  sex);
        return tempEmployee;
    }
    
    /**
     * Method to set parameters in to a mySql statement
     * @param myStmt
     * @param employee
     * @return
     * @throws SQLException 
     */
    private PreparedStatement setParams(PreparedStatement myStmt, Employee employee) throws SQLException{
        myStmt.setInt(1, employee.getEmp_id());
        myStmt.setString(2, employee.getFirst_name());
        myStmt.setString(3, employee.getLast_name());
        myStmt.setString(4 , employee.getNic());
        myStmt.setString(5, employee.getUsername());
        myStmt.setString(6, employee.getPassword());
        myStmt.setString(7, employee.getAccess_level());
        myStmt.setString(8, employee.getStreet());
        myStmt.setString(9, employee.getTown());
        myStmt.setString(10, employee.getPhone());
        myStmt.setString(11, employee.getSex());
        
        return myStmt;
    }
   

}
