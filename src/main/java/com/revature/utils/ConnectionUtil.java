package com.revature.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Connection conn = null;

	public static Connection getConnection() {
<<<<<<< HEAD
		
		try {
			Class.forName("org.postgresql.driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

=======
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
>>>>>>> f2ebc0c45b220120d13081f4c7dd88e7a842f5ae
		try {
			Properties props = new Properties();

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("connection.properties"));

			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			conn = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
