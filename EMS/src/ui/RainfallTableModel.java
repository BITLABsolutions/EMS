
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vo.Rainfall;

/**
 *
 * @author Malith
 */
public class RainfallTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int TIME_COL = 0;
	private static final int HEIGHT_COL = 1;
	
	

	private String[] columnNames;
	private List<Rainfall> rainfall = null;
        
        public RainfallTableModel() {
        this.columnNames = new String[]{"Time", "Height"};
		
	}
        
	public RainfallTableModel(List<Rainfall> theRainfall) {
        this.columnNames = new String[]{"Time", "Height"};
		rainfall = theRainfall;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return rainfall.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
                
		Rainfall tempRainfall = rainfall.get(row);

		switch (col) {
		case TIME_COL:
			return tempRainfall.getTime();
		case HEIGHT_COL:
			return tempRainfall.getHeight();
                case OBJECT_COL:
			return tempRainfall;
		default:
			return tempRainfall.getTime();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
