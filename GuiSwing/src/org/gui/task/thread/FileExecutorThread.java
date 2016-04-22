package org.gui.task.thread;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gui.task.model.ExecuteEntry;
import org.gui.task.model.MyTableArrayModel;

public class FileExecutorThread extends Thread {

    private MyTableArrayModel model;
    private ExecuteEntry minEntry;
    private List<ExecuteEntry> list;
 	
    public FileExecutorThread(MyTableArrayModel model) {
    	this.model = model;
    	list = new ArrayList<>();
	}
	
	@Override
	public void run() {
		Date currentDate = null;
		while(true) {
			try {
				Thread.sleep(20000);
				currentDate = new Date();
				list = model.getList();
				if(list.size() > 0) {
					minEntry = list.get(0);
					for(ExecuteEntry entry : list) {
						if (minEntry.getDate().getTime() > entry.getDate().getTime()) {
							minEntry = entry;
						}
					}
					long dif = minEntry.getDate().getTime() - currentDate.getTime();
					if(dif > 0 && dif < 60000 ) {
						Thread.sleep(dif);
						openFileDesktop(minEntry.getFile());
						model.removeRow(minEntry);
					} else if(dif < 0) {
						openFileDesktop(minEntry.getFile());
						model.removeRow(minEntry);
					}
				}
			} catch (InterruptedException e) {
				System.err.println("fck");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	public static void main(String[] args) {
	
		
	}
	
	public void openFileDesktop(File file) throws IOException {
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
	}
	
	public MyTableArrayModel getModel() {
		return model;
	}

	public void setModel(MyTableArrayModel model) {
		this.model = model;
	}

	public ExecuteEntry getMinEntry() {
		return minEntry;
	}

	public void setMinEntry(ExecuteEntry minEntry) {
		this.minEntry = minEntry;
	}
	
	
	
	
	
}
