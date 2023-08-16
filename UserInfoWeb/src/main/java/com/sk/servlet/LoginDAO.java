package com.sk.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	private final static String USER_QUERY = "SELECT COUNT(*) FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?";
	
	public static int validate(String username, String password) {
		
		int count = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//create Connection Object and perform validation
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				PreparedStatement ps = con.prepareStatement(USER_QUERY);
				) {
			
			//set Query param
			ps.setString(1, username);
			ps.setString(2, password);
			
			//execute the query
			try(ResultSet rs = ps.executeQuery()) {
				
				if(rs != null) {
					rs.next();
					
					count = rs.getInt(1);
				}//if
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;
		
	}//validate
}//class
