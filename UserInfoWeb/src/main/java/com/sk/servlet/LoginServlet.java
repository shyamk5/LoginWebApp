package com.sk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set response content type
		res.setContentType("text/html");
		
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		
		if(LoginDAO.validate(username, pwd)==0) {
			pw.println("<h1 style='color:red; text-align:center;'>Invalid Username/Password</h1>");
		}
		else {
			pw.println("<h1 style='color:green; text-align:center;'>Login Successful</h1>");
			RequestDispatcher rd = req.getRequestDispatcher("welcomeservlet");
			rd.forward(req, res);
		}
		
		
		
		pw.close();
		
	}//doGet
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
