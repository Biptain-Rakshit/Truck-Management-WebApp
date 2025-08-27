package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sun.jvm.hotspot.code.Location.Where;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String URL = "jdbc:mysql://localhost:3306/userdb";
    public static String USERNAME = "root";
    public static String PASSWORD = "password";
    public static String  LoadDriver = "com.mysql.cj.jdbc.Driver";
    Connection connection;
    
    public loginServlet() {
       
    }

	
	public void init(ServletConfig config) throws ServletException {
		 try {
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String uname = request.getParameter("uname");
	 String pass = request.getParameter("pass");
	 
	 try {
		 PreparedStatement statement = connection.prepareStatement("Select *from userinfo Where uname=? AND pass=?");
		 statement.setString(1, uname);
		 statement.setString(2, pass);
		 
		 ResultSet executeQuery = statement.executeQuery();
		 PrintWriter pw = response.getWriter();
		 
		 // style of after login page ...
		 pw.println("<html>");
		 pw.println("<head>");
		 pw.println("<link rel='stylesheet' href='afterlogin.css'>");
		 pw.println("</head>");
		 pw.println("<body>");
		 pw.println("<div class='card'>");

		 if (executeQuery.next()) {
		     pw.println("<h1>Login Successfully ....</h1><br><br>");
		     pw.println("<h2>Welcome : " + uname + "</h2>");
		 } else {
		     pw.println("<h1 class='error'>User Not Found</h1>");
		 }

		 pw.println("</div>");
		 pw.println("</body>");
		 pw.println("</html>");

		 
		 
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	  
	}

}
