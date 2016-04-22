package org.fire.jdbc.example;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.gui.task.model.ExecuteEntry;

public class ActionCRUDImpl implements ActionsCRUD{

	private static Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	public ActionCRUDImpl(Properties properties) {
		conn = getConnection(properties);
	}
	
	public List<ExecuteEntry> getAllExecutions() {
		List<ExecuteEntry> list = new ArrayList<ExecuteEntry>();
		try {
			stmt = conn.createStatement();
			String selectAll = "SELECT ACTION, VAR_TIME FROM EXECUTION";
			ResultSet rs = stmt.executeQuery(selectAll);
			while (rs.next()) {
				list.add(new ExecuteEntry(new File(rs.getString("ACTION")),
						new Date(rs.getTimestamp("VAR_TIME").getTime())));
			}
			
		} catch (SQLException e) {
			System.err.println("failed to get All executions");
		}
		return list;
	}

	public boolean save(ExecuteEntry entry) {
		try {
			String insertSQL = "INSERT INTO EXECUTION (ACTION, VAR_TIME) VALUES "
					+ "(?,?)" ;
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, entry.getFile().getAbsolutePath());
			pstmt.setTimestamp(2, new Timestamp(entry.getDate().getTime()));
			int i = pstmt.executeUpdate();

			if(i == 1) {
				return true;
				
			} else return false;
		} catch (SQLException e) {
			System.err.println("failed to Insert Field ");

			return false;
		}
	}

	public boolean update(ExecuteEntry newEntry, ExecuteEntry oldEntry ) {
		try {
			String updateSQL = "UPDATE EXECUTION SET ACTION = ?, VAR_TIME = ? "
					+ "WHERE ACTION = ? AND VAR_TIME = ?" ;
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, newEntry.getFile().getAbsolutePath());
			pstmt.setTimestamp(2, new Timestamp(newEntry.getDate().getTime()));
			pstmt.setString(3, oldEntry.getFile().getAbsolutePath());
			pstmt.setTimestamp(4, new Timestamp(oldEntry.getDate().getTime()));
			int i = pstmt.executeUpdate();
			if(i == 1) {
				return true;
			} else return false;
		} catch (SQLException e) {
			System.err.println("failed to update field ");

			return false;
		}
	}

	public boolean updateDate(Date date, ExecuteEntry oldEntry) {
		try {
			String updateSQL = "UPDATE EXECUTION SET VAR_TIME = ? "
					+ "WHERE ACTION = ? AND VAR_TIME = ?" ;
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setTimestamp(1, new Timestamp(date.getTime()));
			pstmt.setString(2, oldEntry.getFile().getAbsolutePath());
			pstmt.setTimestamp(3, new Timestamp(oldEntry.getDate().getTime()));
			int i = pstmt.executeUpdate();
			if(i == 1) {
				return true;
			} else return false;
		} catch (SQLException e) {
			System.err.println("failed to update field ");

			return false;
		}
	}

	public boolean updateAction(File file, ExecuteEntry oldEntry) {
		try {
			String updateSQL = "UPDATE EXECUTION SET ACTION = ? "
					+ "WHERE ACTION = ? AND VAR_TIME = ?" ;
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, file.getAbsolutePath());
			pstmt.setString(2, oldEntry.getFile().getAbsolutePath());
			pstmt.setTimestamp(3, new Timestamp(oldEntry.getDate().getTime()));
			int i = pstmt.executeUpdate();
			if(i == 1) {
				return true;
			} else return false;
		} catch (SQLException e) {
			System.err.println("failed to update field ");

			return false;
		}
	}

	public boolean remove(ExecuteEntry entry) {
		try {
			String updateSQL = "DELETE FROM EXECUTION "
					+ "WHERE ACTION = ? AND VAR_TIME = ?" ;
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, entry.getFile().getAbsolutePath());
			pstmt.setTimestamp(2, new Timestamp(entry.getDate().getTime()));
			int i = pstmt.executeUpdate();
			if(i == 1) {
				return true;
			} else return false;
		} catch (SQLException e) {
			System.err.println("failed to update field ");

			return false;
		}
	}
	
	public static Connection getConnection(Properties prop) {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String host = prop.getProperty("db.host");
		String login = prop.getProperty("db.login");
		String password = prop.getProperty("db.password");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					prop.getProperty("db.host"), 
					prop.getProperty("db.login"), 
					prop.getProperty("db.password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
}
