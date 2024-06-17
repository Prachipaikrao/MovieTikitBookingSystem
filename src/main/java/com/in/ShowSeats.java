package com.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowSeats
 */
@WebServlet("/ShowSeats")
public class ShowSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	



		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		         
		         
		       try{  
		        Class.forName("oracle.jdbc.driver.OracleDriver");  
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");
		           PreparedStatement ps=con.prepareStatement("select * from MovieSeat");  
		           ResultSet rs=ps.executeQuery();  
		             
		            out.println("MovieName\t\t\tSeatNo \t\t\t");
		           
		    while(rs.next())
		    {
		    out.println("<br>"+rs.getString(1)+"   \t"+rs.getString(2)+ "\n");
		    }
		    con.close();
		   
		           
		             
		       }catch(Exception e)
		       {
		        out.print(e);}  
	
		      out.print("<input type='submit' value='submit'>");
		
		       

	}

}
