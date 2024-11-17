package com.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConnection()throws SQLException {
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/teca49?user=root&password=12345");
	}

}
