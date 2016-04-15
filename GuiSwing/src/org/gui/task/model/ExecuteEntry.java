package org.gui.task.model;

import java.io.File;
import java.util.Date;

public class ExecuteEntry {
	
	private static Integer inc = 1;
	
	private Integer id;
	private File file;
	private Date date;
	private String[] rowData;
	
	public ExecuteEntry(File file, Date date) {
		this.id = inc++;
		this.file = file;
		this.date = date;
		rowData = new String[3];
		rowData[0] = id.toString();
		rowData[1] = file.getAbsolutePath();
		rowData[2] = date.toString();
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
	
	public String[] getRowData() {
		return rowData;
	}
	
	
	
}
