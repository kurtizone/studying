package org.client.model;

import java.rmi.RemoteException;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.server.controller.PropertiesDataController;

public class PropertiesTableModel extends AbstractTableModel{
	
	private Map<String, String> map;
	private PropertiesDataController controller;
	private String[] columnNames;
	
	public PropertiesTableModel(PropertiesDataController controller) {
		this.controller = controller;
		columnNames = new String[2];
		columnNames[0] = "PROP_KEY";
		columnNames[1] = "PROP_VALUE";
		updateModel();
	}
	
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 1) {
		Object[] entries = map.entrySet().toArray();
		Map.Entry entry = (Map.Entry) entries[rowIndex];
		map.put((String) entry.getKey(), (String) aValue);
		try {
			controller.updateValue((String)entry.getKey(), (String)entry.getValue());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} 
		fireTableCellUpdated(rowIndex,columnIndex);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0 ) {
			return false;
		} else return true;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public int getRowCount() {
		return map.size();
	}

	public int getColumnCount() {
		return 2;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] entries = map.entrySet().toArray();
		Map.Entry entry = (Map.Entry) entries[rowIndex];
		if(columnIndex == 0) {
			return entry.getKey();
		} else if(columnIndex == 1) {
			return entry.getValue();
		} else {
			throw new IndexOutOfBoundsException("mappppy");
		}
	}

	public void updateModel() {
		try {
			map = controller.getAllProperties();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

	public void commit() {
		try {
			controller.commitChanges();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
