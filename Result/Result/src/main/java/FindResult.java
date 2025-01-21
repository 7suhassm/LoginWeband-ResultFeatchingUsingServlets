



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
 * Servlet implementation class FindResult
 */
@WebServlet("/FindResult")
public class FindResult extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	PrintWriter pw = null;

	String sql = "select * from student where id=?";
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
		int cid = Integer.parseInt(id);
		ps.setInt(1, cid);
		rs = ps.executeQuery();
		if (rs.next() == true) {

		    pw.println("<!DOCTYPE html>");
		    pw.println("<html>");
		    pw.println("<head>");
		    pw.println("<title>Student Result</title>");
		    pw.println("<style>");
		    pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
		    pw.println(".container { background-color: #ffffff; padding: 20px 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; }");
		    pw.println("h1 { color: #333333; margin-bottom: 20px; }");
		    pw.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
		    pw.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
		    pw.println("th { background-color: #007BFF; color: white; }");
		    pw.println("td { background-color: #f9f9f9; }");
		    pw.println("a { text-decoration: none; color: #007BFF; font-size: 14px; }");
		    pw.println("a:hover { text-decoration: underline; }");
		    pw.println("</style>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println("<div class='container'>");
		    pw.println("<h1>STUDENT RESULT</h1>");
		    pw.println("<table>");
		    pw.println("<tr><th>ID</th><th>Name</th><th>SUB1</th><th>SUB2</th><th>SUB3</th><th>TOTAL</th></tr>");
		    pw.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getInt(3) + "</td><td>" + rs.getInt(4) + "</td><td>" + rs.getInt(5) + "</td><td>" + rs.getInt(6) + "</td></tr>");
		    pw.println("</table>");
		    pw.println("<a href='http://localhost:9090/Result/Register.html'>Register Here</a>");
		    pw.println("</div>");
		    pw.println("</body>");
		    pw.println("</html>");

		} else {
		    res.setContentType("text/html");
		    pw.println("<!DOCTYPE html>");
		    pw.println("<html>");
		    pw.println("<head>");
		    pw.println("<title>Invalid ID</title>");
		    pw.println("<style>");
		    pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
		    pw.println(".container { text-align: center; background-color: #ffffff; padding: 20px 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
		    pw.println("p { font-size: 16px; color: red; margin-bottom: 20px; }");
		    pw.println("a { text-decoration: none; color: #007BFF; font-size: 14px; }");
		    pw.println("a:hover { text-decoration: underline; }");
		    pw.println("</style>");
		    pw.println("</head>");
		    pw.println("<body>");
		    pw.println("<div class='container'>");
		    pw.println("<p>INVALID ID, PLEASE TRY AGAIN</p>");
		    pw.println("<a href='http://localhost:9090/GetResult'>Click Here</a>");
		    pw.println("</div>");
		    pw.println("</body>");
		    pw.println("</html>");
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


