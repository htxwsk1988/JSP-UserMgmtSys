package com.cc.cad.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {
	
	// DB variables
	private Connection conn = null; 
	private Statement st= null; 
	private ResultSet rs= null; 
	
	// Paging variables
	private int pageSize=3; 
	private int rowCount=0; 
	private int pageCount=0; 
	
	//Validate user credential. 
	public boolean checkUser(String u, String p){
		
		boolean b= false; 
		
		try{
			
			conn = new ConnDB().getConn(); 
			
			st = conn.createStatement(); 
			
			rs = st.executeQuery("SELECT TOP 1 PASSWORD FROM USERS WHERE USERNAME='" + u + "'");
			
			if(rs.next()){
				if(rs.getString(1).equals(p)){
					b=true; 
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		
		return b; 
	}
	
	// Search results by paging. 
	public ArrayList<UserBean> getUsersByPage(int pageNow){
		
		ArrayList<UserBean> lstUser = new ArrayList<>(); 

		try{
			// Query rowCount. 
			conn = new ConnDB().getConn(); 
			
			st = conn.createStatement(); 
			
			// Search records for page
			rs = st.executeQuery("SELECT TOP "+ pageSize 
							+" * FROM USERS WHERE USERID NOT IN (SELECT TOP " + pageSize*(pageNow-1) 
							+" USERID FROM USERS)");
			
			while(rs.next()){
				UserBean user = new UserBean(); 
				
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPasswd(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setGroup(rs.getInt(5));
				
				lstUser.add(user); 
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close(); 
		}
		
		return lstUser; 
	}
	
	public boolean delUserById(String id){
		boolean b = false; 
		
		try{
			conn = new ConnDB().getConn(); 
			
			st= conn.createStatement();
			
			int rowDel = st.executeUpdate("DELETE FROM USERS WHERE USERID='"+id+"'"); 
			
			if(rowDel==1){
				b=true; 
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		
		return b; 
	}
	
	// Add user.
	public boolean addUser(String name, String pass, String email, String group){
		boolean b = false; 
		
		try{
			conn = new ConnDB().getConn(); 
			
			st= conn.createStatement();
			
			int rowAdd = st.executeUpdate("INSERT INTO USERS VALUES ('"+name +"', '"+pass+"', '"+email+"', '"+group+"')"); 
			
			if(rowAdd==1){
				b=true; 
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		
		return b; 
	}
	
	// Update user.
	public boolean updateUser(String id, String name, String pass, String email, String group){
		boolean b = false; 
		
		try{
			conn = new ConnDB().getConn(); 
			
			st= conn.createStatement();
			
			int rowUpdated = st.executeUpdate("UPDATE USERS SET USERNAME='"+name +"', PASSWORD='"+pass+"', EMAIL='"+email+"', [GROUP]='"+group+"' WHERE USERID='"+id+"'"); 
			
			if(rowUpdated==1){
				b=true; 
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		
		return b; 
	}
	
	// Return rowCount. 
	public int getPageCount(){
		
		try{
			// Query rowCount. 
			conn = new ConnDB().getConn(); 
			
			st = conn.createStatement(); 
			
			rs = st.executeQuery("SELECT COUNT(*) FROM USERS");
			
			if(rs.next()){
				 rowCount = rs.getInt(1); 
			}
			
			// Calculate pageCount. 
			if(rowCount%pageSize==0){
				pageCount = rowCount/pageSize; 
			}else{
				pageCount = rowCount/pageSize + 1; 
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close(); 
		}

		return pageCount; 
	}
	
	// Close DB resources. 
	private void close(){
		try{
			
			if(rs!=null){
				rs.close();
				rs=null; 
			}
			
			if(st!=null){
				st.close();
				st=null; 
			}
			
			if(conn!=null){
				conn.close();
				conn=null; 
			}
	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}

