package org.gui.task.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyTableArrayModel extends AbstractTableModel{
	
	List<ExecuteEntry> list;	
	String[] headers;
	
	public MyTableArrayModel() {
		headers = new String[3];
		list = new ArrayList<>();
		headers[0] = "Id";
		headers[1] = "File Path";
		headers[2] = "Time of Execution";
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ExecuteEntry entry = list.get(rowIndex);
		switch (columnIndex) {
		case 0: return entry.getId(); 			
		case 1: return entry.getFile().getName(); 			
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
	
	public void addRow(ExecuteEntry entry) {
		list.add(entry);
		fireTableDataChanged();
	}
	
	public void removeRow(int rowIndex) {
		list.remove(rowIndex);
		fireTableDataChanged();
	}

}
