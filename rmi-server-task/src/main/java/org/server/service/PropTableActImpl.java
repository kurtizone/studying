package org.server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.server.main.RMIServerSide;

public class PropTableActImpl implements PropTableAct {
	
	public static volatile int COUNT_COMMITED_QUERY = 0;
	public static volatile int COUNT_QUERY = 0;
	
	private Connection conn;
	private ConnectPool pool;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	public PropTableActImpl() {
		pool = RMIServerSide.POOL;
		try {
			conn = pool.getPool().getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, String> getAllProperties() {
		if(PropTableActImpl.COUNT_QUERY == 0 && !RMIServerSide.LOAD_ON_START && COUNT_COMMITED_QUERY == 0) {
			ConnectPool.fillTable();
		}
		String selectSQL = "SELECT PROP_KEY, PROP_VALUE FROM PROPERTIES";
		Map<String, String> map = new HashMap<String, String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectSQL);
			while (rs.next()) {
				map.put(rs.getString("PROP_KEY"), rs.getString("PROP_VALUE"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean updateValue(String entryKey, String entryValue) {
		String updateSQL = "UPDATE PROPERTIES SET PROP_VALUE = ? WHERE PROP_KEY = ?";
		try {
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, entryValue);
			pstmt.setString(2, entryKey);
			COUNT_QUERY++;
			return pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean commitChanges() {
		try {
			conn.commit();
			COUNT_COMMITED_QUERY++;
			COUNT_QUERY = 0;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
	

	
	
}
