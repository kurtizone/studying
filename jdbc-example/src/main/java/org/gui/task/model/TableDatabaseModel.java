package org.gui.task.model;

import java.io.File;
import java.util.Properties;

import org.fire.jdbc.example.ActionCRUDImpl;
import org.fire.jdbc.example.ActionsCRUD;

public class TableDatabaseModel extends TableMemoryModel{
	
	private ActionsCRUD crud;
	
	public TableDatabaseModel(Properties prop) {
		crud = new ActionCRUDImpl(prop);
		list = crud.getAllExecutions();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ExecuteEntry oldEntry = list.get(rowIndex);
		ExecuteEntry entry = oldEntry.clone();
		switch (columnIndex) {		
		case 1: entry.setFile(new File((String) aValue)); 
				System.out.println(crud.updateAction(entry.getFile(), oldEntry));
				break;			
		case 2: entry.setDate((String) aValue); 
				System.out.println(crud.updateDate(entry.getDate(), oldEntry));
				break;			
		default: System.out.println("ERROR");
		}
		list.set(rowIndex, entry);
		fireTableCellUpdated(rowIndex,columnIndex);
	}

	@Override
	public void addRow(ExecuteEntry entry) {
		System.out.println(crud.save(entry));
		super.addRow(entry);
	}

	@Override
	public void removeRow(int rowIndex) {
		System.out.println(crud.remove(list.get(rowIndex)));
		super.removeRow(rowIndex);
	}

	@Override
	public void removeRow(ExecuteEntry entry) {
		System.out.println(crud.remove(entry));
		super.removeRow(entry);
	}
	
	
}
