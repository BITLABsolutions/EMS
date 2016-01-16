package ui;

import common.DbConnector;
import dao.LocationDAO;
import dao.SensorDAO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.imageio.ImageIO;
import vo.Location;
import vo.Sensor;


public class MapGenarator extends JComponent implements ChangeListener {

    JPanel gui;
    /**
     * Displays the image.
     */
    JLabel imageCanvas;
    Dimension size;
    double scale = 1.0;
    public BufferedImage image;
    LocationDAO locationDAO ;
    SensorDAO sensorDAO;
    Connection myConn;
    

    public MapGenarator() {
        size = new Dimension(10, 10);
        setBackground(Color.black);
        
        try {
           
            this.myConn =  DbConnector.getInstance().getMyConn();
            // make a DAO for Employee class by sending in the DB Connection from dbConnector
            locationDAO = new LocationDAO(myConn);
            sensorDAO = new SensorDAO(myConn);
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        try {
            List<Sensor> SensorList = null;
            try {
                SensorList = sensorDAO.getAllSensors();
            } catch (SQLException ex) {
                System.out.println("Can't get sensor data");
            }

            Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            System.out.println(screenSize.height+"  "+screenSize.width);
            String markers ="";
            String breakIcon="http://icons.iconarchive.com/icons/paomedia/small-n-flat/32/pin-icon.png";

            for (Sensor tempSensor : SensorList) {
                Location tempLoc = null;
                if(tempSensor.getOperational()==0){
                    tempLoc = locationDAO.getLocationOfASensor(tempSensor.getSensor_id());
                
                    markers +="&markers=icon:"+breakIcon+"|size:mid|color:orange|lable:X|"+ tempLoc.getLongitude()+","+tempLoc.getLattitude();
                    System.out.println(markers);
                }
            }
            for (Sensor tempSensor : SensorList) {
                Location tempLoc = null;
                if(tempSensor.getOperational()==1){
                    tempLoc = locationDAO.getLocationOfASensor(tempSensor.getSensor_id());
                
                    markers +="&markers=size:mid|color:green|lable:R|"+tempLoc.getLongitude() +","+tempLoc.getLattitude();
                    System.out.println(markers);
                }
            }
            
            
            String MayorIcon ="http://icons.iconarchive.com/icons/paomedia/small-n-flat/24/star-icon.png";
            String imageUrl="https://maps.googleapis.com/maps/api/staticmap?center=6.9296969,79.8755994&zoom=13&size=50000x50000&scale=4&maptype=roadmap&format=png32&markers=size:mid|icon:"+MayorIcon+"|color:red|lable:R|6.9311442,79.8428824"+markers+"&key=AIzaSyDskFi-OpjNmEeA35ygy-iMyKIqBuQAtHo";
            
            //String imageUrl="https://maps.googleapis.com/maps/api/staticmap?center=6.9306969,79.9175994&zoom=12&size=50000x50000&scale=4&maptype=roadmap&format=png32&style=feature:road.local|element:geometry&markers=size:mid|icon:"+MayorIcon+"|color:red|lable:R|6.9311442,79.8428824"+markers+"&key=AIzaSyDskFi-OpjNmEeA35ygy-iMyKIqBuQAtHo";
            
            image = ImageIO.read(new URL(imageUrl));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setImage(Image image) {
        imageCanvas.setIcon(new ImageIcon(image));
    }

    public void initComponents() {
        
        
        
        
        if (gui == null) {
            gui = new JPanel(new BorderLayout());
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));
            imageCanvas = new JLabel();
            JPanel imageCenter = new JPanel(new GridBagLayout());
            imageCenter.add(imageCanvas);
            JScrollPane imageScroll = new JScrollPane(imageCenter);
            imageScroll.setPreferredSize(new Dimension(300, 100));
            gui.add(imageScroll, BorderLayout.CENTER);
        }
    }

    public Container getGui() {
        initComponents();
        return gui;
    }

    public void stateChanged(ChangeEvent e) {
        int value = ((JSlider) e.getSource()).getValue();
        scale = value / 100.0;
        paintImage();
    }

    protected void paintImage() {
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        BufferedImage bi = new BufferedImage(
                (int)(imageWidth*scale), 
                (int)(imageHeight*scale), 
                image.getType());
        Graphics2D g2 = bi.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        double x = (w - scale * imageWidth) / 2;
        double y = (h - scale * imageHeight) / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        at.scale(scale, scale);
        g2.drawRenderedImage(image, at);
        setImage(bi);
    }

    public Dimension getPreferredSize() {
        int w = (int) (scale * size.width);
        int h = (int) (scale * size.height);
        return new Dimension(w, h);
    }

    public JSlider getControl() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 500, 50);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        return slider;
    }

    
}