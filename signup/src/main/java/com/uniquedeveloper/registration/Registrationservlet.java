package com.uniquedeveloper.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registrationservlet
 */
@WebServlet("/register")
public class Registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//This is to post the method is working or not 
		//PrintWriter out = response.getWriter();
		//out.print("Working");
		
		//this is grt method to get the user data
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String mobileNo = request.getParameter("contact");
		
	    
		
//		PrintWriter out = response.getWriter();
//		out.print(username);
//		out.print(email);
//		out.print(password);
//		out.print(mobileNo);
		
		RequestDispatcher dispatcher = null;
		Connection con =null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio?useSSL=false","root","root");
			PreparedStatement pst = con.prepareStatement("insert into users(username,password,email,mobileNo) values (?,?,?,?)");
			pst.setString(1, username);
		    pst.setString(2, password);
		    pst.setString(3, email);
		    pst.setString(4, mobileNo);
			
			int rowCount =pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			if(rowCount >0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
