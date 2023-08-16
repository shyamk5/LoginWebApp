package com.sk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
	
	private final static String WELCOME_QUERY = "SELECT NAME,USERNAME,EMAIL,ADDRESS,EDUCATION FROM USER_LOGIN WHERE USERNAME=?";
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set response content type
		res.setContentType("text/html");
		
		//get request data
		String user = req.getParameter("username");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//create Connection Object and perform validation
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				PreparedStatement ps = con.prepareStatement(WELCOME_QUERY);
				) {
			
			//set Query param
			ps.setString(1, user);
			
			try(ResultSet rs = ps.executeQuery()) {
				
				if(rs != null) {
					if(rs.next()) {
						String name = rs.getString(1);
						String username = rs.getString(2);
						String email = rs.getString(3);
						String address = rs.getString(4);
						String education = rs.getString(5);
						
						pw.println("<h1 style='color:green; text-align:center;'>Welcome "+name+"</h1>");
						pw.println("<br><br><h3 style='text-align:center;'>Here are your details</h3>");
						pw.println("<br><p style='text-align:center;'>Name:: "+name+"</p>");
						pw.println("<br><p style='text-align:center;'>Username:: "+username+"</p>");
						pw.println("<br><p style='text-align:center;'>Email:: "+email+"</p>");
						pw.println("<br><p style='text-align:center;'>Address:: "+address+"</p>");
						pw.println("<br><p style='text-align:center;'>Education.:: "+education+"</p>");
						
					}
					else {
						pw.println("<h1 style='color:red; text-align:center;'>Record Not Found</h1>");
					}
					
				}//if
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
