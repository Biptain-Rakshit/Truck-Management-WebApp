package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class regServlet
 */
@WebServlet("/reg")
public class regServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String URL = "jdbc:mysql://localhost:3306/userdb";
    public static String USERNAME = "root";
    public static String PASSWORD = "password";
    public static String  LoadDriver = "com.mysql.cj.jdbc.Driver";
    Connection connection;
    
    public regServlet() {
        super();
        
    }

    public void init(ServletConfig config) throws ServletException {
		 try {
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		 } catch (SQLException e) {
		
			e.printStackTrace();
		 }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		try {
			PreparedStatement ps = connection.prepareStatement("insert into userinfo values(?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uname);
			ps.setString(4, pass);
			
			ps.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			
		// after registration
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' href='afterreg.css'>"); // Or css/styles.css
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div class='card'>");
			pw.println("<h1>Registration Successful...</h1>");
			pw.println("<a class='btn' href='login.html'>Login</a><br><br>");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
