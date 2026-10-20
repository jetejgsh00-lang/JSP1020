package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptSelect")
public class DeptSelectServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);//get, post 알아서 구별해줌
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8"); //UTF-8 파일만들때 기본언어
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cloud1";
		String userName = "root";
		String password = "1111";
		
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String query = "SELECT * FROM dept";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userName, password);
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					
					out.print(deptno + "\t" + dname + "\t" + loc + "<br>");
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					
					if (rs!=null)rs.close();
					if (pstmt!=null)pstmt.close();
					if (conn!=null)conn.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		out.print("<body><html>");
	}
}


