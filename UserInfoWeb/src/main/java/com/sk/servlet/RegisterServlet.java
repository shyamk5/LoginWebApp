package com.sk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set response content type
		res.setContentType("text/html");
		
		//get request data
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String education = req.getParameter("education");
		String password = req.getParameter("password");
		
		//register the user
		if(RegisterDAO.register(name, username, password, email, address, education)==0) {
			pw.println("<h1 style='color:red; text-align:center;'>Something went wrong! Please Try Again</h1>");
			pw.println("<br><a href='index.html'>Home</a>");
		} else {
			pw.println("<h1 style='color:green; text-align:center;'>User Registered</h1>");
			pw.println("<br><a href='login.html'>Login Now</a>");
			pw.println("<br><a href='index.html'>Home</a>");
		}
		
		pw.close();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
