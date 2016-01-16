/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.DbConnector;
import dao.NotificationDAO;
import dao.SensorDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import vo.Notification;
import vo.Sensor;

/**
 *
 * @author prabath s
 */
public class ShowNotifications extends javax.swing.JDialog {

    /**
     * Creates new form Notification
     */
    NotificationDAO notificationDAO;
    SensorDAO sensorDAO;
    ShowNotifications s;
    String priority;

    public ShowNotifications(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        try {
            notificationDAO = new NotificationDAO(DbConnector.getInstance().getMyConn());
            sensorDAO = new SensorDAO(DbConnector.getInstance().getMyConn());
        } catch (IOException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            createButtons(notificationDAO.getNotificationCount("all"), notificationDAO.getAllNotifications(), "2");
        } catch (SQLException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        s=this;
        //createButtons();

    }

    public ShowNotifications(java.awt.Frame parent, boolean modal,String priority) {
         super(parent, modal);
        initComponents();
        this.priority = priority;
        try {
            notificationDAO = new NotificationDAO(DbConnector.getInstance().getMyConn());
            sensorDAO = new SensorDAO(DbConnector.getInstance().getMyConn());
        } catch (IOException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            createButtons(notificationDAO.getNotificationCount("all"), notificationDAO.getAllNotifications(), priority);
        } catch (SQLException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        s=this;
        
    }
    
    public void createButtons(int count, List<Notification> list, String priority) {

        Dimension d = new Dimension(jScrollPane1.getWidth(), 60 * count+100);
        jPanel1.setPreferredSize(d);
        System.out.println("inside create:"+count);
        for (int i = 0; i < count; i++) {
            Notification n = list.get(i);
            if (Integer.parseInt(n.getPriority()) <= Integer.parseInt(priority)) {
                JButton j = new JButton();
                jPanel1.add(j);
                j.setSize(450, 60);
                if (i == 0) {
                    j.setLocation(1, 50);
                } else {
                    j.setLocation(1, 70 * i + 50);
                }
                if (priority == "2" && !n.isSeenByAdmin()) {
                    j.setBackground(Color.DARK_GRAY);
                }
                if (priority == "1" && !n.isSeenByMaintain()) {
                    j.setBackground(Color.DARK_GRAY);
                }
                //j.setText("<html>prabath sandaruwan is requesting to change the details of prabath sandaruwan");
                if (n.isBroken()) {
                    j.setText("<html>" + " Sensor " + n.getSensorId() + " is broken");
                }
                if (!n.isBroken()) {
                    j.setText("<html>" + " Sensor " + n.getSensorId() + " is fixed");
                }
                j.setName(String.valueOf(i));
                j.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                //new NotificationDetails(null, rootPaneCheckingEnabled).setVisible(true);
                                if (n.isBroken()) {
                                    NotificationDetails notificationDetails = new NotificationDetails(null, rootPaneCheckingEnabled,s,n.getNotificationId());
                                    //Sensor sensor=sensorDAO.getSensorById(n.getSensorId());
                                    notificationDetails.setBrokenText(n.getSensorId(), n.getGenaratedDate(), n.getMessage());
                                    notificationDetails.setVisible(true);
                                }
                                if (!n.isBroken()) {
                                    NotificationDetails notificationDetails = new NotificationDetails(null, rootPaneCheckingEnabled,s,n.getNotificationId());
                                    //Sensor sensor=sensorDAO.getSensorById(n.getSensorId());
                                    notificationDetails.setFixedText(n.getSensorId(), n.getGenaratedDate(), n.getMessage());
                                    notificationDetails.setVisible(true);

                                }
                                if (priority.equals("2")) {
                                    try {
                                        notificationDAO.changeSeenStatus(n.getNotificationId(), "admin");
                                        j.setBackground(Color.LIGHT_GRAY);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                if (priority.equals("1")) {
                                    try {
                                        notificationDAO.changeSeenStatus(n.getNotificationId(), "maintain");
                                        j.setBackground(Color.LIGHT_GRAY);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        });

            }

        }
    }

    public void createButtons() {

        for (int i = 0; i < 5; i++) {
            //Notification n = list.get(i);
            JButton j = new JButton();
            jPanel1.add(j);
            j.setSize(450, 60);
            if (i == 0) {
                j.setLocation(1, 50);
            } else {
                j.setLocation(1, 70 * i + 50);
            }
            j.setText("<html>prabath sandaruwan is requesting to change the details of prabath sandaruwan");

            j.setName(String.valueOf(i));
            j.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new NotificationDetails(null, rootPaneCheckingEnabled).setVisible(true);

                        }
                    });

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(458, 825));

        jPanel1.setPreferredSize(new java.awt.Dimension(473, 1137));

        jButton1.setText("Clear all notifications");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(1103, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            notificationDAO.deleteAllNotifications();
            refreshGui();
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void refreshGui(){
        this.dispose();
        new ShowNotifications(null, rootPaneCheckingEnabled).setVisible(true);
    }
    public void refreshGui(NotificationDetails d){
        this.dispose();
        d.dispose();
        new ShowNotifications(null, rootPaneCheckingEnabled).setVisible(true);
        
    }
    public NotificationDAO getNotificationDAO() {
        return notificationDAO;
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowNotifications.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowNotifications.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowNotifications.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowNotifications.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShowNotifications dialog = new ShowNotifications(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
