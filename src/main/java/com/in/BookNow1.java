package com.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookNow1
 */
@WebServlet("/BookNow1")
public class BookNow1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
				String movie=request.getParameter("name");
				String seat=request.getParameter("n");
				 try {

					 Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");

		                PreparedStatement ps = con.prepareStatement("insert into MovieSeat values(?,?)");
			            ps.setString(1, movie);
			            ps.setString(2, seat);
			  
			            int i = ps.executeUpdate();
		                out.print(i+"You are sucessfully registered");
		                
		                out.print("<html><head></head><body>");
		        		out.print("<form action='BookNow'><input type=\"text\"  name=\"n\">");
		        		out.print("<input type='submit' value='delete'>");
		        		out.print("</form>");
			               con.close();
			        }

				 catch(Exception e) {

			            System.out.print(e);

			        }
					
		out.close();
	}

}
