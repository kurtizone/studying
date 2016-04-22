package org.gui.task.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableMemoryModel extends AbstractTableModel{
	protected List<ExecuteEntry> list;	
	private String[] headers;
	
	public TableMemoryModel() {
		headers = new String[3];
		list = new ArrayList<ExecuteEntry>();
		headers[0] = "Id";
		headers[1] = "File Path";
		headers[2] = "Time of Execution";
	}
	

	public int getColumnCount() {
		return 3;
	}


	public int getRowCount() {
		return list.size();
	}


	public Object getValueAt(int rowIndex, int columnIndex) {
		ExecuteEntry entry = list.get(rowIndex);
		switch (columnIndex) {
		case 0: return entry.getId(); 			
		case 1: return entry.getFile().getAbsolutePath(); 			
		case 2: return entry.getDate();			
		default:
			return "Error";
		}
	}

	public List<ExecuteEntry> getList() {
		return list;
	}

	public void setList(List<ExecuteEntry> list) {
		this.list = list;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
	
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ExecuteEntry entry = list.get(rowIndex);
		switch (columnIndex) {		
		case 1: entry.setFile(new File((String) aValue)); break;			
		case 2: entry.setDate((String) aValue); break;			
		default: System.out.println("ERROR");
		}
		fireTableCellUpdated(rowIndex,columnIndex);
		
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if(col < 1) {
			return false;
		} else 
			return true;
		
	}
	
	public void addRow(ExecuteEntry entry) {
		list.add(entry);
		fireTableDataChanged();
	}
	
	public void removeRow(int rowIndex) {
		list.remove(rowIndex);
		fireTableDataChanged();
	}

	public void removeRow(ExecuteEntry entry) {
		list.remove(entry);
		fireTableDataChanged();
	}

}
