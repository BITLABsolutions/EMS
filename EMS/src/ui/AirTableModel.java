
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vo.QualityOfAir;

/**
 *
 * @author Malith
 */
public class AirTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int TIME_COL = 0;
	private static final int CO2_COL = 1;
	private static final int O2_COL = 2;
        private static final int N2_COL = 3;
        private static final int HUMIDITY_COL = 4;
	

	private String[] columnNames;
	private List<QualityOfAir> qualityOfAir = null;
        
        public AirTableModel() {
        this.columnNames = new String[]{"Time", "CO2", "O2", "N2", "Humidity"};
		
	}
        
	public AirTableModel(List<QualityOfAir> theQualityOfAir) {
        this.columnNames = new String[]{"Time", "CO2", "O2", "N2", "Humidity"};
		qualityOfAir = theQualityOfAir;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return qualityOfAir.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
               
                
		QualityOfAir tempQualityOfAir = qualityOfAir.get(row);

		switch (col) {
		case TIME_COL:
			return tempQualityOfAir.getTime();
		case CO2_COL:
			return tempQualityOfAir.getCo2();
                case O2_COL:
                    return tempQualityOfAir.getO2();
                case N2_COL:
                    return tempQualityOfAir.getN2();
                case HUMIDITY_COL:
                    return tempQualityOfAir.getHumadity();
                case OBJECT_COL:
			return tempQualityOfAir;
		default:
			return tempQualityOfAir.getTime();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
