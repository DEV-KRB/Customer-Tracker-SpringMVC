package com.eculant.crud.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDb")
public class TestDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//setup connection variables
		String user= "root";
		String pass= "root";
		String jdbcUrl= "jdbc:mysql://localhost:3306/customer_directory?useSSL=false";
		String driver= "com.mysql.cj.jdbc.Driver";
		
		//get connection to database
		try {
			PrintWriter out= response.getWriter();
			out.println("Connecting to database" +jdbcUrl);
			Class.forName(driver);
			Connection myConn= DriverManager.getConnection(jdbcUrl, user, pass);
			
			out.println("Success!!");
			myConn.close();
			
		}catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
	
	}
}
