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

@WebServlet("/updateServlet")
public class EmpUpdateServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String job = req.getParameter("job");
		String sal = req.getParameter("sal");
		String comm = req.getParameter("comm");
		String empno = req.getParameter("empno");
		
		out.print("<html><body>");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE emp SET job = ?, sal = ?, comm = ? WHERE empno = ?";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, job);
			pstmt.setInt(2, Integer.parseInt(sal));
			pstmt.setInt(3, Integer.parseInt(comm));
			pstmt.setInt(4, Integer.parseInt(empno));
			
			
			int result = pstmt.executeUpdate();
			
			
			
			if (result == 1) {
				out.print("update()성공");
			} else {
				out.print("update()실패");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
			
		}
		
		
		out.print("</body></html>");
	}
}
