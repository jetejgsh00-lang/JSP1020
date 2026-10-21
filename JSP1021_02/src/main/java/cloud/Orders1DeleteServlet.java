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

@WebServlet("/orders1Delete")
public class Orders1DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String order_num = req.getParameter("order_num");
		
		out.print("<html><body>");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String qurey = "DELETE FROM orders1 WHERE order_num = ?";
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(qurey);
			
			pstmt.setInt(1, Integer.parseInt(order_num));
			
			int result = pstmt.executeUpdate();
			
			if (result == 1) {
				out.print("delete()성공");
			} else {
				out.print("delete()실패");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		out.print("<html><body>");
	}
}
