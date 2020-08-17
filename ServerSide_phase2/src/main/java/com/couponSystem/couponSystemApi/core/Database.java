package com.couponSystem.couponSystemApi.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	
	
	final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	final static String DATABASE = "coupon_system";
	final static String URL = "jdbc:mysql://localhost:3306/coupon_system";
	final static String URL_PARAMETERS ="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=IST";
	final static String USERNAME = "root";
	final static String PASSWORD = "1234";
	// "jdbc:mysql://localhost:3306/coupon_system?autoReconnect=true&useSSL=false&createDatabaseIfNotExist=true";
	//useTimezone=false
	//URL_PARAMETERS ="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT+3";
	
	static {
		try {
			Class.forName(DRIVER_NAME);
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();			
		}
		 System.out.println("Driver loaded: " + DRIVER_NAME);
		
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL+URL_PARAMETERS,USERNAME,PASSWORD );
			
	}
	
	
	public static void executeSql(Statement statement,String sql) throws SQLException{
		try {
			statement.execute(sql);
			
		} catch (SQLException e) {
			
		}
		
		
		
	}
	
	public static void initialize() {
		try {
			
			try (Connection con = DriverManager.getConnection(URL+ URL_PARAMETERS, USERNAME, PASSWORD);
					Statement statement = con.createStatement();) {
						System.out.println("connected to: "+ URL);
						System.out.println("===========");
						executeSql(statement, "CREATE DATABASE IF NOT EXISTS coupon_system" );
				
						
						
//						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Company ("
//								+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
//								+ "comp_name VARCHAR(18) NOT NULL,"
//								+ "password VARCHAR(16) NOT NULL,"
//								+ "email VARCHAR(50) NOT NULL,"
//								+ "type VARCHAR(7) "
//								+")");
//								
//						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Customer ("
//								+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
//								+ "cust_name VARCHAR(18) NOT NULL,"
//								+ "password VARCHAR(16) NOT NULL,"
//								+ "email VARCHAR(50) NOT NULL,"
//								+ "type VARCHAR(8) "
//								+")");
						
						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Users ("
								+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
								+ "user_name VARCHAR(18) NOT NULL,"
								+ "password VARCHAR(16) NOT NULL,"
								+ "email VARCHAR(50) NOT NULL,"
								+ "user_type VARCHAR(8) "
								+")");
						
						
						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Coupon ("
								+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
								+ "comp_id BIGINT NOT NULL,"
								+ "title VARCHAR(50) NOT NULL,"
								+ "start_date DATE NOT NULL,"
								+ "end_date DATE NOT NULL,"
								+ "amount INTEGER NOT NULL,"
								+ "type VARCHAR(50) NOT NULL,"
								+ "message VARCHAR(255) NOT NULL,"
								+ "price FLOAT NOT NULL,"
								+ "image VARCHAR(255) NOT NULL"
								+ ")");
						
	
						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Customer_Coupon ("
								+ "cust_id BIGINT,"
								+ "coupon_id BIGINT,"
								+ "PRIMARY KEY(CUST_ID, COUPON_ID)"
								+")");
						
//						executeSql(statement, "CREATE TABLE IF NOT EXISTS coupon_system.Permissions ("
//								+ "client_type VARCHAR(50),"
//								+ "perm_type VARCHAR(50),"
//								+ "password VARCHAR(16) NOT NULL,"
//								+ "PRIMARY KEY(client_type, perm_type)"
//								+")");
						
						
				
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}
