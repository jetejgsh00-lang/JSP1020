package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cloud.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orders1Select")
public class Orders1SelectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doPost(req, resp); 는 폼파일에 method="post" 가 있냐 없냐 차이임
	
		resp.setContentType("text/html; charset=UTF-8"); 
		//setContentType 서버가 보내는 응답의 종류를 브라우저에 알려줌
		PrintWriter out = resp.getWriter();
	//resp.getWriter() 응답에 쓸 도구를 가져옴
		out.print("<html><body>");
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		//PreparedStatement는 sql을 준비,실행
		ResultSet rs = null;
		
		String qurey = "SELECT * FROM orders1";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(qurey);
			
			rs = pstmt.executeQuery();//쿼리가 실행되는것을 읽음
			
			out.print("----------------------------주문정보----------------------------"+"<br>");
			while (rs.next()) {
				int order_num = rs.getInt("order_num");
				String order_date = rs.getString("order_date");
				String cust_id = rs.getString("cust_id");
				
				out.print(order_num+"\t"+order_date+"\t"+cust_id+"<br>");
			}
			out.print("------------------------------------------------------------"+"<br>");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		out.print("</body></html>");
	}
}
