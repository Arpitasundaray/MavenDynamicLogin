package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");

        try {
            // Loading drivers for mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/form_db","root","123456789");

            // Ensure the column names match those in your table
            PreparedStatement ps = con.prepareStatement("INSERT INTO registrationform (name, email, pass, gender, city) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, gender);
            ps.setString(5, city);
            int i = ps.executeUpdate();

            if (i > 0) {
                req.setAttribute("message", "You are successfully registered");
                req.setAttribute("messageType", "success");
            } else {
                req.setAttribute("message", "You are not registered");
                req.setAttribute("messageType", "error");
            }

            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("message", "Exception Occurred: " + e.getMessage());
            req.setAttribute("messageType", "error");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, resp);
        } finally {
            out.close();
        }
    }
}
