package org.gui.task.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyTableGenericModel<T> extends AbstractTableModel {
	
	private List<T> data;
	
	private static List<String> colNames =  new ArrayList<String>();
	
	public MyTableGenericModel(List<T> data) {
		this.data = data;
		setColumnNames();
	}
	
	private void setColumnNames() {
		for(Field field : data.getClass().getDeclaredFields()) {
			colNames.add(field.getName());
		}
		
	}


	public int getColumnCount() {
		return colNames.size();
	}


	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		T entry = data.get(rowIndex);
		String colName = colNames.get(columnIndex).toLowerCase();
		String methodName = "";
		List<Method> methods = Arrays.asList(entry.getClass().getMethods());
		for(Method method : methods) {
			methodName = method.getName().toLowerCase();
			if(methodName.contains(colName) &&  methodName.contains("get")) {
				try {
					return method.invoke(entry, null);
				} catch (Exception e) {
					System.err.println("Reflection");
					e.printStackTrace();
				}
			}
		}
			
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames.get(column);
	}
	

}
