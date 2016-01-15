
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vo.Wind;

/**
 *
 * @author Malith
 */
public class WindTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int TIME_COL = 0;
	private static final int SPEED_COL = 1;
	private static final int DIRECTION_COL = 2;
	

	private String[] columnNames;
	private List<Wind> wind = null;
        
        public WindTableModel() {
        this.columnNames = new String[]{"Time", "Speed", "Direction"};
		
	}
        
	public WindTableModel(List<Wind> theWind) {
        this.columnNames = new String[]{"Time", "Speed", "Direction"};
		wind = theWind;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return wind.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
                
		Wind tempWind = wind.get(row);

		switch (col) {
		case TIME_COL:
			return tempWind.getTime();
		case SPEED_COL:
			return tempWind.getSpeed();
                case DIRECTION_COL:
                    return tempWind.getDirection();
                case OBJECT_COL:
			return tempWind;
		default:
			return tempWind.getTime();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
