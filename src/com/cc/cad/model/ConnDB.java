package com.cc.cad.model;

import java.sql.*; 


public class ConnDB {
	
	private final String SQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	private final String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TEMP"; 
	private final String DB_USER = "java"; 
	private final String DB_PASSWD = "java";
	
	private Connection conn =null; 
	
	public Connection getConn(){
		try{
			Class.forName(SQL_DRIVER); 
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return conn; 
	}


}
