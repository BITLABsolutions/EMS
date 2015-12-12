
package ui;

import common.DbConnector;
import dao.LocationDAO;
import dao.SensorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import vo.Location;
import vo.Sensor;

/**
 *
 * @author Malith
 */
public class SensorTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int LOCATION_COL = 1;
	private static final int PROPERTIES_COL = 2;
	

	private String[] columnNames;
	private List<Sensor> sensor = null;
        private LocationDAO locationDAO;
        
        public SensorTableModel() {
        this.columnNames = new String[]{"Sensor ID", "Location", "Prperties Messured"};
        try {
                locationDAO = new LocationDAO(DbConnector.getInstance().getMyConn());
            } catch (IOException | SQLException ex) {
                Logger.getLogger(SensorTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
        
	public SensorTableModel(List<Sensor> theSensor) {
        this.columnNames = new String[]{"Sensor ID", "Location", "Prperties Messured"};
		sensor = theSensor;
            try {
                locationDAO = new LocationDAO(DbConnector.getInstance().getMyConn());
            } catch (IOException | SQLException ex) {
                Logger.getLogger(SensorTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return sensor.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
            
                
            try {
                Sensor tempSensor = sensor.get(row);
                Location tempLocation = locationDAO.getLocationOfASensor(tempSensor.getSensor_id());
               
                switch (col) {
                    case ID_COL:
                        return tempSensor.getSensor_id();
                    case LOCATION_COL:                       
                        return tempLocation.getStreet() + ", " + tempLocation.getNearest_junction();
                    case PROPERTIES_COL:
                         return tempSensor.getMeasure_types();
                    case OBJECT_COL:
                        return tempSensor;
                    default:
                        return tempSensor.getSensor_id();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SensorTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
