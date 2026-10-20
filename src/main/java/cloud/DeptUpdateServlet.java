package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptUpdate")
public class DeptUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		
		String dname = req.getParameter("dname");//폼 파일 에 설정한 순서대로 정렬
		String loc = req.getParameter("loc");
		String deptno = req.getParameter("deptno");
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		
			String driver = "com.mysql.cj.jdbc.Driver";
			String url ="jdbc:mysql://localhost:3306/cloud1";
			String username ="root";
			String password ="1111";
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			String query = "UPDATE dept SET dname = ?, loc = ? WHERE deptno = ?";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, dname);
				pstmt.setString(2, loc);
				pstmt.setInt(3, Integer.parseInt(deptno));
				
				int result = pstmt.executeUpdate();
				if (result == 1) {
					out.print("update()실행성공");
				} else {
					out.print("update()실행실패");

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
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
