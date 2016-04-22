package org.gui.task.model;

import java.io.File;
import java.util.Date;

public class ExecuteEntry {
	
	private static Integer inc = 1;
	
	private Integer id;
	private File file;
	private Date date;
	
	public ExecuteEntry(File file, Date date) {
		this.id = inc++;
		this.file = file;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ExecuteEntry [id=" + id + ", file=" + file + ", date=" + date + "]";
	}
	
	
	
	
}
