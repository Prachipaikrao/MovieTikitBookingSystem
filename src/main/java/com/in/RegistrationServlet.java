package com.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
          try {
              Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");
               PreparedStatement ps = con.prepareStatement("insert into Movieregistration values(?,?,?)");
               ps.setString(1, name);
			   ps.setString(2, pass);
			   ps.setString(3, email);
		        int i = ps.executeUpdate();
		        out.print(i+"You are sucessfully registered");
		                
		           if(i>0)
		               {
		                RequestDispatcher dispatcher=request.getRequestDispatcher("Login.html");
		                dispatcher.forward(request,response);
		               }
                  con.close();

			        }

		   catch(Exception e)
               {
                  System.out.print(e);

			    }
 	
		out.close();
		
		
	}

}
