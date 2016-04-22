package org.server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import org.firebirdsql.pool.FBWrappingDataSource;
import org.server.main.RMIServerSide;
import org.server.prop.ConfigProp;

public final class ConnectPool {
	
	private static volatile FBWrappingDataSource pool;
	
	public ConnectPool() {
		pool = new FBWrappingDataSource();
		pool.setMaxPoolSize(15);
		pool.setMinPoolSize(3);
		pool.setMaxStatements(100);
		pool.setMaxIdleTime(30 * 60 * 60);
		pool.setDefaultResultSetHoldable(false);
		pool.setKeepStatements(false);
		pool.setDefaultTransactionIsolation(1);
		pool.setBuffersNumber(0);
		pool.setDatabase("localhost/3052:D:\\databases\\properties.fdb");
		pool.setUserName("SYSDBA");
		pool.setPassword("masterkey");
		createTable();
		if(RMIServerSide.LOAD_ON_START) {
			fillTable();
		}
	}
	
	private static void createTable(){
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = pool.getConnection();
			stmt = connection.createStatement();
			stmt.execute("RECREATE TABLE PROPERTIES("
					+ "PROP_KEY varchar(2000) not null, "
					+ "PROP_VALUE varchar(2000))");

//			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void fillTable() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Properties prop = ConfigProp.prop;
		try {
			connection = pool.getConnection();
			String insertSQL = "INSERT INTO PROPERTIES (PROP_KEY, PROP_VALUE) VALUES (?,?)" ;
			pstmt = connection.prepareStatement(insertSQL);
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				pstmt.setString(1, (String) entry.getKey());
				pstmt.setString(2, (String) entry.getValue());
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static FBWrappingDataSource getPool() {
		return pool;
	}

	public static void setPool(FBWrappingDataSource pool) {
		ConnectPool.pool = pool;
	}

	
	
}
