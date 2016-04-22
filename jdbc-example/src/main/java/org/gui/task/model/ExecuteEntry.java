package org.gui.task.model;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteEntry implements Cloneable{
	
	private static Integer inc = 1;
	
	private Integer id;
	private File file;
	private Date date;
	
	public ExecuteEntry(File file, Date date) {
		this.id = inc++;
		this.file = file;
		this.date = date;
	}
	
	public ExecuteEntry(File file, String date) {
		this.id = inc++;
		this.file = file;
		setDate(date);
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
	
	public void setDate(String strDate) {
		Date date = new Date();
		Date diff;
		try {
			diff = new SimpleDateFormat("HH:mm").parse(strDate);
			date.setHours(diff.getHours());
			date.setMinutes(diff.getMinutes());
			date.setSeconds(0);
		} catch (ParseException e) {
			System.err.println("Error while parse");
			e.printStackTrace();
		}
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExecuteEntry other = (ExecuteEntry) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExecuteEntry [id=" + id + ", file=" + file + ", date=" + date + "]";
	}

	@Override
	public ExecuteEntry clone() {
		try {
			return (ExecuteEntry) super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Cloning error");
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	
	
}
