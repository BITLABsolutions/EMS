
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vo.Temperature;

/**
 *
 * @author Malith
 */
public class TemperatureTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int TIME_COL = 0;
	private static final int VALUE_COL = 1;
	
	

	private String[] columnNames;
	private List<Temperature> temperature = null;
        
        public TemperatureTableModel() {
        this.columnNames = new String[]{"Time", "Value"};
		
	}
        
	public TemperatureTableModel(List<Temperature> theTemperature) {
        this.columnNames = new String[]{"Time", "Value"};
		temperature = theTemperature;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return temperature.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
                
		Temperature tempTemperature = temperature.get(row);

		switch (col) {
		case TIME_COL:
			return tempTemperature.getTime();
		case VALUE_COL:
			return tempTemperature.getTempValue();
                case OBJECT_COL:
			return tempTemperature;
		default:
			return tempTemperature.getTime();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
