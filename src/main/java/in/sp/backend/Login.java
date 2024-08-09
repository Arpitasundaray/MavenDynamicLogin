package in.sp.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/LoginForm")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
    	 resp.setContentType("text/html");
    	PrintWriter out = resp.getWriter();
        String email = req.getParameter("email1");
        String password = req.getParameter("pass1");
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/form_db");
        	
        	PreparedStatement ps = con.prepareStatement("select * from  registrationform where email=? and pass=?");
        	ps.setString(1, email);
        	ps.setString(2, password);
        	
        	ResultSet rs = ps.executeQuery();
        	if(rs.next())
        	{
        		RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
         		rd.include(req, resp);	
        	}else
        	{
               
                out.print("<h3 style='color:red'> Email id and password didn't matched</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        		rd.include(req, resp);
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	
             out.print("<h3 style='color:red'> "+e.getMessage()+"</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
     		rd.include(req, resp);
        }

    

    }
}
