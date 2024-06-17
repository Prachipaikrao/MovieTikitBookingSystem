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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String email= request.getParameter("name");

        String pass1 = request.getParameter("password");

        
        PrintWriter out = response.getWriter();

        boolean isValidUser = validateUser(email, pass1);

        if(isValidUser) {

            out.println("<h2>Login Successful!</h2>");
            response.sendRedirect("MovieSelection.html?sucess=true");

        } else {

            out.println("<h2>Invalid username or password. Please try again.</h2>");

        }

    }


    private boolean validateUser(String email,String pass1) {

        boolean isValidUser = false;

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        

        try {

            // Establish connection to the database

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "pass");

            String sql = ("SELECT * FROM Movieregistration WHERE UserName = ? AND password= ?");

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            stmt.setString(2, pass1);
            rs = stmt.executeQuery();
            if(rs.next())
            {

                isValidUser = true;

            }
        }
        catch (SQLException e) 
        {

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
