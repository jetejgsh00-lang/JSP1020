package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import cloud.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/empInsert")
public class EmpInsertServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		//getParameter 는 사용자가 요청으로 보낸값을 꺼냄(추가할값)
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String job = req.getParameter("job");
		String mgr = req.getParameter("mgr");
		String hiredate = req.getParameter("hiredate");
		String sal = req.getParameter("sal");
		String comm = req.getParameter("comm");
		String deptno = req.getParameter("deptno");
		
		
		
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print("<html><body>");
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String query = "INSERT INTO emp (empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES(?,?,?,?,?,?,?,?)<br>";
			
			try {
				conn = DBUtil.getConnection();
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, Integer.parseInt(empno));
				pstmt.setString(2, ename);
				pstmt.setString(3, job);
				pstmt.setInt(4, Integer.parseInt(mgr));
				pstmt.setString(5, hiredate);
				pstmt.setInt(6, Integer.parseInt(sal));
				pstmt.setInt(7, Integer.parseInt(comm));
				pstmt.setInt(8, Integer.parseInt(deptno));
				
				
				int result = pstmt.executeUpdate();
				
				if (result == 1) {
					out.print("insert()실행성공");
				} else {
					out.print("insert()실행실패");
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.close(pstmt, conn);
				
			}
			
			
		
		out.print("</body></html>");
	
	
	}
}
