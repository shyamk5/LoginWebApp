package com.sk.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDAO {
	
private final static String REGISTER_QUERY = "INSERT INTO USER_LOGIN VALUES(?,?,?,?,?,?)";
	
	public static int register(String name, String username, String password, String email, String address, String education) {
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//create Connection Object and perform validation
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				PreparedStatement ps = con.prepareStatement(REGISTER_QUERY);
				) {
			
			//set Query param
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.setString(5, address);
			ps.setString(6, education);
			
			result = ps.executeUpdate();
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}//register
}//class
