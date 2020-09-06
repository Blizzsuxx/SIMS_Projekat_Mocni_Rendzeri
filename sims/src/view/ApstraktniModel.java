package view;

import javax.swing.table.AbstractTableModel;

public abstract class ApstraktniModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private String [] columnNames;

	public ApstraktniModel(String[] columnNames) {
		super();
		this.columnNames = columnNames;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex){
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
