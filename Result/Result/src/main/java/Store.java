

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Store
 */
@WebServlet("/Store")
public class Store extends HttpServlet {
	
	Connection con = null;
	PreparedStatement ps = null;
	String sql = "insert into register values(?,?,?)";
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
	public void service( HttpServletRequest req, HttpServletResponse res) {
		try {
			String id = req.getParameter("id");
			int cid = Integer.parseInt(id);
			String name = req.getParameter("name");
			String department = req.getParameter("dep");
			ps.setInt(1, cid);
			ps.setString(2, name);
			ps.setString(3, department);
			ps.executeUpdate();
			res.sendRedirect("http://localhost:9090/Result/Login.html");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void destory() {
		try {
			con.close();
			ps.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
