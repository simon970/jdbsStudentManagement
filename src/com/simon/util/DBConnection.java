package com.simon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	
	static {
		try {
           Class.forName("com.mysql.cj.jdbc.Driver");			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/students";
		String user = "root";
		String password = "Bsimon@2001";
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void close(Connection connect, Statement stmt) throws SQLException {
		stmt.close();
		connect.close();
	}
}
