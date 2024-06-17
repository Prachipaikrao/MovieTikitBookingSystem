package com.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookNow
 */
@WebServlet("/BookNow")
public class BookNow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String Movie= request.getParameter("name");

        String  seat = request.getParameter("n");

        
        PrintWriter out = response.getWriter();

        boolean isValidUser = validateUser(Movie, seat);

        if(isValidUser) {

            out.println("<h2>Login Successful!</h2>");
            response.sendRedirect("MovieSelection.html?sucess=true");
            

        } else {
        	out.print("invalid");

   

        }
	}

    private boolean validateUser(String Movie,String seat) {

        boolean isValidUser = false;

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "pass");

            String sql = ("SELECT * FROM MovieSeat WHERE MovieSeat = ? AND seat= ?");

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, Movie);

            stmt.setString(2, seat);

            rs = stmt.executeQuery();
            if(rs.next()) {

                isValidUser = true;

            }
            
            

        } catch (SQLException e) {

            e.printStackTrace();

        } 

        finally {

            try {

                if(rs != null) rs.close();

                if(stmt != null) stmt.close();

                if(conn != null) conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }
       
        }
        return isValidUser;
  
	}

}
