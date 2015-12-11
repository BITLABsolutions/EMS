
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
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
	private List<Sensor> person = null;
        
        public SensorTableModel() {
        this.columnNames = new String[]{"Sensor ID", "Location", "Prperties Messured"};
		
	}
        
	public SensorTableModel(List<Sensor> theSensor) {
        this.columnNames = new String[]{"Sensor ID", "Location", "Prperties Messured"};
		person = theSensor;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return person.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
                
		Sensor tempSensor = person.get(row);

		switch (col) {
		case ID_COL:
			return tempSensor.getSensor_id();
		case LOCATION_COL:
			// query the location from location table
		case PROPERTIES_COL:
			// query the properties table
                case OBJECT_COL:
			return tempSensor;
		default:
			return tempSensor.getSensor_id();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
