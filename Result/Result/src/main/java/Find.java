

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
 * Servlet implementation class Find
 */
@WebServlet("/Find")
public class Find extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	PrintWriter pw = null;

	String sql = "select * from register where id=?";
	String path = "com.mysql.cj.jdbc.Driver";
	String dpath = "jdbc:mysql://localhost:3306/university?user=root&password=7suhas";
	public void init() {
		try {
			Class.forName(path);
			con = DriverManager.getConnection(dpath);
			ps = con.prepareStatement(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			pw = res.getWriter();

			String id = req.getParameter("id");
			String name = req.getParameter("name");
			int cid = Integer.parseInt(id);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if (rs.next() == true) {

				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<head><title>WELCOME</title><head>");
				pw.println("<body>");
				pw.println("<center><h1>LOGIN SUCCESSFUL</h1>");
				pw.println("ID: " + rs.getInt(1) + "NAME: " + rs.getString(2) + "DEPARTMENT: " + rs.getString(3));
				pw.println("<a href='http://localhost:9090/Result/Register.html'>Register Here</a></center>");
				pw.println("</body></html>");
			} else {
				res.setContentType("text/html");
				pw.println("<!DOCTYPE>");
				pw.println("<html>");
				pw.println("<head><title>LOGIN FAILED PLEASE TRY AGAIN</title><head>");
				pw.println("<body><p>INVALID ID PLEASE TRY AGAIN</p>");
				pw.println("<a href='http://localhost:9090/Result/Find/Login.html'>click here</a>");
				pw.println("</body></html>");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			con.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
