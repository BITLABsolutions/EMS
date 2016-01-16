/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.DbConnector;
import dao.NotificationDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;

/**
 *
 * @author prabath s
 */
public class CheckNotificationCount implements Runnable {

    JMenu menu;
    NotificationDAO notificationDAO;
    String priority;

    public CheckNotificationCount(JMenu menu,String priority) throws IOException, SQLException {
        this.menu = menu;
        this.notificationDAO = new NotificationDAO(DbConnector.getInstance().getMyConn());
        this.priority = priority;
    }

    @Override
    public void run() {
        while (true) {
            if (priority.equals("1")) {
                try {
                    menu.setText("Notifications(" + notificationDAO.getNotificationCount("maintain") + ")");
                } catch (SQLException ex) {
                    Logger.getLogger(CheckNotificationCount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (priority.equals("2")) {
                try {
                    menu.setText("Notifications("+notificationDAO.getNotificationCount("admin")+")");
                } catch (SQLException ex) {
                    Logger.getLogger(CheckNotificationCount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckNotificationCount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}


