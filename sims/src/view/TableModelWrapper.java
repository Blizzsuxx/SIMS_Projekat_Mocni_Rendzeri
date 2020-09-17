package view;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableModelWrapper extends AbstractTableModel
{
	private ArrayList<Object[]> data;
	private String[] columnNames;
	private Class<?>[] columnTypes;
	private boolean[] editableColumns;
	private int[] columnWidths;
	private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
	
	public TableModelWrapper(String[] columnNames, Class<?>[] columnTypes,
		boolean[] editableColumns, ArrayList<Object[]> data) {
		this.columnNames = columnNames;
		this.columnTypes = columnTypes;
		this.editableColumns = editableColumns;
		this.data = data;
	}
	
	public TableModelWrapper(String[] columnNames, Class<?>[] columnTypes,
			boolean[] editableColumns, int[] columnWidths, ArrayList<Object[]> data) {
			this.columnNames = columnNames;
			this.columnTypes = columnTypes;
			this.editableColumns = editableColumns;
			this.columnWidths = columnWidths;
			this.data = data;
		}
	
	public void setColumnEditable(int index, boolean editable) {
		this.editableColumns[index] = editable;
	}
	
	public void setColumnWidths(TableColumnModel columns) {
		for (int i = 0; i < columns.getColumnCount(); i++) {
			columns.getColumn(i).setPreferredWidth(this.columnWidths[i]);
		}
	}
	
	@Override
	public void addTableModelListener(TableModelListener l)
	{
		if (listeners.contains(l)) {
			return;
		}
		listeners.add(l);
	}
	
	/**
	 * Returns the most specific superclass for all the cell values in the
	 * column. This is used by the JTable to set up a default renderer and
	 * editor for the column.
	*/
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return columnTypes[columnIndex];
	}
	
	/**
	 * Returns the number of columns in the model. A JTable uses this method to
	 * determine how many columns it should create and display by default.
	*/
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	/**
	 * Returns the name of the column at columnIndex. This is used to initialize
	 * the table's column header name. Note: this name does not need to be
	 * unique; two columns in a table can have the same name.
	*/
	@Override
	public String getColumnName(int columnIndex)
	{
		return columnNames[columnIndex];
	}
	
	/**
	 * Returns the number of rows in the model. A JTable uses this method to
	 * determine how many rows it should display. This method should be quick,
	 * as it is called frequently during rendering.
	*/
	@Override
	public int getRowCount()
	{
		return data.size();
	}
	
	/**
	 * Returns the value for the cell at columnIndex and rowIndex.
	*/
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data.get(rowIndex)[columnIndex];
	}
	
	/**
	 * Returns true if the cell at rowIndex and columnIndex is editable.
	 * Otherwise, setValueAt on the cell will not change the value of that cell.
	*/
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return editableColumns[columnIndex];
	}
	
	/**
	 * Removes a listener from the list that is notified each time a change to
	 * the data model occurs.
	*/
	@Override
	public void removeTableModelListener(TableModelListener l)
	{
		if (listeners.contains(l)) {
			listeners.remove(l);
		}
	}
	
	/**
	 * Sets the value in the cell at columnIndex and rowIndex to value.
	*/
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex)
	{
		data.get(rowIndex)[columnIndex] = value;
		TableModelEvent dogadjaj = new TableModelEvent(this, rowIndex,
				rowIndex, columnIndex, TableModelEvent.UPDATE);
		/*
		 * Table has been changed, you must inform listeners about that, because
		 * you won't see any change otherwise.
		 */
		for (TableModelListener l: listeners)
			l.tableChanged(dogadjaj);
	}
	
	/**
	 * This method returns current table model data
	*/
	public ArrayList<Object[]> getData() {
		return data;
	}
	
	/**
	 * This method returns record from model data based on index
	*/
	public Object[] getRecord(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < data.size()) {
			return data.get(rowIndex);
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is used for adding a new row into JTable
	*/
	public void addRow(Object[] row)
	{
		data.add(row);
		TableModelEvent event = new TableModelEvent(this, data.size() - 1,
				data.size() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		for (TableModelListener l: listeners)
			l.tableChanged(event);
	}
	
	/**
	 * This method is used for removing a row from JTable
	*/
	public void deleteRow(int rowIndex)
	{
		data.remove(rowIndex);
		TableModelEvent event = new TableModelEvent(this, rowIndex,
				rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		for (TableModelListener l: listeners)
			l.tableChanged(event);
	}
	
	/**
	 * This method is used for get column index from column name
	*/
	public int columnIndex(String columnName) {
		int i = 0;
		for (String column : columnNames) {
			if (column == columnName) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

