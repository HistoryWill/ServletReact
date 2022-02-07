package com.cognixia.jump.react.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static Connection connection = null;

	private static String URL = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;


	private static void makeConnection() {

		Properties props = new Properties();

		try(FileInputStream fileIn = new FileInputStream("/Users/macha/java-workspace/reactmaven/resources/config.properties");){
			
		
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			props.load(fileIn);

			URL = props.getProperty("url");
			USERNAME = props.getProperty("username");
			PASSWORD = props.getProperty("password");

			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection made to: " + URL
					+ "\nAs user: " + USERNAME);

		}catch(SQLException e) {
			System.out.println("Could not make connection to DB. Please see below message\n"
					+ e.getMessage());
		}catch(IOException e1) {
			System.out.println("could not load .properties file.\n" + e1.getMessage());
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		if(connection == null) {
			makeConnection();
		}

		return connection;
	}
}
