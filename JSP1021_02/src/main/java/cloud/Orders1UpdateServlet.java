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

@WebServlet("/orders1Update")
public class Orders1UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	req.setCharacterEncoding("UTF-8"); //빈값 오류방지
		
	resp.setContentType("text/html; charset=UTF-8");
	PrintWriter out = resp.getWriter();
	
	
	String order_date = req.getParameter("order_date");
	String cust_id = req.getParameter("cust_id");
	String order_num = req.getParameter("order_num");
	
	out.print("<html><body>");
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String query = "UPDATE orders1 SET order_date = ?, cust_id = ? WHERE order_num = ?";
		
	try {
		conn = DBUtil.getConnection();
		pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, order_date);
		pstmt.setString(2, cust_id);
		pstmt.setInt(3, Integer.parseInt(order_num));
		
		int result = pstmt.executeUpdate();
		
		if (result == 1) {
			out.print("update()성공");
		} else {
			out.print("update()실패");
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
