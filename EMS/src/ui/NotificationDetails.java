/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.DbConnector;
import dao.LocationDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Location;
import vo.Notification;
import vo.Sensor;

/**
 *
 * @author prabath s
 */
public class NotificationDetails extends javax.swing.JDialog {

    /**
     * Creates new form NotificationDetails
     */
    LocationDAO locationDAO;
    int notification_id;
    ShowNotifications showNotifications;
    //private NotificationDetails d;
    public NotificationDetails(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            locationDAO=new LocationDAO(DbConnector.getInstance().getMyConn());
        } catch (IOException ex) {
            Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setBrokenText(null, null,null);
        //setFixedText(null, null, null);
        //text.setText("<html>prabath<br><t><t>sandaruwan</html>");
        //setBrokenText(null, null,null);
        //setFixedText(null, null, null);
        //text.setText("<html>prabath<br><t><t>sandaruwan</html>");
        
    }
    
    public NotificationDetails(java.awt.Frame parent, boolean modal,ShowNotifications showNotifications,int notification_id) {
        super(parent, modal);
        initComponents();
        try {
            locationDAO=new LocationDAO(DbConnector.getInstance().getMyConn());
        } catch (IOException ex) {
            Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.notification_id=notification_id;
        this.showNotifications=showNotifications;
        //setBrokenText(null, null,null);
        //setFixedText(null, null, null);
        //text.setText("<html>prabath<br><t><t>sandaruwan</html>");
       
    }

    public void setBrokenText(String sensorId,Date genaratedDate,String message){
        
        String temp = "<html><font  size=\"4\" color=\"red\"><center><B>Sensor "+sensorId+" is broken !</B></center></font><br>";
        temp +="<font size=\"4\"><b>Genarated Date : "+genaratedDate+" </b></font><br><br>" ;
        temp +=locationSumary(sensorId);
        if(message!=null){temp+=           "<tr><td><b>Status of damage</b></td><td>"+message+"</td></tr>";}
        text.setText(temp);
    }
    
    public void setFixedText(String sensorId,Date genaratedDate,String message){
       
        String temp = "<html><font  size=\"4\" color=\"blue\"><center><B>Sensor "+sensorId+" is fixed !</B></center></font><br>";
        temp +="<font size=\"4\"><b>Genarated Date : "+genaratedDate+" </b></font><br><br>" ;
        temp +=locationSumary(sensorId);
        if(message!=null){temp+=           "<tr><td><b>Modifications done</b></td><td>"+message+"</td></tr>";}
        text.setText(temp);
    }
    
    private String locationSumary(String sensorId) {
        String temp="";
        temp +="<font  size=\"4\" ><center><B>Sensor Details</B></center></font>";
        temp +="<table>";
         try {
            Location tempLocation = locationDAO.getLocationOfASensor(sensorId);
            String street = tempLocation.getStreet();
            String junction = tempLocation.getNearest_junction();
            Double longitude = tempLocation.getLongitude();
            Double lattitude = tempLocation.getLattitude();
            
            if(street != null & !"".equals(street)){temp+=           "<tr><td><b>Street</b></td><td>"+street+"</td></tr>";}
            if(junction != null & !"".equals(junction)){temp+=       "<tr><td><b>Junction</b></td><td>"+junction+"</td></tr>";}
            temp+=                                                   "<tr><td><b>longitude</b></td><td>"+longitude+"</td></tr>";
            temp+=                                                   "<tr><td><b>lattitude</b></td><td>"+lattitude+"</td></tr>";
            
               
             
        } catch (SQLException ex) {
            Logger.getLogger(SensorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        temp+= "</table></body></html>";
        return temp;
        
    }
    
    private String locationSumary() {
        String temp="";
            temp+="<table>";
            temp+=           "<tr><td><b>Street</b></td><td>"+"mathugama"+"</td></tr>";
            temp+=       "<tr><td><b>Junction</b></td><td>"+"mathugama"+"</td></tr>";
            temp+=     "<tr><td><b>longitude</b></td><td>"+33.33+"</td></tr>";
            temp+=     "<tr><td><b>lattitude</b></td><td>"+44.44+"</td></tr>";
            temp+=           "<tr><td><b>Status of damage</b></td><td>"+"no report since 6 hours"+"</td></tr>";
               
             
        
        temp+= "</table></body></html>";
        return temp;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Delete Notification");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        text.setEditable(false);
        text.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jButton1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            showNotifications.getNotificationDAO().deleteNotification(notification_id);
            showNotifications.refreshGui(this);
            //this.dispose();
            //this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NotificationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NotificationDetails dialog = new NotificationDetails(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane text;
    // End of variables declaration//GEN-END:variables
}
