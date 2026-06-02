package com.simon.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBConnection {
	
	private static Properties properties = new Properties();
	
	static {
		try(InputStream input = DBConnection.class
				.getClassLoader()
				.getResourceAsStream("db.properties")) {
			
			properties.load(input);
			
           Class.forName("com.mysql.cj.jdbc.Driver");			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void close(Connection connect, Statement stmt) throws SQLException {
		stmt.close();
		connect.close();
	}
}
