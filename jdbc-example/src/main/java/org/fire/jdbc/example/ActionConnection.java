package org.fire.jdbc.example;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.gui.task.model.ExecuteEntry;

public class ActionConnection {
	
	private Properties prop;

	public Connection getConnection() throws Exception {
		prop = new Properties();
		Class.forName("org.firebirdsql.jdbc.FBDriver");
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);

		String host = prop.getProperty("db.host");
		String login = prop.getProperty("db.login");
		String password = prop.getProperty("db.password");

		Connection conn = DriverManager.getConnection(host, login, password);
		return conn;
	}

	public static void main(String[] args) throws Exception {
		ActionConnection action = new ActionConnection();
		action.getConnection();
		ActionsCRUD crud = new ActionCRUDImpl(action.prop);
		
		ActionConnection.printExecution(crud);
		File file = new File("example.txt");
		file.createNewFile();
		ExecuteEntry entryOther = new ExecuteEntry(file, new Date());

		System.out.println("---save  " + crud.save(entryOther));
		Thread.sleep(5000);	
		ActionConnection.printExecution(crud);
		ExecuteEntry some = new ExecuteEntry(new File("example2.txt"), new Date());
		
		System.out.println("---update  " + crud.update(some, entryOther));
		Thread.sleep(5000);	
		
		ActionConnection.printExecution(crud);
		
		File newFile = new File("example3.txt");
		Thread.sleep(5000);	
		
		System.out.println("---updateAction  " + crud.updateAction(newFile, some));
		some.setFile(newFile);
		Thread.sleep(5000);	
		
		ActionConnection.printExecution(crud);
		
		System.out.println("---updateDate  " + crud.updateDate(new Date(), some));
		
		ActionConnection.printExecution(crud);
		
	}
	
	public static void printExecution(ActionsCRUD crud) {
		List<ExecuteEntry> list = crud.getAllExecutions();
		for (ExecuteEntry entry : list) {
			System.out.println(entry);
		}
	}
}
