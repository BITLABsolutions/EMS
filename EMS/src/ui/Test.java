/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.DbConnector;
import dao.EmployeeDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malith
 */
public class Test {
    public static void main(String args[]){
        try {
            EmployeeDAO empDao = new EmployeeDAO(DbConnector.getInstance().getMyConn());
            List ls = empDao.getAllEmployee();
            int i =4;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
