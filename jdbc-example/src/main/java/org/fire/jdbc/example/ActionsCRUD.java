package org.fire.jdbc.example;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.gui.task.model.ExecuteEntry;

public interface ActionsCRUD {
	
	List<ExecuteEntry> getAllExecutions();
	boolean save(ExecuteEntry entry);
	boolean update(ExecuteEntry newEntry, ExecuteEntry oldEntry );
	boolean updateDate(Date date, ExecuteEntry oldEntry );
	boolean updateAction(File file, ExecuteEntry oldEntry );
	boolean remove(ExecuteEntry entry);
	
}
