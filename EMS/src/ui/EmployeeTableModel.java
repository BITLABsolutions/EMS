
package ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vo.Employee;

/**
 *
 * @author Malith
 */
public class EmployeeTableModel extends AbstractTableModel {
        public static final int OBJECT_COL = -1;
	private static final int EMP_ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	

	private String[] columnNames;
	private List<Employee> person = null;
        
        public EmployeeTableModel() {
        this.columnNames = new String[]{"Employee ID", "First Name", "Last Name"};
		
	}
        
	public EmployeeTableModel(List<Employee> theEmployee) {
        this.columnNames = new String[]{"Employee ID", "First Name", "Last Name"};
		person = theEmployee;
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
               
                
		Employee tempEmployee = person.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempEmployee.getLast_name();
		case FIRST_NAME_COL:
			return tempEmployee.getFirst_name();
		case EMP_ID_COL:
			return tempEmployee.getEmp_id();
                case OBJECT_COL:
			return tempEmployee;
		default:
			return tempEmployee.getLast_name();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


}
